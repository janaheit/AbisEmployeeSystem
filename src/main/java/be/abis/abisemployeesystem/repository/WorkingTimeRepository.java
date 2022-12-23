package be.abis.abisemployeesystem.repository;

import be.abis.abisemployeesystem.model.Consultant;
import be.abis.abisemployeesystem.model.Employee;
import be.abis.abisemployeesystem.model.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Integer> {

}
