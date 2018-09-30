package uniandes.isis2304.SuperAndes.negocio;


public interface VOProducto {
	
	public long getIdProducto();
	
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
	
	@Override
	public String toString();

}
