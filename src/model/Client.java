package model;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Client {
	/**
	 * client id
	 */
	private int id;
	/**
	 * client name
	 */
	private String name;
	/**
	 * client address
	 */
	private String address;
	/**
	 * client emial
	 */
	private String email;
	/**
	 * client age
	 */
	private int age;

	/**
	 * constructor
	 * 
	 * @param id
	 * @param name
	 * @param address
	 * @param email
	 * @param age
	 */
	public Client(int id, String name, String address, String email, int age) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
	}

	/**
	 * constructor
	 * 
	 * @param name
	 * @param address
	 * @param email
	 * @param age
	 */
	public Client(String name, String address, String email, int age) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
	}

	/**
	 * constructor
	 */
	public Client() {
	}

	/**
	 * get id client
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * set id client
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get name client
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name client
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get address client
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * set address client
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * get age client
	 * 
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * set age client
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * get email client
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * set email client
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * display clients
	 */
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", age=" + age
				+ "]";
	}

}
