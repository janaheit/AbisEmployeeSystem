package be.abis.abisemployeesystem.dto;

public class WorkingTimeSalaryDTO {
    private double salary;
    private long minutesWorked;

    public WorkingTimeSalaryDTO(double salary, long minutesWorked) {
        this.salary = salary;
        this.minutesWorked = minutesWorked;
    }

    public double getSalary() {
        return salary;
    }

    public long getMinutesWorked() {
        return minutesWorked;
    }
}
