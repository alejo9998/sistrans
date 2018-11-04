package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DialogoCliente extends JDialog implements ActionListener
{
	/**
	 * Atributo con la interfaz
	 */
	private interfazSucursal inte; 
	
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
	
	private JTextField txtIdetificacion;
	
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
	
	private final static String EMPRESA="EMPRESA";
	
	
	private JCheckBox emrpesa;
	
	/**
	 * Interfaz del dialogo
	 * @param inter
	 */
	public DialogoCliente(interfazSucursal inter)
	{
		inte = inter;
		
		setTitle("Agregar Cliente");
		setLayout(new BorderLayout());
		setSize(320,200);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 6, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
		
        JLabel empres = new JLabel("Es empresa: ");
		JLabel nombre = new JLabel("Nombre: ");
		JLabel identi = new JLabel("Identificaion: ");
		JLabel correo = new JLabel("Correo");
		JLabel dir = new JLabel("Dirección: ");
		
		emrpesa = new JCheckBox();
		emrpesa.setActionCommand(EMPRESA);
		emrpesa.addActionListener(this);
		emrpesa.setSelected(false);
		txtnombre = new JTextField();
		txtCorreo = new JTextField();
		txtDireccion = new JTextField();
		txtDireccion.setEnabled(false);
		txtIdetificacion= new JTextField();
		
		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
		
		campos.add(empres);
		campos.add(emrpesa);
		campos.add(nombre);
		campos.add(txtnombre);
		campos.add(correo);
		campos.add(txtCorreo);
		campos.add(dir);
		campos.add(txtDireccion);
		campos.add(identi);
		campos.add(txtIdetificacion);
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
			String identif= txtIdetificacion.getText();
			inte.agregarCliente(nombre, corr, direc, identif);
			
			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}
		else if (mensaje.equalsIgnoreCase(EMPRESA))
		{
			if( emrpesa.isSelected())
			{
				txtDireccion.enable(true);
			}
			else
			{
				txtDireccion.setText("");
				txtDireccion.enable(false);
			}
		}
		
	}

}
