package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.SushiGameModel;

public class SushiGameView extends JPanel implements ActionListener, BeltObserver {

	private PlayerChefView player_chef_ui;
	private List<RotationRequestListener> rotation_request_listeners;
	private JLabel controller_messages;
	ScoreboardWidget scoreboard;
	
	public SushiGameView(SushiGameModel game_model) {
		setLayout(new BorderLayout());
		
		scoreboard = new ScoreboardWidget(game_model);
		add(scoreboard, BorderLayout.WEST);
				
		player_chef_ui = new PlayerChefView(game_model.getBelt().getSize());
		add(player_chef_ui, BorderLayout.EAST);
		
		BeltView belt_view = new BeltView(game_model.getBelt());
		add(belt_view, BorderLayout.CENTER);
		
		//JScrollPane scrollpane = new JScrollPane(belt_view);
		//scrollpane.setVerticalScrollBar(null);
		//scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//scrollpane.setPreferredSize(new Dimension(300,20*(game_model.getBelt().getSize())));
		//add(scrollpane, BorderLayout.CENTER);
		
		JPanel bottom_panel = new JPanel();
		bottom_panel.setLayout(new BorderLayout());
		bottom_panel.setBackground(Color.WHITE);
		
		JButton rotate_button = new JButton("Rotate");
		rotate_button.setActionCommand("rotate");
		rotate_button.addActionListener(this);
		rotate_button.setBackground(new Color(43, 95, 117));
		rotate_button.setForeground(Color.WHITE);
		
		bottom_panel.add(rotate_button, BorderLayout.WEST);
		
		controller_messages = new JLabel(" Controller messages.");
		controller_messages.setForeground(new Color(8, 25, 45));
		bottom_panel.add(controller_messages, BorderLayout.CENTER);
		
		add(bottom_panel, BorderLayout.SOUTH);
		
		rotation_request_listeners = new ArrayList<RotationRequestListener>();
		
		game_model.getBelt().registerBeltObserver(this);
	}
	
	public void registerPlayerChefListener(ChefViewListener cl) {
		player_chef_ui.registerChefListener(cl);
	}
	
	public void registerRotationRequestListener(RotationRequestListener rrl) {
		rotation_request_listeners.add(rrl);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rotate")) {
			for (RotationRequestListener rrl : rotation_request_listeners) {
				rrl.handleRotationRequest();
			}
		}
	}
	
	public void setControllerMessage(String message) {
		controller_messages.setText(message);
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			controller_messages.setText("");
		}
	}
	
	public void refreshScoreboard() {
		scoreboard.refresh();
	}
}
