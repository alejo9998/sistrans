package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import javafx.scene.layout.Border;

public class interfazSucursal extends JFrame 
{
	private bannerSucursal baner;
	
	private botoneraImplementos botonesIzquierda;
	
	private BotonesOrdenes botonesDerecha;
	
	private BotonProductos btonProductos;
	
	private InterfazAdministrador inter;
	
	public String id;
	

	
	public interfazSucursal(String pId)
	{
		inter = new InterfazAdministrador();
		inter.setVisible(true);
	
		id=pId;

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
	
	public void verificar( String idSuc) throws Exception
	{
		if(idSuc.equalsIgnoreCase("2"))
		{
			interfazSucursal inter = new interfazSucursal(idSuc);
			inter.setVisible(true);	
		}
		else
		{
			throw new Exception("No existe una sucursal con ese Id");
		}
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
	public void agregarCompra(String pCantidad, String pIdProduSucu,String pIdCliente,String pidFactura,String pTotal)
	{
		inter.agregarCompra( pCantidad, pIdProduSucu, pIdCliente, pidFactura, pTotal);
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

}
