package uniandes.isis2304.SuperAndes.persistencia;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.SuperAndes.negocio.ProductoSucursal;

class SQLProductoSucursal {
	
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes pp;
	
	public SQLProductoSucursal (PersistenciaSuperAndes pp) {
		this.pp = pp;
	}
	
	public long adicionarProductoSucursal (PersistenceManager pm , long idProductoSucursal, String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, String fechaVencimiento , int nivelReorden, double precioUnitario, int cantidadBodega, int cantidadEstante, double precioUnidadMedida, long idBodega, long idEstante, Long idPromocion)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProductoSucursal() + " (idProductoSucursal, nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, bodega, estante, promocion) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idProductoSucursal, nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion);
		return (long) q.executeUnique();
	}
	
	public long eliminarProductoSucursalPorId (PersistenceManager pm, long idProductoSucursal) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProductoSucursal() + " WHERE idProductoSucursal = ?");
		q.setParameters(idProductoSucursal);
		return (long) q.executeUnique();
	}
	
	public ProductoSucursal darProductoSucursalPorId (PersistenceManager pm, long idProductoSucursal) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoSucursal() + " WHERE idProductoSucursal = ?");
		q.setResultClass(ProductoSucursal.class);
		q.setParameters(idProductoSucursal);
		return (ProductoSucursal) q.executeUnique();
	}
	
	public List<ProductoSucursal> darProductosSucursalPorCategoria (PersistenceManager pm, String categoria) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoSucursal() + " WHERE categoria = ?");
		q.setResultClass(ProductoSucursal.class);
		q.setParameters(categoria);
		return (List<ProductoSucursal>) q.executeList();
	}
	
	public List<ProductoSucursal> darProductosSucursalPorCodigoBarras(PersistenceManager pm, long barras){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoSucursal() + "WHERE codigoBarras = ?");
		q.setResultClass(ProductoSucursal.class);
		q.setParameters(barras);
		return (List<ProductoSucursal>) q.executeList();
	}
	
	public List<ProductoSucursal> darProductosSucursalPorTipo (PersistenceManager pm, String tipo) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoSucursal() + " WHERE tipo = ?");
		q.setResultClass(ProductoSucursal.class);
		q.setParameters(tipo);
		return (List<ProductoSucursal>) q.executeList();
	}
	
	public List<ProductoSucursal> darProductosSucursal(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProductoSucursal());
		q.setResultClass(ProductoSucursal.class);
		List<ProductoSucursal> retor =  (List<ProductoSucursal>) q.executeList();
		return retor;
	}
	
	public long aumentarCantidadBodegaProductoSucursal (PersistenceManager pm, long idProductoSucursal, int cantidad) {
		Query q = pm.newQuery(SQL ,"UPDATE " + pp.darTablaProductoSucursal() + " SET cantidadBodega = cantidadBodega + " + cantidad + " WHERE idProductoSucursal = ?");
		q.setParameters(idProductoSucursal);
		return (long) q.executeUnique();
	}
	
	public long aumentarCantidadEstanteProductoSucursal (PersistenceManager pm, long idProductoSucursal, int cantidad) {
		Query q = pm.newQuery(SQL ,"UPDATE " + pp.darTablaProductoSucursal() + " SET cantidadEstante = cantidadEstante + " + cantidad + " WHERE idProductoSucursal = ?");
		q.setParameters(idProductoSucursal);
		return (long) q.executeUnique();
	}
	
	public long disminuirCantidadBodegaProductoSucursal (PersistenceManager pm, long idProductoSucursal, int cantidad) {
		Query q = pm.newQuery(SQL ,"UPDATE " + pp.darTablaProductoSucursal() + " SET cantidadBodega = cantidadBodega - " + cantidad + " WHERE idProductoSucursal = ?");
		q.setParameters(idProductoSucursal);
		return (long) q.executeUnique();
	}
	
	public long disminuirCantidadEstanteProductoSucursal (PersistenceManager pm, long idProductoSucursal, int cantidad) {
		Query q = pm.newQuery(SQL ,"UPDATE " + pp.darTablaProductoSucursal() + " SET cantidadEstante = cantidadEstante - " + cantidad + " WHERE idProductoSucursal = ?");
		q.setParameters(idProductoSucursal);
		return (long) q.executeUnique();
	}
	

}
