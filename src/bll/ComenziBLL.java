package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.QuantityValidator;
import bll.validators.Validator;
import dao.ComenziDAO;
import model.Comenzi;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ComenziBLL {
	/**
	 * list of comenzi
	 */

	private static List<Validator<Comenzi>> validators;
	
	/**
	 * constructor
	 */

	public ComenziBLL() {
		validators = new ArrayList<Validator<Comenzi>>();
		validators.add(new QuantityValidator());
	}
	/**
	 * method that checks if an order exists
	 * @param id
	 * @return
	 */

	public static Comenzi findComenziById(int id) {
		Comenzi st = ComenziDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The Comenzi with id = " + id + " was not found!");
		}
		return st;
	}
	/**
	 * mehod that ensure an order is inserted corectly
	 * @param Comenzi
	 * @return
	 */

	public static int insertComenzi(Comenzi Comenzi) {
		for (Validator<Comenzi> v : validators) {
			v.validate(Comenzi);
		}
	
		return ComenziDAO.insert(Comenzi);
	}
	
	/*public static int deleteComenzi(int id) {
		int st = ComenziDAO.delete(id);
		System.out.println(st);
		if (st == -1) {
			throw new NoSuchElementException("The Comenzi with id = " + id + " was not found!");
		}
		return st;
	}*/
	
}
