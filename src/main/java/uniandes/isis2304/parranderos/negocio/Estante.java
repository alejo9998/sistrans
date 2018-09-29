package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Estante
{
	private Double volumen;
	
	private Double peso;
	
	private String tipo;
	
	private Integer nivelDeAprovionamiento;
	
	private Long idEstante;
	
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getNivelDeAprovionamiento() {
		return nivelDeAprovionamiento;
	}

	public void setNivelDeAprovionamiento(Integer nivelDeAprovionamiento) {
		this.nivelDeAprovionamiento = nivelDeAprovionamiento;
	}

	public Long getIdEstante() {
		return idEstante;
	}

	public void setIdEstante(Long idEstante) {
		this.idEstante = idEstante;
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
