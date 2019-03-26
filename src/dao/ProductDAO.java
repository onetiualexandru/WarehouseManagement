package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Product;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ProductDAO {
	/**
	 * sql statments
	 */
	protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());

	private static final String insertStatementString = "INSERT INTO Product (id,name,price,quantity)"
			+ " VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT id,name,price,quantity FROM Product";

	private final static String findIdStatementString = "SELECT id,name,price,quantity FROM Product WHERE id=?";

	private static final String updateStatamentString = "UPDATE Product SET name = ?, price = ?, quantity=? WHERE id = ? ";

	private static final String deleteStatamentString = "DELETE FROM Product WHERE id =?";

	/**
	 * display all products
	 * 
	 * @return
	 */

	public static Product[] dispalyAll() {

		Product[] toReturn = new Product[100];

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			rs = findStatement.executeQuery();

			int i = 0;
			while (rs.next()) {
				int id = rs.getInt("id");

				String name = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				toReturn[i] = new Product(id, name, price, quantity);
				i++;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/**
	 * find product by id
	 * 
	 * @param ProductId
	 * @return
	 */
	public static Product findById(int ProductId) {
		Product toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findIdStatementString);
			findStatement.setLong(1, ProductId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			int price = rs.getInt("price");
			int quantity = rs.getInt("quantity");
			toReturn = new Product(ProductId, name, price, quantity);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

		return toReturn;
	}

	/**
	 * insert product
	 * 
	 * @param Product
	 * @return
	 */

	public static int insert(Product Product) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Product.getId());
			insertStatement.setString(2, Product.getName());
			insertStatement.setInt(3, Product.getPrice());
			insertStatement.setInt(4, Product.getQuantity());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	/**
	 * delete product
	 * 
	 * @param ProductId
	 * @return
	 */
	public static int delete(int ProductId) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		int deltedId = -1;
		try {

			deleteStatement = dbConnection.prepareStatement(deleteStatamentString);
			deleteStatement.setLong(1, ProductId);
			deltedId = deleteStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return deltedId;
	}

	/**
	 * update product
	 * 
	 * @param ProductId
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public static void update(int ProductId, String name, int price, int quantity) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		try {

			updateStatement = dbConnection.prepareStatement(updateStatamentString, Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, name);
			updateStatement.setInt(2, price);
			updateStatement.setInt(3, quantity);
			updateStatement.setInt(4, ProductId);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

}
