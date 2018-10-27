package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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

	/**
	 * Dialogo de comprar
	 * @param inter interfaz
	 */
	public DialogoCompra(interfazSucursal inter) 
	{
		inte= inter;
		setTitle("Agregar Compra");
		setLayout(new BorderLayout());
		setSize(300,300);
		setLocationRelativeTo(null);

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 6, 2, 3, 3 ) );
		campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
		add( campos, BorderLayout.CENTER );


		JLabel m1= new JLabel("Cantidad: ");
		JLabel n1 = new JLabel("Producto: ");
		JLabel reap = new JLabel("Cliente: ");
		JLabel fechaCad = new JLabel("Factura: ");
		JLabel total = new JLabel("Total: ");


	
		cantidad = new JTextField();
		productoSucursal = new JTextField();
		cliente = new JTextField();
		factura = new JTextField();
		totalPagado = new JTextField();
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);


		campos.add(m1);
		campos.add(cantidad);
		campos.add(n1);
		campos.add(productoSucursal);
		campos.add(reap);
		campos.add(cliente);
		campos.add(total);
		campos.add(totalPagado);
		campos.add(fechaCad);
		campos.add(factura);
		campos.add(aceptar);
		campos.add(cancelar);

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
			String canti = cantidad.getText();
			String prod = productoSucursal.getText();
			String clie = cliente.getText();
			String fac = factura.getText();
			String total = totalPagado.getText();
			inte.agregarCompra(canti, prod, clie, fac, total);
			dispose();
		}
		else if (mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}
	}

}
