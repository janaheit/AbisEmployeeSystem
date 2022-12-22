package be.abis.abisemployeesystem;

import be.abis.abisemployeesystem.repository.AbisEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    AbisEmployeeRepository repository;

    @Test
    void findAll(){
        System.out.println(repository.findAll());
    }

    @Test
    void getEmployeesWithoutConsultant(){
        assertEquals(5, repository.getEmployeesWithoutConsultant().size());
    }
    @Test
    void getConsultant(){
        assertEquals(5, repository.getConsultants().size());
    }
}
