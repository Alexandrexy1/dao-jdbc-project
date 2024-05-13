import java.time.LocalDate;

import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) {
        Department obj = new Department(1, "Computers");
        Seller seller = new Seller(1, "Alexandre", "teste@teste.com", 3100, LocalDate.now(), obj);
        System.out.println(obj);
        System.out.println(seller);
    }
}

