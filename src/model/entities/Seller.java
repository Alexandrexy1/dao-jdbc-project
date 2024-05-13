package model.entities;

import java.io.Serializable;
import java.time.LocalDate;

public class Seller implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String email;
    private Double baseSalary;
    private LocalDate birthDate;

    private Department department;
    
    public Seller(int id, String name, String email, double baseSalary, LocalDate birthDate, Department department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.baseSalary = baseSalary;
        this.birthDate = birthDate;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Seller other = (Seller) obj;
        if (id == null) {
            if (other.id == null) return false; 
        } else if (!id.equals(other.id)) return false;
        return true;
    }

    public String toString() {
        return "Id: " + id + " Name: " + name + " Email: " + email + " BaseSalary: " + baseSalary +
            " BirthDate: " + birthDate + " Department: " + department; 
    }
}

