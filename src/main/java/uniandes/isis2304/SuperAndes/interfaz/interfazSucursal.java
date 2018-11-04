package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import javafx.scene.layout.Border;
import uniandes.isis2304.SuperAndes.negocio.Carrito;
import uniandes.isis2304.SuperAndes.negocio.Estante;
import uniandes.isis2304.SuperAndes.negocio.SuperAndes;

public class interfazSucursal extends JFrame 
{
	private bannerSucursal baner;
	
	private botoneraImplementos botonesIzquierda;
	
	private BotonesOrdenes botonesDerecha;
	
	public BotonProductos btonProductos;
	
	public InterfazAdministrador inter;
	
	public String id;
	
	public ArrayList<Carrito> c;

	
	public interfazSucursal(String pId) throws Exception
	{
		inter = new InterfazAdministrador();
		inter.setVisible(true);
		inter.verificarSucursal(pId);
		id=pId;
		
		c= new ArrayList<>();
		setTitle("Sucursal " + id );
		baner = new bannerSucursal();
		
		botonesIzquierda= new botoneraImplementos(id,this);
		btonProductos = new BotonProductos(this);
		botonesDerecha = new BotonesOrdenes(this);
		
		setSize(600,550);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		add(baner, BorderLayout.NORTH);
		add(botonesIzquierda,BorderLayout.WEST);
		add(botonesDerecha,BorderLayout.EAST);
		add(btonProductos,BorderLayout.CENTER);
		  
	
	}
	public InterfazAdministrador darInterfazAdministrados()
	{
		return inter;
	}
	public Long daridSucursal()
	{
		return Long.parseLong(id);
	}
	
	public Object[] darCliente()
	{
		return inter.darCliente();
	}
	
	public void verificar( String idSuc) throws Exception
	{
		try
		{
			
			interfazSucursal interf = new interfazSucursal(idSuc);
			interf.setVisible(true);	
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	public ArrayList<Carrito> darCarritos()
	{
		long idSuc= Long.parseLong(id);
		c=inter.darCarritos(idSuc);
		return c;
	}
	public void dentroCarrito(long idCarrito,long idProductoSucursal,int cantidad)
	{
		inter.dentroCarrito(idCarrito, idProductoSucursal, cantidad);
		
	}
	
	public void eliminarProducto(long idCarrito,long idProductoSucursal)
	{
		inter.eliminarDentroCarrito(idCarrito, idProductoSucursal);
	}
	
	public Object[] darProductoProveedor()
	{
		return inter.darProductosProveedor();
	}
	public void eliminarCarrito()
	{
		try
		{
		long idSucursal= Long.parseLong(id);
		Long carrito =c.get(btonProductos.darItemSeleccionado()).getIdCarrito();
		inter.eliminarCarrito(carrito,idSucursal);
		btonProductos.actalizar();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Seleccione algun carrito", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public Carrito darCarritoLibre()
	{
		
		Carrito aux = null;
		boolean encontra=false;
		for (int i =0; i<c.size()&&!encontra;i++)
		{
			if(c.get(i).getOcupado()==0)
			{
				aux=c.get(i);
				encontra=true;
				inter.ocuparCarro(aux.getIdCarrito());
				
				btonProductos.actalizar();
			}
		}
		
		
		if(!encontra)
		{
			JOptionPane.showMessageDialog(null, "No hay carritos disponibles", "SuperAndes",JOptionPane.ERROR_MESSAGE);
			
		}
		return aux;
	}
	public void recolectarProductos()
	{
		long idSuc = Long.parseLong(id);
		inter.recogerProductos(idSuc);
		actualizar();
	}
	public void actualizar()
	{
		btonProductos.actalizar();
	}
	
	public void agergarCarrito()
	{
		long idSuc= Long.parseLong(id);
		inter.agregarCarrito(idSuc);
		actualizar();
	}
	public void abandonarCarrito(long idCarrito)
	{
		inter.abandonarCarrito(idCarrito);
		actualizar();
	}
	
	public void agregarBodega(String pTipo,String pVol,String pPeso)
	{
		inter.agregarBodega(pTipo, pVol, pPeso, id);
		
	}
	
	public void agregarCliente(String pNombre,String pCorreo,String pDireccion,String identificacion)
	{
		inter.agregarCliente(pNombre, pCorreo, pDireccion);
	}
	public void agregarEstante(String pTipo,String pPeso,String volum,String nivAprovi)
	{
		inter.agregarEstante(pTipo, pPeso, volum, nivAprovi, id);
	}
	public void agregarCompra( String pIdCarrito,String pIdCliente)
	{
		long idCliente = Long.parseLong(pIdCliente);
		long idCarrito = Long.parseLong(pIdCarrito);
		inter.agregarCompra(idCliente, idCarrito);
	}
	public void agregarProductoSucursal(String pNombre,String pMarca,String pPresentacion,String cantidadPresentacion,String unidadmedida,String VolumenEmpaquetado,String pPesoEmpaquetado,String pCodBarras,String pCategoria,String pTipo,String pFechaVencimiento,String pNivelReorden,String pPrecioUnitario,String cantiBodega,String cantEstante,String pPrecioUnidadMedida,String idProm,String idEstante,String idBodega)
	{
		inter.agregarProductoSucursal(pNombre, pMarca, pPresentacion, cantidadPresentacion, unidadmedida, VolumenEmpaquetado, pPesoEmpaquetado, pCodBarras, pCategoria, pTipo, pFechaVencimiento, pNivelReorden, pPrecioUnitario, cantiBodega, cantEstante, pPrecioUnidadMedida, idProm, idEstante, idBodega);
	}
	public void agregarOrdenPedido(String fechEntrega,String fechEsp,String cali,String idProv,String  prec,String entre,String cantid)
	{
		inter.agregarOrdenPedido(fechEntrega, fechEsp, cali, idProv, id, prec, entre, cantid);
	}
	public void agregarPromocion(int ptipo,String p,String lleve,String fechaCad)
	{
		inter.agregarPromocion(ptipo, p, lleve, fechaCad);
	}
	public void pagar(long idCarrito,long idCliente)
	{
		inter.pagar(idCarrito, idCliente);
	}

}
