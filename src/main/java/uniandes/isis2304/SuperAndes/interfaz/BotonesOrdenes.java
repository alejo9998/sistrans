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
	
	public BotonesOrdenes() 
	{
		setBorder(new TitledBorder("Ordenes"));
		setLayout(new GridLayout( 4, 1, 30 ,30));
	
		ordenarPedido = new JButton("Ordenar pedido");
		
		agregarPromocion = new JButton("Agregar promocion");
		
		finalizarPromocion = new JButton("Finalizar promocion");
		
		registrarPedido = new JButton("Registrar llegada");
		
		add(ordenarPedido);
		add(agregarPromocion);
		add(finalizarPromocion);
		add(registrarPedido);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
