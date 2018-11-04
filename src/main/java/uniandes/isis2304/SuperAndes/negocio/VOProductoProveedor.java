package uniandes.isis2304.SuperAndes.negocio;

import java.sql.Date;
import java.sql.Timestamp;

public interface VOProductoProveedor {

	public long getIdProductoProveedor();

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

	public Timestamp getFechaVencimiento();

	public double getCalidad();

	public double getPrecio();

	public int getNumeroCalificaciones();

	public double getSumaCalificaciones();

	public long getProveedor();
	
	@Override
	public String toString();

}
