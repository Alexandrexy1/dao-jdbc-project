
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class App {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDeparmentDao();
        
        Department dep = departmentDao.findById(6);
        System.out.println(dep);
        // Department Id: 6 Name: Computers
        
        departmentDao.deleteById(6);
        Department newDep = departmentDao.findById(6);
        System.out.println(newDep);
        // null
        
    }
}

