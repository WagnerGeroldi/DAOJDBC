package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		PreparedStatement preparedStatement = null;

		try {
			connection = DB.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO seller "
					+ "(name, email, birthDate, baseSalary, department_id) " + "values " + "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, department.getName());
			preparedStatement.setString(2, department.getEmail());
			preparedStatement.setDate(3, new java.sql.Date(department.getBirthDate().getTime()));
			preparedStatement.setDouble(4, department.getBaseSalary());
			preparedStatement.setInt(5, department.getDepartment().getId());

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = preparedStatement.getGeneratedKeys();

				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Pronto! linha adicionada: " + id);
				}
			} else {
				System.out.println("Nenhuma linha alterado");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeConnection();
		}

	}

	@Override
	public void update(Seller department) {

		PreparedStatement preparedStatement = null;

		try {
			connection = DB.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE seller "
					+ "SET name= ?, email= ?, birthDate= ?, baseSalary= ?, department_id= ? " + "WHERE id_seller = ?");

			preparedStatement.setString(1, department.getName());
			preparedStatement.setString(2, department.getEmail());
			preparedStatement.setDate(3, new java.sql.Date(department.getBirthDate().getTime()));
			preparedStatement.setDouble(4, department.getBaseSalary());
			preparedStatement.setInt(5, department.getDepartment().getId());
			preparedStatement.setInt(6, department.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeConnection();
		}

	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement preparedStatement = null;

		try {
			connection = DB.getConnection();
			preparedStatement = connection.prepareStatement("DELETE from seller " + "WHERE id_seller = ?");

			preparedStatement.setInt(1, id);

			int confirm = preparedStatement.executeUpdate();

			if (confirm != 0) {
				System.out.println("Vendedor deletado com sucesso");
			} else {
				System.out.println("Erro ao deletar!");
			}


		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeConnection();
		}

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

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = connection.prepareStatement("SELECT* " + "FROM seller INNER JOIN department "
					+ "ON seller.department_id = department.id_department " + "WHERE department.id_department = ?");

			preparedStatement.setInt(1, department.getId());

			resultSet = preparedStatement.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (resultSet.next()) {
				Department dep = map.get(resultSet.getInt("id_department"));

				if (dep == null) {
					dep = instanceOfDepartment(resultSet);
					map.put(resultSet.getInt("id_department"), dep);
				}
				Seller seller = instanceOfSeller(resultSet, dep);
				list.add(seller);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Seller> findAll() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			preparedStatement = connection.prepareStatement("SELECT* " + "FROM seller INNER JOIN department "
					+ "ON seller.department_id = department.id_department ");

			resultSet = preparedStatement.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (resultSet.next()) {
				Department dep = map.get(resultSet.getInt("id_department"));

				if (dep == null) {
					dep = instanceOfDepartment(resultSet);
					map.put(resultSet.getInt("id_department"), dep);
				}
				Seller seller = instanceOfSeller(resultSet, dep);
				list.add(seller);
			}
			return list;
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

}
