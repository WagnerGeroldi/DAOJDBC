package programSeller;

import java.text.ParseException;

import model.dao.DaoFactory;
import model.dao.SellerDao;

public class Delete {

	public static void main(String[] args) throws ParseException {

		SellerDao sellerDao = DaoFactory.createSellerDao();

		sellerDao.deleteById(2);

	}

}
