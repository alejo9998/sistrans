package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class bannerSucursal extends JPanel{

	public bannerSucursal () 
	{
		JLabel imagen = new JLabel(); 
		ImageIcon logo = new ImageIcon("./data/sucursales.png");
		setSize(573, 150);
		imagen.setIcon(logo);
		add(imagen);

	}
}
