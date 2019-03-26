package presentation;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dal.Reflection;
import dao.ClientDAO;
import model.Client;

public class View {
	/**
	 * frames
	 */
	private JFrame frame = new JFrame();
	private JFrame frame1 = new JFrame();
	private JFrame frame2 = new JFrame();
	/**
	 * panles
	 */
	private JPanel panel = new JPanel();
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	/**
	 * label
	 */
	private JLabel title = new JLabel("Client");
	private JLabel id = new JLabel("ID");
	private JLabel name = new JLabel("Name");
	private JLabel address = new JLabel("Address");
	private JLabel email = new JLabel("Email");
	private JLabel age = new JLabel("Age");
	/**
	 * text filds
	 */
	private JTextField idF = new JTextField();
	private JTextField nameF = new JTextField();
	private JTextField addressF = new JTextField();
	private JTextField emailF = new JTextField();
	private JTextField ageF = new JTextField();
	private JButton show = new JButton("Show");
	private JButton insert = new JButton("Insert");
	private JButton update = new JButton("Update");
	private JButton delete = new JButton("Delete");

	private JLabel title1 = new JLabel("Comenzi");
	private JLabel id1 = new JLabel("ID");
	private JLabel quantity = new JLabel("Quantity");
	private JLabel idClient = new JLabel("ID Client");
	private JLabel idProdus = new JLabel("ID Produs");
	private JTextField idF1 = new JTextField();
	private JTextField quantityF1 = new JTextField();
	private JTextField idClientF = new JTextField();
	private static JTextField idProdusF = new JTextField();
	private JButton show1 = new JButton("Show");
	private JButton insert1 = new JButton("Insert");
	private JButton update1 = new JButton("Update");
	private JButton delete1 = new JButton("Delete");

	private JLabel titlePro = new JLabel("Product");
	private JLabel idPro = new JLabel("ID");
	private JLabel namePro = new JLabel("Name");
	private JLabel pricePro = new JLabel("Price");
	private JLabel quantityPro = new JLabel("Quantity");
	private JTextField idFPro = new JTextField();
	private JTextField nameFPro = new JTextField();
	private JTextField priceFPro = new JTextField();
	private JTextField quantityFPro = new JTextField();
	private JButton showPro = new JButton("Show");
	private JButton insertPro = new JButton("Insert");
	private JButton updatePro = new JButton("Update");
	private JButton deletePro = new JButton("Delete");

	private JScrollPane pane;
	private JScrollPane pane1;
	private JScrollPane pane2;
/**
 * constructor
 */
	public View() {

		pane = new JScrollPane();
		pane1 = new JScrollPane();
		pane2 = new JScrollPane();

		title.setBounds(300, 20, 200, 30);
		title.setFont(new Font("Courier New", Font.ITALIC, 18));
		id.setBounds(50, 60, 20, 50);
		name.setBounds(50, 105, 50, 20);
		address.setBounds(50, 135, 50, 20);
		email.setBounds(50, 160, 50, 30);
		age.setBounds(50, 190, 250, 20);
		idF.setBounds(200, 70, 250, 20);
		nameF.setBounds(200, 100, 250, 20);
		addressF.setBounds(200, 130, 250, 20);
		emailF.setBounds(200, 160, 250, 20);
		ageF.setBounds(200, 190, 250, 20);
		show.setBounds(120, 230, 100, 20);
		insert.setBounds(220, 230, 100, 20);
		update.setBounds(320, 230, 100, 20);
		delete.setBounds(420, 230, 100, 20);

		panel.add(title);
		panel.add(id);
		panel.add(name);
		panel.add(address);
		panel.add(email);
		panel.add(age);
		panel.add(idF);
		panel.add(nameF);
		panel.add(addressF);
		panel.add(emailF);
		panel.add(ageF);
		panel.add(show);
		panel.add(insert);
		panel.add(update);
		panel.add(delete);
		panel.setLayout(null);

		title1.setBounds(300, 20, 200, 30);
		title1.setFont(new Font("Courier New", Font.ITALIC, 18));
		id1.setBounds(50, 60, 20, 50);
		quantity.setBounds(50, 105, 100, 20);
		idClient.setBounds(50, 135, 100, 20);
		idProdus.setBounds(50, 160, 100, 30);
		idF1.setBounds(200, 70, 250, 20);
		quantityF1.setBounds(200, 100, 250, 20);
		idClientF.setBounds(200, 130, 250, 20);
		idProdusF.setBounds(200, 160, 250, 20);
		show1.setBounds(120, 230, 100, 20);
		insert1.setBounds(220, 230, 100, 20);
		update1.setBounds(320, 230, 100, 20);
		delete1.setBounds(420, 230, 100, 20);

		panel1.add(title1);
		panel1.add(id1);
		panel1.add(quantity);
		panel1.add(idClient);
		panel1.add(idProdus);
		panel1.add(idF1);
		panel1.add(quantityF1);
		panel1.add(idClientF);
		panel1.add(idProdusF);
		panel1.add(show1);
		panel1.add(insert1);
		panel1.add(update1);
		panel1.add(delete1);
		panel1.setLayout(null);

		titlePro.setBounds(300, 20, 200, 30);
		titlePro.setFont(new Font("Courier New", Font.ITALIC, 18));
		idPro.setBounds(50, 60, 20, 50);
		namePro.setBounds(50, 105, 50, 20);
		pricePro.setBounds(50, 135, 50, 20);
		quantityPro.setBounds(50, 160, 50, 30);
		idFPro.setBounds(200, 70, 250, 20);
		nameFPro.setBounds(200, 100, 250, 20);
		priceFPro.setBounds(200, 130, 250, 20);
		quantityFPro.setBounds(200, 160, 250, 20);
		showPro.setBounds(120, 230, 100, 20);
		insertPro.setBounds(220, 230, 100, 20);
		updatePro.setBounds(320, 230, 100, 20);
		deletePro.setBounds(420, 230, 100, 20);

		panel2.add(titlePro);
		panel2.add(idPro);
		panel2.add(namePro);
		panel2.add(pricePro);
		panel2.add(quantityPro);
		panel2.add(idFPro);
		panel2.add(nameFPro);
		panel2.add(priceFPro);
		panel2.add(quantityFPro);
		panel2.add(showPro);
		panel2.add(insertPro);
		panel2.add(updatePro);
		panel2.add(deletePro);
		panel2.setLayout(null);

		frame2.add(panel2);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(600, 600);
		frame2.setTitle("Product");
		frame2.setVisible(true);
		frame2.setLocation(750, 30);

		frame1.add(panel1);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(600, 600);
		frame1.setTitle("Comenzi");
		frame1.setVisible(true);
		frame1.setLocation(400, 30);

		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setTitle("Client");
		frame.setVisible(true);
		frame.setLocation(50, 30);
	}
/**
 * display data in table
 * @param input
 */
	public void afisare(Object[] input) {

		DefaultTableModel model = new DefaultTableModel(Reflection.reflectionDisplay(input),
				Reflection.afisareCol(input));

		JTable table = new JTable(model);
		panel.remove(pane);
		panel.repaint();
		panel.validate();
		pane = new JScrollPane(table);

		pane.setBounds(35, 300, 500, 200);
		panel.add(pane);

		table.setModel(model);
		model.fireTableDataChanged();

	}
/**
 * view get id client
 * @return
 */
	public int getIdClient() {

		return Integer.parseInt(idF.getText());
	}
/**
 * view get name client
 * @return
 */
	public String getNameClient() {
		return nameF.getText();
	}

