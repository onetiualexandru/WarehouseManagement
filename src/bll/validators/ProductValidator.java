package bll.validators;

import model.Product;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ProductValidator implements Validator<Product> {
	/**
	 * defined variable for minimum quantity
	 */
	private static final int MIN_QUA = 1;
	/**
	 * defined variable for minimum price
	 */
	private static final int MIN_PRET = 0;
/**
 * check the price and quantity
 * @param Product
 */
	public void validate(Product t) {

		if (t.getQuantity() < MIN_QUA) {
			throw new IllegalArgumentException("The Product quantity is not respected!");
		}
		if (t.getPrice() < MIN_PRET) {
			throw new IllegalArgumentException("The Product price is not respected!");
		}

	}

}
