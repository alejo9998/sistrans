package uniandes.isis2304.SuperAndes.negocio;

import java.util.Calendar;

public class Sucursal implements VOSucursal{
	
	private long idSucursal;
	
	private String ciudad;
	
	private String direccion;
	
	private String nombre;
	
	
	
	public Sucursal() {
		this.idSucursal = 0;
		this.ciudad = "";
		this.direccion = "";
		this.nombre = "";
	}

	public Sucursal(long idSucursal, String ciudad, String direccion, String nombre) {
		this.idSucursal = idSucursal;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.nombre = nombre;
	}

	public long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Sucursal [idSucursal=" + idSucursal + ", ciudad=" + ciudad + ", direccion=" + direccion + ", nombre=" + nombre + 
				"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sucursal other = (Sucursal) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (idSucursal != other.idSucursal)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	

}
