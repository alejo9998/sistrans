package uniandes.isis2304.SuperAndes.negocio;


public class ProductoSucursal extends Producto implements VOProductoSucursal {
	
	private int nivelReorden;
	
	private double precioUnitario;
	
	private int cantidadBodega;
	
	private int cantidadEstante;
	
	private double precioUnidadMedida;
	
	private Bodega bodega;
	
	private Estante estante;
	
	private Promocion promocion;

	public ProductoSucursal() {
		super();
		this.nivelReorden = 0;
		this.precioUnitario = 0;
		this.cantidadBodega = 0;
		this.cantidadEstante = 0;
		this.precioUnidadMedida = 0;
		this.bodega = null;
		this.estante = null;
		this.promocion = null;
		// TODO Auto-generated constructor stub
	}

	

	public ProductoSucursal(long id, String nombre, String marca, String presentacion, double cantidadPresentacion,
			String unidadMedida, double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria,
			String tipo, String fechaVencimiento,int nivelReorden, double precioUnitario, int cantidadBodega, int cantidadEstante,
			double precioUnidadMedida, Bodega bodega, Estante estante, Promocion promocion) {
		super(id, nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras,
				categoria, tipo, fechaVencimiento);
		this.nivelReorden = nivelReorden;
		this.precioUnitario = precioUnitario;
		this.cantidadBodega = cantidadBodega;
		this.cantidadEstante = cantidadEstante;
		this.precioUnidadMedida = precioUnidadMedida;
		this.bodega = bodega;
		this.estante = estante;
		this.promocion = promocion;
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
	
	public Bodega getBodega() {
		return bodega;
	}
	
	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
	}
	
	public Estante getEstante() {
		return estante;
	}
	
	public void setEstante(Estante estante) {
		this.estante = estante;
	}
	
	public Promocion getPromocion() {
		return promocion;
	}
	
	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}
	
	@Override
	public String toString() {
		return "ProductoSucursal [idProductoSucursal=" + idProducto + ", nombre=" + nombre + ", marca=" + marca + ", presentacion="+ presentacion + ", cantidadPresentacion=" + cantidadPresentacion +
				", unidadMedida=" + unidadMedida + ", volumenEmpaque=" + volumenEmpaque + ", pesoEmpaque=" + pesoEmpaque + ", codigoBarras=" + codigoBarras + ", categoria=" + categoria + ", tipo=" + tipo + ", fechaVencimiento=" + fechaVencimiento + 
				", nivelReorden=" + nivelReorden + ", precioUnitario=" + precioUnitario + ", cantidadBodega=" + cantidadBodega + ", cantidadEstante=" + cantidadEstante + ", precioUnidadMedida=" + precioUnidadMedida + ", bodega=" + bodega.getIdBodega() +
				", estante=" + estante.getIdEstante() + ", promocion=" + promocion.getIdPromocion() +"]";
	}
	
}
