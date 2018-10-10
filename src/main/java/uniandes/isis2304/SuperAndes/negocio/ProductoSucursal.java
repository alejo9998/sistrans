package uniandes.isis2304.SuperAndes.negocio;


public class ProductoSucursal implements VOProductoSucursal {

	private long idProductoSucursal;
	private String nombre;
	private String marca;
	private String presentacion;
	private double cantidadPresentacion;
	private String unidadMedida;
	private double volumenEmpaque;
	private double pesoEmpaque;
	private long codigoBarras;
	private String categoria;
	private String tipo;
	private String fechaVencimiento;

	private int nivelReorden;

	private double precioUnitario;

	private int cantidadBodega;

	private int cantidadEstante;

	private double precioUnidadMedida;

	private long bodega;

	private long estante;

	private long promocion;

	public ProductoSucursal() {
		this.idProductoSucursal = 0;
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
		this.nivelReorden = 0;
		this.precioUnitario = 0;
		this.cantidadBodega = 0;
		this.cantidadEstante = 0;
		this.precioUnidadMedida = 0;
		this.bodega = 0;
		this.estante = 0;
		this.promocion = 0;
		// TODO Auto-generated constructor stub
	}



	public ProductoSucursal(long id, String nombre, String marca, String presentacion, double cantidadPresentacion,
			String unidadMedida, double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria,
			String tipo, String fechaVencimiento,int nivelReorden, double precioUnitario, int cantidadBodega, int cantidadEstante,
			double precioUnidadMedida, long bodega, long estante, long promocion) {
		this.idProductoSucursal = id;
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
		this.nivelReorden = nivelReorden;
		this.precioUnitario = precioUnitario;
		this.cantidadBodega = cantidadBodega;
		this.cantidadEstante = cantidadEstante;
		this.precioUnidadMedida = precioUnidadMedida;
		this.bodega = bodega;
		this.estante = estante;
		this.promocion = promocion;
	}

	public long getIdProductoSucursal() {
		return idProductoSucursal;
	}

	public void setIdProductoSucursal(long idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
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

	public int getNivelReorden() {
		return nivelReorden;
	}

	public void setNivelReorden(int nivelReorden) {
		this.nivelReorden = nivelReorden;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getCantidadBodega() {
		return cantidadBodega;
	}

	public void setCantidadBodega(int cantidadBodega) {
		this.cantidadBodega = cantidadBodega;
	}

	public int getCantidadEstante() {
		return cantidadEstante;
	}

	public void setCantidadEstante(int cantidadEstante) {
		this.cantidadEstante = cantidadEstante;
	}

	public double getPrecioUnidadMedida() {
		return precioUnidadMedida;
	}

	public void setPrecioUnidadMedida(double precioUnidadMedida) {
		this.precioUnidadMedida = precioUnidadMedida;
	}

	public long getBodega() {
		return bodega;
	}

	public void setBodega(long bodega) {
		this.bodega = bodega;
	}

	public long getEstante() {
		return estante;
	}

	public void setEstante(long estante) {
		this.estante = estante;
	}

	public long getPromocion() {
		return promocion;
	}

	public void setPromocion(long promocion) {
		this.promocion = promocion;
	}

	@Override
	public String toString() {
		return "ProductoSucursal [idProductoSucursal=" + idProductoSucursal + ", nombre=" + nombre + ", marca=" + marca + ", presentacion="+ presentacion + ", cantidadPresentacion=" + cantidadPresentacion +
				", unidadMedida=" + unidadMedida + ", volumenEmpaque=" + volumenEmpaque + ", pesoEmpaque=" + pesoEmpaque + ", codigoBarras=" + codigoBarras + ", categoria=" + categoria + ", tipo=" + tipo + ", fechaVencimiento=" + fechaVencimiento + 
				", nivelReorden=" + nivelReorden + ", precioUnitario=" + precioUnitario + ", cantidadBodega=" + cantidadBodega + ", cantidadEstante=" + cantidadEstante + ", precioUnidadMedida=" + precioUnidadMedida + ", bodega=" + bodega +
				", estante=" + estante + ", promocion=" + promocion +"]";
	}

}
