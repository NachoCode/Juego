package portadaGraficos;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccionesDeClick implements ActionListener {
	private String name;
	private CardLayout card;
	private JPanel panel;
	private JPanel panelACrear;

	public AccionesDeClick(String name, CardLayout card, JPanel panel,
			JPanel panelACrear) {
		this.name = name;
		this.card = card;
		this.panel = panel;
		this.panelACrear = panelACrear;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (name) {
		case "Portada": {
			card.show(this.panel, this.name);
			break;
		}

		case "Instrucciones":
			crearIntrucciones();
			card.show(this.panel, this.name);
			break;

		case "Acerca de": {
			crearAcercaDe();
			card.show(this.panel, this.name);
			break;
		}
		case "Salir": {
			System.exit(0);
		}
		default:
			break;
		}
		// if (!name.equals("Salir")) {
		// System.out.println(name);
		// card.show(this.panel, this.name);
		// } else {
		// System.exit(0);
		// }
	}

	private void crearIntrucciones() {
		JLabel imgLabel = new JLabel();
		imgLabel.setIcon(new ImageIcon("src/img/s1.png"));
		panelACrear.setForeground(Color.WHITE);
		panelACrear.setFont(new Font("Arial", Font.PLAIN, 30));
		panelACrear.add(new Label("-CORRE"));
		panelACrear.add(new Label("-Usando la direccional derecha"));
		panelACrear.add(imgLabel);
	}

	private void crearAcercaDe() {
		JPanel panel = new JPanel(new GridLayout(8, 1));
		panelACrear.add(new Label("-DESARROLLADORES"), BorderLayout.NORTH);
		panelACrear.setForeground(Color.orange);
		panelACrear.setFont(new Font("Arial", Font.PLAIN, 30));

		panel.setBackground(Color.BLACK);
		panel.setForeground(Color.WHITE);
		panel.setFont(new Font("Mono", Font.BOLD, 20));
		panel.add(new Label("-Jesus Alfredo Rios " + "EL KOMANDER"));
		panel.add(new Label("-Jesus Ignacio Castillo Barrios"));
		panel.add(new Label("-Intituto Tecnologico de Ciudad Valles"));
		panel.add(new Label("-Semestre: 2"));
		panel.add(new Label("-Grupo: A"));
		panel.add(new Label("Carrera: Ing.Sistemas"));
		panel.add(new Label("Materia: POO"));
		panelACrear.add(panel, BorderLayout.CENTER);

	}

}
