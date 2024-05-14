import java.sql.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        Department dep = new Department(1, null);

        Seller seller = new Seller(null, "Blue", "Blueenemyred@test.com", 2800, date, dep);

        sellerDao.insertSeller(seller);

        System.out.println("Seller id: " + seller.getId()); // Seller id: 11

        Seller sellerInDataBase = sellerDao.findById(seller.getId());
        System.out.println(sellerInDataBase);
        // Id: 11 Name: Blue Email: Blueenemyred@test.com BaseSalary: 2800.0 BirthDate: 2024-05-14 Department: Id: 1 Name: Computers
    }
}

