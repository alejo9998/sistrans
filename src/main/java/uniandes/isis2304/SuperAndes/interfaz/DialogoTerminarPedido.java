package uniandes.isis2304.SuperAndes.interfaz;

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

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class DialogoTerminarPedido extends JDialog implements ActionListener
{
	private JDateChooser fechaLlegada;

	private JComboBox<String> calificacion;

	private JComboBox<String> entrega; 

	private JButton aceptar;
	
	private JButton cancelar;
	
	private String datos;
	
	private interfazSucursal inter;
	
	private DialogoRegistrarPedido drp;
	
	public static final String ENTREGADO="Entregado";
	public static final String NOENTREGADO="No Entregado";
	
	public static final String ACEPTAR="ACEPTAR";
	public static final String CANCELAR="CANCELAR";
	
	public DialogoTerminarPedido(String pdatos, interfazSucursal inte, DialogoRegistrarPedido dial)
	{
		datos=pdatos;
		inter= inte;
		drp=dial;
		setTitle("Finalizar pedido");
		JPanel aux = new JPanel();
		setSize(300, 300);
		aux.setLayout(new GridLayout(4, 2));
		aux.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
		setLocationRelativeTo(null);
		
		JLabel fech= new JLabel("Fecha de llegada");
		JLabel cal= new JLabel("Calificación");
		JLabel entre= new JLabel("Entrega");

		fechaLlegada = new JDateChooser();
		String [] cale = {"1","2","3","4","5"};
		calificacion = new JComboBox<String>(cale);
		String a[] ={ENTREGADO,NOENTREGADO};
		entrega = new JComboBox<String>(a);

		aceptar= new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		
		cancelar= new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(ACEPTAR);
		
		aux.add(fech);
		aux.add(fechaLlegada);
		aux.add(cal);
		aux.add(calificacion);
		aux.add(entre);
		aux.add(entrega);
		aux.add(aceptar);
		aux.add(cancelar);
		
		
		add(aux);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(ACEPTAR))
		{
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
			
			String tod[] = datos.split(" ");
			String FechaEsp =tod[0];
			String precio =tod[1];
			String cantidad=tod[2];
			String prov= tod[3];
			String fechLleg = formato.format(fechaLlegada.getDate());			
			String cali = (String)calificacion.getSelectedItem();
			String entr = (String)entrega.getSelectedItem();
			
			inter.agregarOrdenPedido(fechLleg, FechaEsp, cali, prov, precio, entr, cantidad);
			drp.eliminarEle();
			dispose();
			
		}
		else if( e.getActionCommand().equals(CANCELAR))
		{
			dispose();
		}

	}

}
