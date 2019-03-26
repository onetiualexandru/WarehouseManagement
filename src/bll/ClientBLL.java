package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.ClientAgeValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Author: Onetiu Alexandru Lucian
 * @Since: Apr 03, 2017
 */
public class ClientBLL {

	/**
	 * list validators
	 */
	private static List<Validator<Client>> validators;

	/**
	 * costructor without parameters
	 */
	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator());
		validators.add(new ClientAgeValidator());
	}

	/**
	 * method that checks if a CLinet exists
	 * 
	 * @param id
	 * @return
	 */

	public static Client findClientById(int id) {
		Client st = ClientDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The Client with id = " + id + " was not found!");
		}
		return st;
	}

	/**
	 * mehod that ensure a client is inserted corectly
	 * 
	 * @param Client
	 * @return
	 */
	public static int insertClient(Client Client) {
		for (Validator<Client> v : validators) {
			v.validate(Client);
		}
		return ClientDAO.insert(Client);
	}

	/**
	 * mehod that ensure a client is updated corectly
	 * 
	 * @param CLientId
	 * @param name
	 * @param address
	 * @param email
	 * @param age
	 */

	public static void updateClient(int CLientId, String name, String address, String email, int age) {
		Client cl = new Client(CLientId, name, address, email, age);
		for (Validator<Client> v : validators) {
			v.validate(cl);
		}
		ClientDAO.update(CLientId, name, address, email, age);
	}

	/**
	 * method that check if the client can be deleted
	 * 
	 * @param id 
	 * @return 
	 */
	public static int deleteClient(int id) {
		int st = ClientDAO.delete(id);
		if (st < 1) {
			throw new NoSuchElementException("The Client with id = " + id + " can not be deleted!");
		}
		return st;
	}

}
