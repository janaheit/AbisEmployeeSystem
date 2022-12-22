package be.abis.abisemployeesystem.model;



import javax.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "e_kind", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("e")
@Table(name = "employees")
public class Employee {

    @SequenceGenerator(name = "employeeSeq", sequenceName = "employees_seq", allocationSize = 1)

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeSeq")
    @Column(name = "employees_id")
    private int id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "abbreviation")
    private String abbreviation;
    @Column(name = "pass")
    private String password;
    @ElementCollection
    @CollectionTable(name = "personroles", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name="role")
    private List<String> roles;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String abbreviation, String password, List<String> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.abbreviation = abbreviation;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
