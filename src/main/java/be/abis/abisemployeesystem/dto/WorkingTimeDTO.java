package be.abis.abisemployeesystem.dto;


import java.time.LocalDate;
import java.time.LocalTime;


public class WorkingTimeDTO {


    private int id;

    private LocalDate date;

    private LocalTime startTime;
    private LocalTime endTime;
    private int timeWorkedMin;
    private ConsultantDTO consultant;

    public WorkingTimeDTO() {
    }

    public WorkingTimeDTO(int id, LocalDate date, LocalTime startTime, LocalTime endTime, int timeWorkedMin, ConsultantDTO consultant) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeWorkedMin = timeWorkedMin;
        this.consultant = consultant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setTimeWorkedMin(int timeWorkedMin) {
        this.timeWorkedMin = timeWorkedMin;
    }

    public ConsultantDTO getConsultant() {
        return consultant;
    }

    public void setConsultant(ConsultantDTO consultant) {
        this.consultant = consultant;
    }
}
