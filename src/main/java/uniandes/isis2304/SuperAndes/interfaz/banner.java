package uniandes.isis2304.SuperAndes.interfaz;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class banner extends JPanel
{
	public banner()
	{
		JLabel imagen = new JLabel(); 
		ImageIcon logo = new ImageIcon("./data/logo.png");
		imagen.setIcon(logo);
		add(imagen);
	}

}
