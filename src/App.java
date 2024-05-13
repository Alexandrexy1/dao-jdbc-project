import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class App {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(7);
        System.out.println(seller); 
        // output: Id: 7 Name: Alexandre N Email: Alexandre12345@teste.com BaseSalary: 4700.0 
        // BirthDate: 2003-07-20 Department: Id: 2 Name: Electronics
    }
}

