package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection connection;

	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller department) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller department) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = connection
					.prepareStatement("SELECT seller.*, department.* " + "FROM seller INNER JOIN department "
							+ "ON seller.department_id = department.id_department " + "WHERE seller.id_seller = ?");

			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Department dep = instanceOfDepartment(resultSet);
				Seller seller = instanceOfSeller(resultSet, dep);

				return seller;

			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}

	}

	private Seller instanceOfSeller(ResultSet resultSet, Department dep) throws SQLException {

		Seller seller = new Seller();
		seller.setId(resultSet.getInt("id_seller"));
		seller.setName(resultSet.getString("name"));
		seller.setBirthDate(resultSet.getDate("birthDate"));
		seller.setEmail(resultSet.getString("email"));
		seller.setBaseSalary(resultSet.getDouble("baseSalary"));
		seller.setDepartment(dep);
		return seller;
	}

	private Department instanceOfDepartment(ResultSet resultSet) throws SQLException {
		Department dep = new Department();
		dep.setId(resultSet.getInt("id_department"));
		dep.setName(resultSet.getString("name_department"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
