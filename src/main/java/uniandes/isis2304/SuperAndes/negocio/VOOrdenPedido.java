package uniandes.isis2304.SuperAndes.negocio;

import java.sql.Timestamp;

public interface VOOrdenPedido {
	
	public long getIdOrdenPedido();
	
	public double getPrecio();
	
	public Timestamp getFechaEntrega();
	
	public Timestamp getFechaEsperadaEntrega();
	
	public Double getCalificacion();
	
	public int getEntregado();
	
	public int getCantidad();
	
	public long getProductoProveedor();
	
	public long getSucursal();
	
	@Override
	public String toString();

}
