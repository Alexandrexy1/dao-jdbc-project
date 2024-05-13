package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public void insert(Seller obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public void update(Seller obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
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
                int depId = resultSet.getInt("DepartmentId");
                String depName = resultSet.getString("DepName");
                Department dep = new Department(depId, depName);

                int sellerId = resultSet.getInt("Id");
                String sellerName = resultSet.getString("Name");
                String sellerEmail = resultSet.getString("Email");

                java.sql.Date sellerBD = resultSet.getDate("BirthDate");

                double sellerBS = resultSet.getDouble("BaseSalary");

                Seller seller = new Seller(sellerId, sellerName, sellerEmail, sellerBS, sellerBD, dep);

                return seller;
            }
        } catch (SQLException e) {
            throw new DbException("Erro: " + e.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preSt);
        }
        return null;
    }

    @Override
    public List<Seller> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
