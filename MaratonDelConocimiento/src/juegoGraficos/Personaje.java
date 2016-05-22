package juegoGraficos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Personaje extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String RUTA = "src/img/s";
	private static final String EXT = ".png";
	private ImageIcon imageIcon;
	private int index;
	private final int POSY = 430;
	private int POSX = 0;
	private static int separacion = 0;
	private int dist_colision = 0;

	public Personaje() {
		this.setText("");
		index = 1;
		imageIcon = new ImageIcon(RUTA + index + EXT);
		this.setIcon(imageIcon);
		this.setBounds(POSX, POSY, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
	}

	public void animar() {
		imageIcon = new ImageIcon(RUTA + (++index) + EXT);
		this.setIcon(imageIcon);
		this.setBounds(POSX, POSY, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
		if (this.index == 9) {
			this.index = 1;
		}
	}

	public void reposo() {
		this.index = 1;
		imageIcon = new ImageIcon(RUTA + index + EXT);
		this.setIcon(imageIcon);
		this.setBounds(POSX, POSY, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
	}

	public boolean colisionar() {
		dist_colision -= 10;
		// System.out.println(dist_colision);
		if (dist_colision == 0) {
			dist_colision = separacion;
			return true;
		} else {
			return false;
		}

	}

	public void setDistanciaParaColisionar(int separacion) {
		Personaje.separacion = separacion;
		this.dist_colision = separacion;
	}
}
