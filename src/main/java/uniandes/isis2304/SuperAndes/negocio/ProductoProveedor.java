package uniandes.isis2304.SuperAndes.negocio;


public class ProductoProveedor extends Producto implements VOProductoProveedor {
	
	public double calidad;
	
	public double precio;
	
	public int numeroCalificaciones;
	
	public double sumaCalificaciones;
	
	public Proveedor proveedor;

	public ProductoProveedor() {
		super();
		this.calidad = 0;
		this.precio = 0;
		this.numeroCalificaciones = 0;
		this.sumaCalificaciones = 0;
		proveedor = null;
		// TODO Auto-generated constructor stub
	}

	public ProductoProveedor(long id, String nombre, String marca, String presentacion, double cantidadPresentacion,
			String unidadMedida, double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria,
			String tipo, String fechaVencimiento,double calidad, double precio, int numeroCalificaciones, double sumaCalificaciones, Proveedor proveedor) {
		super(id, nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras,
				categoria, tipo, fechaVencimiento);
		this.calidad = calidad;
		this.precio = precio;
		this.numeroCalificaciones = numeroCalificaciones;
		this.sumaCalificaciones = sumaCalificaciones;
		this.proveedor = proveedor;
	}

	public double getCalidad() {
		return calidad;
	}

	public void setCalidad(double calidad) {
		this.calidad = calidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getNumeroCalificaciones() {
		return numeroCalificaciones;
	}

	public void setNumeroCalificaciones(int numeroCalificaciones) {
		this.numeroCalificaciones = numeroCalificaciones;
	}

	public double getSumaCalificaciones() {
		return sumaCalificaciones;
	}

	public void setSumaCalificaciones(double sumaCalificaciones) {
		this.sumaCalificaciones = sumaCalificaciones;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	@Override
	public String toString()
	{
		return "ProductoProveedor [idProductoProveedor=" + idProducto + ", nombre=" + nombre + ", marca=" + marca + ", presentacion="+ presentacion + ", cantidadPresentacion=" + cantidadPresentacion +
				", unidadMedida=" + unidadMedida + ", volumenEmpaque=" + volumenEmpaque + ", pesoEmpaque=" + pesoEmpaque + ", codigoBarras=" + codigoBarras + ", categoria=" + categoria + ", tipo=" + tipo + ", fechaVencimiento=" + fechaVencimiento + 
				", calidad=" + calidad + ", precio=" + precio + ", numeroCalificaciones=" + numeroCalificaciones + ", sumaCalificaciones=" + sumaCalificaciones + ", proveedor=" + proveedor.getNit()
				+ "]";
	}
}
