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
	// Personaje
	private Personaje personaje;
	// Enemigos
	private Enemigos[] enemigos;
	// CAMBIAR ESTA VARIABLE
	private final static int CANT_ENEMIGOS = 6;

	// Menu
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem[] items;

	// Preguntas
	private DialogPreguntas dialogPreguntas;
	private static int CANT_PREGUNTAS;
	private ArrayList<Integer> listaNumeros;
	private Preguntas preguntas;
	private Random rand = new Random(System.nanoTime());

	// Aux
	private Teclado teclado;
	// Barra de ignorancia
	private BarraDeIgnorancia barraDeIgnorancia;

	public VentanaJuego(Teclado teclado) {
		// Variables
		CANT_PREGUNTAS = 25;

		this.teclado = teclado;
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
		// Instanciar Personaje
		personaje = new Personaje();
		// Barra de ignorancia
		barraDeIgnorancia = new BarraDeIgnorancia(3600);

		enemigos = new Enemigos[CANT_ENEMIGOS];

		// Preguntas
		preguntas = new Preguntas();
		listaNumeros = preguntas.hacerNumerosAleatorios();
		preguntas.crearFichero();
		preguntas.obtenerPreguntas();
		preguntas.escribirFichero();
		dialogPreguntas = new DialogPreguntas(teclado);

	}

	private void añadirComponentes() {
		crearEnemigos();
		panel.add(barraDeIgnorancia);
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

	public DialogPreguntas getdialogo() {
		return this.dialogPreguntas;
	}

	public BarraDeIgnorancia getBarraDeIgnorancia() {
		return this.barraDeIgnorancia;
	}

	public int getTurnos() {
		return VentanaJuego.CANT_ENEMIGOS;
	}

	public void crearEnemigos() {
		int separacionAprox = (3600 / CANT_ENEMIGOS);
		personaje.setDistanciaParaColisionar(separacionAprox);

		int separacion = 0;
		for (int i = 0; i < enemigos.length; i++) {

			// Separacion
			separacion += separacionAprox;

			// Enemigos personaje
			enemigos[i] = new Enemigos((i + 1));

			enemigos[i].crearEnimigo(separacion);

			panel.add(enemigos[i]);
		}
	}

	public void moverEnemigos() {
		for (int i = 0; i < enemigos.length; i++) {
			this.getEnemigos(i).moverEnemigo();
		}
	}

	public void moverEnemigosIgnorancia() {
		for (int i = 0; i < enemigos.length; i++) {

		}
	}

	public void animarEnemigos() {
		for (int i = 0; i < enemigos.length; i++) {
			this.enemigos[i].animar();
		}
	}

	public boolean verificarColision() {
		if (personaje.colisionar()) {
			armarDialog();
			dialogPreguntas.setVisible(true);
			return true;
		} else {
			return false;
		}

	}

	private void armarDialog() {
		int random = rand.nextInt(CANT_PREGUNTAS);
		int numeroAleatorio = listaNumeros.get(random);
		listaNumeros.remove(random);
		CANT_PREGUNTAS--;
		for (int i = 0; i < listaNumeros.size(); i++) {
			System.out.println("index " + i + ": " + listaNumeros.get(i));
		}

		System.out.println("Numero eliminado: " + numeroAleatorio);

		int aux = 0;
		ArrayList<String> tempOpciones = preguntas
				.ExtraerOpciones(numeroAleatorio);

		dialogPreguntas.setPreguntaAContestar(numeroAleatorio);

		for (int i = 0; i < enemigos.length && aux == 0; i++) {
			if (enemigos[i].getColision()) {

				enemigos[i].setColision(false);

				dialogPreguntas.armarPregunta(
						preguntas.ExtraerPregunta(numeroAleatorio),
						enemigos[i].getNumeroPregunta());

				dialogPreguntas.armarOpciones(tempOpciones);

				aux = 1;

			}
		}
	}

}
