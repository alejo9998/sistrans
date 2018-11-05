package uniandes.isis2304.SuperAndes.negocio;

public class Cliente implements VOCliente{
	
	private long identificacion;
	
	private String nombre;
	
	private String correo;
	
	private String direccion;
	
	public Cliente() {
		this.identificacion = 0;
		this.nombre = "";
		this.correo = "";
		this.direccion = "";
	}

	public Cliente(long identificacion, String nombre, String correo, String direccion) {
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.correo = correo;
		this.direccion = direccion;
	}

	public long getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(long identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@Override
	public String toString() {
		return "Cliente [identificacion=" + identificacion + ", nombre=" + nombre + ", correo=" + correo + 
				", direccion=" + direccion + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (identificacion != other.identificacion)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	
	

}
