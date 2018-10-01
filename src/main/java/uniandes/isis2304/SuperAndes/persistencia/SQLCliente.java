package uniandes.isis2304.SuperAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Cliente;


class SQLCliente {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;
	
	public SQLCliente ( PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	public long adicionarCliente (PersistenceManager pm, long identificacion, String nombre, String correo, String direccion) {
		Query q = pm.newQuery(SQL , "INSERT INTO " + pp.darTablaCliente() + "(identificacion, nombre, correo, direccion) values (?, ?, ?, ?)");
		q.setParameters(identificacion, nombre, correo, direccion);
		return (long) q.executeUnique();
	}
	
	public long eliminarClientePorIdentificacion (PersistenceManager pm, long identificacion) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente() + " WHERE identificacion = ?");
		q.setParameters(identificacion);
		return (long) q.executeUnique();
	}
	
	public Cliente darClientePorIdentificacion (PersistenceManager pm, long identificacion) {
		Query q = pm.newQuery(SQL , "SELECT * FROM " + pp.darTablaCliente() + " WHERE identificacion = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(identificacion);
		return (Cliente) q.executeUnique();
	}
	
	public List<Cliente> darClientesIndividuos (PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente() + " WHERE direccion IS null");
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	public List<Cliente> darClientesEmpresas (PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente() + " WHERE direccion IS NOT null");
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}
	
	public List<Cliente> darClientes (PersistenceManager pm){
		Query q = pm.newQuery(SQL , "SELECT * FROM " + pp.darTablaCliente());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
		
	}
	
	

}
