package dao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Comenzi;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ComenziDAO {
	/**
	 * sql statements
	 */
	protected static final Logger LOGGER = Logger.getLogger(ComenziDAO.class.getName());

	private static final String insertStatementString = "INSERT INTO Comenzi (id,quantity,idClient,idProdus)"
			+ " VALUES (?,?,?,?)";

	private final static String findStatementString = "SELECT id,quantity,idClient,idProdus FROM Comenzi";

	private final static String findIdStatementString = "SELECT id,quantity,idClient,idProdus FROM Comenzi WHERE id=?";

	private static final String updateStatamentString = "UPDATE Comenzi SET quantity = ?, idClient = ?, idProdus = ? WHERE id = ? ";

	private static final String deleteStatamentString = "DELETE FROM Comenzi WHERE id =?";

	/**
	 * display all the data from orders
	 * 
	 * @return Object[]
	 */
	public static Object[] dispalyAll() {

		Comenzi[] toReturn = new Comenzi[100];

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			rs = findStatement.executeQuery();

			int i = 0;
			while (rs.next()) {
				int id = rs.getInt("id");
				int quantity = rs.getInt("quantity");
				int idClient = rs.getInt("idClient");
				int idProdus = rs.getInt("idProdus");
				toReturn[i] = new Comenzi(id, quantity, idClient, idProdus);
				i++;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComenziDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	/**
	 * find order by id
	 * 
	 * @param ComenziId
	 * @return
	 */
	public static Comenzi findById(int ComenziId) {
		Comenzi toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findIdStatementString);
			findStatement.setLong(1, ComenziId);
			rs = findStatement.executeQuery();
			rs.next();

			int quantity = rs.getInt("quantity");
			int idClient = rs.getInt("idClient");
			int idProdus = rs.getInt("idProdus");
			toReturn = new Comenzi(ComenziId, quantity, idClient, idProdus);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComenziDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

		return toReturn;
	}

	/**
	 * insert order
	 * 
	 * @param Comenzi
	 * @return
	 */
	public static int insert(Comenzi Comenzi) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		Statement stmt = null;
		ResultSet rs = null;

		Statement stmt1 = null;
		int rs1 = -1;

		int insertedId = -1;
		int cantiate = -1;
		try {

			stmt = dbConnection.createStatement();
			stmt1 = dbConnection.createStatement();
			rs = stmt.executeQuery("SELECT quantity FROM product where id=" + Comenzi.getIdProdus());
			rs.next();
			rs = stmt.getResultSet();
			cantiate = rs.getInt(1);
			int ka = cantiate - Comenzi.getQuantity();
			if (cantiate >= Comenzi.getQuantity()) {
				insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
				insertStatement.setInt(1, Comenzi.getId());
				insertStatement.setInt(2, Comenzi.getQuantity());
				insertStatement.setInt(3, Comenzi.getIdClient());
				insertStatement.setInt(4, Comenzi.getIdProdus());
				insertStatement.executeUpdate();
				rs1 = stmt1.executeUpdate("UPDATE product set quantity=" + ka + " where id=" + Comenzi.getIdProdus());
			} else
				throw new IllegalArgumentException("Quantity exceeds the current stock");

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComenziDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	/**
	 * make a bill
	 * 
	 * @param Comenzi
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void printFactura(Comenzi Comenzi) throws FileNotFoundException, UnsupportedEncodingException {
		Connection dbConnection = ConnectionFactory.getConnection();
		PrintWriter writer = new PrintWriter("Factura Id." + Comenzi.getId() + ".txt", "UTF-8");

		Statement stmt = null, stmtClient = null;
		ResultSet rs = null, rsClient = null;

		try {

			writer.println("Factura Id." + Comenzi.getId());

			stmt = dbConnection.createStatement();
			rs = stmt.executeQuery("SELECT id,name,price FROM product where id=" + Comenzi.getIdProdus());
			rs.next();
			writer.println("Product Id: " + rs.getInt(1));
			writer.println("Product Name: " + rs.getString(2));
			writer.println("Product Price: " + rs.getInt(3) + " RON");
			writer.println("Ordered quantity: " + Comenzi.getQuantity());

			stmtClient = dbConnection.createStatement();
			rsClient = stmt.executeQuery("SELECT name,address,email,age FROM client where id=" + Comenzi.getIdClient());
			rsClient.next();
			writer.println("Client Id: " + Comenzi.getIdClient());
			writer.println("Client Name: " + rsClient.getString(1));
			writer.println("Client Address: " + rsClient.getString(2));
			writer.println("Client Email: " + rsClient.getString(3));
			writer.println("Client Age: " + rsClient.getInt(4));

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComenziDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(stmt);
			ConnectionFactory.close(stmtClient);
			ConnectionFactory.close(dbConnection);

		}
		writer.close();
	}

	/**
	 * delete an order
	 * 
	 * @param ComenziId
	 */

	public static void delete(int ComenziId) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement deleteStatement = null;
		try {

			deleteStatement = dbConnection.prepareStatement(deleteStatamentString, Statement.RETURN_GENERATED_KEYS);
			deleteStatement.setInt(1, ComenziId);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComenziDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

	/**
	 * update an order
	 * 
	 * @param ComenziId
	 * @param quantity
	 * @param idClient
	 * @param idProdus
	 */
	public static void update(int ComenziId, int quantity, int idClient, int idProdus) {
		Connection dbConnection = ConnectionFactory.getConnection();

		Statement stmt = null;
		ResultSet rs = null;

		Statement stmt1 = null;
		int rs1 = -1;

		int cantiate = -1;
		int cantiateCom = -1;

		PreparedStatement updateStatement = null;
		try {
			stmt = dbConnection.createStatement();
			stmt1 = dbConnection.createStatement();
			rs = stmt.executeQuery("SELECT quantity FROM product where id=" + idProdus);
			rs.next();
			rs = stmt.getResultSet();
			cantiate = rs.getInt(1);
			rs = stmt.executeQuery("SELECT quantity FROM comenzi where id=" + ComenziId);
			rs.next();
			rs = stmt.getResultSet();
			cantiateCom = rs.getInt(1);
			System.out.println("cantiatecom" + cantiateCom);
			int ka = cantiate + cantiateCom - quantity;
			System.out.println(ka);
			if (cantiate >= quantity || ka >= 0) {
				updateStatement = dbConnection.prepareStatement(updateStatamentString, Statement.RETURN_GENERATED_KEYS);
				updateStatement.setInt(1, quantity);
				updateStatement.setInt(2, idClient);
				updateStatement.setInt(3, idProdus);
				updateStatement.setInt(4, ComenziId);
				updateStatement.executeUpdate();
				rs1 = stmt1.executeUpdate("UPDATE product set quantity=" + ka + " where id=" + idProdus);
			} else
				throw new IllegalArgumentException("Quantity exceeds the current stock");
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComenziDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}

}
