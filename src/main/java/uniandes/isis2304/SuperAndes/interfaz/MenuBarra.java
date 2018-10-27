package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarra extends JMenuBar implements ActionListener
{
	private InterfazAdministrador prinCipal;
	
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
	
	private JMenuItem requerimiento1;
	private final static String REQUERIMIENTO1="REQUERIMIENTO1";
	
	private JMenuItem requerimiento2;
	private final static String REQUERIMIENTO2="REQUERIMIENTO2";
	
	private JMenuItem requerimiento3;
	private final static String REQUERIMIENTO3="REQUERIMIENTO3";
	
	private JMenuItem requerimiento4;
	private final static String REQUERIMIENTO4="REQUERIMIENTO4";
	
	private JMenu mantenimiento;
	
	public MenuBarra(InterfazAdministrador inter)
	{
	
		prinCipal= inter;
		agregar = new JMenu("Agregar");
		add(agregar);
		
		
		agregarFactura = new JMenuItem("Agregar Factura");
		agregarFactura.setActionCommand(ADDFACTURA);
		agregarFactura.addActionListener(this);
		
		agregarSucursal = new JMenuItem("Agregar sucursal");
		agregarSucursal.setActionCommand(ADDSUCURSAL);
		agregarSucursal.addActionListener(this);		
		
		agregarProveedor = new JMenuItem("Agregar Proveedor");
		agregarProveedor.setActionCommand(ADDPROVEEDOR);
		agregarProveedor.addActionListener(this);
		
		agregarProductoProveedor = new JMenuItem("Agergar Producto Proveedor");
		agregarProductoProveedor.setActionCommand(ADDPRODUCTOPROVEEDOR);
		agregarProductoProveedor.addActionListener(this);
		
	
		agregar.add(agregarFactura);
		agregar.add(agregarSucursal);
		agregar.add(agregarProductoProveedor);
	
		
		requerimientos = new JMenu("Requerimientos");
		requerimiento1 = new JMenuItem("Requerimiento 1");
		requerimiento1.addActionListener(this);
		requerimiento1.setActionCommand(REQUERIMIENTO1);
		requerimiento2 = new JMenuItem("Requerimiento 2");
		requerimiento2.addActionListener(this);
		requerimiento2.setActionCommand(REQUERIMIENTO2);
		requerimiento3 = new JMenuItem("Requerimiento 3");
		requerimiento3.addActionListener(this);
		requerimiento3.setActionCommand(REQUERIMIENTO3);
		requerimiento4 = new JMenuItem("Requerimiento 4");
		requerimiento4.addActionListener(this);
		requerimiento4.setActionCommand(REQUERIMIENTO4);
		
		requerimientos.add(requerimiento1);
		requerimientos.add(requerimiento2);
		requerimientos.add(requerimiento3);
		requerimientos.add(requerimiento4);
		add(requerimientos);
		
		mantenimiento = new JMenu("Mantenimiento");
		add(mantenimiento);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String mensaje= e.getActionCommand();
	 if (mensaje.equalsIgnoreCase(ADDFACTURA))
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
				
		else if ( mensaje.equalsIgnoreCase(ADDPRODUCTOPROVEEDOR))
		{
			DialogoProductoProveedor dialogo = new DialogoProductoProveedor(prinCipal);
			dialogo.setVisible(true);
		}

	}

}
