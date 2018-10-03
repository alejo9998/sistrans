package uniandes.isis2304.SuperAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Compra;

class SQLCompra {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;


	public SQLCompra(PersistenciaSuperAndes pp) {
		this.pp=pp;
	}
	
	public long adicionarCompra (PersistenceManager pm, long idCompra, String fecha, int cantidad, long idProductoSucursal, long idCliente, long idFactura ) {
		Query q = pm.newQuery(SQL , "INSERT INTO " + pp.darTablaCompra() + "(idCompra, fecha, cantidad, productoSucursal, cliente, factura) values (?, ?, ?, ?, ?, ?)" );
		q.setParameters(idCompra, fecha, cantidad, idProductoSucursal, idCliente, idFactura );
		return (long) q.executeUnique();
	}
	
	public long eliminarCompraPorId (PersistenceManager pm, long idCompra) {
		Query q = pm.newQuery(SQL , "DELETE FROM " + pp.darTablaCompra() + " WHERE idCompra = ?");
		q.setParameters(idCompra);
		return (long) q.executeUnique();
	}
	
	public Compra darCompraPorId(PersistenceManager pm, long idCompra) {
		Query q = pm.newQuery(SQL , "SELECT * FROM " + pp.darTablaCompra() + " WHERE idCompra = ?");
		q.setResultClass(Compra.class);
		q.setParameters(idCompra);
		return (Compra) q.executeUnique();
	}
	
	public List<Compra> darComprasPorCliente (PersistenceManager pm, long idCliente){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCompra() + " WHERE cliente = ?");
		q.setResultClass(Compra.class);
		q.setParameters(idCliente);
		return (List<Compra>) q.executeList();	
	}
	
	public List<Compra> darComprasPorFactura (PersistenceManager pm, long idFactura){
		Query q = pm.newQuery(SQL , "SELECT * FROM " + pp.darTablaCompra() + " WHERE factura = ?");
		q.setResultClass(Compra.class);
		q.setParameters(idFactura);
		return (List<Compra>) q.executeList();
	}
	
	public List<Compra> darCompras (PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCompra());
		q.setResultClass(Compra.class);
		return (List<Compra>) q.executeList();
	}

}
