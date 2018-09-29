package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

public class Bodega 
{
	private Double volumen;
	
	private Double peso;
	
	private Long idBodega;
	
	private String tipo;
	
	private List<ProductoSucursal> productos;

	public Double getVolumen() {
		return volumen;
	}

	public void setVolumen(Double volumen) {
		this.volumen = volumen;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Long getIdBodega() {
		return idBodega;
	}

	public void setIdBodega(Long idBodega) {
		this.idBodega = idBodega;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<ProductoSucursal> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoSucursal> productos) {
		this.productos = productos;
	}
	
	public void addProducto(ProductoSucursal p)
	{
		if(productos==null)
		{
			productos= new ArrayList<>();
		}
		productos.add(p);
	}

}
