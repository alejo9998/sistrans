package uniandes.isis2304.SuperAndes.negocio;

public interface VOCompra {
	
	public long getIdCompra();
	
	public String getFecha();
	
	public int getCantidad();
	
	public ProductoSucursal getProductoSucursal();
	
	public Cliente getCliente();
	
	public Factura getFactura();
	
	@Override
	public String toString();

}
