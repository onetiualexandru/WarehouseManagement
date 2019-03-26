package bll.validators;

import model.Comenzi;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class QuantityValidator implements Validator<Comenzi> {
	private static final int MIN_AGE = 1;
	private static final int MAX_AGE = 1000;

	public void validate(Comenzi t) {

		if (t.getQuantity() < MIN_AGE || t.getQuantity() > MAX_AGE) {
			throw new IllegalArgumentException("The Order quantity limit is not respected!");
		}

	}

}
