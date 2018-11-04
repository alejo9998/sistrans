package uniandes.isis2304.SuperAndes.interfaz;
import javax.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfazSucursalPrincipal extends JFrame implements ActionListener{

	private JTextField idSuc;
	private JLabel text;
	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";
	private interfazSucursal inter;

	public interfazSucursalPrincipal()
	{

		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setSize (300,150);
		setTitle("Iniciar sucursal");

		idSuc= new JTextField();
		JPanel a = new JPanel();
		a.setLayout(new GridLayout(1, 1));
		a.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );

		text= new JLabel("Ingrese el id de la sucursal");
		text.setEnabled(false);
		text.setFont(new Font("Courier",Font.BOLD,18));

		aceptar= new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);

		add(text,BorderLayout.NORTH);
		a.add(idSuc);
		add(a,BorderLayout.CENTER);
		add(aceptar,BorderLayout.SOUTH);

	}

	
	public void actionPerformed(ActionEvent e) 
	{
		String m = e.getActionCommand();
		if(m.equals(ACEPTAR))
		{
			try
			{
				
				inter= new interfazSucursal(idSuc.getText());

				inter.verificar(idSuc.getText());

				this.dispose();
			}
			catch( Exception t)
			{
				t.printStackTrace();
				JOptionPane.showMessageDialog(null,t.getMessage()+": " + idSuc.getText(),"SuperAndes",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		interfazSucursalPrincipal inter = new interfazSucursalPrincipal();
		inter.setVisible(true);
	}

}
