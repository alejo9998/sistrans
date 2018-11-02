package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import uniandes.isis2304.SuperAndes.negocio.Carrito;
import uniandes.isis2304.SuperAndes.negocio.Estante;
import uniandes.isis2304.SuperAndes.negocio.ProductoSucursal;


public class BotonProductos extends JPanel implements ActionListener 
{
	private JList lista;

	private JScrollPane scrollLista;

	private JList carrito;


	private JScrollPane scrollCarrito;
	
	private JButton agregarProducto;
	private final static String PRODUCTOS ="PRODUCTOS";
	
	private JButton recoger;
	private final static String RecogerProductos ="Recoger";
	
	private DialogoProductoSucursal dp;
	
	private interfazSucursal inter;
	
	private Object[] productosProveedor;

	public BotonProductos(interfazSucursal pInter)
	{
		inter=pInter;
		
		
		setLayout(new BorderLayout());
		lista = new JList<String>();
		carrito= new JList<String>();
		
		productosProveedor= inter.darProductoProveedor();
		
		JPanel aux = new JPanel();
		JPanel aux2 = new JPanel();
		aux2.setLayout(new GridLayout(1, 1));
		aux2.setBorder(new TitledBorder("Productos"));
		
		aux.setLayout(new GridLayout(1, 2));
		scrollLista= new JScrollPane(lista);
		scrollLista.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scrollLista.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		datos();
		
		JPanel aux3 = new JPanel();
		aux3.setLayout(new GridLayout(1, 1));
		aux3.setBorder(new TitledBorder("Carritos"));
		scrollCarrito= new JScrollPane(carrito);
		scrollLista.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scrollLista.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		darCarritos();
		
		JPanel aux4= new JPanel();
		aux4.setLayout(new GridLayout(1, 2));
		agregarProducto = new JButton("Agregar Producto");
		agregarProducto.addActionListener(this);
		agregarProducto.setActionCommand(PRODUCTOS);
		
		recoger= new JButton("Recoger Productos");
		recoger.setActionCommand(RecogerProductos);
		recoger.addActionListener(this);
		aux4.add(agregarProducto);
		aux4.add(recoger);
		aux2.add(scrollLista);
		aux3.add(scrollCarrito);
		aux.add(aux2);
		aux.add(aux3);
		add(aux,BorderLayout.CENTER);
		add(aux4,BorderLayout.SOUTH);
	}
	
	public void darCarritos()
	{
		ArrayList<Carrito> carritos = inter.darCarritos();
		String [] a = new String[carritos.size()];
		for (int i =0; i<carritos.size();i++)
		{
			long aux = carritos.get(i).getIdCarrito();
			a[i]= aux+"    " + carritos.get(i).getOcupado();
		}
		carrito.setListData(a);		
		
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
	public void actalizar()
	{
		datos();
		darCarritos();
	}
	
	public int darItemSeleccionado()
	{
		return carrito.getSelectedIndex();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String a = e.getActionCommand();
		if(a.equals(PRODUCTOS))
		{
			dp= new DialogoProductoSucursal(inter);
			dp.setVisible(true);
			
		}
		if(a.equals(RecogerProductos))
		{
			inter.recolectarProductos();
		}

	}

}
