package be.abis.abisemployeesystem.service;

import be.abis.abisemployeesystem.model.WorkingTime;

import java.time.LocalDate;
import java.util.List;

public interface WorkingTimeService {

    List<WorkingTime> getAll();
    WorkingTime getById(int id);
    List<WorkingTime> getByConsultantId(int consultantId);
    List<WorkingTime> getByDate(LocalDate date);
    WorkingTime getByConsultantIdAndDate(int consultantId, LocalDate date);

}
