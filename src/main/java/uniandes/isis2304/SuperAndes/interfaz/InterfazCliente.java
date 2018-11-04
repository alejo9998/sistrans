package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.isis2304.SuperAndes.negocio.Carrito;
import uniandes.isis2304.SuperAndes.negocio.Cliente;
import uniandes.isis2304.SuperAndes.negocio.Estante;
import uniandes.isis2304.SuperAndes.negocio.ProductoSucursal;

public class InterfazCliente extends JFrame implements ActionListener,ListSelectionListener
{
	private JList<String> carrito;
	private JList<String> productos;

	private JScrollPane scrollCarrito;
	private JScrollPane scrollProductos;

	private JButton agregar;
	private final static String AGREGAR="AGREGAR";

	private JButton eliminar;
	private final static String ELIMINAR="ELIMINAR";

	private JButton solicitarCarrito;
	private JButton abandonarCarrito;
	private JButton pagar;
	private JButton RecogerProductos;

	private final static String SOLICITARCARRITO="SOLICITARCARRITO";
	private final static String ABANDONARCARRITO="ABANDONARCARRITO";
	private final static String PAGAR="PAGAR";
	private final static String RECOGERPRODUCTOS="RECOGERPRODUCTOS";

	private JLabel total;
	private JLabel totalVal;

	private ArrayList<ProductoSucursal> car;
	private ArrayList<ProductoSucursal> productosLista;
	
	private Carrito carritoDeCompras;
	
	private String [] f;

	private long id;

	private JLabel dir;
	
	private DialogoCompra dc;

	public InterfazCliente(Cliente clie,DialogoCompra pDc) 
	{
		
		dc=pDc;
		productosLista= new ArrayList<>();
		car= new ArrayList<ProductoSucursal>();
		id = clie.getIdentificacion();
		setSize(620,600);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setTitle("Carrito del cliente");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
			
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(2, 2));
		JLabel Encabezado = new JLabel("Cliente numero: "+id);
		Encabezado.setHorizontalAlignment(JLabel.CENTER);
		JLabel Nom = new JLabel("Nombre: "+clie.getNombre());
		Nom.setHorizontalAlignment(JLabel.CENTER);
		JLabel cor= new JLabel("Correo: " + clie.getCorreo());
		cor.setHorizontalAlignment(JLabel.CENTER);
		dir = new JLabel();
		dir.setHorizontalAlignment(JLabel.CENTER);
		aux.add( Nom);
		aux.add(cor);
		aux.add(dir);
		aux.add(Encabezado);

		add(aux,BorderLayout.NORTH);

		JPanel productosPanel = new JPanel();
		productosPanel.setBorder(new TitledBorder("Productos"));
		productosPanel.setLayout(new BorderLayout());
		productosPanel.setPreferredSize(new Dimension(300, 400));

		productos= new JList<>();
		scrollProductos= new JScrollPane(productos);
		scrollProductos.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scrollProductos.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		datos();

		agregar= new JButton("Agregar al Carrito");
		agregar.setActionCommand(AGREGAR);
		agregar.addActionListener(this);

		productosPanel.add(scrollProductos,BorderLayout.CENTER);
		productosPanel.add(agregar,BorderLayout.SOUTH);

		JPanel carritoPanel = new JPanel();
		carritoPanel.setBorder(new TitledBorder("Carrito"));
		carritoPanel.setLayout(new BorderLayout());
		carritoPanel.setPreferredSize(new Dimension(300, 400));

		carrito= new JList<>();
		scrollCarrito = new JScrollPane(carrito);
		scrollCarrito.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scrollCarrito.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		carritoPanel.add(scrollCarrito,BorderLayout.CENTER);

		JPanel botonesCarrito = new JPanel();
		botonesCarrito.setLayout(new BorderLayout());

		total = new JLabel("Total :");
		total.setEnabled(false);
		totalVal= new JLabel();


		totalVal.setText("0.00");

		eliminar= new JButton("Eliminar del carrito");
		eliminar.addActionListener(this);
		eliminar.setActionCommand(ELIMINAR);
		botonesCarrito.add(total, BorderLayout.WEST);
		botonesCarrito.add(totalVal, BorderLayout.EAST);
		botonesCarrito.add(eliminar, BorderLayout.SOUTH);

		carritoPanel.add(botonesCarrito,BorderLayout.SOUTH);

		JPanel botones = new JPanel();
		solicitarCarrito = new JButton("Solicitar carrito");
		solicitarCarrito.setActionCommand(SOLICITARCARRITO);
		solicitarCarrito.addActionListener(this);

		abandonarCarrito= new JButton("Abandonar Carrito");
		abandonarCarrito.addActionListener(this);
		abandonarCarrito.setActionCommand(ABANDONARCARRITO);

		pagar= new JButton("Pagar");
		pagar.setActionCommand(PAGAR);
		pagar.addActionListener(this);

