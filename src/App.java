import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class App {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        List<Seller> sellerList = sellerDao.findByDepartment(2);

        for (Seller seller: sellerList) {
            System.out.println(seller);
            // Id: 6 Name: Alex Pink Email: bob@gmail.com BaseSalary: 3600.0 BirthDate: 1997-03-04 Department: Id: 2 Name: Electronics
            // Id: 7 Name: Alexandre N Email: Alexandre12345@teste.com BaseSalary: 4700.0 BirthDate: 2024-05-10 Department: Id: 2 Name: Electronics
            // Id: 8 Name: Joaozinho Teste Email: Joaozinhoteste12345@teste.com BaseSalary: 4100.0 BirthDate: 2024-05-10 Department: Id: 2 Name: Electronics 
        }

    }
}

