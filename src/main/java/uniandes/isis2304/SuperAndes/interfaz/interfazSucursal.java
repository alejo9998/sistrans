package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import javafx.scene.layout.Border;

public class interfazSucursal extends JFrame 
{
	private bannerSucursal baner;
	
	private botoneraImplementos botonesIzquierda;
	
	private BotonesOrdenes botonesDerecha;
	
	private BotonProductos btonProductos;
	
	private InterfazAdministrador inter;
	
	public String id;
	
	public interfazSucursal(String pId)
	{
		inter = new InterfazAdministrador();
		id=pId;
		setTitle("Sucursal " + id );
		baner = new bannerSucursal();
		botonesIzquierda= new botoneraImplementos(id,this);
		btonProductos = new BotonProductos();
		botonesDerecha = new BotonesOrdenes();
		setSize(600,550);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		add(baner, BorderLayout.NORTH);
		add(botonesIzquierda,BorderLayout.WEST);
		add(botonesDerecha,BorderLayout.EAST);
		add(btonProductos,BorderLayout.CENTER);
	}
	
	public void verificar( String idSuc) throws Exception
	{
		if(idSuc.equalsIgnoreCase("2"))
		{
			interfazSucursal inter = new interfazSucursal(idSuc);
			
			inter.setVisible(true);	
		}
		else
		{
			throw new Exception("No existe una sucursal con ese Id");
		}
	}

	public void agregarBodega(String pTipo,String pVol,String pPeso)
	{
		inter.agregarBodega(pTipo, pVol, pPeso, id);
	}



}
