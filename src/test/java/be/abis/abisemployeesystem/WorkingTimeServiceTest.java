package be.abis.abisemployeesystem;

import be.abis.abisemployeesystem.exception.EmployeeNotFoundException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotEndException;
import be.abis.abisemployeesystem.exception.WorkingTimeCannotStartException;
import be.abis.abisemployeesystem.exception.WrongTypeException;
import be.abis.abisemployeesystem.model.WorkingTime;
import be.abis.abisemployeesystem.service.WorkingTimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WorkingTimeServiceTest {

    @Autowired
    WorkingTimeService workingTimeService;

    @Transactional
    @Test
    void startingWorkingTimeOfConsultantWithOpenWorkingTimeThrowsException() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException {
        workingTimeService.startWorkingTime(9);
        assertThrows(WorkingTimeCannotStartException.class, () -> workingTimeService.startWorkingTime(9));
    }

    @Transactional
    @Test
    void startingWorkingTimeForConsultantWithoutOpenWorks() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException {
        WorkingTime time = workingTimeService.startWorkingTime(3);
        assertEquals(3, time.getConsultant().getId());
        assertNull(time.getEndTime());
        assertEquals(0, time.getTimeWorkedMin());
    }

    @Transactional
    @Test
    void startWorkingTimeForNonConsultantThrowsException() {
        assertThrows(WrongTypeException.class, () -> workingTimeService.startWorkingTime(7));
    }

    @Transactional
    @Test
    void startWorkingTimeForNonExistingEmployeeThrowsException() {
        assertThrows(EmployeeNotFoundException.class, () -> workingTimeService.startWorkingTime(1000));
    }

    @Transactional
    @Test
    void endWorkingTimeForConsultantWithOpenWorks() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException, WorkingTimeCannotEndException {
        WorkingTime start = workingTimeService.startWorkingTime(3);
        WorkingTime end = workingTimeService.endWorkingTime(3);
        assertEquals(start.getId(), end.getId());
        assertEquals(start.getStartTime(), end.getStartTime());
        assertEquals(start.getConsultant().getId(), end.getConsultant().getId());
        assertEquals(start.getDate(), end.getDate());
    }

    @Transactional
    @Test
    void endWorkingTimeOfConsultantWithoutOpenWorkingTimeThrowsException() throws WrongTypeException, WorkingTimeCannotStartException, EmployeeNotFoundException, WorkingTimeCannotEndException {
        WorkingTime start = workingTimeService.startWorkingTime(3);
        WorkingTime end = workingTimeService.endWorkingTime(3);
        assertThrows(WorkingTimeCannotEndException.class, () -> workingTimeService.endWorkingTime(3));
    }

    @Transactional
    @Test
    void endWorkingTimeForNonConsultantThrowsException() {
        assertThrows(WrongTypeException.class, () -> workingTimeService.endWorkingTime(7));
    }

    @Transactional
    @Test
    void endWorkingTimeForNonExistingEmployeeThrowsException() {
        assertThrows(EmployeeNotFoundException.class, () -> workingTimeService.endWorkingTime(1000));
    }
}
