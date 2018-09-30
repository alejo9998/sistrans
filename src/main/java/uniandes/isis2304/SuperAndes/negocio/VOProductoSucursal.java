package uniandes.isis2304.SuperAndes.negocio;

public interface VOProductoSucursal extends VOProducto {
	
	public int getNivelReorden();
	
	public double getPrecioUnitario();
	
	public int getCantidadBodega();
	
	public int getCantidadEstante();
	
	public double getPrecioUnidadMedida();
	
	public Bodega getBodega();
	
	public Estante getEstante();
	
	public Promocion getPromocion();
	
	@Override
	public String toString();

}
