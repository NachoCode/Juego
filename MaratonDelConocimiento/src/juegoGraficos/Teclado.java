package juegoGraficos;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {
	private static final int NUM_TECLAS = 120;
	private boolean[] teclas = new boolean[NUM_TECLAS];
	public boolean derecha, izquierda;

	public void actualizar() {
		derecha = teclas[KeyEvent.VK_RIGHT];
		izquierda = teclas[KeyEvent.VK_LEFT];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
