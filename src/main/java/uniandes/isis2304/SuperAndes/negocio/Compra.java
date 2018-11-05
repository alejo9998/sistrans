package uniandes.isis2304.SuperAndes.negocio;

import java.sql.Timestamp;

public class Compra implements VOCompra{
	
	private long idCompra;
	
	private Timestamp fecha;
	
	private int cantidad;
	
	private double totalPagado;
	
	private long productoSucursal;
	
	private long cliente;
	
	private long factura;
	
	public Compra() {
		this.idCompra = 0;
		this.fecha = null;
		this.cantidad = 0;
		this.totalPagado = 0;
		this.productoSucursal = 0;
		this.cliente = 0;
		this.factura = 0;
	}

	public Compra(long idCompra, Timestamp fecha, int cantidad, double totalPagado, long productoSucursal, long cliente,
			long factura) {
		this.idCompra = idCompra;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.totalPagado = totalPagado;
		this.productoSucursal = productoSucursal;
		this.cliente = cliente;
		this.factura = factura;
	}

	public long getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(long idCompra) {
		this.idCompra = idCompra;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public long getProductoSucursal() {
		return productoSucursal;
	}

	public void setProductoSucursal(long productoSucursal) {
		this.productoSucursal = productoSucursal;
	}

	public long getCliente() {
		return cliente;
	}

	public void setCliente(long cliente) {
		this.cliente = cliente;
	}

	public long getFactura() {
		return factura;
	}

	public void setFactura(long factura) {
		this.factura = factura;
	}
	
	public double getTotalPagado() {
		return totalPagado;
	}
	
	public void setTotalPagado(double totalPagado) {
		this.totalPagado = totalPagado;
	}
	
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", fecha=" + fecha + ", cantidad=" + cantidad + ", totalPagado=" + totalPagado +", productoSucursal=" + productoSucursal + ", cliente=" + cliente + ", factura=" + factura + 
				"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compra other = (Compra) obj;
		if (cantidad != other.cantidad)
			return false;
		if (cliente != other.cliente)
			return false;
		if (factura != other.factura)
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (idCompra != other.idCompra)
			return false;
		if (productoSucursal != other.productoSucursal)
			return false;
		if (Double.doubleToLongBits(totalPagado) != Double.doubleToLongBits(other.totalPagado))
			return false;
		return true;
	} 
	
	

}
