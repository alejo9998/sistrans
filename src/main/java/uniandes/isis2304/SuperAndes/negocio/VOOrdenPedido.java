package uniandes.isis2304.SuperAndes.negocio;

public interface VOOrdenPedido {
	
	public long getIdOrdenPedido();
	
	public double getPrecio();
	
	public String getFechaEntrega();
	
	public String getFechaEsperadaEntrega();
	
	public double getCalificacion();
	
	public boolean getEntregado();
	
	public int getCantidad();
	
	public ProductoProveedor getProductoProveedor();
	
	public Sucursal getSucursal();
	
	@Override
	public String toString();

}
