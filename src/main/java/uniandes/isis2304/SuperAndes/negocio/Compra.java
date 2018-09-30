package uniandes.isis2304.SuperAndes.negocio;

public class Compra implements VOCompra{
	
	private long idCompra;
	
	private String fecha;
	
	private int cantidad;
	
	private ProductoSucursal productoSucursal;
	
	private Cliente cliente;
	
	private Factura factura;
	
	public Compra() {
		this.idCompra = 0;
		this.fecha = "";
		this.cantidad = 0;
		this.productoSucursal = null;
		this.cliente = null;
		this.factura = null;
	}

	public Compra(long idCompra, String fecha, int cantidad, ProductoSucursal productoSucursal, Cliente cliente,
			Factura factura) {
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

	public ProductoSucursal getProductoSucursal() {
		return productoSucursal;
	}

	public void setProductoSucursal(ProductoSucursal productoSucursal) {
		this.productoSucursal = productoSucursal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", fecha=" + fecha + ", cantidad=" + cantidad + ", productoSucursal=" + productoSucursal.getIdProducto() + ", cliente=" + cliente.getIdentificacion() + ", factura=" + factura.getIdFactura() + 
				"]";
	} 

}
