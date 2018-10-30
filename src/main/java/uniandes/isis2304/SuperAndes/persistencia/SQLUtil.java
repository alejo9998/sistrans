package uniandes.isis2304.SuperAndes.persistencia;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;


class SQLUtil {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes pp;
	
	public SQLUtil (PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT " + pp.darSeqSuperAndes() + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long)q.executeUnique();
		return resp;
	}
	
	public long [] limpiarSuperAndes (PersistenceManager pm) {
		Query qOrdenPedido = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrdenPedido());
		Query qProductoProveedor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoProveedor());
		Query qProveedor = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor());
		Query qCompra = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCompra());
		Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente());
		Query qFactura = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFactura());
		Query qDentroCarrito = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDentroCarrito());
		Query qCarrito = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarrito());
		Query qProductoSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoSucursal());
		Query qBodega = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega());
		Query qEstante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEstante());
		Query qPromocion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion());
		Query qSucursal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSucursal());
		
		long ordenPedidoEliminado = (long) qOrdenPedido.executeUnique();
		long productoProveedorEliminado = (long) qProductoProveedor.executeUnique();
		long proveedorEliminado = (long) qProveedor.executeUnique();
		long compraEliminado = (long) qCompra.executeUnique();
		long clienteEliminado = (long) qCliente.executeUnique();
		long facturaEliminado = (long) qFactura.executeUnique();
		long dentroCarritoEliminado = (long) qDentroCarrito.executeUnique();
		long carritoEliminado = (long) qCarrito.executeUnique();
		long productoSucursalEliminado = (long) qProductoSucursal.executeUnique();
		long bodegaEliminado = (long) qBodega.executeUnique();
		long estanteEliminado = (long) qEstante.executeUnique();
		long promocionEliminado = (long) qPromocion.executeUnique();
		long sucursalEliminado = (long) qSucursal.executeUnique();
		return new long[] {ordenPedidoEliminado, productoProveedorEliminado, proveedorEliminado,
				compraEliminado, clienteEliminado, facturaEliminado,dentroCarritoEliminado, carritoEliminado, productoSucursalEliminado,
				bodegaEliminado, estanteEliminado, promocionEliminado, sucursalEliminado};
		}

}
