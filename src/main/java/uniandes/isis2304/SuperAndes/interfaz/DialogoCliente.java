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

public class DialogoCliente extends JDialog implements ActionListener
{
	/**
	 * Atributo con la interfaz
	 */
	private InterfazApp inte; 
	
	/**
	 * nombre del cliente
	 */
	private JTextField txtnombre;
	
	/**
	 * Correo del cliente
	 */
	private JTextField txtCorreo;
	/**
	 * direccion del cliente
	 */
	private JTextField txtDireccion;
	
	/**
	 * boton de aceptar
	 */
	private JButton aceptar;
	/**
	 * constante con aceptar
	 */
	private final static String ACEPTAR="ACEPTAR";
	
	/**
	 * boton de cancelar
	 */
	private JButton cancelar;
	
	/**
	 * constante con cancelar
	 */
	private final static String CANCELAR="CANCELAR";
	
	/**
	 * Interfaz del dialogo
	 * @param inter
	 */
	public DialogoCliente(InterfazApp inter)
	{
		inte = inter;
		
		setTitle("Agregar Cliente");
		setLayout(new BorderLayout());
		setSize(300,200);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 4, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
		
		JLabel nombre = new JLabel("Nombre: ");
		JLabel identi = new JLabel("Identificaion: ");
		JLabel correo = new JLabel("Correo");
		JLabel dir = new JLabel("Dirección: ");
		
		txtnombre = new JTextField();
		txtCorreo = new JTextField();
		txtDireccion = new JTextField();
		
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
		
		campos.add(nombre);
		campos.add(txtnombre);
		campos.add(correo);
		campos.add(txtCorreo);
		campos.add(dir);
		campos.add(txtDireccion);
		campos.add(aceptar);
		campos.add(cancelar);
	}
	
	@Override
	/**
	 * Manejo de eventos del usuario
	 */
	public void actionPerformed(ActionEvent e)
	{
		String mensaje = e.getActionCommand();
		
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{

			String nombre = txtnombre.getText();
			String corr= txtCorreo.getText();
			String direc= txtDireccion.getText();
			inte.agregarCliente(nombre, corr, direc);
			
			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}
		
	}

}
