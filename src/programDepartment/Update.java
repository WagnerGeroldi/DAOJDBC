package programDepartment;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Update {

	public static void main(String[] args) {
		
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Department department = new Department(1, "Computadores");
		
		departmentDao.update(department);
		

	}

}
