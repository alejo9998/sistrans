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

public class DialogoSucursal extends JDialog implements ActionListener
{

	private InterfazAdministrador inte;
	
	
	private JTextField nombre;
	
	private JTextField ciudad;
	
	private JTextField direccion;
	
	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";
	
	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";
	
	public DialogoSucursal(InterfazAdministrador inter) 
	{
		inte = inter;
		inte.setVisible(true);
		setTitle("Agregar Sucursal");
		setLayout(new BorderLayout());
		setSize(350,200);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 4, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
        
        JLabel nom = new JLabel("Nombre de la sucursal");
        JLabel ciud= new JLabel("Ciudad");
        JLabel dir = new JLabel("Direccion");
        
        nombre = new JTextField();
        ciudad = new JTextField();
        direccion = new JTextField();
        
        aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
		

		campos.add(nom);
		campos.add(nombre);
		campos.add(ciud);
		campos.add(ciudad);
		campos.add(dir);
		campos.add(direccion);
		campos.add(aceptar);
		campos.add(cancelar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String mensaje = e.getActionCommand();
		
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{

			String nomb = nombre.getText();
			String ciu= ciudad.getText();
			String direc= direccion.getText();
			inte.agregarSucursal(nomb, ciu, direc);
			
			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}
		
	}

}
