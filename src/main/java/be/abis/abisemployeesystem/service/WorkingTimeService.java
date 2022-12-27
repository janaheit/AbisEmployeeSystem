package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
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
    List<WorkingTime> getByConsultantIdAndDate(int consultantId, LocalDate date);
    WorkingTime startWorkingTime(int consultantId) throws EmployeeNotFoundException, WrongTypeException, WorkingTimeCannotStartException;
    WorkingTime endWorkingTime(int consultantId);


}
