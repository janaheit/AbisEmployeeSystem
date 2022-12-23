package be.abis.abisemployeesystem.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private int timeWorkedMin;
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
        return timeWorkedMin;
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
