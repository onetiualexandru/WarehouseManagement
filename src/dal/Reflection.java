package dal;

import java.lang.reflect.Field;
import java.util.Vector;

public class Reflection {
	/**
	 * method to access data from the database
	 * @param input
	 * @return
	 */
	public static Vector<Vector<Object>> reflectionDisplay(Object[] input) {
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		int i = 0;
		while (input[i] != null) {
			Vector<Object> tableRow = new Vector<Object>();
			for (Field field : input[i].getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					value = field.get(input[i]);
					tableRow.add(value);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			data.add(tableRow);
			i++;
		}
		return data;
		
	}
	/**
	 * method to access data from the database
	 * @param input
	 * @return
	 */
	public static Vector<Object> afisareCol (Object[] input){
		Vector<Object> tableCol = new Vector<Object>();
		for (Field field : input[0].getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				tableCol.add(field.getName());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return tableCol;
	}

}
