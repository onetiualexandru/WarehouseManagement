package bll.validators;

import model.Client;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ClientAgeValidator implements Validator<Client> {
	/**
	 * defined variable for minimum age
	 */
	private static final int MIN_AGE = 14;
	/*
	 * defined variable for maximum age
	 */
	private static final int MAX_AGE = 100;

	/**
	 * method that check the age
	 */
	public void validate(Client t) {

		if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
			throw new IllegalArgumentException("The Client Age limit is not respected!");
		}

	}

}
