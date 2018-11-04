package uniandes.isis2304.SuperAndes.negocio;

import java.sql.Timestamp;

public interface VOPromocion {
	
	public long getIdPromocion();
	
	public int getTipo();
	
	public double getN();
	
	public double getM();
	
	public Timestamp getFechaCaducidad();
	
	@Override
	public String toString();

}
