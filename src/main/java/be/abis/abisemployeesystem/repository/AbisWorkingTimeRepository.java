package be.abis.abisemployeesystem.repository;

import be.abis.abisemployeesystem.model.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbisWorkingTimeRepository extends JpaRepository<WorkingTime, Integer> {
}
