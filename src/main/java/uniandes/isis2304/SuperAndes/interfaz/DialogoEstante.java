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

public class DialogoEstante extends JDialog implements ActionListener
{

	private InterfazApp inte;
	

	private JTextField tipo;

	private JTextField peso;

	private JTextField volumen;
	
	private JTextField nivelReaprovisionamiento;
	
	private JTextField Sucursal;

	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";

	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";
	
	public DialogoEstante(InterfazApp inter) 
	{
		inte= inter;
		setTitle("Agregar Estante");
		setLayout(new BorderLayout());
		setSize(300,300);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 6, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
        
        JLabel tipos = new JLabel("Tipo de estante: ");
        JLabel m1= new JLabel("Peso: ");
        JLabel n1 = new JLabel("Volumen: ");
        JLabel reap = new JLabel("Nivel de reaprovisionamiento: ");
        JLabel fechaCad = new JLabel("Id Sucursal: ");
        
        
        peso= new JTextField();
        volumen = new JTextField();
        nivelReaprovisionamiento = new JTextField();
        Sucursal = new JTextField();
        tipo = new JTextField();
        
        aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
		

		campos.add(tipos);
		campos.add(tipo);
		campos.add(m1);
		campos.add(peso);
		campos.add(n1);
		campos.add(volumen);
		campos.add(reap);
		campos.add(nivelReaprovisionamiento);
		campos.add(fechaCad);
		campos.add(Sucursal);
		campos.add(aceptar);
		campos.add(cancelar);

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	  
		String mensaje = e.getActionCommand();
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{
			String pes = peso.getText();
			String tip = tipo.getText();
			String vol = volumen.getText();
			String reapro= nivelReaprovisionamiento.getText();
			String idSuc = Sucursal.getText();
			inte.agregarEstante(tip, pes, vol, reapro, idSuc);
			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}	
	}
}
