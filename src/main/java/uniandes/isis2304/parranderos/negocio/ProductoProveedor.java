package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;
import java.util.List;

public class ProductoProveedor extends Producto
{

	private Double calidad;
	
	private Double precio;
	
	private Long idProductoProveedor;
	
	private Integer numeroCalificaciones;
	
	private Double sumaCalificaciones;
	
	private Proveedor proveedores;
	
	private List<OrdenPedido> ordenes;
	
	
	public ProductoProveedor(String pNombre, String pMarca, String pPresentacion, Double pcantidadPresentacion,
			String punidadDemedida, Double pVolumenEmpaque, Double pPesoEmpaque, String pCodigoDeBarras,
			String pCategoria, String pTipo, String pFechaDeVencimiento, Long pId)
	{
		
//		super(pNombre, pMarca, pPresentacion, pcantidadPresentacion, punidadDemedida, pVolumenEmpaque, pPesoEmpaque,
//				pCodigoDeBarras, pCategoria, pTipo, pFechaDeVencimiento, pId);
//	
	}


	public Double getCalidad() {
		return calidad;
	}


	public void setCalidad(Double calidad) {
		this.calidad = calidad;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}


	public Long getIdProductoProveedor() {
		return idProductoProveedor;
	}


	public void setIdProductoProveedor(Long idProductoProveedor) {
		this.idProductoProveedor = idProductoProveedor;
	}


	public Integer getNumeroCalificaciones() {
		return numeroCalificaciones;
	}


	public void setNumeroCalificaciones(Integer numeroCalificaciones) {
		this.numeroCalificaciones = numeroCalificaciones;
	}


	public Double getSumaCalificaciones() {
		return sumaCalificaciones;
	}


	public void setSumaCalificaciones(Double sumaCalificaciones) {
		this.sumaCalificaciones = sumaCalificaciones;
	}


	public Proveedor getProveedores() {
		return proveedores;
	}


	public void setProveedores(Proveedor proveedores) {
		this.proveedores = proveedores;
	}
	public void addOrdenDePedido(OrdenPedido e)
	{
		if( ordenes==null)
		{
			ordenes=new ArrayList<>();
		}
		 ordenes.add(e);
	}

	public void deleteordendePedido(OrdenPedido b) throws Exception
	{
		if( ordenes==null)
		{
		throw new Exception("No se puede borrar");
		}
		ordenes.remove(b);
	}


}
