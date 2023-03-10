package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.dto.ConsultantSalaryDTO;
import be.abis.abisemployeesystem.dto.WorkingTimeSalaryDTO;
import be.abis.abisemployeesystem.exception.*;
import be.abis.abisemployeesystem.mapper.ConsultantSalaryMapper;
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
import java.util.ArrayList;
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
        if (getOpenWorkingTimeForConsultantIdToday(consultantId) != null) throw new WorkingTimeCannotStartException("uren voor deze persoon zijn nog steeds " +
                "open, daarom kunt U geen nieuwe beginnen");

        // close any previously open ones at midnight (23:59)
        List<WorkingTime> previousOpen = workingTimeRepository.getOpenWorkingTimesBeforeDateForConsultant(consultantId,
                LocalDate.now());
        if (previousOpen.size() != 0){
            for (WorkingTime time : previousOpen){
                endAndSaveWorkingTime(time, LocalTime.MIDNIGHT.minusMinutes(1).truncatedTo(ChronoUnit.MINUTES));
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
        WorkingTime update = getOpenWorkingTimeForConsultantIdToday(consultantId);

        if (update == null){
            throw new WorkingTimeCannotEndException("Er zijn geen uren voor deze persoon die kunnen worden gestopt");
        }

        return endAndSaveWorkingTime(update, LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
    }

    private WorkingTime endAndSaveWorkingTime(WorkingTime time, LocalTime endTime){
        time.setEndTime(endTime);
        // calculate difference in minutes = timeWorked
        int mins = (int) time.getStartTime().until(time.getEndTime(), ChronoUnit.MINUTES);
        time.setTimeWorkedMin(mins);

        return workingTimeRepository.save(time);
    }

    @Override
    public List<WorkingTime> getLastOpenWorkingTimesForConsultantIdBeforeDate(int consultantId, LocalDate date) throws EmployeeNotFoundException, WrongTypeException {
        return workingTimeRepository.getOpenWorkingTimesBeforeDateForConsultant(consultantId, date);
    }

    @Override
    public WorkingTime getOpenWorkingTimeForConsultantIdToday(int consultantId) throws EmployeeNotFoundException, WrongTypeException {
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
    public List<ConsultantSalaryDTO> calculateSalariesOfAllConsultantsForMonth(int month, int year) throws EmployeeNotFoundException, WrongTypeException {
        List<Consultant> consultants = employeeService.getAllConsultants();

        List<ConsultantSalaryDTO> salaries = new ArrayList<>();
        consultants.forEach(c -> {
            try {
                WorkingTimeSalaryDTO workingTimeSalaryDTO = calculateSalaryOfConsultantForMonth(c.getId(), month, year);
                salaries.add(ConsultantSalaryMapper.toDTO(c, workingTimeSalaryDTO.getSalary(), workingTimeSalaryDTO.getMinutesWorked()));
            } catch (EmployeeNotFoundException | WrongTypeException e) {
                throw new RuntimeException(e);
            }
        });

        return salaries;
    }

    @Override
    public WorkingTimeSalaryDTO calculateSalaryOfConsultantForMonth(int consultantId, int month, int year) throws EmployeeNotFoundException, WrongTypeException {
        Employee e = employeeService.getById(consultantId);
        Consultant c = null;
        if (e instanceof Consultant) c = (Consultant) e;
        else throw new WrongTypeException("salaris kan alleen voor zelfstandige worden berekend");

        long min = getWorkingMinutesOfConsultantForMonth(consultantId, month, year);
        double hours = ((double)min)/60;
        double salary = c.getHourlyRate() * hours;
        System.out.println("hours of id: " + c.getId() + " : " + hours + " has salary: " + salary);
        return new WorkingTimeSalaryDTO(salary, min);
    }

    @Override
    public long getWorkingMinutesOfConsultantForMonth(int consultantId, int month, int year) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = LocalDate.of(year, month, start.lengthOfMonth());

        List<WorkingTime> monthlyWorkingTimesList = workingTimeRepository.getWorkingTimesByConsultantIdBetweenDates(
                consultantId, start, end);


        Map<LocalDate, Integer> dailyWorkingTimesMap = new HashMap<>();

        for(WorkingTime time: monthlyWorkingTimesList){
            dailyWorkingTimesMap.compute(time.getDate(), (w, prev) -> prev == null ? time.getTimeWorkedMin() :
                        prev + time.getTimeWorkedMin());
        }

        long minutesWorkedPerMonth = dailyWorkingTimesMap.values().stream().map(this::roundWorkingTime).mapToLong(Integer::longValue).sum();

        // Here calculating the days where a person only registered one working time and this time being more than
        // 6 hours => only then, we want to subtract 1 hour lunch break (so that people are not paid for lunch)
        int daysWithOutLunch = workingTimeRepository.calculateDaysWithOnlyOneWorkingTimeAndWorkingFor6HoursOrMoreOfConsultantId(consultantId, start, end);
        System.out.println("dayswihtoutlunch: " + daysWithOutLunch);
        System.out.println("mins worked rounded: " + minutesWorkedPerMonth);
        // subtract 1 hours (60 mins) for each day without a lunch break
        return minutesWorkedPerMonth - daysWithOutLunch* 60L;
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

    @Override
    public WorkingTime findById(int id) throws WorkingTimeNotFoundException {
        return workingTimeRepository.findById(id).orElseThrow(() -> new WorkingTimeNotFoundException("deze werktijd bestaat niet"));
    }

    @Override
    public void deleteWorkingTime(int id) throws WorkingTimeCannotBeDeletedException {
        try {
            findById(id);
            workingTimeRepository.deleteById(id);
        } catch (WorkingTimeNotFoundException e){
            throw new WorkingTimeCannotBeDeletedException("deze werktijd bestaat niet en kan dus niet verwijderd worden");
        }

    }
}
