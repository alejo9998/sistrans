package uniandes.isis2304.SuperAndes.negocio;

public interface VOProductoProveedor extends VOProducto {
	
	public double getCalidad();
	
	public double getPrecio();
	
	public int getNumeroCalificaciones();
	
	public double getSumaCalificaciones();
	
	public long getProveedor();

}
