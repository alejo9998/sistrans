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

@SuppressWarnings("serial")
public class DialogoBodega extends JDialog implements ActionListener
{
	/**
	 * atributo de la clase principal de la interfaz
	 */
	private interfazSucursal inte;

	/**
	 * Combo con los diferentes tipos de bodega
	 */
	private JComboBox<String> tipo;

	/*
	 * Peso de la bodega
	 */
	private JTextField peso;

	/**
	 * volumen de la bodega
	 */
	private JTextField volumen;



	/**
	 * Boton de aceptar
	 */
	private JButton aceptar;

	/**
	 * Constante con aceptar
	 */
	private final static String ACEPTAR="ACEPTAR";

	/**
	 * Boton de cancelar
	 */
	private JButton cancelar;
	
	/**
	 * constante con cancelar
	 */
	private final static String CANCELAR="CANCELAR";
	
	private interfazSucursal interfaz;

	/**
	 * Clase principal de dialogo 
	 * @param inter interfaz
	 */
	public DialogoBodega(interfazSucursal inter)
	{
		inte = inter;

		setTitle("Agregar Bodega");
		setLayout(new BorderLayout());
		setSize(300,250);
		setLocationRelativeTo(null);

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 4, 2, 3, 3 ) );
		campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
		add( campos, BorderLayout.CENTER );


		JLabel tipos = new JLabel("Tipo de Bodega: ");
		JLabel m1= new JLabel("Peso: ");
		JLabel n1 = new JLabel("Volumen: ");
		


		peso= new JTextField();
		volumen = new JTextField();
	
		String [] g ={"carne", "galleta", "fruta", "tuberculo", "escoba", "balde", "trapeador", "brillador", "cepillo", "desodorante", "crema", "cera"};
		tipo = new JComboBox<>(g);

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
		
		
		campos.add(aceptar);
		campos.add(cancelar);
	}

	@Override
	/**
	 * Manejo de envetos de usuario
	 */
	public void actionPerformed(ActionEvent e)
	{
		String mensaje = e.getActionCommand();
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{
			String pes = peso.getText();
			String tip = (String)tipo.getSelectedItem();
			String vol = volumen.getText();
		
			inte.agregarBodega(tip, vol, pes);
			
			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}

	}

}
