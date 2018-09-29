package uniandes.isis2304.parranderos.negocio;

public class OrdenPedido 
{
private Double precio;
private String fechaDeEntrega;
private String fechaesperada;
private Double calificacion;
private Boolean entregado;
private Integer cantidad;
private Long idOrdenPedido;
private Sucursal sucursal;
private ProductoProveedor productoProveedor;

public Double getPrecio() {
	return precio;
}
public void setPrecio(Double precio) {
	this.precio = precio;
}
public String getFechaDeEntrega() {
	return fechaDeEntrega;
}
public void setFechaDeEntrega(String fechaDeEntrega) {
	this.fechaDeEntrega = fechaDeEntrega;
}
public String getFechaesperada() {
	return fechaesperada;
}
public void setFechaesperada(String fechaesperada) {
	this.fechaesperada = fechaesperada;
}
public Double getCalificacion() {
	return calificacion;
}
public void setCalificacion(Double calificacion) {
	this.calificacion = calificacion;
}
public Boolean getEntregado() {
	return entregado;
}
public void setEntregado(Boolean entregado) {
	this.entregado = entregado;
}
public Integer getCantidad() {
	return cantidad;
}
public void setCantidad(Integer cantidad) {
	this.cantidad = cantidad;
}
public Long getIdOrdenPedido() {
	return idOrdenPedido;
}
public void setIdOrdenPedido(Long idOrdenPedido) {
	this.idOrdenPedido = idOrdenPedido;
}
public Sucursal getSucursal() {
	return sucursal;
}
public void setSucursal(Sucursal sucursal) {
	this.sucursal = sucursal;
}
public ProductoProveedor getProductoProveedor() {
	return productoProveedor;
}
public void setProductoProveedor(ProductoProveedor productoProveedor) {
	this.productoProveedor = productoProveedor;
}
}
