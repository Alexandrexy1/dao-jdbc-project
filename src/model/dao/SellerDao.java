package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {
    void insertSeller(Seller obj);
    void updateSeller(Seller obj);
    void deleteById(int id);
    Seller findById(int id);
    List<Seller> findByDepartment(int departmentId);
    List<Seller> findAll();
}
