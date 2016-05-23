package portadaGraficos;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	private JButton[] button;
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
		panel[1].setBackground(Color.black);

		// Panel 2
		panel[2] = new JPanel(new GridLayout(5, 1, 0, 10));
		panel[2].setBounds(200, 120, 400, 300);
		panel[2].setBackground(Color.BLACK);

		// Paneles dentro del card
		panel[3] = new JPanel();
		panel[3].setBackground(Color.BLUE);

		// Panel 4
		panel[4] = new JPanel();
		panel[4].setBackground(Color.DARK_GRAY);

		panel[5] = new JPanel();
		panel[5].setBackground(Color.LIGHT_GRAY);

		panel[6] = new JPanel();
		panel[6].setBackground(Color.ORANGE);

		// Botones
		button = new JButton[5];
		button[0] = new JButton("Jugar");
		button[1] = new JButton("Instrucciones");
		button[2] = new JButton("Puntuaciones");
		button[3] = new JButton("Acerca de");
		button[4] = new JButton("Salir");

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
				card, panel[0]));

		button[2].addActionListener(new AccionesDeClick(button[2].getText(),
				card, panel[0]));

		button[3].addActionListener(new AccionesDeClick(button[3].getText(),
				card, panel[0]));

		button[4].addActionListener(new AccionesDeClick(button[4].getText(),
				card, panel[0]));

		// Eventos del menu
		items[0].addActionListener(new AccionesDeClick("Portada", card,
				panel[0]));

		items[1].addActionListener(new AccionesDeClick(button[3].getText(),
				card, panel[0]));

	}

	public boolean cerrado() {
		return true;
	}
}
