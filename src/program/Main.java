package program;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Main {

	public static void main(String[] args) {
		
		Department dp = new Department(1, "Livros");
		Seller seller = new Seller(21, "wag", "wagner@gmail.com", new Date(), 1500.0, dp);
		
		
		
		System.out.println(dp.toString());
		System.out.println(seller.toString());

	}

}
