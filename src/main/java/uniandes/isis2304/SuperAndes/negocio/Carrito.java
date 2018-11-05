package uniandes.isis2304.SuperAndes.negocio;

/**
 * Clase que representa un carrito de alguna sucursal
 * @author Rouzajor
 *
 */
public class Carrito implements VOCarrito{
	
	/**
	 * id del carrito
	 */
	private long idCarrito;
	
	/**
	 * Dice di el carrito esta ocupado
	 * 0 = Libre
	 * 1 = Ocupado
	 * -1 = abandonado
	 */
	private int ocupado;
	
	/**
	 * Sucursal a la cual pertenece el carrito
	 */
	private long sucursal;
	
	/**
	 * Contructo vacio del carrito
	 */
	public Carrito() {
		this.idCarrito = 0;
		this.ocupado = 0;
		this.sucursal = 0;
	}

	/**
	 * Contructo con parametro del carrito
	 * @param idCarrito id del carrito
	 * @param ocupado numero que representa si el carro esta libre, ocupado o abandoando (0 ,1 ,-1) respectivamente 
	 * @param sucursal sucursal a la que pertence el carrito
	 */
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


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carrito other = (Carrito) obj;
		if (idCarrito != other.idCarrito)
			return false;
		if (ocupado != other.ocupado)
			return false;
		if (sucursal != other.sucursal)
			return false;
		return true;
	}
	
	
	

}
