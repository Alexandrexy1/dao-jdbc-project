package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
    void insertDepartment(Department obj);
    void updateDepartment(Department obj);
    void deleteById(int id);
    Department findById(int id);
    List<Department> findAll();
}
