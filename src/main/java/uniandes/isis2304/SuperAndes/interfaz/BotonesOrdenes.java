package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class BotonesOrdenes extends JPanel implements ActionListener
{
	private JButton ordenarPedido;
	private JButton agregarPromocion;
	private JButton finalizarPromocion;
	private JButton registrarPedido;
	
	private final static String REGPEDIDO="REGISTRARPEDIDO";
	private final static String AGREGARPROM="AGREGARPROMOCION";
	private final static String FINALIZARPROM="FINALIZARPROM";
	private final static String ORDENARPEDIDO="ORDENARPEDIDO";
	
	private DialogoPromocion dp;
	private DialogoOrdenPedido dop;
	
	private interfazSucursal inter;
	
	public BotonesOrdenes(interfazSucursal inte) 
	{
		inter=inte;
		
		setBorder(new TitledBorder("Ordenes"));
		setLayout(new GridLayout( 4, 1, 30 ,30));
	
		ordenarPedido = new JButton("Ordenar pedido");
		ordenarPedido.addActionListener(this);
		ordenarPedido.setActionCommand(ORDENARPEDIDO);
		
		agregarPromocion = new JButton("Agregar promocion");
		agregarPromocion.addActionListener(this);
		agregarPromocion.setActionCommand(AGREGARPROM);
		
		finalizarPromocion = new JButton("Finalizar promocion");
		finalizarPromocion.setActionCommand(FINALIZARPROM);
		finalizarPromocion.addActionListener(this);
		
		registrarPedido = new JButton("Registrar llegada");
		registrarPedido.addActionListener(this);
		registrarPedido.setActionCommand(REGPEDIDO);
		
		
		add(ordenarPedido);
		add(agregarPromocion);
		add(finalizarPromocion);
		add(registrarPedido);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(AGREGARPROM))
		{
			dp= new DialogoPromocion(inter);
			dp.setVisible(true);
		}
		else if (e.getActionCommand().equals(ORDENARPEDIDO))
		{
			dop= new DialogoOrdenPedido(inter);
			dop.setVisible(true);
		}
		
	}

}
