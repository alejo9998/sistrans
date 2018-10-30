package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.DentroCarrito;

class SQLDentroCarrito {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;
	
	public SQLDentroCarrito ( PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	public long adicionarDentroCarrito (PersistenceManager pm, long idCarrito, long idProductoSucursal, int cantidad) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaDentroCarrito() + "(idCarrito, idProductoSucursal, cantidad) values (?, ?, ?)");
		q.setParameters(idCarrito, idProductoSucursal, cantidad);
		return (long)q.executeUnique();
	}
	
	public long eliminarDentroCarrito (PersistenceManager pm, long idCarrito, long idProductoSucursal) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDentroCarrito() + " WHERE idCarrito = ? AND idProductoSucursal = ?");
		q.setParameters(idCarrito, idProductoSucursal);
		return (long) q.executeUnique();
	}
	
	public List<DentroCarrito> darDentroCarrito (PersistenceManager pm ){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaDentroCarrito());
		q.setResultClass(DentroCarrito.class);
		return (List<DentroCarrito>) q.executeList();
	}
	
	/**
	 * Modifica la cantidad de existencias de un producto dentro del carrito
	 * @param pm
	 * @return
	 */
	public long modificarCantidadDentroCarrito (PersistenceManager pm, long idCarrito, long idProductoSucursal, int cantidad) {
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaDentroCarrito() + " SET cantidad = cantidad + ? WHERE idCarrito = ? AND idProductoSucursal = ?");
		q.setParameters(cantidad, idCarrito, idProductoSucursal);
		return (long) q.executeUnique();
	}

}