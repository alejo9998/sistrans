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

	private InterfazApp inte;
	
	private JTextField idSucursal;
	
	private JTextField nombre;
	
	private JTextField ciudad;
	
	private JTextField direccion;
	
	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";
	
	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";
	
	public DialogoSucursal(InterfazApp inter) 
	{
		inte = inter;
		
		setTitle("Agregar Sucursal");
		setLayout(new BorderLayout());
		setSize(300,300);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 5, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
        
        JLabel nom = new JLabel("Nombre de la sucursal");
        JLabel id = new JLabel("Identificacion de la sucursal");
        JLabel ciud= new JLabel("Ciudad");
        JLabel dir = new JLabel("Direccion");
        
        nombre = new JTextField();
        idSucursal= new JTextField();
        ciudad = new JTextField();
        direccion = new JTextField();
        
        aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
		

		campos.add(id);
		campos.add(idSucursal);
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
