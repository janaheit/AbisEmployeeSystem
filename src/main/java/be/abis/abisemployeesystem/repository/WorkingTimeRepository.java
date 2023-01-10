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

    @Query(value = "select * from workingtimes where employee_id = :consultantId and workingdate = :date", nativeQuery = true)
    List<WorkingTime> getWorkingTimesByConsultantIdAndDate(int consultantId, LocalDate date);

    @Query(value = "select * from workingtimes where employee_id = :consultantId and workingdate >= :start " +
            "and workingdate <= :end", nativeQuery = true)
    List<WorkingTime> getWorkingTimesByConsultantIdBetweenDates(int consultantId, LocalDate start, LocalDate end);

    @Query(value = "select count(*) from workingtimes where employee_id=:consultantId " +
            "and workingdate >= :start and workingdate <= :end and timeworked >=360 " +
            "and starttime >= '07:00:00' and endtime <= '18:00:00' and workingdate in " +
            "(select workingdate from workingtimes where employee_id =:consultantId " +
            "group by workingdate having count(workingdate)=1)", nativeQuery = true)
    int calculateDaysWithOnlyOneWorkingTimeAndWorkingFor6HoursOrMoreOfConsultantId(int consultantId, LocalDate start, LocalDate end);

    @Query(value = "select * from workingtimes where employee_id=:consultantId and workingdate < :date and endtime is null",
    nativeQuery = true)
    List<WorkingTime> getOpenWorkingTimesBeforeDateForConsultant(int consultantId, LocalDate date);
}
