package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;

import uniandes.isis2304.SuperAndes.persistencia.PersistenciaSuperAndes;

public class InterfazApp extends JFrame
{
	private PanelDatos panelDatos;
	
	private banner imagen;
	
	private MenuBarra menu;
	
	private PersistenciaSuperAndes presistencia;
	
	public InterfazApp()
	{
		
		setSize(800,430);
		setLocationRelativeTo(null);
		setTitle("SuperAndes");
		setLayout( new BorderLayout());
		
		imagen = new banner();
		panelDatos = new PanelDatos();
		
		add(panelDatos, BorderLayout.SOUTH);
		add(imagen,BorderLayout.NORTH);
		
		menu = new MenuBarra(this);
		this.setJMenuBar(menu);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfazApp inter = new InterfazApp();
		inter.setVisible(true);
	}

}
