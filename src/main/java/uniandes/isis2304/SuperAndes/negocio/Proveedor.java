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
	
	public boolean equals(Object proveedor) {
		Proveedor p = (Proveedor) proveedor;
		return nit == p.getNit() && nombre.equalsIgnoreCase(p.getNombre());
	}
	
	

}
