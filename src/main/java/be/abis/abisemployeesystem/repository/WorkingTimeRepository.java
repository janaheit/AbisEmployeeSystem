package be.abis.abisemployeesystem.repository;

import be.abis.abisemployeesystem.model.WorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface WorkingTimeRepository extends JpaRepository<WorkingTime, Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into workingtimes (workingdate, starttime, employee_id) values (:date, :starttime, :employeeId)",
            nativeQuery = true)
    void startTime(LocalDate date, @Param("starttime") LocalTime startTime, int employeeId);

    @Transactional
    @Modifying
    @Query(value = "update workingtimes set endtime = :endtime where workingtimes_id = :id",nativeQuery = true)
    void endTime(int id, @Param("endtime") LocalTime endTime);


}
