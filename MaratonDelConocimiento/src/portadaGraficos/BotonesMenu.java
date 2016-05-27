package portadaGraficos;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class BotonesMenu extends JButton {
	private String[] nombre = { "Jugar", "Instrucciones", "Acerca de", "Salir" };

	public BotonesMenu(int index, Color defecto) {
		this.setBackground(defecto);
		this.setText(nombre[index]);
		this.setForeground(Color.WHITE);
		this.setBorderPainted(false);
		this.setFont(new Font("Mono", Font.PLAIN, 20));
	}

}
