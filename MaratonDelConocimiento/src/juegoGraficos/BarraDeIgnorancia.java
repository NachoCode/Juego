package juegoGraficos;

import java.awt.Color;

import javax.swing.JProgressBar;

public class BarraDeIgnorancia extends JProgressBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BarraDeIgnorancia(int max) {
		this.setMaximum(max);
		this.setStringPainted(true);
		this.setString("NIVEL DE IGNORANCIA");
		this.setForeground(Color.RED);
		this.setBounds(0, 0, 400, 30);
	}

	public void aumentarIgnorancia(int aumentar) {
		this.setValue(aumentar);
	}

}
