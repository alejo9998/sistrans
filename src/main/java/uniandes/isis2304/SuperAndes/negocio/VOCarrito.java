package uniandes.isis2304.SuperAndes.negocio;

/**
 * Interfaz para reprensentar el CarritoDeCompras
 * @author Rouzajor
 *
 */

public interface VOCarrito {
	
	public long getIdCarrito();
	
	/**
	 * 0 si no esta ocupado
	 * 1 si si esta ocupado
	 * -1 si esta abandonado
	 * @return
	 */
	public int getOcupado();
	
	@Override
	public String toString();

}
