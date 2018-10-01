package uniandes.isis2304.SuperAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Promocion;

class SQLPromocion {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;
	
	public SQLPromocion ( PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	public long adicionarPromocion (PersistenceManager pm, long idPromocion, int tipo, double n, double m, String fechaCaducidad ) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPromocion() + "(idPromocion, tipo, n, m, fechaCaducidad) values (?, ?, ?, ?, ?)");
		q.setParameters(idPromocion, tipo, n, m, fechaCaducidad);
		return (long) q.executeUnique();
	}
	
	public long eliminarPromocionPorId (PersistenceManager pm, long idPromocion) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPromocion() + " WHERE idPromocion = ?");
		q.setParameters(idPromocion);
		return (long) q.executeUnique();
	}
	
	public Promocion darPromocionPorId (PersistenceManager pm, long idPromocion) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion() + " WHERE idPromocion = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(idPromocion);
		return (Promocion) q.executeUnique();
	}
	
	public List<Promocion> darPromociones (PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion());
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
	
	public List<Promocion> darPromocionesPorTipo (PersistenceManager pm, int tipo){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPromocion() + " WHERE tipo = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(tipo);
		return (List<Promocion>) q.executeList();
	}

}
