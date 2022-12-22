package be.abis.abisemployeesystem.model;


import java.util.List;

public class Consultant extends Employee {

    private double hourlyRate;

    public Consultant(String firstName, String lastName, String abbreviation, String password, List<String> roles,
                      double hourlyRate) {
        super(firstName, lastName, abbreviation, password, roles);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
