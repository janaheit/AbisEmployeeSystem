package be.abis.abisemployeesystem;

import be.abis.abisemployeesystem.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    void checkLoginExistingEmployee(){
        fail();
    }

    @Test
    void checkLoginWrongPassword(){
        fail();
    }

    @Test
    void checkLoginWrongAbbreviation(){
        fail();
    }


}
