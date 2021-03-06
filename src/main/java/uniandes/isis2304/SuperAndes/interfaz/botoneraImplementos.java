package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uniandes.isis2304.SuperAndes.negocio.Carrito;

public class botoneraImplementos extends JPanel implements ActionListener
{
	private JButton agregarCliente;
	private final static String AA_CLIENTE="AGREGARCLI";
	
	private JButton agregarBodega;
	private final static String AA_BODEGA="AGREGARBOD";
	
	private JButton agregarEstante;
	private final static String AA_ESTANTE="AGREGARESTANTE";
	
	private JButton agregarVenta;
	private final static String AA_VENTA="AGREGARVENTA";
	
	private JButton agregarCarrito;
	private final static String AA_CARRITO="AGREGARCARRITO";
	
	private DialogoBodega db;
	private DialogoCliente dc;	
	private DialogoEstante de;
	private DialogoCompra dv;
	
	private interfazSucursal inter;
	
	public botoneraImplementos(String pId,interfazSucursal r)
	{
		
		inter=r;
		
		setBorder(new TitledBorder("Implementos"));

		setLayout(new GridLayout(5, 1, 30, 30));
		
		agregarBodega= new JButton("Agregar Bodega");
		agregarBodega.addActionListener(this);
		agregarBodega.setActionCommand(AA_BODEGA);
		
		agregarCliente = new JButton("Agregar Cliente");
		agregarCliente.addActionListener(this);
		agregarCliente.setActionCommand(AA_CLIENTE);
		
		agregarEstante = new JButton("Agregar Estante");
		agregarEstante.addActionListener(this);
		agregarEstante.setActionCommand(AA_ESTANTE);
		
		agregarVenta = new JButton("Agregar Venta");
		agregarVenta.addActionListener(this);
		agregarVenta.setActionCommand(AA_VENTA);
		
		agregarCarrito = new JButton("Agregar Carrito");
		agregarCarrito.addActionListener(this);
		agregarCarrito.setActionCommand(AA_CARRITO);
		
		add(agregarCliente);
		add(agregarBodega);
		add(agregarEstante);
		add(agregarVenta);
		add(agregarCarrito);
	}
	public Object[] darProductos()
	{
		return inter.darInterfazAdministrados().darPorudctos();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String d = e.getActionCommand();
		
		if(d.equals(AA_BODEGA))
		{
			db = new DialogoBodega(inter);
			db.setVisible(true);	
		}
		else if(d.equals(AA_CLIENTE))
		{
			dc=new DialogoCliente(inter);
			dc.setVisible(true);
		}
		else if (d.equals(AA_ESTANTE))
		{
			de = new DialogoEstante(inter);
			de.setVisible(true);
		}
		else if (d.equals(AA_VENTA))
		{
			
			dv = new DialogoCompra(inter);
			dv.setVisible(true);
			
		}
		else if(d.equals(AA_CARRITO))
		{
			inter.agergarCarrito();
			inter.actualizar();
		}
		
		
	}
}
