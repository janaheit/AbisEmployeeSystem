package be.abis.abisemployeesystem.repository;

import be.abis.abisemployeesystem.model.Consultant;
import be.abis.abisemployeesystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbisEmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee getByAbbreviationAndPassword(String abbreviation, String password);

    @Query(value = "select * from employees where e_kind='e'", nativeQuery = true)
    List<Employee> getEmployeesWithoutConsultant();
    @Query(value = "select * from employees where e_kind='c'", nativeQuery = true)
    List<Consultant> getConsultants();
}
