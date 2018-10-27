package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

public class DialogoOrdenPedido extends JDialog implements ActionListener
{	
	/**
	 * Interfaz principal
	 */
	private interfazSucursal inte;
/**
 * precio de la orden
 */
	private JTextField precio;

	private JDateChooser fechaEsperada;
		
	private JTextField cantidad;
	
	private JTextField proveedor;

	
	private BotonesOrdenes orden;
	
	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";

	private JButton cancelar;
	
	public static final String ENTREGADO="Entregado";
	public static final String NOENTREGADO="No Entregado";
	private final static String CANCELAR="CANCELAR";
	
	public DialogoOrdenPedido(interfazSucursal inter,BotonesOrdenes bot) 
	{
		inte= inter;
		orden= bot;
		setTitle("Agregar Orden Pedido");
		setLayout(new BorderLayout());
		setSize(300,300);
		setLocationRelativeTo(null);
		
        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 5, 2, 3, 3 ) );
        campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
        add( campos, BorderLayout.CENTER );
        
        
        JLabel tipos = new JLabel("Fecha esperada: ");
        JLabel fechaCad = new JLabel("Cantidad: ");
        JLabel prov = new JLabel("Id ProductoProveedor: ");
        JLabel prec = new JLabel("precio: ");
        
        fechaEsperada= new JDateChooser();
        cantidad = new JTextField();
        proveedor = new JTextField();
        precio= new JTextField();
        
        aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);
		

		campos.add(tipos);
		campos.add(fechaEsperada);
		campos.add(prec);
		campos.add(precio);
		campos.add(fechaCad);
		campos.add(cantidad);
		campos.add(prov);
		campos.add(proveedor);
		campos.add(aceptar);
		campos.add(cancelar);

	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String mensaje = e.getActionCommand();
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
			
			String fechEsp = formato.format(fechaEsperada.getDate());
			String idProv = proveedor.getText();
			String prec = precio.getText();
			String cant = cantidad.getText();
			
			orden.agregarPedido(fechEsp+" "+prec+" "+cant+" "+idProv);

			
			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
		 dispose();	
		}
		
	}

}
