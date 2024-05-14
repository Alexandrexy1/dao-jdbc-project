import java.sql.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(1);
        System.out.println(seller);
        // Id: 1 Name: Bob Brown Email: bob@gmail.com BaseSalary: 3200.0 BirthDate: 1998-04-21 Department: Id: 1 Name: Computers
        
        seller.setName("Mr Bob");
        sellerDao.updateSeller(seller);

        System.out.println(seller);
        // Id: 1 Name: Mr Bob Email: bob@gmail.com BaseSalary: 3200.0 BirthDate: 1998-04-21 Department: Id: 1 Name: Computers
   
    }
}

