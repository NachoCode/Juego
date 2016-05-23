package principal;

import juegoGraficos.Teclado;
import juegoGraficos.VentanaJuego;

public class Juego implements Runnable {
	private Thread hilo;
	private volatile boolean enJuego, enPausa;
	private static VentanaJuego ventanaJuego;
	private Teclado teclado;
	private volatile int pregunta = 0;

	public Juego() {
		enJuego = true;
		enPausa = false;
		hilo = new Thread(this);
		teclado = new Teclado();
	}

	@Override
	public void run() {
		while (enJuego) {
			try {
				synchronized (this) {
					if (enPausa) {
						System.out.println("En pausa");
						wait(3000);
						continuar();
						System.out.println("Resume");
					} else {

						actulizar();

						animacionesDeObjetos();
						// moverIgnorancia();
					}
				}
				// System.out.println("FUNCIONANDO");

				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void empezar() {
		hilo.start();
	}

	private synchronized void salir() {

		try {
			enJuego = false;
			hilo.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private synchronized void pausar() {
		enPausa = true;
	}

	private synchronized void continuar() {
		enPausa = false;

	}

	private synchronized void actulizar() throws InterruptedException {

		if (teclado.getDerecha()) {
			ventanaJuego.getMapa().mover();
			ventanaJuego.getPersonaje().animar();
			ventanaJuego.moverEnemigos();
			if (ventanaJuego.verificarColision()) {
				if (ventanaJuego.getdialogo().verificar_respuesta()) {

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

}
