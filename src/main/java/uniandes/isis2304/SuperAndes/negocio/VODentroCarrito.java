package uniandes.isis2304.SuperAndes.negocio;

/**
 * Interfaz para representar la relacion entre carrito y productosSucursal
 * Relaciona un productoSucursal con una cantidad de productos 	que lleva dentro de un
 * carrito de compras
 * @author Rouzajor
 *
 */

public interface VODentroCarrito {
	
	public long getIdCarrito();
	
	public long getIdProductoSucursal();
	
	/**
	 * CAntidad del productoSucursal que se lleva en el carrito
	 * @return
	 */
	public int getCantidad();

}
