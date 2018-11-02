package uniandes.isis2304.SuperAndes.negocio;

public class Carrito implements VOCarrito{
	
	private long idCarrito;
	
	private int ocupado;
	
	private long sucursal;
	
	public Carrito() {
		this.idCarrito = 0;
		this.ocupado = 0;
		this.sucursal = 0;
	}

	public Carrito(long idCarrito, int ocupado, long sucursal) {
		this.idCarrito = idCarrito;
		this.ocupado = ocupado;
		this.sucursal = sucursal;
	}

	public long getIdCarrito() {
		return idCarrito;
	}

	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	public int getOcupado() {
		return ocupado;
	}

	public void setOcupado(int ocupado) {
		this.ocupado = ocupado;
	}
	
	public long getSucursal() {
		return sucursal;
	}
	
	public void setSucursal(long sucursal) {
		this.sucursal = sucursal;
	}
	
	@Override
	public String toString() {
		return "Carrito [idCarrito= " + idCarrito + ", ocupado= " + ocupado + ", sucursal=" + sucursal + "]";
	}
	
	

}
