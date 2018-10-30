package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uniandes.isis2304.SuperAndes.negocio.Cliente;

public class DialogoCompra extends JDialog implements ActionListener
{
	/**
	 * Clase principal de la interfaz
	 */
	private interfazSucursal inte;

	/**
	 * cantidad
	 */
	private JTextField cantidad;

	/**
	 * identificacion producto sucursal
	 */
	private JTextField productoSucursal;

	/**
	 * identificacion del cliente
	 */
	private JTextField cliente;
	/**
	 * total pagado
	 */
	private JTextField totalPagado;

	/**
	 * identificacion de la factura
	 */
	private JTextField factura;

	/**
	 * Boton aceptar
	 */
	private JButton aceptar;
	/**
	 * constante de aceptar
	 */
	private final static String ACEPTAR="ACEPTAR";

	/**
	 * boton cancelar
	 */
	private JButton cancelar;
	/**
	 * constante de aceptar
	 */
	private final static String CANCELAR="CANCELAR";

	private JComboBox<String> clientes;
	
	private String [] items;
	
	/**
	 * Dialogo de comprar
	 * @param inter interfaz
	 */
	public DialogoCompra(interfazSucursal inter) 
	{
		inte= inter;
		setTitle("Agregar Compra");
		setLayout(new BorderLayout());
		setSize(200,150);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(3, 1));

		JPanel campos = new JPanel( );
		campos.setLayout(new GridLayout(1, 2));
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
		
		campos.add(aceptar);
		campos.add(cancelar);
		
		JLabel aux = new JLabel("Seleccione Cliente");
		aux.setHorizontalAlignment(JLabel.CENTER);
		datos();
		clientes = new JComboBox<>(items);
		add(aux);
		add(clientes);
		add(campos);
	}
	public interfazSucursal darInterSucursal()
	{
		return inte;
	}

	public void datos()
	{
		Object[] a =inte.darCliente();
		items= new String [a.length];
		for(int i=0;i<a.length;i++)
		{
			
			Cliente aux= (Cliente)a[i];
//			
			items[i]=(String)aux.getNombre();
		}
		
	}
	
	/**
	 * manejo de eventos del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String mensaje = e.getActionCommand();

		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{
			int a = clientes.getSelectedIndex();
			Cliente aux =(Cliente) inte.darCliente()[a];
			InterfazCliente interf= new InterfazCliente(aux,this);
			interf.setVisible(true);
			dispose();
		}
		else if (mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}
	}

}
