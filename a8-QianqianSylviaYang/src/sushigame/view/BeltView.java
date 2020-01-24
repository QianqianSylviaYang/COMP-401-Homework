package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private PlateViewWidget[] belt_labels;

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		belt_labels = new PlateViewWidget[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			PlateViewWidget plabel = new PlateViewWidget(this,belt,belt.getPlateAtPosition(i),i);
			plabel.setMinimumSize(new Dimension(300, 20));
			plabel.setPreferredSize(new Dimension(300, 20));
			plabel.setOpaque(true);
			plabel.setBackground(Color.WHITE);
			add(plabel);
			belt_labels[i] = plabel;
		}
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			PlateViewWidget plabel = belt_labels[i];
			plabel.setPlateAndIndex(belt.getPlateAtPosition(i), i);
			plabel.setActionCommand("Click");
			if (p == null) {
				plabel.setText(" ");
				plabel.setBackground(Color.WHITE);
			} else {
				plabel.setText("Click To See Details");
				plabel.setForeground(Color.WHITE);
				switch (p.getColor()) {
				case RED:
					plabel.setBackground(new Color(203, 64, 66)); break;
				case GREEN:
					plabel.setBackground(new Color(27, 129, 62)); break;
				case BLUE:
					plabel.setBackground(new Color(13, 86, 97)); break;
				case GOLD:
					plabel.setBackground(new Color(255, 177, 27)); break;
				}
				;
			}
		}
	}
}