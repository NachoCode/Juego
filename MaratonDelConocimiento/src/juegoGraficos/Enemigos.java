package juegoGraficos;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemigos extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String RUTA = "src/img/point1.png";
	private int index;
	private ImageIcon imageIcon;
	private static final int posy = 450;
	private int posx = 0;
	private boolean colision = false;
	private static int SEPARACION;

	public Enemigos(int separacion) {
		imageIcon = new ImageIcon(RUTA);
		this.setIcon(imageIcon);
		Enemigos.SEPARACION = separacion;
		// this.setBounds(posx, posy, imageIcon.getIconWidth(),
		// imageIcon.getIconHeight());

	}

	public void crearEnimigo(int x) {
		this.setIcon(imageIcon);
		posx += x;

		this.setBounds(posx, posy, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
	}

	public void moverEnemigo() {
		posx -= 10;
		this.setBounds(posx, posy, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
	}

	public void animar() {
		index++;
		imageIcon = new ImageIcon("src/img/point" + index + ".png");
		this.setIcon(imageIcon);
		this.setBounds(posx, posy, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
		if (index == 9) {
			index = 1;
		}

	}

	public int getSeparacion() {
		return Enemigos.SEPARACION;
	}

	public void setSeparacion(int separacionArpox) {
		Enemigos.SEPARACION = separacionArpox;
	}

	public boolean getColision() {
		return this.colision;
	}

	public void setColision(boolean colision) {
		this.colision = colision;
	}

}
