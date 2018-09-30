package uniandes.isis2304.SuperAndes.negocio;

public interface VOBodega {
	
	public long getIdBodega();
	
	public double getVolumen();
	
	public double getPeso();
	
	public String getTipo();
	
	public Sucursal getSucursal();
	
	@Override
	public String toString();

}
