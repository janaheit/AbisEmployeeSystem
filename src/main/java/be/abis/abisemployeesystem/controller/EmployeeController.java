package be.abis.abisemployeesystem.controller;

import be.abis.abisemployeesystem.dto.ConsultantDTO;
import be.abis.abisemployeesystem.dto.EmployeeDTO;
import be.abis.abisemployeesystem.dto.LoginDTO;
import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.mapper.ConsultantMapper;
import be.abis.abisemployeesystem.mapper.EmployeeMapper;
import be.abis.abisemployeesystem.model.Consultant;
import be.abis.abisemployeesystem.model.Employee;
import be.abis.abisemployeesystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("login")
    Employee checkLogin(@RequestBody LoginDTO login) throws EmployeeNotFoundException {

        return employeeService.checkLogin(login.getAbbreviation(), login.getPassword());
    }

    @GetMapping("")
    ResponseEntity<? extends Object> getAllEmployees(){
        List<EmployeeDTO> employees = employeeService.getAll().stream().map(EmployeeMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
    }

    @GetMapping("none-consultants")
    ResponseEntity<? extends Object> getAllNoneConsultants(){
        List<EmployeeDTO> employees = employeeService.getAllNoneConsultants().stream().map(EmployeeMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
    }

    @GetMapping("consultants")
    ResponseEntity<? extends Object> getAllConsultants(){
        List<ConsultantDTO> employees = employeeService.getAllConsultants().stream().map(ConsultantMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<List<ConsultantDTO>>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<? extends Object> getById(@PathVariable("id") int id) throws EmployeeNotFoundException {
        EmployeeDTO employee = EmployeeMapper.toDTO(employeeService.getById(id));
        return new ResponseEntity<EmployeeDTO>(employee, HttpStatus.OK);
    }

    @GetMapping("managers")
    ResponseEntity<? extends Object> getManagers(){
        List<EmployeeDTO> employees = employeeService.getManagers().stream().map(EmployeeMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
    }

    @GetMapping("teachers")
    ResponseEntity<? extends Object> getAllTeachers(){
        List<EmployeeDTO> employees = employeeService.getTeachers().stream().map(EmployeeMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
    }

    @GetMapping("accountants")
    ResponseEntity<? extends Object> getAllAccountants(){
        List<EmployeeDTO> employees = employeeService.getAccountants().stream().map(EmployeeMapper::toDTO).collect(Collectors.toList());
        return new ResponseEntity<List<EmployeeDTO>>(employees, HttpStatus.OK);
    }





}
