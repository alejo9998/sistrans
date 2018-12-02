package uniandes.isis2304.SuperAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.Cliente;
import uniandes.isis2304.SuperAndes.negocio.RFC10Cliente;

public class SQLRequerimientosCiclo3 {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes pp;

	public SQLRequerimientosCiclo3(PersistenciaSuperAndes pp) {
		this.pp=pp;
	}

	public List<RFC10Cliente> RF10CConsultarConsumoSuperAndes (PersistenceManager pm, long idProductoSucursal, String fechaInicial, String fechaFinal, String orderBy){
		String sql = "SELECT cliente.identificacion, cliente.nombre, cliente.correo, cliente.direccion, SUM(compra.cantidad), compra.fecha";
		sql += " FROM ";
		sql += "A_CLIENTE" + " cliente ";
		sql += "INNER JOIN " + "A_COMPRA" + " compra ";
		sql +=  "ON cliente.identificacion = compra.cliente ";
		sql += "WHERE compra.productosurcursal = " + idProductoSucursal;
		sql += " AND compra.fecha BETWEEN '" + fechaInicial + "' AND '" + fechaFinal +"' ";
		sql += "GROUP BY cliente.identificacion, cliente.nombre, cliente.correo, cliente.direccion, compra.fecha ";
		sql += "ORDER BY " + orderBy;
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(RFC10Cliente.class);
		return (List<RFC10Cliente>) q.executeList();
	}
	/**
	public List<RFC10Cliente> RF10CConsultarConsumoSuperAndes (PersistenceManager pm, long idProductoSucursal, String fechaInicial, String fechaFinal, String orderBy){
		String sql = "SELECT cliente.IDENTIFICACION, cliente.NOMBRE, cliente.CORREO, cliente.DIRECCION, SUM(compra.CANTIDAD), compra.FECHA";
		sql += " FROM ";
		sql += pp.darTablaCliente() + " cliente ";
		sql += "INNER JOIN " + pp.darTablaCompra() + " compra ";
		sql +=  "ON cliente.IDENTIFICACION = compra.CLIENTE ";
		sql += "WHERE compra.PRODUCTOSUCURSAL = " + idProductoSucursal;
		sql += " AND compra.FECHA BETWEEN '" + fechaInicial + "' AND '" + fechaFinal +"' ";
		sql += "group by cliente.IDENTIFICACION, cliente.NOMBRE, cliente.CORREO, cliente.DIRECCION, compra.FECHA ";
		sql += "order by " + orderBy + " DESC";
		Query q = pm.newQuery(SQL, sql);
		q.setResultClass(RFC10Cliente.class);
		return (List<RFC10Cliente>) q.executeList();
	}
	*/
	

}
