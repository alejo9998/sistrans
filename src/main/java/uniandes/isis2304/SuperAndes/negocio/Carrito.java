package uniandes.isis2304.SuperAndes.negocio;

public class Carrito implements VOCarrito{
	
	private long idCarrito;
	
	private int ocupado;
	
	public Carrito() {
		this.idCarrito = 0;
		this.ocupado = 0;
	}

	public Carrito(long idCarrito, int ocupado) {
		this.idCarrito = idCarrito;
		this.ocupado = ocupado;
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
	
	@Override
	public String toString() {
		return "Carrito [idCarrito= " + idCarrito + ", ocupado= " + ocupado + "]";
	}
	
	

}
