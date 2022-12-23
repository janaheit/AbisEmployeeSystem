package be.abis.abisemployeesystem.dto;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

public class ConsultantDTO extends EmployeeDTO {

    private double hourlyRate;

    public ConsultantDTO() {
    }

    public ConsultantDTO(int id, String firstName, String lastName, String abbreviation, List<String> roles,
                         double hourlyRate) {
        super(id, firstName, lastName, abbreviation, roles);
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
