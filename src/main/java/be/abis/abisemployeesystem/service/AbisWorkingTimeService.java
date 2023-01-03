package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotEndException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotStartException;
import be.abis.abisemployeesystem.exception.WrongTypeException;
import be.abis.abisemployeesystem.model.Consultant;
import be.abis.abisemployeesystem.model.Employee;
import be.abis.abisemployeesystem.model.WorkingTime;
import be.abis.abisemployeesystem.repository.WorkingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AbisWorkingTimeService implements WorkingTimeService {

    @Autowired
    WorkingTimeRepository workingTimeRepository;
    @Autowired EmployeeService employeeService;
    @Override
    public List<WorkingTime> getAll() {
        return null;
    }

    @Override
    public WorkingTime getById(int id) {
        return null;
    }

    @Override
    public List<WorkingTime> getByConsultantId(int consultantId) {
        return null;
    }

    @Override
    public List<WorkingTime> getByDate(LocalDate date) {
        return null;
    }

    @Transactional
    @Override
    public List<WorkingTime> getByConsultantIdAndDate(int consultantId, LocalDate date) throws EmployeeNotFoundException {
        employeeService.getById(consultantId);
        return workingTimeRepository.getWorkingTimesByConsultantIdAndDate(consultantId, date);
    }

    @Transactional
    @Override
    public WorkingTime startWorkingTime(int consultantId) throws EmployeeNotFoundException, WrongTypeException, WorkingTimeCannotStartException {
        Employee employee = employeeService.getById(consultantId);
        Consultant consultant;
        if (!(employee instanceof Consultant)){
            throw new WrongTypeException("alleen consultants kunnen hun uren registreren");
        } else {
            consultant = (Consultant) employee;
        }

        // if workingTime still open -> exception
        List<WorkingTime> timesToday = workingTimeRepository.getWorkingTimesByConsultantIdAndDate(consultantId, LocalDate.now());
        for(WorkingTime time : timesToday){
            System.out.println(time);
            if(time.getEndTime() == null){
                throw new WorkingTimeCannotStartException("uren voor deze persoon zijn nog steeds " +
                        "open, daarom kunt U geen nieuwe beginnen");
            }
        }

        WorkingTime time = new WorkingTime();
        time.setDate(LocalDate.now());
        time.setStartTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
        time.setConsultant(consultant);
        time.setTimeWorkedMin(0);

        return workingTimeRepository.save(time);
    }

    /**
     * Ends the working time of a consultant.
     * @param consultantId
     * @return WorkingTime
     * @throws WorkingTimeCannotEndException
     * @throws WrongTypeException
     * @throws EmployeeNotFoundException
     */
    @Transactional
    @Override
    public WorkingTime endWorkingTime(int consultantId) throws WorkingTimeCannotEndException, WrongTypeException, EmployeeNotFoundException {

        // check whether a working time for this consultant already exists
        WorkingTime update = getOpenWorkingTimeForConsultantId(consultantId);

        if (update == null){
            throw new WorkingTimeCannotEndException("Er zijn geen uren voor deze persoon die kunnen worden gestopt");
        }

        update.setEndTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
        // calculate difference in minutes = timeWorked
        int mins = (int) update.getStartTime().until(update.getEndTime(), ChronoUnit.MINUTES);
        update.setTimeWorkedMin(mins);

        return workingTimeRepository.save(update);
    }

    @Override
    public WorkingTime getOpenWorkingTimeForConsultantId(int consultantId) throws EmployeeNotFoundException, WrongTypeException {
        if (!(employeeService.getById(consultantId) instanceof Consultant)){
            throw new WrongTypeException("alleen consultants kunnen hun uren registreren");
        }

        List<WorkingTime> timesToday = workingTimeRepository.getWorkingTimesByConsultantIdAndDate(consultantId, LocalDate.now());

        for(WorkingTime time : timesToday){
            if(time.getEndTime() == null){
                return time;
            }
        }
        return null;
    }

    @Override
    public Map<Integer, Double> calculateSalariesOfAllConsultantsForMonth(int month, int year) throws EmployeeNotFoundException, WrongTypeException {
        List<Consultant> consultants = employeeService.getAllConsultants();

        Map<Integer, Double> salaries = new HashMap<>();
        consultants.forEach(c -> {
            try {
                salaries.put(c.getId(), calculateSalaryOfConsultantForMonth(c.getId(), month, year));
            } catch (EmployeeNotFoundException | WrongTypeException e) {
                throw new RuntimeException(e);
            }
        });

        return salaries;
    }

    @Override
    public double calculateSalaryOfConsultantForMonth(int consultantId, int month, int year) throws EmployeeNotFoundException, WrongTypeException {
        Employee e = employeeService.getById(consultantId);
        Consultant c = null;
        if (e instanceof Consultant) c = (Consultant) e;
        else throw new WrongTypeException("salaris kan alleen voor zelfstandige worden berekend");

        int min = getWorkingMinutesOfConsultantForMonth(consultantId, month, year);
        return c.getHourlyRate() * min;
    }

    @Override
    public int getWorkingMinutesOfConsultantForMonth(int consultantId, int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = LocalDate.of(year, month, start.lengthOfMonth());

        List<WorkingTime> monthlyWorkingTimesList = workingTimeRepository.getWorkingTimesByConsultantIdBetweenDates(
                consultantId, start, end);

        Map<LocalDate, Integer> dailyWorkingTimesMap = new HashMap<>();

        for(WorkingTime time: monthlyWorkingTimesList){
            dailyWorkingTimesMap.compute(time.getDate(), (w, prev) -> prev == null ? time.getTimeWorkedMin() :
                        prev + time.getTimeWorkedMin());
        }

        return dailyWorkingTimesMap.values().stream().map(this::roundWorkingTime).mapToInt(Integer::intValue).sum();
    }

    public int roundWorkingTime(int minutesWorked){
        int hours = minutesWorked/60;
        int minutesLeft = minutesWorked%60;

        if (minutesLeft == 0) return minutesWorked;
        else if (minutesLeft <= 15) minutesLeft = 15;
        else if (minutesLeft <= 30) minutesLeft=30;
        else if (minutesLeft <= 45) minutesLeft =45;
        else minutesLeft = 60;

        return hours*60 + minutesLeft;
    }
}
