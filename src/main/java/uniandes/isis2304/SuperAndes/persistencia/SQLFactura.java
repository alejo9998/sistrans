package uniandes.isis2304.SuperAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Factura;

class SQLFactura {

	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;


	public SQLFactura(PersistenciaSuperAndes pp) {
		this.pp=pp;
	}

	public long adicionarFactura (PersistenceManager pm, long idFactura, String descripcion) {
		Query q = pm.newQuery(SQL, "INSERT INTO "+ pp.darTablaFactura() + "(idFactura, descripcion) values (?, ?)");
		q.setParameters(idFactura, descripcion);
		return (long) q.executeUnique();
	}

	public long eliminarFacturaPorId(PersistenceManager pm, long idFactura) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFactura() + " WHERE idFactura = ?");
		q.setParameters(idFactura);
		return (long) q.executeUnique();
	}

	public Factura darFacturaPorId ( PersistenceManager pm , long idFactura) {
		Query q = pm.newQuery("SELECT * FROM " + pp.darTablaFactura() + " WHERE idFactura = ?");
		q.setResultClass(Factura.class);
		q.setParameters(idFactura);
		return (Factura) q.executeUnique();
	}

	public List<Factura> darFacturas(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFactura());
		q.setResultClass(Factura.class);
		return (List<Factura>) q.executeList();
	}



}
