package be.abis.abisemployeesystem.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consultant that = (Consultant) o;
        return Double.compare(that.hourlyRate, hourlyRate) == 0 && getId()==that.getId() &&
                getAbbreviation().equals(that.getAbbreviation()) && getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hourlyRate);
    }

    @Override
    public String toString() {
        return "Consultant{id=" + getId() + "}";
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
