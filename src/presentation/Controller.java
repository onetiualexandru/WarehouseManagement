package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import bll.ClientBLL;
import bll.ComenziBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.ComenziDAO;
import dao.ProductDAO;
import model.Client;
import model.Comenzi;
import model.Product;

public class Controller {
	/**
	 * instance of View
	 */
	private View theView;
	/**
	 * instance of ClientDAO
	 */
	private ClientDAO theClient;
	/**
	 * instance of Client BLL
	 */
	private ClientBLL theCleientBll;
	/**
	 * instance of ComenziBLL
	 */
	private ComenziBLL theComenziBll;
	/**
	 * instance of Comenzi DAO
	 */
	private ComenziDAO theComenzi;
	/**
	 * instance of ProductDAO
	 */
	private ProductDAO theProduct;
	/**
	 * instance of ProductBLL
	 */
	private ProductBLL theProductBll;
/**
 * constructor
 * @param theView
 * @param theClient
 */
	public Controller(View theView, ClientDAO theClient) {
		this.theView = theView;
		this.theView.showListener(new listenerShow());
		this.theView.insertListener(new listenerInsert());
		this.theView.updateListener(new listenerUpdate());
		this.theView.deleteListener(new listenerDelete());

		this.theView.showListenerPro(new listenerShowPro());
		this.theView.insertListenerPro(new listenerInsertPro());
		this.theView.updateListenerPro(new listenerUpdatePro());
		this.theView.deleteListenerPro(new listenerDeletePro());

		this.theView.showListenerComenzi(new listenerShowComenzi());
		this.theView.insertListenerComenzi(new listenerInsertComenzi());
		this.theView.updateListenerComenzi(new listenerUpdateComenzi());
		this.theView.deleteListenerComenzi(new listenerDeleteComenzi());

	}
/**
 * listener 
 * @author Onetiu Alexandru
 *
 */
	class listenerShow implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Client[] data = theClient.dispalyAll();
			theView.afisare(data);

		}
	}

	class listenerInsert implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Client c1 = new Client();
			try {
				c1.setId(theView.getIdClient());
				c1.setName(theView.getNameClient());
				c1.setAddress(theView.getAddressClient());
				c1.setEmail(theView.getEmailClient());
				c1.setAge(theView.getAgeClient());
				theCleientBll.insertClient(c1);
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Parametrii Invalizi!");
			} catch (NoSuchElementException NSE) {
				System.out.println(NSE);
				theView.displayErrorMessage(NSE.getMessage());
			} catch (IllegalArgumentException il) {
				System.out.println(il);
				theView.displayErrorMessage(il.getMessage());
			}
		}
	}

	class listenerUpdate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ClientBLL.updateClient(theView.getIdClient(), theView.getNameClient(), theView.getAddressClient(),
						theView.getEmailClient(), theView.getAgeClient());
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Parametrii Invalizi!");
			} catch (IllegalArgumentException il) {
				System.out.println(il);
				theView.displayErrorMessage(il.getMessage());
			}
		}
	}

	class listenerDelete implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ClientBLL.deleteClient(theView.getIdClient());
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Parametrii Invalizi!");
			} catch (NoSuchElementException NSE) {
				System.out.println(NSE);
				theView.displayErrorMessage(NSE.getMessage());
			}
		}
	}

	class listenerShowPro implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Product[] data = theProduct.dispalyAll();

			// List<Object> data=theClient
			theView.afisareProduct(data);
			// theView.afisareClienti(theClient.ShowAll());

		}
	}

	class listenerInsertPro implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Product c1 = new Product();
			try {
				c1.setId(theView.getIdProduct());
				c1.setName(theView.getNameProduct());
				c1.setPrice(theView.getPriceProduct());
				c1.setQuantity(theView.getQuantityProduct());
				theProductBll = new ProductBLL();
				theProductBll.insertProduct(c1);
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Parametrii Invalizi!");
			} catch (IllegalArgumentException il) {
				System.out.println(il);
				theView.displayErrorMessage(il.getMessage());
			}
		}
	}

	class listenerUpdatePro implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				System.out.println(theView.getIdProduct());
				System.out.println(theView.getQuantityProduct());
				ProductBLL.updateProduct(theView.getIdProduct(), theView.getNameProduct(), theView.getPriceProduct(),
						theView.getQuantityProduct());
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Parametrii Invalizi!");
			} catch (IllegalArgumentException il) {
				System.out.println(il);
				theView.displayErrorMessage(il.getMessage());
			}
		}
	}

	class listenerDeletePro implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ProductBLL.deleteProduct(theView.getIdProduct());
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Parametrii Invalizi!");
			} catch (NoSuchElementException NSE) {
				System.out.println(NSE);
				theView.displayErrorMessage(NSE.getMessage());
			}
		}
	}

	class listenerShowComenzi implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Object[] data = theComenzi.dispalyAll();
			// List<Object> data=theClient
			theView.afisareComenzi(data);

			// theView.afisareClienti(theClient.ShowAll());

		}
	}

	class listenerInsertComenzi implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Comenzi c1 = new Comenzi();
			try {
				c1.setId(theView.getIdComenzi());
				c1.setQuantity(theView.getQuantityComenzi());
				c1.setIdClient(theView.getIdComenziClient());
				c1.setIdProdus(theView.getIdComenziProduct());
				System.out.println(c1);
				theComenziBll = new ComenziBLL();
				theComenziBll.insertComenzi(c1);

				ComenziDAO factura = new ComenziDAO();
				factura.printFactura(c1);

			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Parametrii Invalizi!");
			} catch (IllegalArgumentException il) {
				System.out.println(il);
				theView.displayErrorMessage(il.getMessage());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class listenerUpdateComenzi implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				ComenziDAO.update(theView.getIdComenzi(), theView.getQuantityComenzi(), theView.getIdComenziClient(),
						theView.getIdComenziProduct());
				Comenzi c1 = new Comenzi(theView.getIdComenzi(), theView.getQuantityComenzi(),
						theView.getIdComenziClient(), theView.getIdComenziProduct());
				ComenziDAO factura = new ComenziDAO();
				factura.printFactura(c1);
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Parametrii Invalizi!");
			} catch (IllegalArgumentException il) {
				System.out.println(il);
				theView.displayErrorMessage(il.getMessage());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class listenerDeleteComenzi implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				System.out.println(theView.getIdComenzi());
				ComenziDAO.delete(theView.getIdComenzi());
			} catch (NumberFormatException ex) {
				System.out.println(ex);
				theView.displayErrorMessage("Parametrii Invalizi!");
			} catch (IllegalArgumentException il) {
				System.out.println(il);
				theView.displayErrorMessage(il.getMessage());
			}
		}
	}
}
