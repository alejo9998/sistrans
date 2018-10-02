package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DialogoFactura extends JDialog implements ActionListener
{
	private InterfazApp interfaz;
	
	private JTextArea txtDescripcion;
	
	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";
	
	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";
	
	public DialogoFactura(InterfazApp inter) 
	{
		interfaz= inter;
		
		setTitle("Agregar Factura");
		setLayout(new BorderLayout());
		setSize(500,200);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 2, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
		
     
        JLabel desc = new JLabel("Descripción factura: ");
        

        txtDescripcion = new JTextArea();
                
        aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
        
		campos.add(desc);
		campos.add(txtDescripcion);
		campos.add(aceptar);
		campos.add(cancelar);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	  String mensaje = e.getActionCommand();
			  if(mensaje.equalsIgnoreCase(ACEPTAR))
			  {
				  String descrip = txtDescripcion.getText();
				  
				  dispose();
			  }
			  else if ( mensaje.equalsIgnoreCase(CANCELAR))
			  {
				  dispose();
			  }
		
	}

}
