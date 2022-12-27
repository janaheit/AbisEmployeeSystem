package be.abis.abisemployeesystem.controller;

import be.abis.abisemployeesystem.dto.LoginDTO;
import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotStartException;
import be.abis.abisemployeesystem.exception.WrongTypeException;
import be.abis.abisemployeesystem.model.Employee;
import be.abis.abisemployeesystem.model.WorkingTime;
import be.abis.abisemployeesystem.service.EmployeeService;
import be.abis.abisemployeesystem.service.WorkingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "workingtime")
public class WorkingTimeController {

    @Autowired
    WorkingTimeService workingTimeService;

    @GetMapping("start/{id}")
    WorkingTime start(@PathVariable("id") int consultantId) throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException {
        System.out.println("goes in");
        System.out.println(consultantId);
        return workingTimeService.startWorkingTime(consultantId);
    }



}
