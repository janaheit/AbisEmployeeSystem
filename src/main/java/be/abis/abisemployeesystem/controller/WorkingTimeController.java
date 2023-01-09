package be.abis.abisemployeesystem.controller;

import be.abis.abisemployeesystem.dto.ConsultantSalaryDTO;
import be.abis.abisemployeesystem.exception.*;
import be.abis.abisemployeesystem.model.WorkingTime;
import be.abis.abisemployeesystem.service.WorkingTimeService;
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

    @GetMapping("open/{id}")
    WorkingTime getOpenWorkingTimeTodayForConsultantId(@PathVariable("id") int consultantId) throws WrongTypeException, EmployeeNotFoundException {
        return workingTimeService.getOpenWorkingTimeForConsultantIdToday(consultantId);
    }

    @GetMapping("salaries/{year}/{month}")
    List<ConsultantSalaryDTO> getSalariesOfAllConsultantsForYearAndMonth(@PathVariable("year") int year, @PathVariable("month") int month) throws WrongTypeException, EmployeeNotFoundException {
        return workingTimeService.calculateSalariesOfAllConsultantsForMonth(month, year);
    }

    @DeleteMapping("{id}")
    void deleteWorkingTime(@PathVariable("id") int id) throws WorkingTimeCannotBeDeletedException {
        workingTimeService.deleteWorkingTime(id);
    }

}
