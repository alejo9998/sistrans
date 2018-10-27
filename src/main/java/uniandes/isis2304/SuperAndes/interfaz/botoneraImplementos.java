package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
	

	private DialogoBodega db;
	private DialogoCliente dc;	
	
	private interfazSucursal inter;
	
	public botoneraImplementos(String pId,interfazSucursal r)
	{
		
		inter=r;
		
		setBorder(new TitledBorder("Implementos"));

		setLayout(new GridLayout(4, 1, 30, 30));
		
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
		
		add(agregarCliente);
		add(agregarBodega);
		add(agregarEstante);
		add(agregarVenta);
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
		// TODO Auto-generated method stub
		
	}
}
