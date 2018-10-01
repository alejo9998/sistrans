package uniandes.isis2304.SuperAndes.negocio;

public class Compra implements VOCompra{
	
	private long idCompra;
	
	private String fecha;
	
	private int cantidad;
	
	private long productoSucursal;
	
	private long cliente;
	
	private long factura;
	
	public Compra() {
		this.idCompra = 0;
		this.fecha = "";
		this.cantidad = 0;
		this.productoSucursal = 0;
		this.cliente = 0;
		this.factura = 0;
	}

	public Compra(long idCompra, String fecha, int cantidad, long productoSucursal, long cliente,
			long factura) {
		this.idCompra = idCompra;
		this.fecha = fecha;
		this.cantidad = cantidad;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
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
	
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", fecha=" + fecha + ", cantidad=" + cantidad + ", productoSucursal=" + productoSucursal + ", cliente=" + cliente + ", factura=" + factura + 
				"]";
	} 

}
