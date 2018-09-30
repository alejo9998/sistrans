package uniandes.isis2304.SuperAndes.negocio;

public interface VOPromocion {
	
	public long getIdPromocion();
	
	public int getTipo();
	
	public double getN();
	
	public double getM();
	
	public String getFechaCaducidad();
	
	@Override
	public String toString();

}
