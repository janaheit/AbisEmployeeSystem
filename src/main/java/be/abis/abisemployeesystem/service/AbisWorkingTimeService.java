package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
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
import java.util.List;

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

    @Override
    public List<WorkingTime> getByConsultantIdAndDate(int consultantId, LocalDate date) {
        return null;
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
        time.setStartTime(LocalTime.now());
        time.setConsultant(consultant);
        time.setTimeWorkedMin(0);

        return workingTimeRepository.save(time);
    }

    @Override
    public WorkingTime endWorkingTime(int consultantId) {
        // check whether a working time for this consultant already exists


        // setTimeWorked

        return null;
    }
}
