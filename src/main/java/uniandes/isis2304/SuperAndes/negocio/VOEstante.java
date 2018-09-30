package uniandes.isis2304.SuperAndes.negocio;

public interface VOEstante {
	
	public long getIdEstante();
	
	public double getVolumen();
	
	public double getPeso();
	
	public String getTipo();
	
	public int getNivelAprovisionamiento();
	
	public Sucursal getSucursal();
	
	@Override
	public String toString();

}
