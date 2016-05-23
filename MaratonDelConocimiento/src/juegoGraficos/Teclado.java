package juegoGraficos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {
	public boolean derecha;

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 39) {
			derecha = true;

		} else {
			derecha = false;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		derecha = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean getDerecha() {
		return this.derecha;
	}

	public void setDerecha(boolean derecha) {
		this.derecha = derecha;
	}

}
