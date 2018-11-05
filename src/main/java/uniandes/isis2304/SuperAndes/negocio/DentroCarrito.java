package uniandes.isis2304.SuperAndes.negocio;

public class DentroCarrito implements VODentroCarrito {

	private long idCarrito;

	private long idProductoSucursal;

	private int cantidad;

	public DentroCarrito() {
		this.idCarrito = 0;
		this.idProductoSucursal = 0;
		this.cantidad = 0;
	}

	public DentroCarrito(long idCarrito, long idProductoSucursal, int cantidad)
	{
		this.idCarrito = idCarrito;
		this.idProductoSucursal = idProductoSucursal;
		this.cantidad = cantidad;
	}

	public long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public long getIdProductoSucursal() {
		return idProductoSucursal;
	}

	public void setIdProductoSucursal(long idProductoSucursal) {
		this.idProductoSucursal = idProductoSucursal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Carrito [idCarrito= "+idCarrito+", idProductoSucursal=" + idProductoSucursal + ", cantidad=" + cantidad + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DentroCarrito other = (DentroCarrito) obj;
		if (cantidad != other.cantidad)
			return false;
		if (idCarrito != other.idCarrito)
			return false;
		if (idProductoSucursal != other.idProductoSucursal)
			return false;
		return true;
	}
	
	


}
