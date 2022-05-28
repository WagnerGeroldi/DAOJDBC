package programDepartment;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class findAll {

	public static void main(String[] args) {

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		List<Department> list = departmentDao.findAll();

		for (Department item : list) {
			System.out.println(item);
		}

	}

}
