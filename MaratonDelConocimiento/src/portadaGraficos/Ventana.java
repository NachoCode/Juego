package portadaGraficos;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import principal.Juego;

public class Ventana extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables
	private Container c;
	private JPanel[] panel;
	private BotonesMenu[] button;
	private static final int ANCHO = 800, ALTO = 600;
	// Menu
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem[] items;
	private Juego juego;

	//
	private CardLayout card;

	public Ventana() {
		iniciarComponentes();
		añadirComponentes();
		accionesComponentes();

		this.setTitle("MARATON");
		this.setSize(ANCHO, ALTO);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setJMenuBar(menuBar);
		this.setUndecorated(true);

	}

	private void iniciarComponentes() {
		// MENU BAR
		menuBar = new JMenuBar();
		menu = new JMenu("Opciones");
		menuBar.add(menu);

		items = new JMenuItem[2];
		items[0] = new JMenuItem("Inicio");
		items[1] = new JMenuItem("Salir");
		menu.add(items[0]);
		menu.addSeparator();
		menu.add(items[1]);

		// CONTENEDOR
		c = getContentPane();

		// PANELES
		// CardLAyout
		card = new CardLayout();
		panel = new JPanel[7];
		// Panel pricipal contendor
		panel[0] = new JPanel(card);

		// Panel 1
		panel[1] = new JPanel(null);
		panel[1].setBounds(0, 0, ANCHO, ALTO);
		panel[1].setBackground(Color.BLACK);

		// Panel 2
		panel[2] = new JPanel(new GridLayout(5, 1, 0, 10));
		panel[2].setBounds(200, 120, 400, 300);
		panel[2].setBackground(Color.BLACK);

		// Paneles dentro del card
		panel[3] = new JPanel();
		panel[3].setBackground(Color.BLUE);

		// Panel 4
		panel[4] = new JPanel(new GridLayout(5, 1));
		panel[4].setBackground(Color.DARK_GRAY);

		panel[5] = new JPanel();
		panel[5].setBackground(Color.LIGHT_GRAY);

		panel[6] = new JPanel(new BorderLayout());
		panel[6].setBackground(Color.BLACK);

		// Botones
		button = new BotonesMenu[4];
		for (int i = 0; i < button.length; i++) {
			button[i] = new BotonesMenu(i, Color.BLACK);
		}

	}

	private void añadirComponentes() {
		// panel[1].add(button[0]);
		for (int i = 0; i < button.length; i++) {
			panel[2].add(button[i]);
		}

		panel[1].add(panel[2]);

		panel[0].add(panel[1], "Portada");
		panel[0].add(panel[3], "Jugar");
		panel[0].add(panel[4], "Instrucciones");
		panel[0].add(panel[5], "Puntuaciones");
		panel[0].add(panel[6], "Acerca de");

		c.add(panel[0]);
	}

	private void accionesComponentes() {
		button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				juego = new Juego();

				juego.crearVentanaJuego();

				juego.empezar();
			}
		});

		// Acciones en la ventana
		button[1].addActionListener(new AccionesDeClick(button[1].getText(),
				card, panel[0], panel[4]));

		button[2].addActionListener(new AccionesDeClick(button[2].getText(),
				card, panel[0], panel[6]));

		button[3].addActionListener(new AccionesDeClick(button[3].getText(),
				card, panel[0], null));

		// Acciones de mouse
		button[0].addMouseListener(new EfectoMouse(button[0]));
		button[1].addMouseListener(new EfectoMouse(button[1]));
		button[2].addMouseListener(new EfectoMouse(button[2]));
		button[3].addMouseListener(new EfectoMouse(button[3]));

		// Eventos del menu
		items[0].addActionListener(new AccionesDeClick("Portada", card,
				panel[0], null));

		items[1].addActionListener(new AccionesDeClick(button[3].getText(),
				card, panel[0], null));

	}

	public boolean cerrado() {
		return true;
	}
}
