package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;


public class SellerDaoJDBC implements SellerDao {
    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertSeller(Seller seller) {
        PreparedStatement preSt = null;

        try {
            preSt = conn.prepareStatement(
                "INSERT INTO seller " +
                "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                "VALUES (?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );
            
            preSt.setString(1, seller.getName());
            preSt.setString(2, seller.getEmail());
            preSt.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
            preSt.setDouble(4, seller.getBaseSalary());
            preSt.setInt(5, seller.getDepartment().getId());

            int rowsChanged = preSt.executeUpdate();

            if (rowsChanged > 0) {
                ResultSet resultSet = preSt.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    seller.setId(id);

                    DB.closeResultSet(resultSet);
                } 
                else throw new DbException("Error! no rows changed!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(preSt);
        }
    }

    @Override
    public void updateSeller(Seller obj) {

    }

    @Override
    public void deleteById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public Seller findById(int id) {
        PreparedStatement preSt = null;
        ResultSet resultSet = null;

        try {
            preSt = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName " +
                "FROM seller INNER JOIN department " +
                "ON seller.DepartmentId = department.Id " +
                "WHERE seller.Id = ?"
            );
            preSt.setInt(1, id);
            resultSet = preSt.executeQuery();

            if (resultSet.next()) {
                Department dep = departmentInit(resultSet);
                Seller seller = sellerInit(resultSet, dep);
                return seller;
            } 
            else return null;
        } catch (SQLException e) {
            throw new DbException("Erro: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                DB.closeResultSet(resultSet);
                DB.closeStatement(preSt);
            }
        }
    }
    
    @Override
    public List<Seller> findAll() {
        PreparedStatement preSt = null;
        ResultSet resultSet = null;
        List<Seller> sellerList = new ArrayList<>();

        try {
            preSt = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName " +
                "FROM seller INNER JOIN department " +
                "ON seller.DepartmentId = department.Id " +
                "ORDER by Name" 
            );

            resultSet = preSt.executeQuery();

            while (resultSet.next()) {
                sellerList.add(sellerInit(resultSet, departmentInit(resultSet)));
            }

            return sellerList;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preSt);
        }
    }

    @Override
    public List<Seller> findByDepartment(int departmentId) {
        PreparedStatement preSt = null;
        ResultSet resultSet = null;

        try {
             preSt = conn.prepareStatement(
                "SELECT seller.*,department.Name as DepName " +
                "FROM seller INNER JOIN department " +
                "ON seller.DepartmentId = department.Id " +
                "WHERE DepartmentId = ?"
             );

             preSt.setInt(1, departmentId);
             resultSet = preSt.executeQuery();

             if (resultSet.next()) {

                List<Seller> sellerList = new ArrayList<>();

                while (resultSet.next()) {
                    sellerList.add(sellerInit(resultSet, departmentInit(resultSet)));
                }
                return sellerList;
             } 
             else return null;

        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preSt);
        }
    }
    

    private Seller sellerInit(ResultSet resultSet, Department dep) throws SQLException {
        int sellerId = resultSet.getInt("Id");
        String sellerName = resultSet.getString("Name");
        String sellerEmail = resultSet.getString("Email");

        double sellerBS = resultSet.getDouble("BaseSalary");
        java.sql.Date sellerBD = resultSet.getDate("BirthDate");
        
        return new Seller(sellerId, sellerName, sellerEmail, sellerBS, sellerBD, dep);
    }

    private Department departmentInit(ResultSet resultSet) throws SQLException {
        int depId = resultSet.getInt("DepartmentId");
        String depName = resultSet.getString("DepName");
        return new Department(depId, depName);
    }


}
