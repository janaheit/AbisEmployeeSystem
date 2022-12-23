package be.abis.abisemployeesystem;

import be.abis.abisemployeesystem.repository.WorkingTimeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorkingTimeDTORepositoryTest {

    @Autowired
    WorkingTimeRepository repository;

    @Test
    void JPARepoWorks(){
        System.out.println(repository.findAll());
    }
}
