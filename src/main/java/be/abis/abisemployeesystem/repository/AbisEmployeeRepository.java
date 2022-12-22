package be.abis.abisemployeesystem.repository;

import be.abis.abisemployeesystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbisEmployeeRepository extends JpaRepository<Employee, Integer> {
}
