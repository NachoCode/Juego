package portadaGraficos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class EfectoMouse implements MouseListener {
	private BotonesMenu botonesMenu;

	public EfectoMouse(BotonesMenu botonesMenu) {
		this.botonesMenu = botonesMenu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		botonesMenu.setIcon(new ImageIcon("src/img/icono.png"));

	}

	@Override
	public void mouseExited(MouseEvent e) {
		botonesMenu.setIcon(null);
	}

}
