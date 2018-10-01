package uniandes.isis2304.SuperAndes.negocio;

public interface VOProductoSucursal extends VOProducto {
	
	public int getNivelReorden();
	
	public double getPrecioUnitario();
	
	public int getCantidadBodega();
	
	public int getCantidadEstante();
	
	public double getPrecioUnidadMedida();
	
	public long getBodega();
	
	public long getEstante();
	
	public long getPromocion();
	
	@Override
	public String toString();

}
