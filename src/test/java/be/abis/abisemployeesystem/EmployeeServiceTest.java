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
    void checkLoginExistingEmployee(){
        assertTrue(employeeService.checkLogin("JS", "js123"));
    }

    @Test
    void checkLoginWrongPassword(){
        assertFalse(employeeService.checkLogin("JS", "j123"));
    }

    @Test
    void checkLoginWrongAbbreviation(){
        assertFalse(employeeService.checkLogin("JSA", "js123"));
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
