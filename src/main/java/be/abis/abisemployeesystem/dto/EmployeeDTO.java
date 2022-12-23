package be.abis.abisemployeesystem.dto;


import javax.persistence.*;
import java.util.List;

public class EmployeeDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String abbreviation;
    private List<String> roles;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int id, String firstName, String lastName, String abbreviation, List<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.abbreviation = abbreviation;
        this.roles = roles;
    }

    public boolean hasRole(String role){
        return roles.contains(role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
