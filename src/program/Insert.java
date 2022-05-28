package program;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Insert {

	public static void main(String[] args) throws ParseException {

		Department dep = new Department(2, null);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Seller seller = new Seller(1, "João", "joao@gmail.com", sdf.parse("25/10/1989"), 1999.30, dep);

		sellerDao.insert(seller);

	}

}
