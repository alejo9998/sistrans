package uniandes.isis2304.SuperAndes.negocio;

public class Proveedor implements VOProveedor{
	
	private long nit;
	
	private String nombre;
	
	public Proveedor() {
		this.nit = 0;
		this.nombre = "";
	}

	public Proveedor(long nit, String nombre) {
		this.nit = nit;
		this.nombre = nombre;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Proveedor [nit=" + nit + ", nombre=" + nombre + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proveedor other = (Proveedor) obj;
		if (nit != other.nit)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	

}
