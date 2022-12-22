package be.abis.abisemployeesystem.controller;

import be.abis.abisemployeesystem.dto.LoginDTO;
import be.abis.abisemployeesystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping()
    boolean checkLogin(@RequestBody LoginDTO login){
        return employeeService.checkLogin(login.getAbbreviation(), login.getPassword());
    }
}
