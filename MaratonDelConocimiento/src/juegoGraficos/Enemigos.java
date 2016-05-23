package juegoGraficos;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemigos extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String RUTA = "src/img/point";
	private String EXT = ".png";
	private int index = 1;
	private ImageIcon imageIcon;
	private static final int posy = 460;
	private int posx = 0;
	private boolean colision = false;
	private Random rand = new Random(System.nanoTime());
	private int numPregunta;

	public Enemigos(int numPregunta) {
		imageIcon = new ImageIcon(RUTA + index + EXT);
		this.setIcon(imageIcon);
		this.numPregunta = numPregunta;

	}

	public void crearEnimigo(int x) {
		this.setIcon(imageIcon);
		posx += x;

		this.setBounds(posx, posy, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
	}

	public void moverEnemigo() {
		posx -= 10;
		if (posx == 0) {
			this.setColision(true);
		}
		this.setBounds(posx, posy, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
	}

	public void animar() {
		int efecX = rand.nextInt(5 + 1);
		int efecY = rand.nextInt(6);

		index++;
		imageIcon = new ImageIcon(RUTA + index + EXT);
		this.setIcon(imageIcon);
		this.setBounds(posx + efecX, posy + efecY, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
		if (index == 9) {
			index = 1;
		}

	}

	public void castigo() {
		posx += 30;
		this.setBounds(posx, posy, imageIcon.getIconWidth(),
				imageIcon.getIconHeight());
	}

	public boolean getColision() {
		return this.colision;
	}

	public void setColision(boolean colision) {
		this.colision = colision;
	}

	public int getNumeroPregunta() {
		return this.numPregunta;
	}

}
