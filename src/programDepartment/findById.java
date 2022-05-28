package programDepartment;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

public class findById {

	public static void main(String[] args) {
		
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println(departmentDao.findById(15));
		
		

	}

}
