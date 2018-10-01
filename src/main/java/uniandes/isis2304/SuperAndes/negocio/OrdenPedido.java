package uniandes.isis2304.SuperAndes.negocio;

import java.util.Calendar;

public class OrdenPedido implements VOOrdenPedido{
	
	private long idOrdenPedido;
	
	private double precio;
	
	private String fechaEntrega;
	
	private String fechaEsperadaEntrega;
	
	private double calificacion;
	
	private int entregado;
	
	private int cantidad;
	
	private ProductoProveedor productoProveedor;
	
	private Sucursal sucursal;
	
	public OrdenPedido() {
		this.idOrdenPedido = 0;
		this.precio = 0;
		this.fechaEntrega = "";
		this.fechaEsperadaEntrega = "";
		this.calificacion = 0;
		this.entregado = 0;
		this.cantidad = 0;
		this.productoProveedor = null;
		this.sucursal = null;
	}

	public OrdenPedido(long idOrdenPedido, double precio, String fechaEntrega, String fechaEsperadaEntrega, double calificacion,
			int entregado, int cantidad, ProductoProveedor productoProveedor, Sucursal sucursal) {
		this.idOrdenPedido = idOrdenPedido;
		this.precio = precio;
		this.fechaEntrega = fechaEntrega;
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
		this.calificacion = calificacion;
		this.entregado = entregado;
		this.cantidad = cantidad;
		this.productoProveedor = productoProveedor;
		this.sucursal = sucursal;
	}

	public long getIdOrdenPedido() {
		return idOrdenPedido;
	}

	public void setIdOrdenPedido(long idOrdenPedido) {
		this.idOrdenPedido = idOrdenPedido;
	}

	public String getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getFechaEsperadaEntrega() {
		return fechaEsperadaEntrega;
	}

	public void setFechaEsperadaEntrega(String fechaEsperadaEntrega) {
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
	}

	public double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}

	public int getEntregado() {
		return entregado;
	}

	public void setEntregado(int entregado) {
		this.entregado = entregado;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ProductoProveedor getProductoProveedor() {
		return productoProveedor;
	}

	public void setProductoProveedor(ProductoProveedor productoProveedor) {
		this.productoProveedor = productoProveedor;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "OrdenPedido [idOrdenPedido=" + idOrdenPedido +", precio=" + precio + ", fechaEntrega=" + fechaEntrega + ", fechaEsperadaEntrega=" + fechaEsperadaEntrega + ", calificacion=" + calificacion +
				", entregado=" + entregado + ", cantidad=" + cantidad + ", productoProveedor=" + productoProveedor.getIdProducto() + ", sucursal=" + sucursal.getIdSucursal() + 
				"]";
	}
	
}
