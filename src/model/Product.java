package model;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Product {
	/**
	 * product id
	 */
	private int id;
	/**
	 * product name
	 */
	private String name;
	/**
	 * product price
	 */
	private int price;
	/**
	 * product quantity
	 */
	private int quantity;

	/**
	 * constructor
	 * 
	 * @param id
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public Product(int id, String name, int price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 * constructor
	 * 
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public Product(String name, int price, int quantity) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 * default constructor
	 */
	public Product() {
	}

	/**
	 * get id product
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * set id product
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get id quantity
	 * 
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * set id quantity
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * get product name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name product
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get product price
	 * 
	 * @return
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * set product price
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * disaply products
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ",quantity=" + quantity + "]";
	}

}
