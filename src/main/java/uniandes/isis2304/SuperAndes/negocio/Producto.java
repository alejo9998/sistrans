package uniandes.isis2304.SuperAndes.negocio;


public class Producto implements VOProducto{
	
	protected long idProducto;
	protected String nombre;
	protected String marca;
	protected String presentacion;
	protected double cantidadPresentacion;
	protected String unidadMedida;
	protected double volumenEmpaque;
	protected double pesoEmpaque;
	protected long codigoBarras;
	protected String categoria;
	protected String tipo;
	protected String fechaVencimiento;
	
	
	/**
	 * Contructor por defecto
	 */
	public Producto() {
		this.idProducto = 0;
		this.nombre = "";
		this.marca = "";
		this.presentacion = "";
		this.cantidadPresentacion = 0;
		this.unidadMedida = "";
		this.volumenEmpaque = 0;
		this.pesoEmpaque = 0;
		this.codigoBarras = 0;
		this.categoria = "";
		this.tipo = "";
		this.fechaVencimiento = "";
	}

	/**
	 * Contructor con valores
	 * @param id
	 * @param nombre
	 * @param marca
	 * @param presentacion
	 * @param cantidadPresentacion
	 * @param unidadMedida
	 * @param volumenEmpaque
	 * @param pesoEmpaque
	 * @param codigoBarras
	 * @param categoria
	 * @param tipo
	 * @param fechaVencimiento
	 */
	public Producto(long id, String nombre, String marca, String presentacion, double cantidadPresentacion,
			String unidadMedida, double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria,
			String tipo, String fechaVencimiento) {
		this.idProducto = id;
		this.nombre = nombre;
		this.marca = marca;
		this.presentacion = presentacion;
		this.cantidadPresentacion = cantidadPresentacion;
		this.unidadMedida = unidadMedida;
		this.volumenEmpaque = volumenEmpaque;
		this.pesoEmpaque = pesoEmpaque;
		this.codigoBarras = codigoBarras;
		this.categoria = categoria;
		this.tipo = tipo;
		this.fechaVencimiento = fechaVencimiento;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setId(long id) {
		this.idProducto = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public double getCantidadPresentacion() {
		return cantidadPresentacion;
	}

	public void setCantidadPresentacion(double cantidadPresentacion) {
		this.cantidadPresentacion = cantidadPresentacion;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public double getVolumenEmpaque() {
		return volumenEmpaque;
	}

	public void setVolumenEmpaque(double volumenEmpaque) {
		this.volumenEmpaque = volumenEmpaque;
	}

	public double getPesoEmpaque() {
		return pesoEmpaque;
	}

	public void setPesoEmpaque(double pesoEmpaque) {
		this.pesoEmpaque = pesoEmpaque;
	}

	public long getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(long codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", marca=" + marca + ", presentacion="+ presentacion + ", cantidadPresentacion=" + cantidadPresentacion +
				", unidadMedida=" + unidadMedida + ", volumenEmpaque=" + volumenEmpaque + ", pesoEmpaque=" + pesoEmpaque + ", codigoBarras=" + codigoBarras + ", categoria=" + categoria + ", tipo=" + tipo + ", fechaVencimiento=" + fechaVencimiento + 
				"]";
	}

}
