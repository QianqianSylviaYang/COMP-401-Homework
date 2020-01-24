package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.prism.Graphics;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver, ActionListener {

	private SushiGameModel game_model;
	private JLabel display;
	private JPanel cbuttons;
	private JButton balance;
	private JButton spoiled;
	private JButton consumed;
	private String scoreType;
	private String sb_html;
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		setLayout(new GridLayout(2,1));
		setBackground(new Color(8, 25, 45));
		scoreType = " ";
		sb_html = " ";
		
		cbuttons = new JPanel();
		cbuttons.setLayout(new GridLayout(3,1,1,1));
		
		
		balance = new JButton("Balance View");
		balance.setActionCommand("b");
		balance.addActionListener(this);
		balance.setBackground(new Color(43, 95, 117));
		balance.setForeground(Color.WHITE);
		cbuttons.add(balance);
		
		spoiled = new JButton("Spoiled Plate View");
		spoiled.setActionCommand("s");
		spoiled.setBackground(new Color(43, 95, 117));
		spoiled.setForeground(Color.WHITE);
		spoiled.addActionListener(this);
		cbuttons.add(spoiled);
		
		consumed = new JButton("Consumed Plate View");
		consumed.setActionCommand("c");
		consumed.addActionListener(this);
		consumed.setBackground(new Color(43, 95, 117));
		consumed.setForeground(Color.WHITE);
		cbuttons.add(consumed);
		
		add(cbuttons);
		display = new JLabel();
		//display.setVerticalAlignment(SwingConstants.TOP);
		//setLayout(new BorderLayout());
		add(display);
		display.setText(sb_html);
		display.setForeground(Color.WHITE);
	}

	private String makeScoreboardHTML() {
		sb_html = "<html>";
		if(scoreType.equals("Bt")) {
			sb_html += "<h2>Balance Scoreboard</h2>";

		// Create an array of all chefs and sort by balance.
			Chef[] opponent_chefs= game_model.getOpponentChefs();
			Chef[] chefs = new Chef[opponent_chefs.length+1];
			chefs[0] = game_model.getPlayerChef();
			for (int i=1; i<chefs.length; i++) {
				chefs[i] = opponent_chefs[i-1];
			}
			Arrays.sort(chefs, new HighToLowBalanceComparator());
		
			for (Chef c : chefs) {
				sb_html += c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 +") <br>";
			}
		}else if(scoreType.equals("St")) {
			sb_html += "<h2>Spoiled Plate Scoreboard</h2>";

			// Create an array of all chefs and sort by balance.
				Chef[] opponent_chefs= game_model.getOpponentChefs();
				Chef[] chefs = new Chef[opponent_chefs.length+1];
				chefs[0] = game_model.getPlayerChef();
				for (int i=1; i<chefs.length; i++) {
					chefs[i] = opponent_chefs[i-1];
				}
				 Arrays.sort(chefs, new Comparator<Chef>()
				    {
				        @Override
				        public int compare(Chef x, Chef y)
				        {
				            return ((int)x.getSpoiled()*100)-((int)y.getSpoiled()*100);
				        }
				    });
			
				for (Chef c : chefs) {
					sb_html += c.getName() + "( " + Math.round(c.getSpoiled()*100.0)/100.0 + ") <br>";
				}
		}else if(scoreType.equals("Ct")) {
			sb_html += "<h2>Consumed Plate Scoreboard</h2>";

			// Create an array of all chefs and sort by balance.
				Chef[] opponent_chefs= game_model.getOpponentChefs();
				Chef[] chefs = new Chef[opponent_chefs.length+1];
				chefs[0] = game_model.getPlayerChef();
				for (int i=1; i<chefs.length; i++) {
					chefs[i] = opponent_chefs[i-1];
				}
				 Arrays.sort(chefs, new Comparator<Chef>()
				    {
				        @Override
				        public int compare(Chef x, Chef y)
				        {
				            return ((int)y.getConsumed()*100)-((int)x.getConsumed()*100);
				        }
				    });
			
				for (Chef c : chefs) {
					sb_html += c.getName() + "( " + Math.round(c.getConsumed()*100.0)/100.0 +") <br>";
				}
		}
		return sb_html;
	}
	

	public void refresh() {
		display.setText(makeScoreboardHTML());		
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "b":
			scoreType = "Bt";
			display.setText(makeScoreboardHTML());
			break;
		case "s":
			scoreType = "St";
			display.setText(makeScoreboardHTML());
			break;
		case "c":
			scoreType = "Ct";
			display.setText(makeScoreboardHTML());
			break;
		}
		
	}
	 
}
