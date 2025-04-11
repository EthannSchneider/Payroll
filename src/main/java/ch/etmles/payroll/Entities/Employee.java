package ch.etmles.payroll.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Employee {

    private @Id
    @GeneratedValue Long id;
    private String name;
    private String role;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Departement departement;

    @Column(nullable = false, unique = true)
    private String email;

    public Employee(){}

    public Employee(String name, String role, String email) {
        this.setName(name);
        this.setRole(role);
        this.setEmail(email);
    }

    public Employee(String name, String role, String email, Departement departement) {
        this.setName(name);
        this.setRole(role);
        this.setEmail(email);
        this.setDepartement(departement);
    }

    public Long getID(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getRole(){
        return this.role.toUpperCase();
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Employee employee))
            return false;
        return  Objects.equals(this.id, employee.id) &&
                Objects.equals(this.name, employee.name) &&
                Objects.equals(this.role, employee.role) &&
                Objects.equals(this.email, employee.email);
    }

    @Override
    public int hashCode(){
        return Objects.hash(
                this.id,
                this.name,
                this.role,
                this.email);
    }

    @Override
    public String toString(){
        return "Employee{" + "id=" +
                this.getID() + ", name='" +
                this.getName() + '\'' + ", role='" +
                this.getRole() + '\'' + ", email='" + this.getEmail() + '\'' +
                '}';
    }
}
