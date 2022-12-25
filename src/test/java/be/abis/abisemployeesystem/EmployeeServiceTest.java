package be.abis.abisemployeesystem;

import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    void checkLoginExistingEmployee() throws EmployeeNotFoundException {
        assertEquals("JS",employeeService.checkLogin("JS", "js123").getAbbreviation());
    }

    @Test
    void checkLoginWrongPassword() throws EmployeeNotFoundException {
        assertThrows(EmployeeNotFoundException.class,()-> employeeService.checkLogin("JS", "js12"));
    }

    @Test
    void checkLoginWrongAbbreviation() throws EmployeeNotFoundException {
        assertThrows(EmployeeNotFoundException.class,()-> employeeService.checkLogin("JSA", "js123"));
    }

    @Test
    void getById1ReturnsEmployee() throws EmployeeNotFoundException {
        assertEquals("TAVERNIER", employeeService.getById(2).getLastName().trim());
    }

    @Test
    void getById1000ThrowsException() throws EmployeeNotFoundException {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getById(1000));
    }


}
