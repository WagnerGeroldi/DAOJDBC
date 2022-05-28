package programSeller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class FindByDepartment {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();
		Department department = new Department(1, null);

		List<Seller> seller2 = sellerDao.findByDepartment(department);

		for (Seller seller : seller2) {

			System.out.println(seller);
		}
	}

}
