package uniandes.isis2304.SuperAndes.negocio;

public interface VOOrdenPedido {
	
	public long getIdOrdenPedido();
	
	public double getPrecio();
	
	public String getFechaEntrega();
	
	public String getFechaEsperadaEntrega();
	
	public Double getCalificacion();
	
	public int getEntregado();
	
	public int getCantidad();
	
	public long getProductoProveedor();
	
	public long getSucursal();
	
	@Override
	public String toString();

}
