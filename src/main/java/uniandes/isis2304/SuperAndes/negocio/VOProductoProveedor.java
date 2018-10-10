package uniandes.isis2304.SuperAndes.negocio;

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

	public String getFechaVencimiento();

	public double getCalidad();

	public double getPrecio();

	public int getNumeroCalificaciones();

	public double getSumaCalificaciones();

	public long getProveedor();

}
