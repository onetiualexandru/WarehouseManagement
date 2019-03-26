package start;

import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;
import connection.ConnectionFactory;
import dao.ClientDAO;
import model.Client;
import model.Comenzi;
import presentation.Controller;
import presentation.View;

public class Start {
/**
 * Logger instance
 */
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
/**
 * main
 * @param args
 */
	public static void main(String[] args) {

		ConnectionFactory c = new ConnectionFactory();
		ClientDAO dao=new ClientDAO();
	//	Client client = new Client(13,"dummy name", "dummy address", "dummy@address.co", 22);
		ClientBLL clientbll = new ClientBLL();
	//	clientbll.insertClient(client);
		
		View view = new View();
		Controller theController = new Controller(view,dao);

	}
}
