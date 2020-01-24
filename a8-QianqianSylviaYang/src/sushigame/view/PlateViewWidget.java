package sushigame.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import comp401sushi.IngredientPortion;
import comp401sushi.Plate;
import sushigame.model.Belt;

@SuppressWarnings("serial")
public class PlateViewWidget extends JButton implements ActionListener{
	private Belt belt;
	private Plate plate;
	private int index;
	private JPanel panel;
	public PlateViewWidget(JPanel jp,Belt b, Plate p, int i){
		belt = b;
		plate = p;
		index = i;
		panel = jp;
		this.addActionListener(this);
	}
	public void setPlateAndIndex(Plate p, int i) {
		plate = p;
		index = i;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("Click")) {
			if(plate == null) {
				JOptionPane.showMessageDialog(null, "No Plate Here");
			}else {
			String info;
			boolean isRoll;
			String name = plate.getContents().getName();
			if(name.contains("sashimi")) {
				info = "Type: Sashimi";
				isRoll = false;
			}else if(name.contains("nigiri")) {
				info = "Type: Nigiri";
				isRoll = false;
			}else{
				info = "Type: Roll";
				isRoll = true;
			}
			if(isRoll) {
				IngredientPortion[] ings = plate.getContents().getIngredients();
				String ing_amount = "";
				for(int i=0; i<ings.length; i++) {
					double amount = ((int)(100*ings[i].getAmount()))/100.00;
					ing_amount = ing_amount +  amount+ " ounces of " + ings[i].getName()+ "; " ;
				}
				info = info + ";" + "\n" + "Name: " + name+";" + "\n" + "Ingrdients: " + ing_amount;
			}else {
				info = info + ";" + "\n" + "Name: " + name + ";";
			}
			String chef = plate.getChef().getName();
			info = info + "\n" + "Chef: " + chef + "; ";
			int age;
			age = belt.getAgeOfPlateAtPosition(index);
			info = info + "\n" + "Age: " + age;
			JOptionPane.showMessageDialog(null, info);
		}
		}
			
	}
}
