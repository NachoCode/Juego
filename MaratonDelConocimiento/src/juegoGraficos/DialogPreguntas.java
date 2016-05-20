package juegoGraficos;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DialogPreguntas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container c;
	private JPanel panel;
	private JRadioButton[] radioButton;
	private ButtonGroup buttonGroup;
	private JLabel label;

	public DialogPreguntas() {
		this.setTitle("Pregunta");
		this.setModal(true);
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		// this.setUndecorated(true);
		iniciarComponentes();
		añadirComponentes();

	}

	private void iniciarComponentes() {
		c = getContentPane();
		panel = new JPanel(new GridLayout(1, 3));
		panel.setBackground(Color.BLACK);

		buttonGroup = new ButtonGroup();
		radioButton = new JRadioButton[3];

		label = new JLabel("");

		for (int i = 0; i < radioButton.length; i++) {
			radioButton[i] = new JRadioButton(i + "BOTONES");
			buttonGroup.add(radioButton[i]);
		}

	}

	private void añadirComponentes() {
		for (int i = 0; i < radioButton.length; i++) {
			panel.add(radioButton[i]);
		}
		c.add(panel);
	}

}
