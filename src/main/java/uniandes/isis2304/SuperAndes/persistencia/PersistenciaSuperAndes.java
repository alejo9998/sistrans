package uniandes.isis2304.SuperAndes.persistencia;

import javax.jdo.PersistenceManagerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.SuperAndes.negocio.Cliente;
import uniandes.isis2304.SuperAndes.negocio.Factura;
import uniandes.isis2304.SuperAndes.negocio.Proveedor;
import uniandes.isis2304.SuperAndes.negocio.Sucursal;

public class PersistenciaSuperAndes {
	
	private static Logger log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	
	public final static String SQL = "javax.jdo.query.SQL";
	
	
	private static PersistenciaSuperAndes instance;
	
	private PersistenceManagerFactory pmf;
	
	private List <String> tablas;
	
	private SQLProveedor sqlProveedor;
	
	private SQLSucursal sqlSucursal;
	
	private SQLFactura sqlFactura;
	
	private SQLBodega sqlBodega;
	
	private SQLEstante sqlEstante;
	
	private SQLProductoProveedor sqlProductoProveedor;
	
	private SQLProductoSucursal sqlProductoSucursal;
	
	private SQLOrdenPedido sqlOrdenPedido;
	
	private SQLCompra sqlCompra;
	
	private SQLCliente sqlCliente;
	
	private SQLPromocion sqlPromocion;
	
	private SQLUtil sqlUtil;
	
	
	private PersistenciaSuperAndes() {
		pmf = JDOHelper.getPersistenceManagerFactory("SuperAndes");		
		crearClasesSQL ();
		
		tablas = new LinkedList<String>();
		tablas.add("SuperAndes_sequence");
		tablas.add("PROVEEDOR");
		tablas.add("SUCURSAL");
		tablas.add("FACURA");
		tablas.add("BODEGA");
		tablas.add("ESTANTE");
		tablas.add("PRODUCTOPROVEEDOR");
		tablas.add("PRODUCTOSUCURSAL");
		tablas.add("ORDENPEDIDO");
		tablas.add("COMPRA");
		tablas.add("CLIENTE");
		tablas.add("PROMOCION");		
	}
	
	private PersistenciaSuperAndes (JsonObject tableConfig) {
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig);
		
		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
		log.trace("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
	}
	
