package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import jdk.nashorn.internal.runtime.ListAdapter;
import uniandes.isis2304.SuperAndes.negocio.Estante;
import uniandes.isis2304.SuperAndes.negocio.ProductoSucursal;


public class BotonProductos extends JPanel implements ActionListener 
{
	private JList lista;


	private JScrollPane scrollLista;

	private JButton agregarProducto;
	private final static String PRODUCTOS ="PRODUCTOS";
	
	private DialogoProductoSucursal dp;
	
	private interfazSucursal inter;

	public BotonProductos(interfazSucursal pInter)
	{
		inter=pInter;
		
		setBorder(new TitledBorder("Productos"));
		setLayout(new BorderLayout());
		lista = new JList<String>();
		scrollLista= new JScrollPane(lista);
		scrollLista.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scrollLista.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		datos();

		agregarProducto = new JButton("Agergar Producto");
		agregarProducto.addActionListener(this);
		agregarProducto.setActionCommand(PRODUCTOS);
		
		add(scrollLista,BorderLayout.CENTER);
		add(agregarProducto,BorderLayout.SOUTH);
	}
	
	public void datos(){
		
		ArrayList<String> productos = new ArrayList<String>();
		Object[] a =inter.darInterfazAdministrados().darPorudctos();
		for(int i =0;i<a.length;i++)
		{
			ProductoSucursal aux = (ProductoSucursal) a[i];
			Estante aux2 = inter.darInterfazAdministrados().darEstante(aux.getEstante());
			if(aux2.getSucursal()==inter.daridSucursal())
			{
				productos.add(aux.getNombre());
			}
		}
		lista.setListData(productos.toArray());
	}
	private void actalizar()
	{
		datos();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String a = e.getActionCommand();
		if(a.equals(PRODUCTOS))
		{
			dp= new DialogoProductoSucursal(inter);
			dp.setVisible(true);
			actalizar();
		}

	}

}
