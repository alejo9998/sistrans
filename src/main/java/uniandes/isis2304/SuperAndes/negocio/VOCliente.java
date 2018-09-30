package uniandes.isis2304.SuperAndes.negocio;

public interface VOCliente {
	
	public long getIdentificacion();
	
	public String getNombre();
	
	public String getCorreo();
	
	public String getDireccion();
	
	@Override
	public String toString();

}
