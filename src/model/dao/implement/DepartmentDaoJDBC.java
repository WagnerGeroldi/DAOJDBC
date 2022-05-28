package model.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection connection;

	public DepartmentDaoJDBC(Connection connection) { // criado para instanciar no DAOFACTORY
		this.connection = connection;
	}

	@Override
	public void insert(Department department) {
		PreparedStatement preparedStatement = null;

		try {
			connection = DB.getConnection();

			preparedStatement = connection
					.prepareStatement("INSERT INTO department " + "(name_department) " + "VALUES " + "(?)");

			preparedStatement.setString(1, department.getName());

			int insert = preparedStatement.executeUpdate();

			if (insert != 0) {
				System.out.println("Departamento inserido com sucesso!");
			} else {
				System.out.println("Erro ao inserir departamento!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void update(Department department) {

		PreparedStatement preparedStatement = null;

		try {
			connection = DB.getConnection();

			preparedStatement = connection.prepareStatement(
					"UPDATE department " + "SET name_department = ? " + "WHERE department.id_department = ?");

			preparedStatement.setString(1, department.getName());
			preparedStatement.setInt(2, department.getId());

			int insert = preparedStatement.executeUpdate();

			if (insert != 0) {
				System.out.println("Departamento atualizado com sucesso!");
			} else {
				System.out.println("Erro ao ao atualizar departamento!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement preparedStatement = null;

		try {
			connection = DB.getConnection();
			ResultSet resultSet = null;

			preparedStatement = connection
					.prepareStatement("SELECT * FROM department " + "WHERE department.id_department = ?");

			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Department dep = new Department();
				dep.setName(resultSet.getString("name_department"));
				dep.setId(id);
				return dep;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement preparedStatement = null;

		try {
			connection = DB.getConnection();
			ResultSet resultSet = null;

			preparedStatement = connection.prepareStatement("SELECT * FROM department ");

			resultSet = preparedStatement.executeQuery();

			List<Department> list = new ArrayList<>();

			while (resultSet.next()) {
				Department dep = new Department();

				dep.setName(resultSet.getString("name_department"));
				dep.setId(resultSet.getInt("id_department"));
				list.add(dep);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

}
