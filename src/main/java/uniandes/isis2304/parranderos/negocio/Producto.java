package uniandes.isis2304.parranderos.negocio;

public abstract class Producto 
{
	private String nombre;
	
	private String marca;
	
	private String Presentacion;

	private Double cantidadPresentacion;
	
	private String unidadMedida;
	
	private Double volumenEmpaque;
	
	private Double pesoEmpaque;
	
	private String codigoBarras;
	
	private String categoria;
	
	private String tipo;
	
	private String fechaDeVencimiento;
	
	private Long id;
	
//	public Producto(String pNombre,String pMarca, String pPresentacion,Double pcantidadPresentacion,
//		String punidadDemedida, Double pVolumenEmpaque, Double pPesoEmpaque, String pCodigoDeBarras, String pCategoria,
//		String pTipo,String pFechaDeVencimiento, Long pId)
//	{
//		nombre = pNombre;
//		marca=pMarca;
//		Presentacion=pPresentacion;
//		cantidadPresentacion= pcantidadPresentacion;
//		unidadMedida=punidadDemedida;
//		volumenEmpaque=pVolumenEmpaque;
//		pesoEmpaque=pPesoEmpaque;
//		codigoBarras=pCodigoDeBarras;
//		categoria= pCategoria;
//		tipo=pTipo;
//		fechaDeVencimiento=pFechaDeVencimiento;
//		id=pId;
//		
//	}

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
		return Presentacion;
	}

	public void setPresentacion(String presentacion) {
		Presentacion = presentacion;
	}

	public Double getCantidadPresentacion() {
		return cantidadPresentacion;
	}

	public void setCantidadPresentacion(Double cantidadPresentacion) {
		this.cantidadPresentacion = cantidadPresentacion;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public Double getVolumenEmpaque() {
		return volumenEmpaque;
	}

	public void setVolumenEmpaque(Double volumenEmpaque) {
		this.volumenEmpaque = volumenEmpaque;
	}

	public Double getPesoEmpaque() {
		return pesoEmpaque;
	}

	public void setPesoEmpaque(Double pesoEmpaque) {
		this.pesoEmpaque = pesoEmpaque;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
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

	public String getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(String fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
