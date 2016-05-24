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

	public Juego() {
		turnos = 0;
		preguntasIncorrectas = 0;
		tiempoInicial = 0;
		tiempoFinal = 0;
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
		String texto = "Gracias por jugar!!" + "\nTiempo de maraton: "
				+ (tiempoFinal - tiempoInicial) / 1000000000 + "seg"
				+ "\nRespuestas correctas: " + (turnos - preguntasIncorrectas)
				+ "\nRespuestas Incorrectas: " + preguntasIncorrectas;

		String[] opciones = { "Inicio", "Salir" };

		int op = JOptionPane.showOptionDialog(null, texto, "Poup",
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

	private void reintentar() {
		ventanaJuego = new VentanaJuego(this.teclado);
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
