package juegoGraficos;

import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import preguntas.Preguntas;

public class VentanaJuego extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int ANCHO = 1250, ALTO = 600;
	private Container c;
	private JPanel panel;
	private Mapa mapa;
	private Personaje personaje;
	private Enemigos[] enemigos;
	private DialogPreguntas dialogPreguntas;
	private final static int CANT_ENEMIGOS = 10;
	private Preguntas preguntas;
	private ArrayList<Integer> lista;
	private Random random = new Random(System.nanoTime());

	public VentanaJuego(Teclado teclado) {
		this.setTitle("Maraton");
		this.setSize(ANCHO, ALTO);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.addKeyListener(teclado);

		iniciarComponentes();
		añadirComponentes();
		this.crearFichero();

	}

	private void iniciarComponentes() {
		c = getContentPane();
		panel = new JPanel(null);
		panel.setBounds(0, 0, 1250, 600);
		mapa = new Mapa();
		personaje = new Personaje();
		enemigos = new Enemigos[CANT_ENEMIGOS];
		dialogPreguntas = new DialogPreguntas();
		preguntas = new Preguntas();
		preguntas.hacerNumerosAleatorios();
		lista = preguntas.getLista();

	}

	private void añadirComponentes() {
		panel.add(personaje);
		crearEnemigos();
		panel.add(mapa);
		c.add(panel);

	}

	public Mapa getMapa() {
		return this.mapa;
	}

	public Personaje getPersonaje() {
		return this.personaje;
	}

	public Enemigos getEnemigos(int i) {
		return this.enemigos[i];
	}

	public void crearEnemigos() {
		int separacionAprox = 3600 / CANT_ENEMIGOS;
		System.out.println(separacionAprox);

		int separacion = 0;
		for (int i = 0; i < enemigos.length; i++) {
			enemigos[i] = new Enemigos(separacionAprox);
			separacion += enemigos[i].getSeparacion();
			enemigos[i].crearEnimigo(separacion);

			System.out
					.println("Enemigo " + (i + 1) + ": " + enemigos[i].getX());
			panel.add(enemigos[i]);
		}
	}

	public void moverEnemigos() {
		for (int i = 0; i < enemigos.length; i++) {
			this.getEnemigos(i).moverEnemigo();
		}
	}

	public void animarEnemigos() {
		for (int i = 0; i < enemigos.length; i++) {
			this.getEnemigos(i).animar();
		}
	}

	public boolean verificarColision() {

		for (int i = 0; i < enemigos.length; i++) {
			if (enemigos[i].getColision()) {
				enemigos[i].setColision(false);
				dialogPreguntas.setVisible(true);
				return true;
			}

		}
		return false;
	}

	public void crearFichero() {
		try {
			preguntas.crearFichero();
			// preguntas.escribirFichero();
			preguntas.leerFichero(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
