package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.dto.ConsultantSalaryDTO;
import be.abis.abisemployeesystem.dto.WorkingTimeSalaryDTO;
import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotEndException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotStartException;
import be.abis.abisemployeesystem.exception.WrongTypeException;
import be.abis.abisemployeesystem.model.WorkingTime;

import java.time.LocalDate;
import java.util.List;

public interface WorkingTimeService {

    List<WorkingTime> getAll();
    WorkingTime getById(int id);
    List<WorkingTime> getByConsultantId(int consultantId);
    List<WorkingTime> getByDate(LocalDate date);
    List<WorkingTime> getByConsultantIdAndDate(int consultantId, LocalDate date) throws EmployeeNotFoundException;
    WorkingTime getOpenWorkingTimeForConsultantIdToday(int consultantId) throws EmployeeNotFoundException, WrongTypeException;
    List<WorkingTime> getLastOpenWorkingTimesForConsultantIdBeforeDate(int consultantId, LocalDate date) throws EmployeeNotFoundException, WrongTypeException;
    WorkingTime startWorkingTime(int consultantId) throws EmployeeNotFoundException, WrongTypeException, WorkingTimeCannotStartException;
    WorkingTime endWorkingTime(int consultantId) throws WorkingTimeCannotEndException, WrongTypeException, EmployeeNotFoundException;
    long getWorkingMinutesOfConsultantForMonth(int consultantId, int month, int year);
    int roundWorkingTime(int minutesWorked);
    WorkingTimeSalaryDTO calculateSalaryOfConsultantForMonth(int consultantId, int month, int year) throws EmployeeNotFoundException, WrongTypeException;
    List<ConsultantSalaryDTO> calculateSalariesOfAllConsultantsForMonth(int month, int year) throws EmployeeNotFoundException, WrongTypeException;




}
