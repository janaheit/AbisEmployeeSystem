package be.abis.abisemployeesystem.dto;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("c")
public class ConsultantDTO extends EmployeeDTO {

    private double hourlyRate;

    public ConsultantDTO() {
    }

    public ConsultantDTO(String firstName, String lastName, String abbreviation, String password, List<String> roles,
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
