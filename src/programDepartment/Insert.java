package programDepartment;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Insert {

	public static void main(String[] args) {
		
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		Department department = new Department(null, "Jóias");
		
		departmentDao.insert(department);
		

	}

}
