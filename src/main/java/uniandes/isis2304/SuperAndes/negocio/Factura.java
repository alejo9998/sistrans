package uniandes.isis2304.SuperAndes.negocio;

public class Factura implements VOFactura{
	
	private long idFactura;
	
	private String descripcion;
	
	public Factura() {
		this.idFactura = 0;
		this.descripcion = "";
	}

	public Factura(long idFactura, String descripcion) {
		this.idFactura = idFactura;
		this.descripcion = descripcion;
	}

	public long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", descripcion=" + descripcion + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (idFactura != other.idFactura)
			return false;
		return true;
	}

	
}
