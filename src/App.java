
import model.dao.DaoFactory;
import model.dao.SellerDao;

import model.entities.Seller;

public class App {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(8);
        System.out.println(seller);
        // Id: 8 Name: Joaozinho Teste Email: Joaozinhoteste12345@teste.com BaseSalary: 4100.0 BirthDate: 2024-05-10 Department: Id: 2 Name: Electronics
        
        sellerDao.deleteById(8);

        Seller newSeller = sellerDao.findById(8);
        System.out.println(newSeller);
        // null
    }
}

