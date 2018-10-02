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

public class DialogoProveedor extends JDialog implements ActionListener
{

	private InterfazApp interfaz;
	
	private JTextField txtNombre;
	
	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";
	
	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";
	
	public DialogoProveedor(InterfazApp inter)
	{
		
		interfaz= inter;
		
		setTitle("Agregar Proveedor");
		setLayout(new BorderLayout());
		setSize(300,150);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 2, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
		
        JLabel nit = new JLabel("NIT: ");
        JLabel nomb = new JLabel("Nombre: ");
        
       
        txtNombre = new JTextField();
        
        aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
        

		campos.add(nomb);
		campos.add(txtNombre);
		campos.add(aceptar);
		campos.add(cancelar);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String mensaje = e.getActionCommand();
		
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{

			String nombre = txtNombre.getText();
			interfaz.agregarProveedor(nombre);
			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}
		
	}

}
