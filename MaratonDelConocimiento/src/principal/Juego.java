package principal;

import javax.swing.JOptionPane;

import juegoGraficos.Teclado;
import juegoGraficos.VentanaJuego;
import portadaGraficos.Ventana;

public class Juego implements Runnable {
	private Thread hilo;
	// private volatile boolean enJuego, enPausa;
	private static VentanaJuego ventanaJuego;
	private Teclado teclado;
	private static int turnos, preguntasIncorrectas;
	private long tiempoInicial, tiempoFinal;
	private final static int velocidadDeJuego = 50;
	private Ventana ventana;
	private static String nombre;

	public Juego(String nombre) {
		turnos = 0;
		preguntasIncorrectas = 0;
		tiempoInicial = 0;
		tiempoFinal = 0;
		Juego.nombre = nombre;
		hilo = new Thread(this);
		teclado = new Teclado();
	}

	@Override
	public void run() {
		while (turnos < ventanaJuego.getTurnos()) {
			try {
				synchronized (this) {
					actulizar();
					animacionesDeObjetos();

				}
				// System.out.println("FUNCIONANDO");

				Thread.sleep(velocidadDeJuego);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		tiempoFinal = System.nanoTime();
		int nivelDeIgnorancia = (preguntasIncorrectas * 100)
				/ ventanaJuego.getTurnos();

		String[] colores = { "#008000", "#FFD700", "#FF0000" };
		String[] puntajes = { "EXCELENTE", "REGULAR", "MALO" };
		int tiempo = (int) ((tiempoFinal - tiempoInicial) / 1000000000);
		int respuestasCorrectas = (turnos - preguntasIncorrectas);
		int i = 0;

		if (preguntasIncorrectas <= 1) {
			i = 0;
		} else if (preguntasIncorrectas >= 3 && preguntasIncorrectas <= 5) {
			i = 1;
		} else {
			i = 2;
		}

		String calisString = "<html>" + "<p>" + nombre
				+ " gracias por jugar</p>" + "<br>" + "<p>Tiempo:" + tiempo
				+ "</p>" + "<br>" + "<p>Respuestas correctas:"
				+ respuestasCorrectas + "</p>" + "<br>"
				+ "<p>Respuestas incorrectas:" + preguntasIncorrectas + "</p>"
				+ "<br>" + "<p>Porcentaje de ignorancia: " + nivelDeIgnorancia
				+ "%</p>" + "<br>" + "<p>Eres:" + "<span>" + "<font"
				+ " color=" + colores[i] + ">" + puntajes[i]
				+ "</font></span></p>" + "</html>";

		String[] opciones = { "Inicio", "Salir" };

		int op = JOptionPane.showOptionDialog(null, calisString, "Poup",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				opciones, opciones[0]);

		redireccionar(op);

	}

	public void empezar() {
		tiempoInicial = System.nanoTime();
		hilo.start();
	}

	private synchronized void salir() {

		try {
			hilo.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private synchronized void actulizar() {
		if (teclado.getDerecha()) {
			ventanaJuego.getMapa().mover();
			ventanaJuego.getPersonaje().animar();
			ventanaJuego.moverEnemigos();
			if (ventanaJuego.verificarColision()) {
				turnos++;
				if (!ventanaJuego.getdialogo().verificar_respuesta()) {
					preguntasIncorrectas++;
					int aumentar = ventanaJuego.getPersonaje().getDistancia();

					ventanaJuego.getBarraDeIgnorancia().aumentarIgnorancia(
							aumentar * preguntasIncorrectas);
				}
			}

		} else {
			ventanaJuego.getPersonaje().reposo();
			teclado.setDerecha(false);
		}

	}

	private synchronized void animacionesDeObjetos() {
		ventanaJuego.animarEnemigos();
	}

	public void crearVentanaJuego() {
		ventanaJuego = new VentanaJuego(this.teclado);
		ventanaJuego.setVisible(true);

	}

	private void llamarPortada() {
		ventana = new Ventana();
		ventana.setVisible(true);
	}

	private void redireccionar(int op) {
		switch (op) {
		case 0:
			System.out.println("INICIO");
			ventanaJuego.dispose();
			llamarPortada();
			this.salir();
			break;
		case 1: {
			System.out.println("Salir");
			ventanaJuego.dispose();
			break;
		}
		}
	}

}
