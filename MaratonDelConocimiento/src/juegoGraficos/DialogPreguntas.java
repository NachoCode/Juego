package juegoGraficos;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private String[] respuestas = { "A", "A", "B", "C", "A", "A", "B", "B",
			"C", "A", "C", "C", "B", "A", "B", "B", "B", "A", "C", "C", "A",
			"B", "B", "A", "C", "C" };

	private int num_pregunta = 0;
	private boolean resultado_pregunta = false;
	private Teclado teclado;
	private final int NUM_PREGUNTAS;
	private int turnos = 0;

	public DialogPreguntas(Teclado teclado) {
		NUM_PREGUNTAS = 10;
		turnos = 0;
		this.setTitle("Pregunta");
		this.setModal(true);
		this.setSize(600, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		// this.setUndecorated(true);
		this.teclado = teclado;
		iniciarComponentes();
		añadirComponentes();
		accionesComponentes();

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

	public void armarOpciones(ArrayList<String> listOp) {
		String[] letras = { "A", "B", "C" };
		for (int i = 0; i < radioButton.length; i++) {
			radioButton[i].setName(letras[i]);
			radioButton[i].setText(letras[i] + ") " + listOp.get(i));
		}
	}

	public void setPreguntaAContestar(int num_pregunta) {
		this.num_pregunta = num_pregunta;
	}

	private void añadirComponentes() {
		for (int i = 0; i < radioButton.length; i++) {
			panel.add(radioButton[i]);
		}
		panelPregunta.add(labelPregunta);
		c.add(panelPregunta);
		c.add(panel);
	}

	public boolean verificar_respuesta() {
		return this.resultado_pregunta;
	}

	public void poner_falso(boolean res) {
		this.resultado_pregunta = res;
	}

	private boolean darMensaje() {
		if (turnos == NUM_PREGUNTAS) {
			return true;
		} else {
			return false;
		}
	}

	private void accionesComponentes() {
		radioButton[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioButton[0].getName().equals(respuestas[num_pregunta])) {
					resultado_pregunta = true;
					teclado.setDerecha(false);

					if (!darMensaje()) {
						JOptionPane.showMessageDialog(null, "CORRECTO");
					}

					dispose();

				} else {

					if (!darMensaje()) {
						JOptionPane.showMessageDialog(null, "INCORRECTO");
					}
					teclado.setDerecha(false);
					resultado_pregunta = false;
					dispose();

				}

			}
		});
		radioButton[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioButton[1].getName().equals(respuestas[num_pregunta])) {
					resultado_pregunta = true;
					teclado.setDerecha(false);
					if (!darMensaje()) {
						JOptionPane.showMessageDialog(null, "CORRECTO");
					}
					dispose();
				} else {
					teclado.setDerecha(false);
					resultado_pregunta = false;
					JOptionPane.showMessageDialog(null, "INCORRECTO");
					dispose();
				}

			}
		});

		radioButton[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioButton[2].getName().equals(respuestas[num_pregunta])) {
					resultado_pregunta = true;
					teclado.setDerecha(false);
					if (!darMensaje()) {
						JOptionPane.showMessageDialog(null, "CORRECTO");
					}
					dispose();
				} else {
					teclado.setDerecha(false);
					resultado_pregunta = false;

					if (!darMensaje()) {
						JOptionPane.showMessageDialog(null, "INCORRECTO");
					}

					dispose();
				}

			}
		});

	}
}
