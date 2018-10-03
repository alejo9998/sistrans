package uniandes.isis2304.SuperAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Proveedor;

class SQLProveedor {

	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;


	public SQLProveedor(PersistenciaSuperAndes pp) {
		this.pp=pp;
	}

	public long adicionarProveedor(PersistenceManager pm, long nit, String nombre) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor() + "(nit, nombre) values (?, ?)" );
		q.setParameters(nit, nombre);
		return (long) q.executeUnique();
	}

	public long eliminarProveedorPorNombre(PersistenceManager pm, String nombre) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	public long eliminarProveedorPorNit(PersistenceManager pm , long nit) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor() + " WHERE nit = ?");
		q.setParameters(nit);
		return (long) q.executeUnique();
	}

	public Proveedor darProveedorPorNit(PersistenceManager pm, long nit) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor() + " WHERE nit = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(nit);
		return (Proveedor) q.executeUnique();
	}

	public List<Proveedor> darProveedores (PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
	}

	public List<Proveedor> darProveedoresPorNombre (PersistenceManager pm, String nombre){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor() + " WHERE nombre = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(nombre);
		return (List<Proveedor>) q.executeList();
	}
}