	public String getAddressClient() {
		return addressF.getText();
	}

	public String getEmailClient() {
		return emailF.getText();
	}

	public int getAgeClient() {

		return Integer.parseInt(ageF.getText());
	}

	void showListener(ActionListener listenForButton) {
		show.addActionListener(listenForButton);
	}

	void insertListener(ActionListener listenForButton) {
		insert.addActionListener(listenForButton);
	}

	void updateListener(ActionListener listenForButton) {
		update.addActionListener(listenForButton);
	}

	void deleteListener(ActionListener listenForButton) {
		delete.addActionListener(listenForButton);
	}
	

	public int getIdProduct() {

		return Integer.parseInt(idFPro.getText());
	}

	public String getNameProduct() {
		return nameFPro.getText();
	}

	public int getPriceProduct() {

		return Integer.parseInt(priceFPro.getText());
	}

	public int getQuantityProduct() {

		return Integer.parseInt(quantityFPro.getText());
	}

	void showListenerPro(ActionListener listenForButton) {
		showPro.addActionListener(listenForButton);
	}

	void insertListenerPro(ActionListener listenForButton) {
		insertPro.addActionListener(listenForButton);
	}

	void updateListenerPro(ActionListener listenForButton) {
		updatePro.addActionListener(listenForButton);
	}

	void deleteListenerPro(ActionListener listenForButton) {
		deletePro.addActionListener(listenForButton);
	}

	public int getIdComenzi() {

		return Integer.parseInt(idF1.getText());
	}

	public int getQuantityComenzi() {

		return Integer.parseInt(quantityF1.getText());
	}

	public int getIdComenziClient() {

		return Integer.parseInt(idClientF.getText());
	}

	public static int getIdComenziProduct() {

		return Integer.parseInt(idProdusF.getText());
	}

	void showListenerComenzi(ActionListener listenForButton) {
		show1.addActionListener(listenForButton);
	}

	void insertListenerComenzi(ActionListener listenForButton) {
		insert1.addActionListener(listenForButton);
	}

	void updateListenerComenzi(ActionListener listenForButton) {
		update1.addActionListener(listenForButton);
	}

	void deleteListenerComenzi(ActionListener listenForButton) {
		delete1.addActionListener(listenForButton);
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage);
	}

	public void afisareProduct(Object[] input) {

		DefaultTableModel model = new DefaultTableModel(Reflection.reflectionDisplay(input),
				Reflection.afisareCol(input));

		JTable table = new JTable(model);
		panel2.remove(pane2);
		panel2.repaint();
		panel2.validate();
		pane2 = new JScrollPane(table);

		pane2.setBounds(35, 300, 500, 200);
		panel2.add(pane2);

		table.setModel(model);
		model.fireTableDataChanged();

	}
	
	public JFrame retJFrame() {
		return frame;
		
	}

	public void afisareComenzi(Object[] input) {

		DefaultTableModel model = new DefaultTableModel(Reflection.reflectionDisplay(input),
				Reflection.afisareCol(input));

		JTable table = new JTable(model);
		panel1.remove(pane1);
		panel1.repaint();
		panel1.validate();
		pane1 = new JScrollPane(table);

		pane1.setBounds(35, 300, 500, 200);
		panel1.add(pane1);

		table.setModel(model);
		model.fireTableDataChanged();
	}

	/*
	 * public void afisareClienti(Object[][] data) { DefaultTableModel model = new
	 * DefaultTableModel(data, new Object[] { "Id", "Nume", "Address", "Email",
	 * "Age" });
	 * 
	 * JTable table = new JTable(model); panel.remove(pane); panel.repaint();
	 * panel.validate(); pane = new JScrollPane(table);
	 * 
	 * pane.setBounds(35, 300, 500, 200); panel.add(pane);
	 * 
	 * table.setModel(model); model.fireTableDataChanged(); }
	 */

}
