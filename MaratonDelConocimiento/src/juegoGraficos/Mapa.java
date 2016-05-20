package juegoGraficos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Mapa extends JLabel {

	private static final String RUTA = "src/img/mapa.gif";
	private static final int ANCHO = 5000, ALTO = 600;
	private int x;

	public Mapa() {
		this.setText("");
		this.setBounds(0, 0, ANCHO, ALTO);
		this.setIcon(new ImageIcon(RUTA));
		x = 0;

	}

	public void mover() {
		x -= 10;
		this.setBounds(x, 0, ANCHO, ALTO);
	}
}
