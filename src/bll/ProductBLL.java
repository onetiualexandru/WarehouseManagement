package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.ProductValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Product;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class ProductBLL {

	/**
	 *  list of prduct
	 */
	private static List<Validator<Product>> validators;

	public ProductBLL() {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new ProductValidator());
	}
/**
 *  method that checks if a product exists
 * @param id
 * @return
 */
	public static Product findProductById(int id) {
		Product st = ProductDAO.findById(id);
		if (st == null) {
			throw new NoSuchElementException("The Product with id = " + id + " was not found!");
		}
		return st;
	}
/**
 * mehod that ensure a product is inserted corectly
 * @param Product
 * @return
 */
	public int insertProduct(Product Product) {
		for (Validator<Product> v : validators) {
			v.validate(Product);
		}
		return ProductDAO.insert(Product);
	}
	/**
	 * mehod that ensure a client is updated corectly
	 * @param ProductId
	 * @param name
	 * @param price
	 * @param quantity
	 */

	public static void updateProduct(int ProductId, String name, int price, int quantity) {

		if (quantity < 1) {
			throw new IllegalArgumentException("The Product quantity is not respected!");
		}
		if (price < 0) {
			throw new IllegalArgumentException("The Product price is not respected!");
		}

		ProductDAO.update(ProductId, name, price, quantity);
	}
	
	/***
	 * method that check if the product can be deleted
	 * @param id
	 * @return
	 */

	public static int deleteProduct(int id) {
		int st = ProductDAO.delete(id);
		if (st < 1) {
			throw new NoSuchElementException("The Product with id = " + id + " can not be deleted!");
		}
		return st;
	}

}
