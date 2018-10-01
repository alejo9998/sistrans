package uniandes.isis2304.SuperAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Estante;

class SQLEstante {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;
	
	public SQLEstante ( PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	public long adicionarEstante ( PersistenceManager pm , long idEstante, double volumen , double peso, String tipo, int nivelAprovisionamiento, long idSucursal) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEstante() + "(idEstante, volumen, peso, tipo, nivelAprovisionamiento, sucursal) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(idSucursal, volumen, peso, tipo, nivelAprovisionamiento, idSucursal);
		return (long) q.executeUnique();
	}
	
	public long eliminarEstantesPorTipo (PersistenceManager pm, String tipo) {
		Query q = pm.newQuery(SQL, "DELEETE FROM " + pp.darTablaEstante() + " WHERE tipo = ?");
		q.setParameters(tipo);
		return (long) q.executeUnique();
	}
	
	public long eliminarEstantePorId (PersistenceManager pm , long idEstante) {
		Query q = pm.newQuery(SQL , "DELETE FROM " + pp.darTablaEstante() + " WHERE idEstante = ?");
		q.setParameters(idEstante);
		return (long) q.executeUnique();
	}
	
	public Estante darEstantePorId (PersistenceManager pm , long idEstante) {
		Query q = pm.newQuery(SQL ,"SELECT * FROM " + pp.darTablaEstante() + " WHERE idEstante = ?");
		q.setResultClass(Estante.class);
		q.setParameters(idEstante);
		return (Estante) q.executeUnique();
	}
	
	public List<Estante> darEstantesPorTipo (PersistenceManager pm , String tipo){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante() + " WHERE tipo = ?");
		q.setResultClass(Estante.class);
		q.setParameters(tipo);
		return (List<Estante>) q.executeList();
	}
	
	public List<Estante> darEstantes (PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEstante());
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}

}
