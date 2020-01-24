package sushigame.view;

	import java.awt.BorderLayout;
import java.awt.Color;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
	import java.util.List;

	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
	import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import comp401sushi.Avocado;
import comp401sushi.AvocadoPortion;
import comp401sushi.Crab;
import comp401sushi.CrabPortion;
import comp401sushi.Eel;
import comp401sushi.EelPortion;
import comp401sushi.Ingredient;
import comp401sushi.IngredientPortion;
import comp401sushi.IngredientPortionImpl;
import comp401sushi.Nigiri;
	import comp401sushi.Plate;
	import comp401sushi.RedPlate;
import comp401sushi.Rice;
import comp401sushi.RicePortion;
import comp401sushi.Roll;
	import comp401sushi.Sashimi;
import comp401sushi.Seaweed;
import comp401sushi.SeaweedPortion;
import comp401sushi.Shrimp;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.Tuna;
import comp401sushi.TunaPortion;
import comp401sushi.Yellowtail;
import comp401sushi.YellowtailPortion;

	public class PlayerChefView extends JPanel implements ActionListener {

		private List<ChefViewListener> listeners;
		private Sushi kmp_roll;
		private Sushi crab_sashimi;
		private Sushi eel_nigiri;
		private int belt_size;
		private JPanel colorPanel;
		private JLabel colorChoosing;
		private JComboBox colorChoices;
		private JPanel sushiPanel;
		private JLabel sushiChoosing;
		private JComboBox sushiChoices;
		private JPanel ingsPanel;
		private JLabel ingsChoosing;
		private JPanel ingsChoicesSN;
		private JPanel ingsChoicesR;
		private JButton avo;
		private JButton crab;
		private JButton eel;
		private JButton tuna;
		private JButton yel;
		private JButton shr;
		private JButton rice;
		private JButton sea;
		
		private JPanel amountPanel;
		private JButton amountChoosing;
		private JFormattedTextField amountIn; 
		private JPanel positionPanel;
		private JButton positionChoosing;
		private JPanel positionChoices;
		private JFormattedTextField positionIn;
		private JPanel renewPanel;
		private JButton renewChoosing;
		private String info;
		private int position;
		//private Ingredient ings;
		//private Double amount;
		private ArrayList<IngredientPortion> ingPortion;
		private IngredientPortion[] ingredients;
		public PlayerChefView(int belt_size) {
			this.belt_size = belt_size;
			listeners = new ArrayList<ChefViewListener>();
			info = "";
			position = 0;
			ingPortion = new ArrayList<IngredientPortion>();

			setLayout(new GridLayout(5,1));
			setBackground(new Color(8, 25, 45));
			
			colorPanel = new JPanel();
			colorPanel.setBackground(new Color(8, 25, 45));
			
			colorChoosing = new JLabel();
			colorChoosing.setText("Please Choose Your Plate");
			colorChoosing.setForeground(Color.WHITE);
			colorPanel.add(colorChoosing);
			
			String[] color = new String[] {"Red", "Green", "Blue", "Gold of 5.00", "Gold of 6.00", "Gold of 7.00", "Gold of 8.00",
											"Gold of 9.00", "Gold of 10.00"};
			colorChoices = new JComboBox(color);
			colorChoices.addActionListener(this);
			colorChoices.setBackground(new Color(43, 95, 117));
			colorChoices.setForeground(Color.WHITE);
			colorPanel.add(colorChoices);
			add(colorPanel);
			
			sushiPanel = new JPanel();
			sushiPanel.setBackground(new Color(8, 25, 45));
			sushiPanel.setVisible(false);
			
			sushiChoosing = new JLabel();
			sushiChoosing.setText("Please Choose Your Plate");
			sushiChoosing.setForeground(Color.WHITE);
			sushiPanel.add(sushiChoosing);
			
			String[] sushi = new String[] {"Sashimi", "Nigiri", "Roll"};
			sushiChoices = new JComboBox(sushi);
			sushiChoices.addActionListener(this);
			sushiChoices.setBackground(new Color(43, 95, 117));
			sushiChoices.setForeground(Color.WHITE);
			sushiPanel.add(sushiChoices);
			add(sushiPanel);
			
			
			ingsPanel = new JPanel(new BorderLayout());
			ingsPanel.setBackground(new Color(8, 25, 45));
			ingsPanel.setVisible(false);
			
			ingsChoosing = new JLabel();
			ingsChoosing.setText("Please Choose Your Ingredient");
			//ingsPanel.add(sushiChoosing);
			ingsChoosing.setForeground(Color.WHITE);
			
			ingsChoicesSN = new JPanel(new GridLayout(4,2));
			ingsChoicesSN.setBackground(new Color(8, 25, 45));
			
			ingsChoicesR = new JPanel(new GridLayout(4,2));
			ingsChoicesR.setBackground(new Color(8, 25, 45));
		
			
			avo= new JButton("Avocado");
			avo.setActionCommand("avo");
			avo.addActionListener(this);
			avo.setBackground(new Color(43, 95, 117));
			avo.setForeground(Color.WHITE);
			//ingsChoices.add(avo);
			
			crab= new JButton("Crab");
			crab.setActionCommand("crab");
			crab.addActionListener(this);
			crab.setBackground(new Color(43, 95, 117));
			crab.setForeground(Color.WHITE);
			//ingsChoices.add(crab);
			
			eel= new JButton("Eel");
			eel.setActionCommand("eel");
			eel.addActionListener(this);
			eel.setBackground(new Color(43, 95, 117));
			eel.setForeground(Color.WHITE);
			//ingsChoices.add(eel);
			
			tuna= new JButton("Tuna");
			tuna.setActionCommand("tuna");
			tuna.addActionListener(this);
			tuna.setBackground(new Color(43, 95, 117));
			tuna.setForeground(Color.WHITE);
			//ingsChoices.add(tuna);
			
			yel= new JButton("Yellowtail");
			yel.setActionCommand("yel");
			yel.addActionListener(this);
			yel.setBackground(new Color(43, 95, 117));
			yel.setForeground(Color.WHITE);
			//ingsChoices.add(yel);
			
			shr= new JButton("Shrimp");
			shr.setActionCommand("shr");
			shr.addActionListener(this);
			shr.setBackground(new Color(43, 95, 117));
			shr.setForeground(Color.WHITE);
			//ingsChoices.add(shr);
			
			rice= new JButton("Rice");
			rice.setActionCommand("rice");
			rice.addActionListener(this);
			rice.setBackground(new Color(43, 95, 117));
			rice.setForeground(Color.WHITE);
			//ingsChoices.add(rice);
			
			sea= new JButton("Seaweed");
			sea.setActionCommand("sea");
			sea.addActionListener(this);
			sea.setBackground(new Color(43, 95, 117));
			sea.setForeground(Color.WHITE);
			//ingsChoices.add(sea);
			
			
			
			
			amountPanel = new JPanel(new GridLayout(2,1));
			amountPanel.setBackground(new Color(8, 25, 45));
			
			amountChoosing = new JButton("Confirm");
			amountChoosing.setActionCommand("amountChoosing");
			amountChoosing.addActionListener(this);
			amountChoosing.setBackground(new Color(43, 95, 117));
			amountChoosing.setForeground(Color.WHITE);
			amountPanel.add(amountChoosing);
			
			NumberFormat format = NumberFormat.getInstance();
			NumberFormatter formatter = new NumberFormatter(format);
			formatter.setValueClass(double.class);
			formatter.setMinimum(0);
			formatter.setMaximum(1.5);
			formatter.setAllowsInvalid(false);
			formatter.setCommitsOnValidEdit(true);
			amountIn= new JFormattedTextField();
			
			add(ingsPanel);
			
			
			positionPanel = new JPanel(new BorderLayout());
			positionPanel.setBackground(new Color(8, 25, 45));
			positionPanel.setVisible(false);
			
			positionChoosing = new JButton("Enter Position");
			positionChoosing.setActionCommand("p");
			positionChoosing.addActionListener(this);
			positionChoosing.setBackground(new Color(43, 95, 117));
			positionChoosing.setForeground(Color.WHITE);
			positionPanel.add(positionChoosing, BorderLayout.CENTER);
			
			NumberFormat formatP = NumberFormat.getInstance();
			NumberFormatter formatterP = new NumberFormatter(formatP);
			formatterP.setValueClass(int.class);
			formatterP.setMinimum(0);
			formatterP.setMaximum(20);
			formatterP.setAllowsInvalid(false);
			formatterP.setCommitsOnValidEdit(true);
			positionIn= new JFormattedTextField();
			
			add(positionPanel);
			

			renewPanel = new JPanel(new BorderLayout());
			renewPanel.setBackground(new Color(8, 25, 45));
			renewPanel.setVisible(false);
			
			renewChoosing = new JButton("Confirm to add this plate");
			renewChoosing.setActionCommand("p");
			renewChoosing.addActionListener(this);
			renewChoosing.setBackground(new Color(43, 95, 117));
			renewChoosing.setForeground(Color.WHITE);
			renewPanel.add(renewChoosing, BorderLayout.CENTER);
			
			add(renewPanel);

			//JButton sashimi_button = new JButton("Make red plate of crab sashimi at position 3");
			//sashimi_button.setActionCommand("red_crab_sashimi_at_3");
			//sashimi_button.addActionListener(this);
			//add(sashimi_button);

			//JButton nigiri_button = new JButton("Make blue plate of eel nigiri at position 8");
			//nigiri_button.setActionCommand("blue_eel_nigiri_at_8");
			//nigiri_button.addActionListener(this);
			//add(nigiri_button);

			//JButton roll_button = new JButton("Make gold plate of KMP roll at position 5");
			//roll_button.setActionCommand("gold_kmp_roll_at_5");
			//roll_button.addActionListener(this);
			//add(roll_button);

			//kmp_roll = new Roll("KMP Roll", new IngredientPortion[] {new EelPortion(1.0), new AvocadoPortion(0.5), new SeaweedPortion(0.2)});
			//crab_sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
			//eel_nigiri = new Nigiri(Nigiri.NigiriType.EEL);
		}

		public void registerChefListener(ChefViewListener cl) {
			listeners.add(cl);
		}

		//private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
			//for (ChefViewListener l : listeners) {
				//l.handleRedPlateRequest(plate_sushi, plate_position);
			//}
		//}

		//private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
			//for (ChefViewListener l : listeners) {
				//l.handleGreenPlateRequest(plate_sushi, plate_position);
			//}
		//}

		//private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
			//for (ChefViewListener l : listeners) {
				//l.handleBluePlateRequest(plate_sushi, plate_position);
			//}
		//}
		
		//private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
			//for (ChefViewListener l : listeners) {
				//l.handleGoldPlateRequest(plate_sushi, plate_position, price);
			//}
		//}
		private void makePlateRequest() {
			Sushi s = new Sashimi(Sashimi.SashimiType.CRAB);
			if(info.contains("Sashimi")) {
				if(info.contains("crab")) {
					s = new Sashimi(Sashimi.SashimiType.CRAB);
				}else if(info.contains("eel")) {
					s = new Sashimi(Sashimi.SashimiType.EEL);
				}else if(info.contains("shrimp")) {
					s = new Sashimi(Sashimi.SashimiType.SHRIMP);
				}else if(info.contains("tuna")) {
					s = new Sashimi(Sashimi.SashimiType.TUNA);
				}if(info.contains("yellowtail")) {
					s = new Sashimi(Sashimi.SashimiType.YELLOWTAIL);
				}
			}else if(info.contains("Nigriri")) {
				if(info.contains("crab")) {
					s = new Nigiri(Nigiri.NigiriType.CRAB);
				}else if(info.contains("eel")) {
					s = new Nigiri(Nigiri.NigiriType.EEL);
				}else if(info.contains("shrimp")) {
					s = new Nigiri(Nigiri.NigiriType.SHRIMP);
				}else if(info.contains("tuna")) {
					s = new Nigiri(Nigiri.NigiriType.TUNA);
				}if(info.contains("yellowtail")) {
					s = new Nigiri(Nigiri.NigiriType.YELLOWTAIL);
				}
			}else if(info.contains("Roll")) {
				s = new Roll("Customized Roll", ingredients);
			}
			
			if(info.contains("Red")) {
				double price = 1.00;
				if(s.getCost()>price) {
					JOptionPane.showMessageDialog(null,"Cost is larger than the price, enter again");
				}else {
					for (ChefViewListener l : listeners) {
						l.handleRedPlateRequest(s, position);
					}
				}
			}else if(info.contains("Green")) {
				double price = 2.00;
				if(s.getCost()>price) {
					JOptionPane.showMessageDialog(null,"Cost is larger than the price, enter again");
				}else {
					for (ChefViewListener l : listeners) {
						l.handleGreenPlateRequest(s, position);
					}
				}
			}else if(info.contains("Gold5")) {
				double price = 5.00;
				if(s.getCost()>price) {
					JOptionPane.showMessageDialog(null,"Cost is larger than the price, enter again");
				}else {
					for (ChefViewListener l : listeners) {
						l.handleGoldPlateRequest(s, position,5.00);
					}
				}
			}else if(info.contains("Gold6")) {
				double price = 6.00;
				if(s.getCost()>price) {
					JOptionPane.showMessageDialog(null,"Cost is larger than the price, enter again");
				}else {
					for (ChefViewListener l : listeners) {
						l.handleGoldPlateRequest(s, position,6.00);
					}
				}
			}else if(info.contains("Gold7")) {
				double price = 7.00;
				if(s.getCost()>price) {
					JOptionPane.showMessageDialog(null,"Cost is larger than the price, enter again");
				}else {
					for (ChefViewListener l : listeners) {
						l.handleGoldPlateRequest(s, position,7.00);
					}
				}
			}else if(info.contains("Gold8")) {
				double price = 8.00;
				if(s.getCost()>price) {
					JOptionPane.showMessageDialog(null,"Cost is larger than the price, enter again");
				}else {
					for (ChefViewListener l : listeners) {
						l.handleGoldPlateRequest(s, position,8.00);
					}
				}
			}else if(info.contains("Gold9")) {
				double price = 9.00;
				if(s.getCost()>price) {
					JOptionPane.showMessageDialog(null,"Cost is larger than the price, enter again");
				}else {
					for (ChefViewListener l : listeners) {
						l.handleGoldPlateRequest(s, position,9.00);
					}
				}
			}else if(info.contains("Gold10")) {
				double price = 10.00;
				if(s.getCost()>price) {
					JOptionPane.showMessageDialog(null,"Cost is larger than the price, enter again");
				}else {
					for (ChefViewListener l : listeners) {
						l.handleGoldPlateRequest(s, position,10.00);
					}
				}
			}
		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==colorChoices) {
				if(colorChoices.getSelectedItem().equals("Red")) {
					sushiPanel.setVisible(true);
					info = info + "Red";
				}else if(colorChoices.getSelectedItem().equals("Green")) {
					sushiPanel.setVisible(true);
					info = info + "Green";
				}else if(colorChoices.getSelectedItem().equals("Blue")) {
					sushiPanel.setVisible(true);
					info = info + "Blue";
				}else if(colorChoices.getSelectedItem().equals("Gold of 5.00")) {
					sushiPanel.setVisible(true);
					info = info + "Gold5";
				}else if(colorChoices.getSelectedItem().equals("Gold of 6.00")) {
					sushiPanel.setVisible(true);
					info = info + "Gold6";
				}else if(colorChoices.getSelectedItem().equals("Gold of 7.00")) {
					sushiPanel.setVisible(true);
					info = info + "Gold7";
				}else if(colorChoices.getSelectedItem().equals("Gold of 8.00")) {
					sushiPanel.setVisible(true);
					info = info + "Gold8";
				}else if(colorChoices.getSelectedItem().equals("Gold of 9.00")) {
					sushiPanel.setVisible(true);
					info = info + "Gold9";
				}else if(colorChoices.getSelectedItem().equals("Gold of 10.00")) {
					sushiPanel.setVisible(true);
					info = info + "Gold10";
				}
			}else if(e.getSource()==sushiChoices) {
				if(sushiChoices.getSelectedItem().equals("Sashimi")) {
					ingsPanel.setVisible(true);
					ingsPanel.add(ingsChoosing,BorderLayout.WEST);
					ingsPanel.add(ingsChoicesSN, BorderLayout.CENTER);
					ingsChoicesSN.add(crab);
					ingsChoicesSN.add(eel);
					ingsChoicesSN.add(shr);
					ingsChoicesSN.add(tuna);
					ingsChoicesSN.add(yel);

					info = info + "Sashimi";
				}else if(sushiChoices.getSelectedItem().equals("Nigiri")) {
					ingsPanel.setVisible(true);
					ingsPanel.add(ingsChoosing,BorderLayout.WEST);
					ingsPanel.add(ingsChoicesSN,BorderLayout.CENTER);
					ingsChoicesSN.add(crab);
					ingsChoicesSN.add(eel);
					ingsChoicesSN.add(shr);
					ingsChoicesSN.add(tuna);
					ingsChoicesSN.add(yel);

					info = info + "Nigiri";
				}else if(sushiChoices.getSelectedItem().equals("Roll")) {
					ingsPanel.setVisible(true);
					ingsPanel.add(ingsChoosing,BorderLayout.WEST);
					ingsPanel.add(ingsChoicesR, BorderLayout.CENTER);
					
					ingsChoicesR.add(avo);
					ingsChoicesR.add(crab);
					ingsChoicesR.add(eel);
					ingsChoicesR.add(rice);
					ingsChoicesR.add(sea);
					ingsChoicesR.add(shr);
					ingsChoicesR.add(tuna);
					ingsChoicesR.add(yel);
					info = info + "Roll";
					ingsPanel.add(amountPanel, BorderLayout.EAST);
					
				}
			}else if(e.getSource()==avo) {
				if(info.contains("Roll")) {
				JOptionPane.showConfirmDialog(null, amountIn,
						"Please enter the amount:",
						 JOptionPane.OK_CANCEL_OPTION);
				double amount = Double.parseDouble(amountIn.getText());
				while(amount<0 || amount > 1.5) {
					JOptionPane.showMessageDialog(null,"The amount must be no larger than 1.5 ");
					JOptionPane.showConfirmDialog(null, amountIn,
							"Please enter the amount:",
							 JOptionPane.OK_CANCEL_OPTION);
					amount = Double.parseDouble(amountIn.getText());
				}
				ingPortion.add(new AvocadoPortion(amount));
				}else if(info.contains("Sashimi")||info.contains("Nigiri")) {
					positionPanel.setVisible(true);
				}
			}else if(e.getSource()==crab) {
				if(info.contains("Roll")) {
				JOptionPane.showConfirmDialog(null, amountIn,
						"Please enter the amount:",
						 JOptionPane.OK_CANCEL_OPTION);
				double amount = Double.parseDouble(amountIn.getText());
				while(amount<0 || amount > 1.5) {
					JOptionPane.showMessageDialog(null,"The amount must be no larger than 1.5 ");
					JOptionPane.showConfirmDialog(null, amountIn,
							"Please enter the amount:",
							 JOptionPane.OK_CANCEL_OPTION);
					amount = Double.parseDouble(amountIn.getText());
				}
				ingPortion.add(new CrabPortion(amount));
				}else if(info.contains("Sashimi")||info.contains("Nigiri")) {
					info += "crab";
					positionPanel.setVisible(true);
				}
			}else if(e.getSource()==eel) {
				if(info.contains("Roll")) {
					JOptionPane.showConfirmDialog(null, amountIn,
							"Please enter the amount:",
							 JOptionPane.OK_CANCEL_OPTION);
					double amount = Double.parseDouble(amountIn.getText());
					while(amount<0 || amount > 1.5) {
						JOptionPane.showMessageDialog(null,"The amount must be no larger than 1.5 ");
						JOptionPane.showConfirmDialog(null, amountIn,
								"Please enter the amount:",
								 JOptionPane.OK_CANCEL_OPTION);
						amount = Double.parseDouble(amountIn.getText());
					}
					ingPortion.add(new EelPortion(amount));
					}else if(info.contains("Sashimi")||info.contains("Nigiri")) {
						info+="eel";
						positionPanel.setVisible(true);
					}
			}else if(e.getSource()==rice) {
				if(info.contains("Roll")) {
					JOptionPane.showConfirmDialog(null, amountIn,
							"Please enter the amount:",
							 JOptionPane.OK_CANCEL_OPTION);
					double amount = Double.parseDouble(amountIn.getText());
					while(amount<0 || amount > 1.5) {
						JOptionPane.showMessageDialog(null,"The amount must be no larger than 1.5 ");
						JOptionPane.showConfirmDialog(null, amountIn,
								"Please enter the amount:",
								 JOptionPane.OK_CANCEL_OPTION);
						amount = Double.parseDouble(amountIn.getText());
					}
					ingPortion.add(new RicePortion(amount));
					}else if(info.contains("Sashimi")||info.contains("Nigiri")) {
						positionPanel.setVisible(true);
					}
			}else if(e.getSource()==sea) {
				if(info.contains("Roll")) {
					JOptionPane.showConfirmDialog(null, amountIn,
							"Please enter the amount:",
							 JOptionPane.OK_CANCEL_OPTION);
					double amount = Double.parseDouble(amountIn.getText());
					while(amount<0 || amount > 1.5) {
						JOptionPane.showMessageDialog(null,"The amount must be no larger than 1.5 ");
						JOptionPane.showConfirmDialog(null, amountIn,
								"Please enter the amount:",
								 JOptionPane.OK_CANCEL_OPTION);
						amount = Double.parseDouble(amountIn.getText());
					}
					ingPortion.add(new SeaweedPortion(amount));
					}else if(info.contains("Sashimi")||info.contains("Nigiri")) {
						positionPanel.setVisible(true);
					}
			}else if(e.getSource()==shr) {
				if(info.contains("Roll")) {
					JOptionPane.showConfirmDialog(null, amountIn,
							"Please enter the amount:",
							 JOptionPane.OK_CANCEL_OPTION);
					double amount = Double.parseDouble(amountIn.getText());
					while(amount<0 || amount > 1.5) {
						JOptionPane.showMessageDialog(null,"The amount must be no larger than 1.5 ");
						JOptionPane.showConfirmDialog(null, amountIn,
								"Please enter the amount:",
								 JOptionPane.OK_CANCEL_OPTION);
						amount = Double.parseDouble(amountIn.getText());
					}
					ingPortion.add(new ShrimpPortion(amount));
					}else if(info.contains("Sashimi")||info.contains("Nigiri")) {
						info += "shrimp";
						positionPanel.setVisible(true);
					}
			}else if(e.getSource()==tuna) {
				if(info.contains("Roll")) {
					JOptionPane.showConfirmDialog(null, amountIn,
							"Please enter the amount:",
							 JOptionPane.OK_CANCEL_OPTION);
					double amount = Double.parseDouble(amountIn.getText());
					while(amount<0 || amount > 1.5) {
						JOptionPane.showMessageDialog(null,"The amount must be no larger than 1.5 ");
						JOptionPane.showConfirmDialog(null, amountIn,
								"Please enter the amount:",
								 JOptionPane.OK_CANCEL_OPTION);
						amount = Double.parseDouble(amountIn.getText());
					}
					ingPortion.add(new TunaPortion(amount));
					}else if(info.contains("Sashimi")||info.contains("Nigiri")) {
						info += "tuna";
						positionPanel.setVisible(true);
					}
			}else if(e.getSource()==yel) {
				if(info.contains("Roll")) {
					JOptionPane.showConfirmDialog(null, amountIn,
							"Please enter the amount:",
							 JOptionPane.OK_CANCEL_OPTION);
					double amount = Double.parseDouble(amountIn.getText());
					while(amount<0 || amount > 1.5) {
						JOptionPane.showMessageDialog(null,"The amount must be no larger than 1.5 ");
						JOptionPane.showConfirmDialog(null, amountIn,
								"Please enter the amount:",
								 JOptionPane.OK_CANCEL_OPTION);
						amount = Double.parseDouble(amountIn.getText());
					}
					ingPortion.add(new YellowtailPortion(amount));
					}else if(info.contains("Sashimi")||info.contains("Nigiri")) {
						info += "yellowtail";
						positionPanel.setVisible(true);
					}
			}
			
			else if(e.getActionCommand().equals("amountChoosing")) {
				System.out.println("sdjfh");
				positionPanel.setVisible(true);
				ingredients = new IngredientPortion[ingPortion.size()];
				for(int i = 0; i<ingPortion.size(); i++) {
					ingredients[i] = ingPortion.get(i);
				}
			}
			else if(e.getSource()==positionChoosing) {
				JOptionPane.showConfirmDialog(null, positionIn,
						"Please enter the position:",
						 JOptionPane.OK_CANCEL_OPTION);
				position = Integer.parseInt(positionIn.getText());
				while(position<0 || position > 20) {
					JOptionPane.showMessageDialog(null,"The position can only be from 0 to 20");
					JOptionPane.showConfirmDialog(null, amountIn,
							"Please enter the amount:",
							 JOptionPane.OK_CANCEL_OPTION);
					position = Integer.parseInt(amountIn.getText());
				}
				renewPanel.setVisible(true);
			}
			else if(e.getSource()==renewChoosing) {
				makePlateRequest();
				ingsPanel.remove(ingsChoosing);
				if(info.contains("Roll")) {
					ingsPanel.remove(amountPanel);
					ingsPanel.remove(ingsChoicesR);
				}else {
					ingsPanel.remove(ingsChoicesSN);
				}
				info = "";
				position = 0;
				ingPortion = new ArrayList<IngredientPortion>();
				remove(colorPanel);
				remove(sushiPanel);
				remove(ingsPanel);
				remove(ingsChoosing);
				remove(positionPanel);
				remove(renewPanel);
				add(colorPanel);
				add(sushiPanel);
				sushiPanel.setVisible(false);
				add(ingsPanel);
				ingsPanel.setVisible(false);
				add(positionPanel);
				positionPanel.setVisible(false);
				add(renewPanel);
				renewPanel.setVisible(false);
			}
			//case "red_crab_sashimi_at_3":
				//makeRedPlateRequest(crab_sashimi, 3);
				//break;
			//case "blue_eel_nigiri_at_8":
				//makeBluePlateRequest(eel_nigiri, 8);
				//break;
			//case "gold_kmp_roll_at_5":
				//makeGoldPlateRequest(kmp_roll, 5, 5.00);
			}
		}


