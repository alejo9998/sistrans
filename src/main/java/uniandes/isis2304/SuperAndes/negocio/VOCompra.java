package uniandes.isis2304.SuperAndes.negocio;

public interface VOCompra {
	
	public long getIdCompra();
	
	public String getFecha();
	
	public int getCantidad();
	
	public double getTotalPagado();
	
	public long getProductoSucursal();
	
	public long getCliente();
	
	public long getFactura();
	
	@Override
	public String toString();

}
