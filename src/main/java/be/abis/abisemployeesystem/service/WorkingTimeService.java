package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotEndException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotStartException;
import be.abis.abisemployeesystem.exception.WrongTypeException;
import be.abis.abisemployeesystem.model.WorkingTime;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface WorkingTimeService {

    List<WorkingTime> getAll();
    WorkingTime getById(int id);
    List<WorkingTime> getByConsultantId(int consultantId);
    List<WorkingTime> getByDate(LocalDate date);
    List<WorkingTime> getByConsultantIdAndDate(int consultantId, LocalDate date) throws EmployeeNotFoundException;
    WorkingTime getOpenWorkingTimeForConsultantId(int consultantId) throws EmployeeNotFoundException, WrongTypeException;
    WorkingTime startWorkingTime(int consultantId) throws EmployeeNotFoundException, WrongTypeException, WorkingTimeCannotStartException;
    WorkingTime endWorkingTime(int consultantId) throws WorkingTimeCannotEndException, WrongTypeException, EmployeeNotFoundException;
    int getWorkingMinutesOfConsultantForMonth(int consultantId, int month, int year);
    int roundWorkingTime(int minutesWorked);
    double calculateSalaryOfConsultantForMonth(int consultantId, int month, int year) throws EmployeeNotFoundException, WrongTypeException;
    Map<Integer, Double> calculateSalariesOfAllConsultantsForMonth(int month, int year) throws EmployeeNotFoundException, WrongTypeException;




}
