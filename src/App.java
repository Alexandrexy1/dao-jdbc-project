
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class App {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDeparmentDao();
        
        Department dep = departmentDao.findById(1);
        System.out.println(dep);
        // Department Id: 1 Name: Computers

        dep.setName("Laptops");
        departmentDao.updateDepartment(dep);
        
        Department newDep = departmentDao.findById(dep.getId());
        System.out.println(newDep);
        // Department Id: 1 Name: Laptops
        
    }
}

