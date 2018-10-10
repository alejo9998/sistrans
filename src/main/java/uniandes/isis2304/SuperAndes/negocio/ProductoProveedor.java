package uniandes.isis2304.SuperAndes.negocio;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class ProductoProveedor implements VOProductoProveedor {
	
	private double calidad;
	
	private double precio;
	
	private int numeroCalificaciones;
	
	private double sumaCalificaciones;
	
	private long proveedor;
	
	private long idProductoProveedor;
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

	public ProductoProveedor() {
		this.idProductoProveedor = 0;
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
		this.calidad = 0;
		this.precio = 0;
		this.numeroCalificaciones = 0;
		this.sumaCalificaciones = 0;
		proveedor = 0;
		// TODO Auto-generated constructor stub
	}

	public ProductoProveedor(long id, String nombre, String marca, String presentacion, double cantidadPresentacion,
			String unidadMedida, double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria,
			String tipo, String fechaVencimiento,double calidad, double precio, int numeroCalificaciones, double sumaCalificaciones, long proveedor) {
		this.idProductoProveedor = id;
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

	public long getProveedor() {
		return proveedor;
	}

	public void setProveedor(long proveedor) {
		this.proveedor = proveedor;
	}

	public long getIdProductoProveedor() {
		return idProductoProveedor;
	}

	public void setIdProductoProveedor(long idProductoProveedor) {
		this.idProductoProveedor = idProductoProveedor;
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
	public String toString()
	{
		return "ProductoProveedor [idProductoProveedor=" + idProductoProveedor + ", nombre=" + nombre + ", marca=" + marca + ", presentacion="+ presentacion + ", cantidadPresentacion=" + cantidadPresentacion +
				", unidadMedida=" + unidadMedida + ", volumenEmpaque=" + volumenEmpaque + ", pesoEmpaque=" + pesoEmpaque + ", codigoBarras=" + codigoBarras + ", categoria=" + categoria + ", tipo=" + tipo + ", fechaVencimiento=" + fechaVencimiento + 
				", calidad=" + calidad + ", precio=" + precio + ", numeroCalificaciones=" + numeroCalificaciones + ", sumaCalificaciones=" + sumaCalificaciones + ", proveedor=" + proveedor
				+ "]";
	}
}

