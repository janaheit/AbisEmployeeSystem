package be.abis.abisemployeesystem.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("c")
public class Consultant extends Employee {

    @Column(name = "hourlyrate")
    private double hourlyRate;

    public Consultant() {
    }

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
