package uniandes.isis2304.SuperAndes.persistencia;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.OrdenPedido;

class SQLOrdenPedido {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes pp;
	
	
	public SQLOrdenPedido (PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	public long adicionarOrdenPedido (PersistenceManager pm, long idOrdenPedido, double precio, Timestamp fechaEntrega, Timestamp fechaEsperadaEntrega, Double calificacion, int entregado, int cantidad, long idProductoProveedor, long idSucursal) {
		Query q = pm.newQuery(SQL ,"INSERT INTO " + pp.darTablaOrdenPedido() + "(idOrdenPedido, precio, fechaEntrega, fechaEsperadaEntrega, calificacion, entregado, cantidad, productoProveedor, sucursal) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idOrdenPedido, precio, fechaEntrega, fechaEsperadaEntrega, calificacion, entregado, cantidad, idProductoProveedor, idSucursal);
		return (long) q.executeUnique();
	}
	
	public long eliminarOrdenPedidoPorId (PersistenceManager pm , long idOrdenPedido) {
		Query q = pm.newQuery(SQL ,"DELETE FROM " + pp.darTablaOrdenPedido() + " WHERE idOrdenPedido = ?");
		q.setParameters(idOrdenPedido);
		return (long) q.executeUnique();
	}
	
	public OrdenPedido darOrdenPedidoPorId (PersistenceManager pm, long idOrdenPedido) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido() + " WHERE idOrdenPedido = ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(idOrdenPedido);
		return (OrdenPedido) q.executeUnique();
	}
	
	public List<OrdenPedido> darOrdenesPedido (PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrdenPedido());
		q.setResultClass(OrdenPedido.class);
		return (List<OrdenPedido>)	q.executeList();
	}
	
	public long calificarOrdenPedido (PersistenceManager pm , long idOrdenPedido, double calificacion) {
		Query q = pm.newQuery(SQL , "UPDATE " + pp.darTablaOrdenPedido() + " SET calificacion = " + calificacion + " WHERE idOrdenPedido = ?");
		q.setParameters(idOrdenPedido);
		return (long) q.executeUnique();
	}
	
	public long recibirEntregadoOrdenPedido (PersistenceManager pm, long idOrdenPedido) {
		Query q = pm.newQuery(SQL , "UPDATE " + pp.darTablaOrdenPedido() + " SET entregado = 1 WHERE idOrdenPedido = ?");
		q.setParameters(idOrdenPedido);
		return (long) q.executeUnique();
	}
	
	public long recibirFechaOrdenPedido (PersistenceManager pm, long idOrdenPedido) {
		Calendar c2 = Calendar.getInstance();
		long time2 = c2.getTimeInMillis();
		Timestamp fecha = new Timestamp(time2);
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaOrdenPedido() + " SET fechaEsperadaEntrega = ? WHERE idOrdenPedido = ?");
		q.setParameters(fecha, idOrdenPedido);
		return (long) q.executeUnique();
		
	}
	
	

}
