package uniandes.isis2304.SuperAndes.negocio;

import java.sql.Timestamp;

public class OrdenPedido implements VOOrdenPedido{
	
	private long idOrdenPedido;
	
	private double precio;
	
	private Timestamp fechaEntrega;
	
	private Timestamp fechaEsperadaEntrega;
	
	private Double calificacion;
	
	private int entregado;
	
	private int cantidad;
	
	private long productoProveedor;
	
	private long sucursal;
	
	public OrdenPedido() {
		this.idOrdenPedido = 0;
		this.precio = 0;
		this.fechaEntrega = null;
		this.fechaEsperadaEntrega = null;
		this.calificacion = 0D;
		this.entregado = 0;
		this.cantidad = 0;
		this.productoProveedor = 0;
		this.sucursal = 0;
	}

	public OrdenPedido(long idOrdenPedido, double precio, Timestamp fechaEntrega, Timestamp fechaEsperadaEntrega, Double calificacion,
			int entregado, int cantidad, long productoProveedor, long sucursal) {
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

	public Timestamp getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Timestamp getFechaEsperadaEntrega() {
		return fechaEsperadaEntrega;
	}

	public void setFechaEsperadaEntrega(Timestamp fechaEsperadaEntrega) {
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
	}

	public Double getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Double calificacion) {
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

	public long getProductoProveedor() {
		return productoProveedor;
	}

	public void setProductoProveedor(long productoProveedor) {
		this.productoProveedor = productoProveedor;
	}

	public long getSucursal() {
		return sucursal;
	}

	public void setSucursal(long sucursal) {
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
				", entregado=" + entregado + ", cantidad=" + cantidad + ", productoProveedor=" + productoProveedor + ", sucursal=" + sucursal + 
				"]";
	}
	
}
