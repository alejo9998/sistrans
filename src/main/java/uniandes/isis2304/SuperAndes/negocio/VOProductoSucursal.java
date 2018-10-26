package uniandes.isis2304.SuperAndes.negocio;

public interface VOProductoSucursal {
	
	public long getIdProductoSucursal();
	
	public String getNombre();
	
	public String getMarca();
	
	public String getPresentacion();
	
	public double getCantidadPresentacion();
	
	public String getUnidadMedida();
	
	public double getVolumenEmpaque();
	
	public double getPesoEmpaque();
	
	public long getCodigoBarras();
	
	public String getCategoria();
	
	public String getTipo();
	
	public String getFechaVencimiento();
	
	public int getNivelReorden();
	
	public double getPrecioUnitario();
	
	public int getCantidadBodega();
	
	public int getCantidadEstante();
	
	public double getPrecioUnidadMedida();
	
	public long getBodega();
	
	public long getEstante();
	
	public Long getPromocion();
	
	@Override
	public String toString();

}