	public static PersistenciaSuperAndes getInstance() {
		if (instance == null) {
			instance = new PersistenciaSuperAndes();
		}
		return instance;
	}
	
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes (tableConfig);
		}
		return instance;
	}
	
	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	private void crearClasesSQL() {
		sqlProveedor = new SQLProveedor(this);
		sqlSucursal = new SQLSucursal(this);
		sqlFactura = new SQLFactura(this);
		sqlBodega = new SQLBodega(this);
		sqlEstante = new SQLEstante(this);
		sqlProductoProveedor = new SQLProductoProveedor(this);
		sqlProductoSucursal = new SQLProductoSucursal(this);
		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlCompra = new SQLCompra(this);
		sqlCliente = new SQLCliente(this);
		sqlPromocion = new SQLPromocion(this);
	}
	
	public String darSeqSuperAndes() {
		return tablas.get(0);
	}
	
	public String darTablaProveedor() {
		return tablas.get(1);
	}
	
	public String darTablaSucursal() {
		return tablas.get(2);
	}
	
	public String darTablaFactura() {
		return tablas.get(3);
	}
	
	public String darTablaBodega() {
		return tablas.get(4);
	}
	
	public String darTablaEstante() {
		return tablas.get(5);
	}
	
	public String darTablaProductoProveedor() {
		return tablas.get(6);
	}
	
	public String darTablaProductoSucursal() {
		return tablas.get(7);
	}
	
	public String darTablaOrdenPedido() {
		return tablas.get(8);
	}
	
	public String darTablaCompra() {
		return tablas.get(9);
	}
	
	public String darTablaCliente() {
		return tablas.get(10);
	}
	
	public String darTablaPromocion() {
		return tablas.get(11);
	}
	
	/**
	 * Transacción para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de SuperAndes
	 */
	private long nextval() {
		long resp = sqlUtil.nextval(pmf.getPersistenceManager());
		log.trace("Generando secuencia: " + resp);
		return resp;
	}
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) {
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	/* *****************************************
	 *     metodos para manejar Proveedor
	 ***********************************************/
	
	public Proveedor adicionarProveedor (String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long nit = nextval ();
            long tuplasInsertadas = sqlProveedor.adicionarProveedor(pmf.getPersistenceManager(), nit, nombre);
            tx.commit();

            log.trace ("Inserción de proveedor: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Proveedor (nit, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarProveedorPorNombre(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProveedor.eliminarProveedorPorNombre (pm, nombre);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarProveedorPorNit (long nit) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProveedor.eliminarProveedorPorNit (pm, nit);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Proveedor> darProveedoresPorNombre (String nombre) 
	{
		return sqlProveedor.darProveedoresPorNombre (pmf.getPersistenceManager(), nombre);
	}
	
	public Proveedor darProveedorPorNit (long nit) 
	{
		return (Proveedor) sqlProveedor.darProveedorPorNit (pmf.getPersistenceManager(), nit);
	}
	
	public List<Proveedor> darProveedores() {
		return sqlProveedor.darProveedores(pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las Sucursales
	 *****************************************************************/
	
	public Sucursal adicionarSucursal(String ciudad, String direccion, String nombre) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idSucursal = nextval ();
            long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, idSucursal, ciudad, direccion, nombre);
            tx.commit();
            
            log.trace ("Inserción de sucursal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Sucursal(idSucursal, ciudad, direccion, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarSucursalPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSucursal.eliminarSucursalesPorNombre(pm, nombre);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarSucursalPorId (long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSucursal.eliminarSucursalPorId(pm, idSucursal);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Sucursal> darSucursales ()
	{
		return sqlSucursal.darSucursales (pmf.getPersistenceManager());
	}
	
	public Sucursal darSucursalPorId (long idSucursal)
	{
		return sqlSucursal.darSucursalPorId (pmf.getPersistenceManager(), idSucursal);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las Cliente
	 *****************************************************************/
	
	public Cliente adicionarClienteIndividuo(String nombre, String correo) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long identificacion = nextval ();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, identificacion, nombre, correo, null);
            tx.commit();
            
            log.trace ("Inserción clienteIndividuo: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Cliente (identificacion,nombre, correo, null);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Cliente adicionarClienteEmpresa(String nombre, String correo, String direccion) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long identificacion = nextval ();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, identificacion, nombre, correo, direccion);
            tx.commit();
            
            log.trace ("Inserción clienteEmpresa: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Cliente (identificacion,nombre, correo, direccion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarClientePorIdentificacion (long identificacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorIdentificacion (pm, identificacion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Cliente> darClientesIndividuos ()
	{
		return sqlCliente.darClientesIndividuos (pmf.getPersistenceManager());
	}
	
	public List<Cliente> darClientesEmpresas(){
		return sqlCliente.darClientesEmpresas (pmf.getPersistenceManager());
	}
	
	public List<Cliente> darClientes(){
		return sqlCliente.darClientes (pmf.getPersistenceManager());
	}
	
	public Cliente darClientePorId (long identificacion)
	{
		return sqlCliente.darClientePorIdentificacion (pmf.getPersistenceManager(), identificacion);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las FACTURA
	 *****************************************************************/
	public Factura adicionarTipoBebida(String descripcion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idFactura = nextval ();
            long tuplasInsertadas = sqlFactura.adicionarFactura(pm, idFactura, descripcion);
            tx.commit();
            
            log.trace ("Inserción de Factura: " + idFactura + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Factura(idFactura, descripcion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarFacturaPorId (long idFactura) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlFactura.eliminarFacturaPorId(pm, idFactura);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	
	


}
