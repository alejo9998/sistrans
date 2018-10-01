package uniandes.isis2304.SuperAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Bodega;


class SQLBodega {

	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;
	
	
	public SQLBodega ( PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	public long adicionarBodega (PersistenceManager pm , long idBodega, double volumen, double peso, String tipo, long idSucursal) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBodega() + "(idBodega, volumen, peso, tipo, sucursal) values (?, ?, ?, ?, ?)");
		q.setParameters(idSucursal, volumen, peso, tipo, idSucursal);
		return (long) q.executeUnique();
	}
	
	public long eliminarBodegasPorTipo (PersistenceManager pm, String tipo) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega() + " WHERE tipo = ?");
		q.setParameters(tipo);
		return (long) q.executeUnique();
	}
	
	public long eliminarBodegaPorId (PersistenceManager pm, long idBodega) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega() + " WHERE idBodega = ?");
		q.setParameters(idBodega);
		return (long) q.executeUnique();
	}
	
	public Bodega darBodegaPorId (PersistenceManager pm, long idBodega) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega() + " WHERE idBodega = ?");
		q.setResultClass(Bodega.class);
		q.setParameters(idBodega);
		return (Bodega) q.executeUnique();
	}
	
	public List<Bodega> darBodegasPorTipo (PersistenceManager pm, String tipo){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega() + " WHERE tipo = ?");
		q.setResultClass(Bodega.class);
		q.setParameters(tipo);
		return (List<Bodega>) q.executeList();
	}
	
	public List<Bodega> darBodegas (PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBodega());
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
	
	public long eliminarBodegasPorIdSucursal(PersistenceManager pm, long idSucursal) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBodega() + " WHERE sucursal = ?");
		q.setParameters(idSucursal);
		return (long) q.execute();
	}


}
