package be.abis.abisemployeesystem.controller;

import be.abis.abisemployeesystem.dto.LoginDTO;
import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotEndException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotStartException;
import be.abis.abisemployeesystem.exception.WrongTypeException;
import be.abis.abisemployeesystem.model.Employee;
import be.abis.abisemployeesystem.model.WorkingTime;
import be.abis.abisemployeesystem.service.EmployeeService;
import be.abis.abisemployeesystem.service.WorkingTimeService;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "workingtime")
public class WorkingTimeController {

    @Autowired
    WorkingTimeService workingTimeService;

    @GetMapping("start/{id}")
    WorkingTime start(@PathVariable("id") int consultantId) throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException {
        return workingTimeService.startWorkingTime(consultantId);
    }

    @GetMapping("end/{id}")
    WorkingTime end(@PathVariable("id") int consultantId) throws WrongTypeException, EmployeeNotFoundException, WorkingTimeCannotEndException {
        return workingTimeService.endWorkingTime(consultantId);
    }

    @GetMapping("/{id}")
    List<WorkingTime> getWorkingTimeTodayForConsultantId(@PathVariable("id") int consultantId) throws EmployeeNotFoundException {
        return workingTimeService.getByConsultantIdAndDate(consultantId, LocalDate.now());
    }

}
