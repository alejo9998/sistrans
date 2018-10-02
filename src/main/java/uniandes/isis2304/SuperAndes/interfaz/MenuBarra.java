package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarra extends JMenuBar implements ActionListener
{
	private InterfazApp prinCipal;
	
	private JMenu agregar;
	
	private JMenuItem agregarCliente;
	private final static String ADDCLIENTE="AGREGARCLIENTE";
	
	private JMenuItem agregarFactura;
	private final static String ADDFACTURA="AGREGARFACTURA";
	
	private JMenuItem agregarSucursal;
	private final static String ADDSUCURSAL="AGREFARFACTURA";
	
	private JMenuItem agregarProveedor;
	private final static String ADDPROVEEDOR="AGREGARPROVEEDOR";
	
	private JMenuItem agregarPromocion;
	private final static String ADDPROMOCION ="AGERGARPROMOCION";
	
	private JMenuItem agregarBodega;
	private final static String ADDBODEGA="AGREGARBODEGA";
	
	private JMenuItem agregarEstante;
	private final static String ADDESTANTE ="AGREGARESTANTE";
	
	private JMenuItem agregarProductoSucursal;
	private final static String ADDPRODUCTOSUCURSAL = "AGREGARPRODUCTOSUCURSAL";
	
	private JMenuItem agregarCompra;
	private final static String ADDCOMPRA="AGERGARCOMPRA";
	
	private JMenuItem agregarProductoProveedor;
	private final static String ADDPRODUCTOPROVEEDOR="AGREGARPRODUCTOPROVEEDOR";
	
	private JMenuItem agregarOrdenPedido;
	private final static String  ADDORDENPEDIDO="AGREGARORDENPEDIDO";
	
	private JMenu requerimientos;
	
	private JMenu mantenimiento;
	
	public MenuBarra(InterfazApp inter)
	{
	
		prinCipal= inter;
		agregar = new JMenu("Agregar");
		add(agregar);
		
		agregarCliente = new JMenuItem("Agergar Cliente");
		agregarCliente.setActionCommand(ADDCLIENTE);
		agregarCliente.addActionListener(this);
		
		agregarFactura = new JMenuItem("Agregar Factura");
		agregarFactura.setActionCommand(ADDFACTURA);
		agregarFactura.addActionListener(this);
		
		agregarSucursal = new JMenuItem("Agregar sucursal");
		agregarSucursal.setActionCommand(ADDSUCURSAL);
		agregarSucursal.addActionListener(this);		
		
		agregarProveedor = new JMenuItem("Agregar Proveedor");
		agregarProveedor.setActionCommand(ADDPROVEEDOR);
		agregarProveedor.addActionListener(this);
		
		agregarPromocion = new JMenuItem("Agregar Pomocion");
		agregarPromocion.setActionCommand(ADDPROMOCION);
		agregarPromocion.addActionListener(this);
		
		agregarBodega = new JMenuItem("Agregar Bodega");
		agregarBodega.addActionListener(this);
		agregarBodega.setActionCommand(ADDBODEGA);
		
		agregarEstante = new JMenuItem("Agregar Estante");
		agregarEstante.addActionListener(this);
		agregarEstante.setActionCommand(ADDESTANTE);
		
		agregarProductoSucursal = new JMenuItem("Agregar Producto Sucursal");
		agregarProductoSucursal.addActionListener(this);
		agregarProductoSucursal.setActionCommand(ADDPRODUCTOSUCURSAL);
		
		agregarCompra = new JMenuItem("Agrengar Compra");
		agregarCompra.addActionListener(this);
		agregarCompra.setActionCommand(ADDCOMPRA);
		
		agregarProductoProveedor = new JMenuItem("Agergar Producto Proveedor");
		agregarProductoProveedor.setActionCommand(ADDPRODUCTOPROVEEDOR);
		agregarProductoProveedor.addActionListener(this);
		
		agregarOrdenPedido = new JMenuItem("Agergar Orden Pedido");
		agregarOrdenPedido.addActionListener(this);
		agregarOrdenPedido.setActionCommand(ADDORDENPEDIDO);
		
		agregar.add(agregarCliente);
		agregar.add(agregarFactura);
		agregar.add(agregarSucursal);
		agregar.add(agregarProveedor);
		agregar.add(agregarPromocion);
		agregar.add(agregarBodega);
		agregar.add(agregarEstante);
		agregar.add(agregarProductoSucursal);
		agregar.add(agregarCompra);
		agregar.add(agregarProductoProveedor);
		agregar.add(agregarOrdenPedido);
		
		requerimientos = new JMenu("Requerimientos");
		add(requerimientos);
		
		mantenimiento = new JMenu("Mantenimiento");
		add(mantenimiento);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String mensaje= e.getActionCommand();
		if(mensaje.equalsIgnoreCase(ADDCLIENTE))
		{
			DialogoCliente dialogo = new DialogoCliente(prinCipal);
			dialogo.setVisible(true);
		}
		else if (mensaje.equalsIgnoreCase(ADDFACTURA))
		{
			DialogoFactura dialogo = new DialogoFactura(prinCipal);
			dialogo.setVisible(true);
		}
		else if (mensaje.equalsIgnoreCase(ADDSUCURSAL))
		{
			DialogoSucursal dialogo= new DialogoSucursal(prinCipal);
			dialogo.setVisible(true);
		}
		else if (mensaje.equalsIgnoreCase(ADDPROVEEDOR))
		{
			DialogoProveedor dialogo= new DialogoProveedor(prinCipal);
			dialogo.setVisible(true);
		}
		
		else if (mensaje.equalsIgnoreCase(ADDPROMOCION))
		{
			DialogoPromocion dialogo= new DialogoPromocion(prinCipal);
			dialogo.setVisible(true);
		}
		
		else if (mensaje.equalsIgnoreCase(ADDBODEGA))
		{
			DialogoBodega dialogo= new DialogoBodega(prinCipal);
			dialogo.setVisible(true);
		}
		
		else if (mensaje.equalsIgnoreCase(ADDESTANTE))
		{
			DialogoEstante dialogo= new DialogoEstante(prinCipal);
			dialogo.setVisible(true);
		}
		else if ( mensaje.equalsIgnoreCase(ADDPRODUCTOSUCURSAL))
		{
			DialogoProductoSucursal dialogo = new DialogoProductoSucursal(prinCipal);
			dialogo.setVisible(true);
		}
		else if ( mensaje.equalsIgnoreCase(ADDCOMPRA))
		{
			DialogoCompra dialogo = new DialogoCompra(prinCipal);
			dialogo.setVisible(true);
		}
		
		else if ( mensaje.equalsIgnoreCase(ADDPRODUCTOPROVEEDOR))
		{
			DialogoProductoProveedor dialogo = new DialogoProductoProveedor(prinCipal);
			dialogo.setVisible(true);
		}
		else if ( mensaje.equalsIgnoreCase(ADDORDENPEDIDO))
		{
			DialogoOrdenPedido dialogo = new DialogoOrdenPedido(prinCipal);
			dialogo.setVisible(true);
		}
	}

}
