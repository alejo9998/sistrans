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

public class Cliente extends JFrame implements ActionListener,ListSelectionListener
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
	private JTextField totalVal;

	private ArrayList<String> car;

	private String [] f;

	public Cliente() 
	{
		car= new ArrayList<String>();

		setSize(620,600);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		JPanel aux = new JPanel();
		JLabel Encabezado = new JLabel("Cliente numero: "+"123");
		aux.add(Encabezado);

		Encabezado.setFont(new Font("Courier",Font.BOLD,36));
		add(aux,BorderLayout.NORTH);

		JPanel productosPanel = new JPanel();
		productosPanel.setBorder(new TitledBorder("Productos"));
		productosPanel.setLayout(new BorderLayout());
		productosPanel.setPreferredSize(new Dimension(300, 400));

		productos= new JList<>();
		scrollProductos= new JScrollPane(productos);
		scrollProductos.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scrollProductos.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		String[] a ={"Prod 1","Prod 2","Prod 3"};
		productos.setListData(a);
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
		totalVal= new JTextField();
		totalVal.setEditable(false);
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

	public void add(String e)
	{
		car.add(e);

	}
	public void actualizar()
	{
		f = new String [car.size()];
		for (int i =0; i<car.size();i++)
		{
			f[i]=car.get(i);
		}
		carrito.setListData(f);
	}

	public void Delete(int e)
	{
		car.remove(e);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(AGREGAR))
		{
			add((String)productos.getSelectedValue());	
			actualizar();
		}
		else if(e.getActionCommand().equals(ELIMINAR))
		{
			Delete((int) carrito.getSelectedIndex());
			actualizar();			
		}
		else if(e.getActionCommand().equals(SOLICITARCARRITO))
		{
			carrito.setEnabled(true);
			pagar.setEnabled(true);
			eliminar.setEnabled(true);
			RecogerProductos.setEnabled(true);
			abandonarCarrito.setEnabled(true);
			agregar.setEnabled(true);
		}
		else if (e.getActionCommand().equals(ABANDONARCARRITO)){
			car= new ArrayList<>();
			actualizar();
			desactivar();
		}
		else if(e.getActionCommand().equals(RECOGERPRODUCTOS))
		{	
			car= new ArrayList<>();
			actualizar();

		}

	}
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Cliente inter = new Cliente();
		inter.setVisible(true);
	}

}
