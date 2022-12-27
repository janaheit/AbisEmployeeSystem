package be.abis.abisemployeesystem.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "workingtimes")
public class WorkingTime {

    @SequenceGenerator(name = "workingTimeSeq", sequenceName = "workingtimes_seq", allocationSize = 1)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workingTimeSeq")
    @Column(name = "workingtimes_id")
    private int id;
    @Column(name = "workingdate")
    private LocalDate date;
    @Column(name = "starttime")
    private LocalTime startTime;
    @Column(name = "endtime")
    private LocalTime endTime;
    @Column(name = "timeworked")
    private Integer timeWorkedMin;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Consultant consultant;

    public WorkingTime() {
    }

    public WorkingTime(LocalDate date, LocalTime startTime, LocalTime endTime, int timeWorkedMin, Consultant consultant) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeWorkedMin = timeWorkedMin;
        this.consultant = consultant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingTime that = (WorkingTime) o;
        return timeWorkedMin == that.timeWorkedMin && date.equals(that.date) && startTime.equals(that.startTime) && Objects.equals(endTime, that.endTime) && consultant.equals(that.consultant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, consultant);
    }

    @Override
    public String toString() {
        return "WorkingTime{" +
                "id=" + id +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", timeWorkedMin=" + timeWorkedMin +
                ", consultant=" + consultant +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getTimeWorkedMin() {
        return (timeWorkedMin == null ? 0 : timeWorkedMin);
    }

    public void setTimeWorkedMin(int timeWorked) {
        this.timeWorkedMin = timeWorked;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }
}
