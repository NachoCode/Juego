package portadaGraficos;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class BotonesMenu extends JButton {
	private String[] nombre = { "Jugar", "Instrucciones", "Acerca de", "Salir" };

	public BotonesMenu(int index) {
		this.setBackground(Color.BLACK);
		this.setBorder(null);
		this.setText(nombre[index]);
		this.setForeground(Color.white);
		this.setFont(new Font("Mono", Font.PLAIN, 20));
	}

}
