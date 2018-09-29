package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Promocion 
{
	public final static String TIPO_I="tipo_1";
	public final static String TIPO_II="tipo_2";
	public final static String TIPO_III="tipo_3";
	public final static String TIPO_IV="tipo_4";
	public final static String TIPO_V="tipo_5";

	private Integer tipo;
	
	private Double n;
	private Double m;
	private String feachDeCaducidad;
	private Long idPromocion;
	private List<ProductoSucursal> productos;
	
	
	public Integer getTipo()
	{
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public Double getN() {
		return n;
	}
	public void setN(Double n) {
		this.n = n;
	}
	public Double getM() {
		return m;
	}
	public void setM(Double m) {
		this.m = m;
	}
	public String getFeachDeCaducidad() {
		return feachDeCaducidad;
	}
	public void setFeachDeCaducidad(String feachDeCaducidad) {
		this.feachDeCaducidad = feachDeCaducidad;
	}
	public Long getIdPromocion() {
		return idPromocion;
	}
	public void setIdPromocion(Long idPromocion) {
		this.idPromocion = idPromocion;
	}
	
	public void addProducto(ProductoSucursal p)
	{
		if(productos==null)
		{
			productos= new ArrayList<>();
		}
		
		productos.add(p);
	}
	
	public List<ProductoSucursal> getPorudctos()
	{
		return productos;
	}
}