		RecogerProductos= new JButton("Recoger Productos");
		RecogerProductos.addActionListener(this);
		RecogerProductos.setActionCommand(RECOGERPRODUCTOS);

		botones.setLayout(new GridLayout(2, 2));

		botones.add(pagar);
		botones.add(solicitarCarrito);
		botones.add(RecogerProductos);
		botones.add(abandonarCarrito);

		desactivar();

		add(productosPanel,BorderLayout.WEST);
		add(carritoPanel,BorderLayout.EAST);
		add(botones,BorderLayout.SOUTH);


	}
	public void datos()
	{
		Object[] aux =dc.darInterSucursal().inter.darPorudctos();
		ArrayList<String>produ = new ArrayList<String>();

		Long a = Long.parseLong(dc.darInterSucursal().id);
		for(int i=0; i<aux.length;i++)
		{
			ProductoSucursal aux2 = (ProductoSucursal) aux[i];
			if(dc.darInterSucursal().inter.darEstante(aux2.getEstante()).getSucursal()== a)
			{
				produ.add(aux2.getNombre() +"-----------------  " +aux2.getPrecioUnitario());
				productosLista.add(aux2);
			}

		}

		String[] prod= new String [produ.size()]; 
		for(int i =0 ;i<produ.size();i++)
		{
			prod[i]= produ.get(i);
		}

		productos.setListData(prod);
	}
	public void dispose()
	{
		try
		{
		dc.darInterSucursal().abandonarCarrito(carritoDeCompras.getIdCarrito());
		super.dispose();
		}
		catch(Exception e)
		{
			super.dispose();
		}
	}
	public void desactivar()
	{
		carrito.setEnabled(false);
		pagar.setEnabled(false);
		eliminar.setEnabled(false);
		RecogerProductos.setEnabled(false);
		abandonarCarrito.setEnabled(false);
		agregar.setEnabled(false);
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	public void add(ProductoSucursal e)
	{
		try
		{
			car.add(e);	
		}
		catch(Exception t)

		{
			JOptionPane.showMessageDialog(null,"Seleccione algun producto","SuperAndes",JOptionPane.ERROR_MESSAGE);
		}


	}
	public void actualizar()
	{
		f = new String [car.size()];
		double monto = 0;
		for (int i =0; i<car.size();i++)
		{
			f[i]=car.get(i).getNombre();
			monto= monto + car.get(i).getPrecioUnitario();
		}

		totalVal.setText("$ " + monto);
		carrito.setListData(f);
	}

	public void Delete(ProductoSucursal e)
	{
		car.remove(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getActionCommand().equals(AGREGAR))
		{
			try
			{
				
				add(productosLista.get(productos.getSelectedIndex()));
			 	dc.darInterSucursal().dentroCarrito(carritoDeCompras.getIdCarrito(), productosLista.get(productos.getSelectedIndex()).getIdProductoSucursal(), 1);
				System.out.println(carritoDeCompras.getIdCarrito() +" "+productosLista.get(productos.getSelectedIndex()).getIdProductoSucursal() +" "+ 1);
			 	actualizar();
			}
			catch(Exception t)
			{
				JOptionPane.showMessageDialog(null,"Seleccione algun producto","SuperAndes",JOptionPane.ERROR_MESSAGE);
			}

		}
		else if(e.getActionCommand().equals(ELIMINAR))
		{
			try
			{
				Delete(car.get( carrito.getSelectedIndex()));
				dc.darInterSucursal().eliminarProducto(carritoDeCompras.getIdCarrito(), productosLista.get(productos.getSelectedIndex()).getIdProductoSucursal());
				actualizar();
			}
			catch(Exception t)
			{
				if(car.size()==0)
				{
					JOptionPane.showMessageDialog(null,"No hay productos en el carrito","SuperAndes",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
				JOptionPane.showMessageDialog(null,"Seleccione algun producto","SuperAndes",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(e.getActionCommand().equals(SOLICITARCARRITO))
		{
			try
			{
			carritoDeCompras= dc.solicitarCarrito();
			dir.setText("Carrito id: "+ carritoDeCompras.getIdCarrito());
			dc.darInterSucursal().actualizar();
			carrito.setEnabled(true);
			pagar.setEnabled(true);
			eliminar.setEnabled(true);
			RecogerProductos.setEnabled(true);
			abandonarCarrito.setEnabled(true);
			agregar.setEnabled(true);
			}
			catch(Exception t)
			{
				
			}
		}
		else if (e.getActionCommand().equals(ABANDONARCARRITO))
		{
			car= new ArrayList<>();
			actualizar();
			desactivar();
			dc.darInterSucursal().abandonarCarrito(carritoDeCompras.getIdCarrito());
			
		}
		else if(e.getActionCommand().equals(PAGAR))
		{
			
			dc.darInterSucursal().pagar(carritoDeCompras.getIdCarrito(), id);
		}

	}


}
