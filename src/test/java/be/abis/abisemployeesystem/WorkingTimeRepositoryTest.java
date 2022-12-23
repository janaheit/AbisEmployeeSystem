package be.abis.abisemployeesystem;

import be.abis.abisemployeesystem.repository.WorkingTimeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class WorkingTimeRepositoryTest {

    @Autowired
    WorkingTimeRepository repository;

    @Test
    void consultantCanStartDay(){
        repository.startTime(LocalDate.now(), LocalTime.now(), 8);
    }

    @Test
    void consultantCanEndDay(){
        repository.endTime(5, LocalTime.now());
    }
}
