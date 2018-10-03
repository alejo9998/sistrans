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

import uniandes.isis2304.SuperAndes.negocio.Bodega;
import uniandes.isis2304.SuperAndes.negocio.Cliente;
import uniandes.isis2304.SuperAndes.negocio.Compra;
import uniandes.isis2304.SuperAndes.negocio.Estante;
import uniandes.isis2304.SuperAndes.negocio.Factura;
import uniandes.isis2304.SuperAndes.negocio.OrdenPedido;
import uniandes.isis2304.SuperAndes.negocio.ProductoProveedor;
import uniandes.isis2304.SuperAndes.negocio.ProductoSucursal;
import uniandes.isis2304.SuperAndes.negocio.Promocion;
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
		tablas.add("CLIENTE");
		tablas.add("FACTURA");
		tablas.add("SUCURSAL");
		tablas.add("PROVEEDOR");
		tablas.add("PROMOCION");
		tablas.add("BODEGA");
		tablas.add("ESTANTE");
		tablas.add("PRODUCTOSUCURSAL");
		tablas.add("COMPRA");
		tablas.add("PRODUCTOPROVEEDOR");
		tablas.add("ORDENPEDIDO");	
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
		sqlUtil = new SQLUtil(this);
	}
	
	public String darSeqSuperAndes() {
		return tablas.get(0);
	}
	
	public String darTablaCliente() {
		return tablas.get(1);
	}
	
	public String darTablaFactura() {
		return tablas.get(2);
	}
	
	public String darTablaSucursal() {
		return tablas.get(3);
	}
	
	public String darTablaProveedor() {
		return tablas.get(4);
	}
	
	public String darTablaPromocion() {
		return tablas.get(5);
	}
	
	public String darTablaBodega() {
		return tablas.get(6);
	}
	
	public String darTablaEstante() {
		return tablas.get(7);
	}
	
	public String darTablaProductoSucursal() {
		return tablas.get(8);
	}

	public String darTablaCompra() {
		return tablas.get(9);
	}

	public String darTablaProductoProveedor() {
		return tablas.get(10);
	}

	public String darTablaOrdenPedido() {
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
	
	public List<Proveedor> darProveedoresPorNombre (String nombre) 
	{
		return sqlProveedor.darProveedoresPorNombre (pmf.getPersistenceManager(), nombre);
	}
	
	public Proveedor darProveedorPorNit (long nit) 
	{
		return sqlProveedor.darProveedorPorNit (pmf.getPersistenceManager(), nit);
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
	
	public List<Cliente> darClientesIndividuos ()
	{
		return sqlCliente.darClientesIndividuos (pmf.getPersistenceManager());
	}
	
	public List<Cliente> darClientesEmpresa(){
		return sqlCliente.darClientesEmpresas (pmf.getPersistenceManager());
	}
	
	public List<Cliente> darClientes(){
		return sqlCliente.darClientes (pmf.getPersistenceManager());
	}
	
	public Cliente darClientePorIdentificacion (long identificacion)
	{
		return sqlCliente.darClientePorIdentificacion (pmf.getPersistenceManager(), identificacion);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las FACTURA
	 *****************************************************************/
	public Factura adicionarFactura(String descripcion)
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
	
	public Factura darFacturaPorId (long idFactura)
	{
		return sqlFactura.darFacturaPorId (pmf.getPersistenceManager(), idFactura);
	}
	
	public List<Factura> darFacturas ()
	{
		return sqlFactura.darFacturas (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar el productoProveedor
	 *****************************************************************/
	public ProductoProveedor adicionarProductoProveedor(String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, String fechaVencimiento , double calidad, double precio, int numeroCalificaciones, double sumaCalificaciones, long idProveedor) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idProductoProveedor = nextval ();
            long tuplasInsertadas = sqlProductoProveedor.adicionarProductoProveedor (pmf.getPersistenceManager(), idProductoProveedor, nombre, marca, presentacion, cantidadPresentacion, unidadMedida
            		, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
    		tx.commit();

    		 log.trace ("Inserción de ProdcutoProveedor: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new ProductoProveedor (idProductoProveedor, nombre, marca, presentacion, cantidadPresentacion, unidadMedida
            		, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
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
	
	public List<ProductoProveedor> darProductosProveedor ()
	{
		return sqlProductoProveedor.darProductosProveedor(pmf.getPersistenceManager());
	}
	
	public List<ProductoProveedor> darProductosProveedorPorCategoria (String categoria)
	{
		return sqlProductoProveedor.darProductosProveedorPorCategoria (pmf.getPersistenceManager(), categoria);
	}
	
	public List<ProductoProveedor> darProductosProveedorPorTipo (String tipo)
	{
		return sqlProductoProveedor.darProductosProveedorPorTipo (pmf.getPersistenceManager(), tipo);
	}
	
	public ProductoProveedor darProductoProveedorPorId (long idProductoProveedor)
	{
		return sqlProductoProveedor.darProductoProveedorPorId (pmf.getPersistenceManager(), idProductoProveedor);
	}
	
	public long aumentarNumeroCalificacionesProductoProveedor (long idProductoProveedor)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoProveedor.aumentarNumeroCalificaciones(pm, idProductoProveedor);
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
	
	public long aumentarSumaCalificacionesProductoProveedor (long idProductoProveedor, double suma)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoProveedor.aumentarSumaCalificaciones(pm, idProductoProveedor, suma);
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
	
	public long modificarCalidadProductoProveedor (long idProductoProveedor, double calidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoProveedor.modificarCalidadProductoProveedor(pm, idProductoProveedor, calidad);
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
	
	public long modificarFechaVencimientoProductoProveedor (long idProductoProveedor, String fecha)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoProveedor.modificarFechaVencimiento(pm, idProductoProveedor,fecha);
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
	
	/* ****************************************************************
	 * 			Métodos para manejar el OrdenPedido
	 *****************************************************************/
	
	public OrdenPedido adicionarOrdenPedido(double precio, String fechaEntrega, String fechaEsperadaEntrega,double calificacion, int entregado, int cantidad, long idProductoProveedor, long idSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idOrdenPedido = nextval ();
            long tuplasInsertadas = sqlOrdenPedido.adicionarOrdenPedido(pm, idOrdenPedido, precio, fechaEntrega, fechaEsperadaEntrega, calificacion, entregado, cantidad, idProductoProveedor, idSucursal);
            tx.commit();

            log.trace ("Inserción de OrdenPedido: " + idProductoProveedor + ": " + tuplasInsertadas + " tuplas insertadas");

            return new OrdenPedido (idOrdenPedido, precio, fechaEntrega, fechaEsperadaEntrega, calificacion, entregado, cantidad, idProductoProveedor, idSucursal);
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
	
	public OrdenPedido darOrdenPedidoPorId (long idOrdenPedido)
	{
		return sqlOrdenPedido.darOrdenPedidoPorId (pmf.getPersistenceManager(), idOrdenPedido);
	}
	
	public List<OrdenPedido> darOrdenesPedido ()
	{
		return sqlOrdenPedido.darOrdenesPedido (pmf.getPersistenceManager());
	}
	
	public long calificarOrdenPedido (long idOrdenPedido, double calificacion )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOrdenPedido.calificarOrdenPedido(pm, idOrdenPedido, calificacion);
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
	
	public long recibirEntregadoOrdenPedido (long idOrdenPedido )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOrdenPedido.recibirEntregadoOrdenPedido(pm, idOrdenPedido);
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
	
	public long recibirFechaOrdenPedido (long idOrdenPedido )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOrdenPedido.recibirFechaOrdenPedido(pm, idOrdenPedido);
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
	
	/* ****************************************************************
	 * 			Métodos para manejar BODEGA
	 *****************************************************************/
	public Bodega adicionarBodega(double volumen, double peso, String tipo, long sucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idBodega = nextval ();
            long tuplasInsertadas = sqlBodega.adicionarBodega(pm, idBodega, volumen, peso, tipo, sucursal);
            tx.commit();

            log.trace ("Inserción de Bodega: " + idBodega+ ": " + tuplasInsertadas + " tuplas insertadas");

            return new Bodega (idBodega, volumen, peso, tipo, sucursal);
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
	
	public Bodega darBodegaPorId (long idBodega)
	{
		return sqlBodega.darBodegaPorId (pmf.getPersistenceManager(), idBodega);
	}
	
	public List<Bodega> darBodegasPorTipo (String tipo)
	{
		return sqlBodega.darBodegasPorTipo (pmf.getPersistenceManager(), tipo);
	}
	
	public List<Bodega> darBodegas()
	{
		return sqlBodega.darBodegas (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar ESTANTE
	 *****************************************************************/
	public Estante adicionarEstante(double volumen, double peso, String tipo,int nivelAprovisionamiento, long sucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idEstante = nextval ();
            long tuplasInsertadas = sqlEstante.adicionarEstante(pm, idEstante, volumen, peso, tipo, nivelAprovisionamiento, sucursal);
            tx.commit();

            log.trace ("Inserción de Bodega: " + idEstante+ ": " + tuplasInsertadas + " tuplas insertadas");

            return new Estante (idEstante, volumen, peso, tipo,nivelAprovisionamiento, sucursal);
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
	
	public Estante darEstantePorId (long idEstante)
	{
		return sqlEstante.darEstantePorId (pmf.getPersistenceManager(), idEstante);
	}
	
	public List<Estante> darEstantesPorTipo (String tipo)
	{
		return sqlEstante.darEstantesPorTipo (pmf.getPersistenceManager(), tipo);
	}
	
	public List<Estante> darEstantes()
	{
		return sqlEstante.darEstantes (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar ProductoSucursal
	 *****************************************************************/
	public ProductoSucursal adicionarProductoSucursal(String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, String fechaVencimiento , int nivelReorden, double precioUnitario, int cantidadBodega, int cantidadEstante, double precioUnidadMedida
			, long idBodega, long idEstante, long idPromocion) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idProductoSucursal = nextval ();
            long tuplasInsertadas = sqlProductoSucursal.adicionarProductoSucursal (pmf.getPersistenceManager(), idProductoSucursal, nombre, marca, presentacion, cantidadPresentacion, unidadMedida
            		, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion);
    		tx.commit();

    		 log.trace ("Inserción de ProdcutoSucursal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new ProductoSucursal (idProductoSucursal, nombre, marca, presentacion, cantidadPresentacion, unidadMedida
            		, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion);
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
	
	public List<ProductoSucursal> darProductosSucursal ()
	{
		return sqlProductoSucursal.darProductosSucursal(pmf.getPersistenceManager());
	}
	
	public List<ProductoSucursal> darProductosSucursalPorCategoria (String categoria)
	{
		return sqlProductoSucursal.darProductosSucursalPorCategoria (pmf.getPersistenceManager(), categoria);
	}
	
	public List<ProductoSucursal> darProductosSucursalPorTipo (String tipo)
	{
		return sqlProductoSucursal.darProductosSucursalPorTipo (pmf.getPersistenceManager(), tipo);
	}
	
	public ProductoSucursal darProductoSucursalPorId (long idProductoSucursal)
	{
		return sqlProductoSucursal.darProductoSucursalPorId (pmf.getPersistenceManager(), idProductoSucursal);
	}
	
	public long aumentarCantidadBodegaProductoSucursal (long idProductoSucursal, int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoSucursal.aumentarCantidadBodegaProductoSucursal(pm, idProductoSucursal, cantidad);
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
	
	public long aumentarCantidadEstanteProductoSucursal (long idProductoSucursal, int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoSucursal.aumentarCantidadEstanteProductoSucursal(pm, idProductoSucursal, cantidad);
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
	
	public long disminuirCantidadBodegaProductoSucursal (long idProductoSucursal, int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoSucursal.disminuirCantidadBodegaProductoSucursal(pm, idProductoSucursal, cantidad);
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
	
	public long disminuirCantidadEstanteProductoSucursal (long idProductoSucursal, int cantidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlProductoSucursal.disminuirCantidadEstanteProductoSucursal(pm, idProductoSucursal, cantidad);
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
	
	/* ****************************************************************
	 * 			Métodos para manejar las PROMOCIONES
	 *****************************************************************/
	public Promocion adicionarPromocion(int tipo, double n, double m, String fechaCaducidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idPromocion = nextval ();
            long tuplasInsertadas = sqlPromocion.adicionarPromocion(pm, idPromocion, tipo, n, m, fechaCaducidad);
            tx.commit();

            log.trace ("Inserción de Promocion: " + tipo + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Promocion (idPromocion, tipo, n, m, fechaCaducidad);
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
	
	public List<Promocion> darPromociones ()
	{
		return sqlPromocion.darPromociones (pmf.getPersistenceManager());
	}
	
	public List<Promocion> darPromocionesPorTipo (int tipo)
	{
		return sqlPromocion.darPromocionesPorTipo (pmf.getPersistenceManager(), tipo);
	}
	
	public Promocion darPromocionPorId (long idPromocion)
	{
		return sqlPromocion.darPromocionPorId (pmf.getPersistenceManager(), idPromocion);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las VENTAS
	 *****************************************************************/
	public Compra adicionarCompra(String fecha, int cantidad, double totalPagado, long idProductoSucursal, long idCliente, long idFactura) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idCompra = nextval ();
            long tuplasInsertadas = sqlCompra.adicionarCompra(pm, idCompra, fecha, cantidad, totalPagado, idProductoSucursal, idCliente, idFactura);
            tx.commit();

            log.trace ("Inserción de Compra: " + idCompra + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Compra (idCompra, fecha, cantidad,totalPagado, idProductoSucursal, idCliente, idFactura);
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
	
	public List<Compra> darCompras ()
	{
		return sqlCompra.darCompras (pmf.getPersistenceManager());
	}
	
	public List<Compra> darComprasPorCliente(long idCliente)
	{
		return sqlCompra.darComprasPorCliente (pmf.getPersistenceManager(), idCliente);
	}
	
	public List<Compra> darComprasPorFactura(long idFactura)
	{
		return sqlCompra.darComprasPorFactura (pmf.getPersistenceManager(), idFactura);
	}
	
	public Compra darCompraPorId (long idCompra)
	{
		return sqlCompra.darCompraPorId (pmf.getPersistenceManager(), idCompra);
	}
	
	public long [] limpiarSuperAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarSuperAndes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
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
