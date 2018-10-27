package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DialogoRegistrarPedido extends JDialog implements ActionListener,ListSelectionListener
{
	private interfazSucursal interfaz;

	private JList pedidos;

	private JScrollPane scroll;

	private ArrayList<String> ordenes;

	private JButton finalizarPedido;

	private final static String FINALIZAZRPEDIDO="FINALIZARPEDIDO";
	
	private JTextField prov;

	private JTextField precio;
	
	private JTextField cantidad;
	
	private JTextField FechaEsp;
	
	private DialogoTerminarPedido dtp;
	
	public DialogoRegistrarPedido(interfazSucursal inter, ArrayList<String> ord) 
	{


		interfaz=inter;
		ordenes= ord;
		setLayout(new GridLayout(1, 2));
		setTitle("Registrar llegada de pedido");
		setSize(500,200);
		setLocationRelativeTo(null);

		JPanel aux = new JPanel();
		aux.setLayout(new BorderLayout());
		aux.setBorder(new TitledBorder("Pedidos"));

		pedidos= new JList();
		scroll= new JScrollPane(pedidos);
		scroll.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scroll.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		pedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pedidos.addListSelectionListener(this);
		setData(ord);
		aux.add(scroll,BorderLayout.CENTER);

		finalizarPedido= new JButton("Finalizar Pedido");
		finalizarPedido.addActionListener(this);
		finalizarPedido.setActionCommand(FINALIZAZRPEDIDO);
		aux.add(finalizarPedido,BorderLayout.SOUTH);

		JPanel aux2 = new JPanel();
		aux2.setLayout(new GridLayout(4, 2));
		JLabel idpr = new JLabel("Id Proveedor");
		prov = new JTextField();
		JLabel prec = new JLabel("Total");
		precio = new JTextField();
		JLabel cantid = new JLabel("Cantidad");
		cantidad = new JTextField();
		JLabel fechEsp = new JLabel("Fecha Esperada");
		FechaEsp = new JTextField();
		aux2.setBorder(new TitledBorder("Infromación"));
		
		prov.setEnabled(false);
		precio.setEnabled(false);
		cantidad.setEnabled(false);
		FechaEsp.setEnabled(false);
		
		aux2.add(idpr);
		aux2.add(prov);
		aux2.add(prec);
		aux2.add(precio);
		aux2.add(cantid);
		aux2.add(cantidad);
		aux2.add(fechEsp);
		aux2.add(FechaEsp);
		
		add(aux);
		add(aux2);

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(FINALIZAZRPEDIDO))
		{
			int i = pedidos.getSelectedIndex();
			String a =ordenes.get(i);
			dtp = new DialogoTerminarPedido(a,interfaz,this);
			dtp.setVisible(true);
		}


	}
	
	public void eliminarEle()
	{
		int a = pedidos.getSelectedIndex();
		ordenes.remove(a);
		actualizar("0 0 0 0");
		setData(ordenes);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(pedidos.getSelectedValue()!=null)
		{
			int i = pedidos.getSelectedIndex();
			String a =ordenes.get(i);
			actualizar((String)a);
		}
	}
	
	public void actualizar(String e)
	{
		String tod[]= e.split(" ");
		
		FechaEsp.setText(tod[0]);
		precio.setText(tod[1]);
		cantidad.setText(tod[2]);
		prov.setText(tod[3]);
		
	}
	
	public void setData(ArrayList<String> as)
	{
		ArrayList<String> f = new ArrayList<String>();
		for(int i =0; i< as.size();i++)
		{
			String idprov[] = as.get(i).split(" ");
			f.add(idprov[3]);
			
		}
		pedidos.setListData(f.toArray());
	}

}
