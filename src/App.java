import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class App {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        List<Seller> sellerList = sellerDao.findAll();

        for (Seller seller: sellerList) {
            System.out.println(seller);
            // Id: 3 Name: Alex Grey Email: alex@gmail.com BaseSalary: 3200.0 BirthDate: 1988-01-15 Department: Id: 1 Name: Computers
            // Id: 6 Name: Alex Pink Email: bob@gmail.com BaseSalary: 3600.0 BirthDate: 1997-03-04 Department: Id: 2 Name: Electronics
            // Id: 7 Name: Alexandre N Email: Alexandre12345@teste.com BaseSalary: 4700.0 BirthDate: 2024-05-10 Department: Id: 2 Name: Electronics
            // Id: 1 Name: Bob Brown Email: bob@gmail.com BaseSalary: 3200.0 BirthDate: 1998-04-21 Department: Id: 1 Name: Computers
            // ...
        }

    }
}

