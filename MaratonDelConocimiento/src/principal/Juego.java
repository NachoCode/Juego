package principal;

import juegoGraficos.Teclado;
import juegoGraficos.VentanaJuego;

public class Juego implements Runnable {
	private Thread hilo;
	private volatile boolean enJuego, enPausa;
	private static VentanaJuego ventanaJuego;
	private Teclado teclado;

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
					}
				}
				// System.out.println("FUNCIONANDO");

				Thread.sleep(40);
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

	private synchronized void actulizar() {
		teclado.actualizar();

		if (teclado.derecha) {
			ventanaJuego.getMapa().mover();
			ventanaJuego.getPersonaje().animar();
			ventanaJuego.moverEnemigos();
			System.out.println(ventanaJuego.getEnemigos(1).getX());
			ventanaJuego.verificarColision();
		} else {
			ventanaJuego.getPersonaje().reposo();
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
