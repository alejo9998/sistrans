package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Carrito;

class SQLCarrito {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;
	
	public SQLCarrito ( PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	public long adicionarCarrito (PersistenceManager pm, long idCarrito, int ocupado, long idSucursal) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCarrito() + "(idCarrito, ocupado, sucursal) values (?, ?, ?)");
		q.setParameters(idCarrito, ocupado, idSucursal);
		return (long) q.executeUnique();
	}
	
	public long eliminarCarritoPorId (PersistenceManager pm, long idCarrito) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCarrito() + " WHERE idCarrito = ?");
		q.setParameters(idCarrito);
		return (long) q.executeUnique();
	}
	
	public Carrito darCarritoPorId (PersistenceManager pm, long idCarrito) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito() + " WHERE idCarrito = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(idCarrito);
		return (Carrito) q.executeUnique();
	}
	
	public List<Carrito> darCarritos (PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito());
		q.setResultClass(Carrito.class);
		return (List<Carrito>) q.executeList();
	}
	
	public List<Carrito> darCarritosOcupadosSucursal (PersistenceManager pm, long idSucursal){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito() + " WHERE ocupado = 1 AND sucursal = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(idSucursal);
		return (List<Carrito>) q.executeList();
	}
	
	public List<Carrito> darCarritosLibresSucursal (PersistenceManager pm, long idSucursal){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito() + " WHERE ocupado = 0 AND sucursal = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(idSucursal);
		return (List<Carrito>) q.executeList();
	}
	
	public List<Carrito> darCarritosAbandonadosSucursal (PersistenceManager pm, long idSucursal){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCarrito() + " WHERE ocupado = -1 AND sucursal = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(idSucursal);
		return (List<Carrito>) q.executeList();
	}
	
	public long modificarEstadoOcupacionCarrito (PersistenceManager pm, long idCarrito, int ocupado) {
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCarrito() + " SET ocupado = ? WHERE idCarrito = ?");
		q.setParameters(ocupado, idCarrito);
		return (long) q.executeUnique();
	}

}
