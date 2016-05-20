package portadaGraficos;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class AccionesDeClick implements ActionListener {
	private String name;
	private CardLayout card;
	private JPanel panel;

	public AccionesDeClick(String name, CardLayout card, JPanel panel) {
		this.name = name;
		this.card = card;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!name.equals("Salir")) {
			System.out.println(name);
			card.show(this.panel, this.name);
		} else {
			System.exit(0);
		}
	}

}
