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

public class DialogoPromocion extends JDialog implements ActionListener
{
	private InterfazAdministrador inte;

	private JComboBox<Integer> tipo;

	private JTextField m;

	private JTextField n;
	
	private JTextField fechaCaducidad;

	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";

	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";


	public DialogoPromocion(InterfazAdministrador inter) 
	{
		inte= inter;

		setTitle("Agregar Promoción");
		setLayout(new BorderLayout());
		setSize(300,300);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 5, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
        

        JLabel tipos = new JLabel("Tipo de la promocion: ");
        JLabel m1= new JLabel("Pague: ");
        JLabel n1 = new JLabel("Lleve: ");
        JLabel fechaCad = new JLabel("Fecha de caducidad");
        

        m= new JTextField();
        n = new JTextField();
        fechaCaducidad = new JTextField();
        Integer [] a = {1,2,3,4};
        tipo = new JComboBox<Integer>(a);
        
        aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
		

		campos.add(tipos);
		campos.add(tipo);
		campos.add(m1);
		campos.add(m);
		campos.add(n1);
		campos.add(n);
		campos.add(fechaCad);
		campos.add(fechaCaducidad);
		campos.add(aceptar);
		campos.add(cancelar);


	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String mensaje = e.getActionCommand();
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{
			Integer tip =(Integer)tipo.getSelectedItem();
			String pagu= m.getText();
			String llev =n.getText();
			String fechCad = fechaCaducidad.getText();
			inte.agregarPromocion(tip, pagu, llev, fechCad);
			dispose();
		}
		else if(mensaje.equalsIgnoreCase(ACEPTAR))
		{
			dispose();
		}
	}

}
