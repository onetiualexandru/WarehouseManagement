package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientDAO {
	/**
	 * sql statements
	 */
	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());

	private static final String insertStatementString = "INSERT INTO Client (id,name,address,email,age)"
			+ " VALUES (?,?,?,?,?)";
	private final static String findStatementString = "SELECT id,name,address,email,age FROM Client";

	private final static String findIdStatementString = "SELECT id,name,address,email,age FROM Client WHERE id=?";

	private static final String updateStatamentString = "UPDATE Client SET name = ? , address = ? , email = ? , age = ? WHERE id = ? ";

	private static final String deleteStatamentString = "DELETE FROM Client WHERE id =?";

	/**
	 * displayy all clients
	 * 
	 * @return
	 */
	public static Client[] dispalyAll() {

		Client[] toReturn = new Client[100];

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
				String address = rs.getString("address");
				String email = rs.getString("email");
				int age = rs.getInt("age");
				toReturn[i] = new Client(id, name, address, email, age);
				i++;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static Client findById(int ClientId) {
		Client toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findIdStatementString);
			findStatement.setLong(1, ClientId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String address = rs.getString("address");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			toReturn = new Client(ClientId, name, address, email, age);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

		return toReturn;
	}

	/*
	 public static Object[][] ShowAll() {
	 
	 Object[][] data = null;
	  
	 Connection dbConnection = ConnectionFactory.getConnection();
	  PreparedStatement findStatement = null; ResultSet rs = null; try {
	  findStatement = dbConnection.prepareStatement(findStatementString); rs =
	  findStatement.executeQuery(); int rowCount = getRowCount(rs); // Row Count
	  int columnCount = getColumnCount(rs); // Column Count data = new
	  Object[rowCount][columnCount]; rs.beforeFirst(); int i = 0; while (rs.next())
	  { int j = 0; data[i][j] = rs.getInt("id"); //
	  System.out.println(data[i][j]+" "); j++; data[i][j] = rs.getString("name");
	  // System.out.println(data[i][j]+" "); j++; data[i][j] =
	  rs.getString("address"); // System.out.println(data[i][j]+" "); j++;
	 data[i][j] = rs.getString("email"); // System.out.println(data[i][j]+" ");
	  j++; data[i][j] = rs.getInt("age"); // System.out.println(data[i][j]+" ");
	  j++; i++; } for (int k = 0; k < data.length; k++) { for (int j = 0; j <
	  data[k].length; j++) System.out.print(data[k][j] + " ");
	  System.out.println(); }
	  
	  } catch (SQLException e) { LOGGER.log(Level.WARNING, "ClientDAO:findById " +
	  e.getMessage()); } finally { ConnectionFactory.close(rs);
	  ConnectionFactory.close(findStatement);
	 * ConnectionFactory.close(dbConnection); } return data; }
	 */

	/*
	 * private static int getRowCount(ResultSet rs) {
	 * 
	 * try {
	 * 
	 * if (rs != null) {
	 * 
	 * rs.last();
	 * 
	 * return rs.getRow(); }
	 * 
	 * } catch (SQLException e) {
	 * 
	 * System.out.println(e.getMessage()); e.printStackTrace(); }
	 * 
	 * return 0; }
	 * 
	 * // Method to get Column Count from ResultSet Object private static int
	 * getColumnCount(ResultSet rs) {
	 * 
	 * try {
	 * 
	 * if (rs != null) return rs.getMetaData().getColumnCount();
	 * 
	 * } catch (SQLException e) {
	 * 
	 * System.out.println(e.getMessage()); e.printStackTrace(); }
	 * 
	 * return 0; }
	 */
	/**
	 * insert a client
	 * 
	 * @param Client
	 * @return
	 */
	public static int insert(Client Client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, Client.getId());
			insertStatement.setString(2, Client.getName());
			insertStatement.setString(3, Client.getAddress());
			insertStatement.setString(4, Client.getEmail());
			insertStatement.setInt(5, Client.getAge());
			insertedId = insertStatement.executeUpdate();
			System.out.println(insertedId);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	/**
	 * delete a client
	 * 
	 * @param ClientId
	 * @return
	 */
	public static int delete(int ClientId) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		int deltedId = -1;

		try {

			deleteStatement = dbConnection.prepareStatement(deleteStatamentString);
			deleteStatement.setInt(1, ClientId);
			deltedId = deleteStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return deltedId;
	}

	/**
	 * update a client
	 * 
	 * @param ClientId
	 * @param name
	 * @param address
	 * @param email
	 * @param age
	 */
	public static void update(int ClientId, String name, String address, String email, int age) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement updateStatement = null;
		try {

			updateStatement = dbConnection.prepareStatement(updateStatamentString);
			updateStatement.setString(1, name);
			updateStatement.setString(2, address);
			updateStatement.setString(3, email);
			updateStatement.setInt(4, age);
			updateStatement.setInt(5, ClientId);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

}
