package programSeller;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class FindAll {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		List<Seller> seller2 = sellerDao.findAll();

		for (Seller seller : seller2) {

			System.out.println(seller);
		}

	}

}
