package model;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Comenzi {
	/**
	 * order id
	 */
	private int id;
	/**
	 * order id client
	 */
	private int IdClient;
	/**
	 * order id prosuct
	 */
	private int IdProdus;
	/**
	 * order id qunatity
	 */
	private int quantity;

	/**
	 * constructor
	 * 
	 * @param id
	 * @param quantity
	 * @param IdClient
	 * @param IdProdus
	 */

	public Comenzi(int id, int quantity, int IdClient, int IdProdus) {
		super();
		this.id = id;
		this.IdClient = IdClient;
		this.IdProdus = IdProdus;
		this.quantity = quantity;

	}

	/**
	 * constructor
	 * 
	 * @param quantity
	 */
	public Comenzi(int quantity) {
		super();
		this.quantity = quantity;
	}

	/**
	 * default constructor
	 */
	public Comenzi() {
	}

	/**
	 * get order id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * set order id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get order id client
	 * 
	 * @return
	 */
	public int getIdClient() {
		return IdClient;
	}

	/**
	 * set order id client
	 * 
	 * @param IdClient
	 */

	public void setIdClient(int IdClient) {
		this.IdClient = IdClient;
	}

	/**
	 * get order id product
	 * 
	 * @return
	 */
	public int getIdProdus() {
		return IdProdus;
	}

	/**
	 * set order id product
	 * 
	 * @param IdProdus
	 */
	public void setIdProdus(int IdProdus) {
		this.IdProdus = IdProdus;
	}

	/**
	 * get order qunatity
	 * 
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * set order quantity
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * display orders
	 */
	@Override
	public String toString() {
		return "Comenzi [id=" + id + ", quantity=" + quantity + ", IdClient=" + IdClient + ", IdProdus=" + IdProdus
				+ "]";
	}

}
