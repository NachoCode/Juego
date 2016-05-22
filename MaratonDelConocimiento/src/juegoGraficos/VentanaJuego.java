package juegoGraficos;

import java.awt.Container;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	private final static int CANT_CORREGIR_DIST = 0;

	// Menu
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem[] items;

	// Preguntas
	private ArrayList<Integer> listaNumeros;
	private Preguntas preguntas;
	private Random rand = new Random(System.nanoTime());

	public VentanaJuego(Teclado teclado) {

		iniciarComponentes();
		añadirComponentes();
		this.setTitle("Maraton");
		this.setSize(ANCHO, ALTO);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.addKeyListener(teclado);
		this.setJMenuBar(menuBar);

	}

	private void iniciarComponentes() {

		// MENU
		menuBar = new JMenuBar();
		menu = new JMenu("Opciones");
		menuBar.add(menu);

		items = new JMenuItem[2];
		items[0] = new JMenuItem("Inicio");
		items[1] = new JMenuItem("Salir");
		menu.add(items[0]);
		menu.addSeparator();
		menu.add(items[1]);

		// Grafico

		c = getContentPane();
		panel = new JPanel(null);
		panel.setBounds(0, 0, 1250, 600);
		mapa = new Mapa();
		personaje = new Personaje();
		enemigos = new Enemigos[CANT_ENEMIGOS];

		// Preguntas
		preguntas = new Preguntas();
		listaNumeros = preguntas.hacerNumerosAleatorios();
		preguntas.crearFichero();
		preguntas.obtenerPreguntas();
		preguntas.escribirFichero();
		dialogPreguntas = new DialogPreguntas();

	}

	private void añadirComponentes() {
		crearEnemigos();
		panel.add(personaje);
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
		int separacionAprox = (3600 / CANT_ENEMIGOS) + CANT_CORREGIR_DIST;
		personaje.setDistanciaParaColisionar(separacionAprox);

		int separacion = 0;
		for (int i = 0; i < enemigos.length; i++) {
			enemigos[i] = new Enemigos((i + 1));
			separacion += separacionAprox;
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
			this.enemigos[i].animar();
		}
	}

	public void verificarColision() {
		if (personaje.colisionar()) {
			armarDialog();
			dialogPreguntas.setVisible(true);
		}

	}

	private void armarDialog() {
		int numeroAleatorio = rand.nextInt(10);
		int aux = 0;
		for (int i = 0; i < enemigos.length && aux == 0; i++) {
			if (enemigos[i].getColision()) {
				aux = 1;
				enemigos[i].setColision(false);
				System.out.println("Numero de pregunta: "
						+ enemigos[i].getNumeroPregunta()
						+ "\nNumero Aleatorio: " + numeroAleatorio);
				dialogPreguntas.armarPregunta(
						preguntas.leerFichero(numeroAleatorio),
						enemigos[i].getNumeroPregunta());
			}
		}
	}
}
