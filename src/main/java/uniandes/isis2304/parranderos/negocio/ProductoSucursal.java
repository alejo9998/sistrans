package uniandes.isis2304.parranderos.negocio;

public class ProductoSucursal extends Producto 
{
	private Integer nivelOrden;

	private Double precioUnitario;

	private Integer cantidadDeBodega;

	private Integer cantidadEstante;

	private Double precioUnidadMedida;

	private Long idProductoSucursal;

	private Promocion promocion;

	private Bodega bodega;

	private Estante estante;

	public Integer getNivelOrden() {
		return nivelOrden;
	}

	public void setNivelOrden(Integer nivelOrden) {
		this.nivelOrden = nivelOrden;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Integer getCantidadEstante() {
		return cantidadEstante;
	}

	public void setCantidadEstante(Integer cantidadEstante) {
		this.cantidadEstante = cantidadEstante;
	}

	public Integer getCantidadDeBodega() {
		return cantidadDeBodega;
	}

	public void setCantidadDeBodega(Integer cantidadDeBodega) {
		this.cantidadDeBodega = cantidadDeBodega;
	}

	public Long getIdProductoSucursal() {
		return idProductoSucursal;
	}

	public void setIdProductoSucursal(Long idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
	}

	public Double getPrecioUnidadMedida() {
		return precioUnidadMedida;
	}

	public void setPrecioUnidadMedida(Double precioUnidadMedida) {
		this.precioUnidadMedida = precioUnidadMedida;
	}

	public Promocion getPromocion() {
		return promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
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

}
