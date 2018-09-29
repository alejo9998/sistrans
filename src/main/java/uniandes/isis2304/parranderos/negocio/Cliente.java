package uniandes.isis2304.parranderos.negocio;

import java.util.ArrayList;
import java.util.List;

public class Cliente 
{
	private Long identificacion;

	private String nombre;

	private String correo;

	private String direccion;

	private List<Compra> compras;

	public Long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(Long identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void addCompra( Compra c)
	{
		if(compras==null)
		{
			compras= new ArrayList<>();
		}
		compras.add(c);
	}


}
