package uniandes.isis2304.SuperAndes.persistencia;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.ProductoProveedor;
class SQLProductoProveedor {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes pp;
	
	public SQLProductoProveedor(PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	public long adicionarProductoProveedor (PersistenceManager pm , long idProductoProveedor, String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, Timestamp fechaVencimiento , double calidad, double precio, int numeroCalificaciones, double sumaCalificaciones, long idProveedor)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoProveedor() + " (idProductoProveedor, nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, proveedor) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idProductoProveedor, nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
		return (long) q.executeUnique();
	}
	
	public long eliminarProductoProveedorPorId (PersistenceManager pm, long idProductoProveedor) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoProveedor() + " WHERE idProductoProveedor = ?");
		q.setParameters(idProductoProveedor);
		return (long) q.executeUnique();
	}
	
	public ProductoProveedor darProductoProveedorPorId (PersistenceManager pm, long idProductoProveedor) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoProveedor() + " WHERE idProductoProveedor = ?");
		q.setResultClass(ProductoProveedor.class);
		q.setParameters(idProductoProveedor);
		return (ProductoProveedor) q.executeUnique();
	}
	
	public List<ProductoProveedor> darProductosProveedorPorCategoria (PersistenceManager pm, String categoria) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoProveedor() + " WHERE categoria = ?");
		q.setResultClass(ProductoProveedor.class);
		q.setParameters(categoria);
		return (List<ProductoProveedor>) q.executeList();
	}
	
	public List<ProductoProveedor> darProductosProveedorPorTipo (PersistenceManager pm, String tipo) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoProveedor() + " WHERE tipo = ?");
		q.setResultClass(ProductoProveedor.class);
		q.setParameters(tipo);
		return (List<ProductoProveedor>) q.executeList();
	}
	
	public List<ProductoProveedor> darProductosProveedorPorCodigoBarras(PersistenceManager pm, long barras){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoProveedor() + " WHERE codigoBarras = ?");
		q.setResultClass(ProductoProveedor.class);
		q.setParameters(barras);
		return (List<ProductoProveedor>) q.executeList();
	}
	
	public List<ProductoProveedor> darProductosProveedor(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoProveedor());
		q.setResultClass(ProductoProveedor.class);
		return (List<ProductoProveedor>) q.executeList();
	}
	
	public long aumentarNumeroCalificaciones(PersistenceManager pm, long idProductoProveedor) {
		Query q = pm.newQuery(SQL ,"UPDATE " + pp.darTablaProductoProveedor() + " SET numeroCalificaciones = numeroCalificaciones + 1 WHERE idProductoProveedor = ?");
		q.setParameters(idProductoProveedor);
		return (long) q.executeUnique();
	}
	
	public long aumentarSumaCalificaciones (PersistenceManager pm, long idProductoProveedor, double calificacion) {
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductoProveedor() + " SET sumaCalificaciones = sumaCalificaciones + " + calificacion + " WHERE idProductoProveedor = ?");
		q.setParameters(idProductoProveedor);
		return (long) q.executeUnique();
	}
	
	public long modificarCalidadProductoProveedor (PersistenceManager pm, long idProductoProveedor, double calificacion) {
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaProductoProveedor() + " SET calidad = " + calificacion + " WHERE idProductoProveedor = ?");
		q.setParameters(idProductoProveedor);
		return (long) q.executeUnique();
	}
	
	public long modificarFechaVencimiento (PersistenceManager pm, long idProductoProveedor, Timestamp fecha) {
		Query q = pm.newQuery(SQL ,"UPDATE " + pp.darTablaProductoProveedor() + " SET fechaVencimiento = " + fecha + " WHERE idProductoProveedor = ?");
		q.setParameters(idProductoProveedor);
		return (long) q.executeUnique(); 
	}
	
	
	
	

}
