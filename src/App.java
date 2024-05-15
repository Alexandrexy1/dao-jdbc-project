
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;


public class App {
    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDeparmentDao();
        
        Department dep = new Department(null, "Computers");
        departmentDao.insertDepartment(dep);
        
        System.out.println(departmentDao.findById(dep.getId()));
        // Department Id: 6 Name: Computers
        
    }
}

