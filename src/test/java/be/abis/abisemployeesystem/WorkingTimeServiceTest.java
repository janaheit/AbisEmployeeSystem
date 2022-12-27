package be.abis.abisemployeesystem;

import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotStartException;
import be.abis.abisemployeesystem.exception.WrongTypeException;
import be.abis.abisemployeesystem.model.WorkingTime;
import be.abis.abisemployeesystem.service.WorkingTimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WorkingTimeServiceTest {

    @Autowired
    WorkingTimeService workingTimeService;

    @Test
    void startingWorkingTimeOfConsultantWithOpenWorkingTimeThrowsException() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException {
        assertThrows(WorkingTimeCannotStartException.class, () -> workingTimeService.startWorkingTime(9));
    }

    //@Transactional
    @Test
    void startingWorkingTimeForConsultantWithoutOpenWorks() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException {
        workingTimeService.startWorkingTime(3);
        //WorkingTime time = workingTimeService.startWorkingTime(3);
        //assertEquals(3, time.getConsultant().getId());
        //assertNull(time.getEndTime());
        //assertEquals(0, time.getTimeWorkedMin());
    }

    @Test
    void startWorkingTimeForNonConsultantThrowsException() {
        assertThrows(WrongTypeException.class, () -> workingTimeService.startWorkingTime(7));
    }

    @Test
    void startWorkingTimeForNonExistingEmployeeThrowsException() {
        assertThrows(EmployeeNotFoundException.class, () -> workingTimeService.startWorkingTime(1000));
    }

}
