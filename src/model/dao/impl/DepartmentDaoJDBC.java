package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertDepartment(Department dep) {
        PreparedStatement preSt = null;
        ResultSet resultSet = null;

        try {
            preSt = conn.prepareStatement(
                "INSERT INTO department " +
                "(Name) " +
                "VALUES (?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            preSt.setString(1, dep.getName());
            int rowsChanged = preSt.executeUpdate();

            if (rowsChanged > 0) {
                resultSet = preSt.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    dep.setId(id);

                    DB.closeResultSet(resultSet);
                }
                else throw new DbException("Error: no rows changed!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preSt);
        }
    }

    @Override
    public void updateDepartment(Department obj) {
        PreparedStatement preSt = null;

        try {
            preSt = conn.prepareStatement(
                "UPDATE department " +
                "SET Name = ?" +
                "WHERE Id = ?"
            );

            preSt.setString(1, obj.getName());
            preSt.setInt(2, obj.getId());

            preSt.executeUpdate();

    

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preSt);
        }
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement preSt = null;

        try {
            preSt = conn.prepareStatement(
                "DELETE FROM department " +
                "WHERE Id = ?"
            );
            preSt.setInt(1, id);
            preSt.executeUpdate();

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preSt);
        }
    }

    @Override
    public Department findById(int id) {
        PreparedStatement preSt = null;
        ResultSet resultSet = null;

        try {
            preSt = conn.prepareStatement(
                "SELECT department.* " +
                "FROM department " +
                "WHERE department.Id = ?"
            );

            preSt.setInt(1, id);
            resultSet = preSt.executeQuery();

            if (resultSet.next()) {
                return departmentInit(resultSet);
            } else return null;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preSt);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement preSt = null;
        ResultSet resultSet = null;
        List<Department> departmentList = new ArrayList<>();

        try {
            preSt = conn.prepareStatement(
                "SELECT * " +
                "FROM department " +
                "ORDER BY Name"
            );

            resultSet = preSt.executeQuery();

            while (resultSet.next()) {
                departmentList.add(departmentInit(resultSet));
            }

            return departmentList;

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preSt);
            
        }
    }

    private Department departmentInit(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        return new Department(id, name);
    }
    
}
