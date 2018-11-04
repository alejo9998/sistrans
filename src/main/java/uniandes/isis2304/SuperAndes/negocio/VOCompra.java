package uniandes.isis2304.SuperAndes.negocio;

import java.sql.Timestamp;

public interface VOCompra {
	
	public long getIdCompra();
	
	public Timestamp getFecha();
	
	public int getCantidad();
	
	public double getTotalPagado();
	
	public long getProductoSucursal();
	
	public long getCliente();
	
	public long getFactura();
	
	@Override
	public String toString();

}
