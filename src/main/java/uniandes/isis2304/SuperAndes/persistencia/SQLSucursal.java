package uniandes.isis2304.SuperAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Sucursal;

class SQLSucursal {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes pp;
	
	public SQLSucursal(PersistenciaSuperAndes pp) {
		this.pp=pp;
	}
	
	public long adicionarSucursal (PersistenceManager pm, long idSucursal, String ciudad, String direccion, String nombre) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSucursal() + "(idSucursal, ciudad, direccion, nombre) values (?, ?, ?, ?)");
		q.setParameters(idSucursal, ciudad, direccion, nombre);
		return (long) q.executeUnique();
	}
	
	public long eliminarSucursalesPorNombre(PersistenceManager pm, String nombre) {
		Query q = pm.newQuery("DELETE FROM " + pp.darTablaProveedor() + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}
	
	public long eliminarSucursalPorId(PersistenceManager pm, long idSucursal) {
		Query q = pm.newQuery("DELETE FROM " + pp.darTablaSucursal() + " WHERE idSucursal = ?");
		q.setParameters(idSucursal);
		return (long) q.executeUnique();
	}
	
	public Sucursal darSucursalPorId(PersistenceManager pm, long idSucursal) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSucursal() + " WHERE idSucursal = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(idSucursal);
		return (Sucursal) q.executeUnique();
	}
	
	public List<Sucursal> darSucursales (PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM "+ pp.darTablaSucursal());
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();
	}
	
	
	

}
