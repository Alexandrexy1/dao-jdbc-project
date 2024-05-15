
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class App {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDeparmentDao();
        
        List<Department> depList = departmentDao.findAll();
        System.out.println(depList);
        // [Department Id: 4 Name: Books, Department Id: 1 Name: Computers, Department Id: 2 Name: Electronics, Department Id: 3 Name: Fashion]

        
    }
}

