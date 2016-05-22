package juegoGraficos;

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
	private JPanel panel, panelPregunta;
	private JRadioButton[] radioButton;
	private ButtonGroup buttonGroup;
	private JLabel labelPregunta;

	public DialogPreguntas() {
		this.setTitle("Pregunta");
		this.setModal(true);
		this.setSize(600, 200);
		this.setLocationRelativeTo(null);
		// this.setUndecorated(true);
		iniciarComponentes();
		añadirComponentes();

	}

	private void iniciarComponentes() {

		c = getContentPane();
		c.setLayout(new GridLayout(2, 1));

		panelPregunta = new JPanel(new GridLayout(1, 1));
		panel = new JPanel(new GridLayout(3, 1));

		buttonGroup = new ButtonGroup();
		radioButton = new JRadioButton[3];

		labelPregunta = new JLabel("");

		for (int i = 0; i < radioButton.length; i++) {
			String[] letra = { "A)", "B)", "C)" };
			radioButton[i] = new JRadioButton(letra[i]);
			buttonGroup.add(radioButton[i]);
		}

	}

	public void armarPregunta(String pregunta, int numeroPregunta) {
		labelPregunta.setText(numeroPregunta + ".- " + pregunta);

	}

	private void añadirComponentes() {
		for (int i = 0; i < radioButton.length; i++) {
			panel.add(radioButton[i]);
		}
		panelPregunta.add(labelPregunta);
		c.add(panelPregunta);
		c.add(panel);
	}

}
