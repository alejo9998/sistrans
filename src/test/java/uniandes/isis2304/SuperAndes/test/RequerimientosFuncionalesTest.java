package uniandes.isis2304.SuperAndes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.SuperAndes.negocio.ProductoSucursal;
import uniandes.isis2304.SuperAndes.negocio.SuperAndes;
import uniandes.isis2304.SuperAndes.negocio.VOBodega;
import uniandes.isis2304.SuperAndes.negocio.VOCarrito;
import uniandes.isis2304.SuperAndes.negocio.VOCliente;
import uniandes.isis2304.SuperAndes.negocio.VOCompra;
import uniandes.isis2304.SuperAndes.negocio.VODentroCarrito;
import uniandes.isis2304.SuperAndes.negocio.VOEstante;
import uniandes.isis2304.SuperAndes.negocio.VOOrdenPedido;
import uniandes.isis2304.SuperAndes.negocio.VOProductoProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOProductoSucursal;
import uniandes.isis2304.SuperAndes.negocio.VOPromocion;
import uniandes.isis2304.SuperAndes.negocio.VOProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

/**
 * Clase para hacer los test de cada requerimiento funcionl de la aplicacion
 * @author Rouzajor
 *
 */
public class RequerimientosFuncionalesTest {

	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ProductoProveedorTest.class.getName());
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * La clase que se quiere probar
	 */
	private SuperAndes superAndes;

	@Test
	public void pruebaRequerimientosFuncionales() {
		try
		{
			log.info ("Probando la funcionalidad de SuperAndes");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de la funcionalidad de SuperAndes incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de la funcionalidad de SuperAndes incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			//*****************************************************
			//RF1 - Registrar Proveedores
			//*****************************************************

			List <VOProveedor> listaProveedores = superAndes.darVOProveedores();
			assertEquals ("No debe haber proveedores creados!!", 0, listaProveedores.size ());

			String nombreProveedor2 = "Navisco";
			VOProveedor proveedor2 = superAndes.registrarProveedoresRF1(nombreProveedor2);
			long idProveedor2 = proveedor2.getNit();

			listaProveedores = superAndes.darVOProveedores();
			assertEquals ("Debe haber un proveedor creado !!", 1, listaProveedores.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", proveedor2, listaProveedores.get (0));

			String nombreProveedor1 = "Chuletones";
			VOProveedor proveedor1 = superAndes.registrarProveedoresRF1(nombreProveedor1);
			long idProveedor1 = proveedor1.getNit();

			String nombreProveedor3 = "CasaLimpia";
			VOProveedor proveedor3 = superAndes.registrarProveedoresRF1(nombreProveedor3);
			long idProveedor3 = proveedor3.getNit();

			listaProveedores =superAndes.darVOProveedores();
			assertEquals ("Debe haber tres proveedores creado !!", 3, listaProveedores.size ());

			//*****************************************************
			//RF2 - RegistrarProductos
			//*****************************************************

			//Primero hay que poblar las tablas con algunos datos
			
			//***************************
			//Aca se testea el RF4 registarSucursal
			//***************************
			
			List<VOSucursal> listaSucursales = superAndes.darVOSucursales();
			assertEquals ("No debe haber sucursal creadas", 0 , listaSucursales.size());
			
			String ciudadSucursal1 = "Zipaquira";
			String direccionSucursal1 = "cra 6 #1-39";
			String nombreSucursal1 = "SuperAndino";
			VOSucursal sucursal1 = superAndes.adicionarSucursal(ciudadSucursal1, direccionSucursal1, nombreSucursal1);
			long idSucursal1 = sucursal1.getIdSucursal();
			
			listaSucursales = superAndes.darVOSucursales();
			assertEquals ("Debe haber una sucursal creada", 1, listaSucursales.size());
			assertEquals ("Deben coincidir los datos de la sucursal", sucursal1, listaSucursales.get(0));
			
			//***************************
			//Aca se testea RF5 registrar Bodega a sucursal
			//***************************
			
			List<VOBodega> listaBodegas = superAndes.darVOBodegas();
			assertEquals ("No debe haber bodegas creadas", 0 ,listaBodegas.size());
			
			String tipoBodega1 = "carne";
			double pesoBodega1 = 1000000 ;
			double volumenBodega1 = 1000000;
			VOBodega bodega1 = superAndes.registrarBodegaASucursalRF5(volumenBodega1, pesoBodega1, tipoBodega1, idSucursal1);
			long idBodega1 = bodega1.getIdBodega();
			
			listaBodegas = superAndes.darVOBodegas();
			assertEquals ("Debe haber una bodega creada" ,1 , listaBodegas.size());
			assertEquals ("Los datos de la bodega deben coincidir", bodega1, listaBodegas.get(0));
			
			String tipoBodega2 = "galleta";
			double pesoBodega2 = 1000000 ;
			double volumenBodega2 = 1000000;
			VOBodega bodega2 = superAndes.registrarBodegaASucursalRF5(volumenBodega2, pesoBodega2, tipoBodega2, idSucursal1);
			long idBodega2 = bodega2.getIdBodega();
			
			String tipobodega3 = "escoba";
			double pesoBodega3 = 1000000;
			double volumenBodega3 = 1000000;
			VOBodega bodega3 = superAndes.registrarBodegaASucursalRF5(volumenBodega3, pesoBodega3, tipobodega3, idSucursal1);
			long idBodega3 = bodega3.getIdBodega();
			
			listaBodegas = superAndes.darVOBodegas();
			assertEquals ("Debe haber tres bodegas creada" ,3 , listaBodegas.size());
			
			/* ***************************************
			 * Aca se testea RF6 registarEstante en sucursal
			 ***********************************************/
			
			List<VOEstante> listaEstantes = superAndes.darVOEstantes();
			assertEquals("No deben haber estantes creados",0, listaEstantes.size());
			
			String tipoEstante1 = "carne";
			double pesoEstante1 = 100000 ;
			double volumenEstante1 = 100000;
			int nivelAprovisionamientoEstante1 = 20; 
			VOEstante estante1 = superAndes.registrarEstanteASucursalRF6(volumenEstante1, pesoEstante1, tipoEstante1, nivelAprovisionamientoEstante1, idSucursal1);
			long idEstante1 = estante1.getIdEstante();
			
			listaEstantes = superAndes.darVOEstantes();
			assertEquals ("Debe haber un estante creado",1 , listaEstantes.size());
			assertEquals ("Los datos del estante deben coincidir", estante1, listaEstantes.get(0));
			
			String tipoEstante2 = "galleta";
			double pesoEstante2 = 100000 ;
			double volumenEstante2 = 100000;
			int nivelAprovisionamientoEstante2 = 30; 
			VOEstante estante2 = superAndes.registrarEstanteASucursalRF6(volumenEstante2, pesoEstante2, tipoEstante2, nivelAprovisionamientoEstante2, idSucursal1);
			long idEstante2 = estante2.getIdEstante();
			
			String tipoEstante3 = "escoba";
			double pesoEstante3 = 100000 ;
			double volumenEstante3 = 100000;
			int nivelAprovisionamientoEstante3 = 15; 
			VOEstante estante3 = superAndes.registrarEstanteASucursalRF6(volumenEstante3, pesoEstante3, tipoEstante3, nivelAprovisionamientoEstante3, idSucursal1);
			long idEstante3 = estante3.getIdEstante();
			
			listaEstantes = superAndes.darVOEstantes();
			assertEquals ("Debe haber tres estantes creado",3 , listaEstantes.size());
			
			// RF2
			List<VOProductoSucursal> listaProductosSucursal = superAndes.darVOProductosSucursal();
			List<VOProductoProveedor> listaProductosProveedor = superAndes.darVOProductosProveedor();
			assertEquals ("NO debe haber productosSucursales creados", 0, listaProductosSucursal.size());
			assertEquals ("NO debe haber productosProveedor creados", 0 , listaProductosProveedor.size());
			
			String nombreProductoSucursal1 = "Carne de Cerdo";
			String marcaProductoSucursal1 = "Chuletones";
			String presentacionProductoSucursal1 = "5 paquetes de 200 gr. cada uno";
			double cantidadPresentacionProductoSucursal1 = 5;
			String unidadMedidaProductoSucursal1 = "gramos";
			double volumenEmpaqueProductoSucursal1 = 500;
			double pesoEmpaqueProductoSucursal1 = 1000;
			long codigoBarrasProductoSucursal1 = 1;
			String categoriaProductoSucursal1 = "perecedero";
			String tipoProductoSucursal1 = "carne";
			String fechaVencimientoProductoSucursal1 = "15/12/2018";
			int nivelReordenProductoSucursal1 = 60;
			double precioUnitarioProductoSucursal1 = 20000;
			int cantidadBodegaProductoSucursal1 = 10;
			int cantidadEstanteProductoSucursal1 = 15;
			double precioUnidadMedidaProductoSucursal1 = 20;
			VOProductoSucursal productoSucursal1 = superAndes.registrarProductoSucursalRF2(nombreProductoSucursal1, marcaProductoSucursal1, presentacionProductoSucursal1, cantidadPresentacionProductoSucursal1, unidadMedidaProductoSucursal1, volumenEmpaqueProductoSucursal1, pesoEmpaqueProductoSucursal1, codigoBarrasProductoSucursal1, categoriaProductoSucursal1, tipoProductoSucursal1, fechaVencimientoProductoSucursal1, nivelReordenProductoSucursal1, precioUnitarioProductoSucursal1, cantidadBodegaProductoSucursal1, cantidadEstanteProductoSucursal1, precioUnidadMedidaProductoSucursal1, idBodega1, idEstante1, null);
			long idProductoSucursal1 = productoSucursal1.getIdProductoSucursal();

			listaProductosSucursal = superAndes.darVOProductosSucursal();
			assertEquals ("Debe haber un productoSucursal creado", 1, listaProductosSucursal.size());
			assertEquals ("El productoSucursal recuperado debe ser igual", productoSucursal1, listaProductosSucursal.get(0));
			
			String nombreProductoSucursal2 = "Galletas";
			String marcaProductoSucursal2 = "Oreo";
			String presentacionProductoSucursal2 = "5 paquetes de 100 gr. cada uno";
			double cantidadPresentacionProductoSucursal2 = 5;
			String unidadMedidaProductoSucursal2 = "gramos";
			double volumenEmpaqueProductoSucursal2 = 300;
			double pesoEmpaqueProductoSucursal2 = 500;
			long codigoBarrasProductoSucursal2 = 2;
			String categoriaProductoSucursal2 = "perecedero";
			String tipoProductoSucursal2 = "galleta";
			String fechaVencimientoProductoSucursal2 = "15/02/2019";
			int nivelReordenProductoSucursal2 = 80;
			double precioUnitarioProductoSucursal2 = 5000;
			int cantidadBodegaProductoSucursal2 = 60;
			int cantidadEstanteProductoSucursal2 = 40;
			double precioUnidadMedidaProductoSucursal2 = 10;
			VOProductoSucursal productoSucursal2 = superAndes.registrarProductoSucursalRF2(nombreProductoSucursal2, marcaProductoSucursal2, presentacionProductoSucursal2, cantidadPresentacionProductoSucursal2, unidadMedidaProductoSucursal2, volumenEmpaqueProductoSucursal2, pesoEmpaqueProductoSucursal2, codigoBarrasProductoSucursal2, categoriaProductoSucursal2, tipoProductoSucursal2, fechaVencimientoProductoSucursal2, nivelReordenProductoSucursal2, precioUnitarioProductoSucursal2, cantidadBodegaProductoSucursal2, cantidadEstanteProductoSucursal2, precioUnidadMedidaProductoSucursal2, idBodega2, idEstante2, null);
			long idProductoSucursal2 = productoSucursal2.getIdProductoSucursal();

			String nombreProductoSucursal3 = "Escoba";
			String marcaProductoSucursal3 = "Kankun";
			String presentacionProductoSucursal3 = "una escoba";
			double cantidadPresentacionProductoSucursal3 = 1;
			String unidadMedidaProductoSucursal3 = "gramos";
			double volumenEmpaqueProductoSucursal3 = 100;
			double pesoEmpaqueProductoSucursal3 = 300;
			long codigoBarrasProductoSucursal3 = 3;
			String categoriaProductoSucursal3 = "albarrote";
			String tipoProductoSucursal3 = "escoba";
			String fechaVencimientoProductoSucursal3 = "15/02/4000";
			int nivelReordenProductoSucursal3 = 40;
			double precioUnitarioProductoSucursal3 = 3000;
			int cantidadBodegaProductoSucursal3 = 30;
			int cantidadEstanteProductoSucursal3 = 30;
			double precioUnidadMedidaProductoSucursal3 = 10;
			VOProductoSucursal productoSucursal3 = superAndes.registrarProductoSucursalRF2(nombreProductoSucursal3, marcaProductoSucursal3, presentacionProductoSucursal3, cantidadPresentacionProductoSucursal3, unidadMedidaProductoSucursal3, volumenEmpaqueProductoSucursal3, pesoEmpaqueProductoSucursal3, codigoBarrasProductoSucursal3, categoriaProductoSucursal3, tipoProductoSucursal3, fechaVencimientoProductoSucursal3, nivelReordenProductoSucursal3, precioUnitarioProductoSucursal3, cantidadBodegaProductoSucursal3, cantidadEstanteProductoSucursal3, precioUnidadMedidaProductoSucursal3, idBodega3, idEstante3, null);
			
			listaProductosSucursal = superAndes.darVOProductosSucursal();
			assertEquals ("Debe haber 3 productosSucursales creados", 3, listaProductosSucursal.size());
			
			String nombreProductoProveedor1 = "Carne de Cerdo";
			String marcaProductoProveedor1 = "Chuletones";
			String presentacionProductoProveedor1 = "5 paquetes de 200 gr. cada uno";
			double cantidadPresentacionProductoProveedor1 = 5;
			String unidadMedidaProductoProveedor1 = "gramos";
			double volumenEmpaqueProductoProveedor1 = 500;
			double pesoEmpaqueProductoProveedor1 = 1000;
			long codigoBarrasProductoProveedor1 = 1;
			String categoriaProductoProveedor1 = "perecedero";
			String tipoProductoProveedor1 = "carne";
			String fechaVencimientoProductoProveedor1 = "15/12/2019";
			double calidadProductoProveedor1 = 0;
			double precioProductoProveedor1 = 15000;
			int numeroCalificacionesProductoProveedor1 = 0;
			double sumaCalificacionesProductoProveedor1 = 0;
			VOProductoProveedor productoProveedor1 = superAndes.registrarProductoProveedorRF2(nombreProductoProveedor1, marcaProductoProveedor1, presentacionProductoProveedor1, cantidadPresentacionProductoProveedor1, unidadMedidaProductoProveedor1, volumenEmpaqueProductoProveedor1, pesoEmpaqueProductoProveedor1, codigoBarrasProductoProveedor1, categoriaProductoProveedor1, tipoProductoProveedor1, fechaVencimientoProductoProveedor1, calidadProductoProveedor1, precioProductoProveedor1, numeroCalificacionesProductoProveedor1, sumaCalificacionesProductoProveedor1, idProveedor1);
			long idProductoProveedor1 = productoProveedor1.getIdProductoProveedor();
			
			listaProductosProveedor = superAndes.darVOProductosProveedor();
			assertEquals ("Debe haber un productoProveedor creado", 1, listaProductosProveedor.size());
			assertEquals ("Los datos del productoProveedor deben coincidir", productoProveedor1, listaProductosProveedor.get(0));
			
			String nombreProductoProveedor2 = "Galletas";
			String marcaProductoProveedor2 = "Oreo";
			String presentacionProductoProveedor2 = "5 paquetes de 100 gr. cada uno";
			double cantidadPresentacionProductoProveedor2 = 5;
			String unidadMedidaProductoProveedor2 = "gramos";
			double volumenEmpaqueProductoProveedor2 = 300;
			double pesoEmpaqueProductoProveedor2 = 500;
			long codigoBarrasProductoProveedor2 = 2;
			String categoriaProductoProveedor2 = "perecedero";
			String tipoProductoProveedor2 = "galleta";
			String fechaVencimientoProductoProveedor2 = "15/12/2019";
			double calidadProductoProveedor2 = 0;
			double precioProductoProveedor2 = 3000;
			int numeroCalificacionesProductoProveedor2 = 0;
			double sumaCalificacionesProductoProveedor2 = 0;
			VOProductoProveedor productoProveedor2 = superAndes.registrarProductoProveedorRF2(nombreProductoProveedor2, marcaProductoProveedor2, presentacionProductoProveedor2, cantidadPresentacionProductoProveedor2, unidadMedidaProductoProveedor2, volumenEmpaqueProductoProveedor2, pesoEmpaqueProductoProveedor2, codigoBarrasProductoProveedor2, categoriaProductoProveedor2, tipoProductoProveedor2, fechaVencimientoProductoProveedor2, calidadProductoProveedor2, precioProductoProveedor2, numeroCalificacionesProductoProveedor2, sumaCalificacionesProductoProveedor2, idProveedor2);
			long idProductoProveedor2 = productoProveedor2.getIdProductoProveedor();
			
			String nombreProductoProveedor3 = "Escoba";
			String marcaProductoProveedor3 = "Kankun";
			String presentacionProductoProveedor3 = "una escoba";
			double cantidadPresentacionProductoProveedor3 = 1;
			String unidadMedidaProductoProveedor3 = "gramos";
			double volumenEmpaqueProductoProveedor3 = 100;
			double pesoEmpaqueProductoProveedor3 = 300;
			long codigoBarrasProductoProveedor3 = 3;
			String categoriaProductoProveedor3 = "albarrote";
			String tipoProductoProveedor3 = "escoba";
			String fechaVencimientoProductoProveedor3 = "15/02/4000";
			double calidadProductoProveedor3 = 0;
			double precioProductoProveedor3 = 2000;
			int numeroCalificacionesProductoProveedor3 = 0;
			double sumaCalificacionesProductoProveedor3 = 0;
			VOProductoProveedor productoProveedor3 = superAndes.registrarProductoProveedorRF2(nombreProductoProveedor3, marcaProductoProveedor3, presentacionProductoProveedor3, cantidadPresentacionProductoProveedor3, unidadMedidaProductoProveedor3, volumenEmpaqueProductoProveedor3, pesoEmpaqueProductoProveedor3, codigoBarrasProductoProveedor3, categoriaProductoProveedor3, tipoProductoProveedor3, fechaVencimientoProductoProveedor3, calidadProductoProveedor3, precioProductoProveedor3, numeroCalificacionesProductoProveedor3, sumaCalificacionesProductoProveedor3, idProveedor3);
			long idProductoProveedor3 = productoProveedor3.getIdProductoProveedor();
			
			listaProductosProveedor = superAndes.darVOProductosProveedor();
			assertEquals ("Debe haber 3 produtosProveedor creados", 3, listaProductosProveedor.size());
			
			//*****************************************************
			//RF3 - Registrar Clientes
			//*****************************************************
			
			List<VOCliente> listaClientes = superAndes.darVOClientes();
			List<VOCliente> listaClientesIndividuo = superAndes.darVOClientesIndividuos();
			List<VOCliente> listaClientesEmpresa = superAndes.darVOClientesEmpresa();
			assertEquals ( "No debe haber clientes creados",0 ,listaClientes.size());
			assertEquals ( "No debe haber clientesIndividuo creados",0 ,listaClientesIndividuo.size());
			assertEquals ( "No debe haber clientesEmpresa creados",0 ,listaClientesEmpresa.size());
			
			long idClienteInd1 = 1075690311;
			String nombreClienteInd1 = "Alejandro";
			String correoClienteInd1 = "chonal77@hotmail.com";
			VOCliente clienteInd1= superAndes.registrarClienteIndividuoRF3(idClienteInd1, nombreClienteInd1, correoClienteInd1);
			
			listaClientesIndividuo = superAndes.darVOClientesIndividuos();
			assertEquals ("Debe haber un cliente individuo creado", 1, listaClientesIndividuo.size());
			assertEquals ("Deben coincidir los datos del ClienteIndividuo", clienteInd1 , listaClientesIndividuo.get(0));
			
			long idClienteEmp1 = 320150156;
			String nombreClienteEmp1 = "Rouzz.inc";
			String correoClienteEmp1 = "rouzz@outlook.com";
			String direccionClienteEmp1 =  "Cra 8 #1-38";
			VOCliente clienteEmp1 = superAndes.registrarClienteEmpresaRF3(idClienteEmp1, nombreClienteEmp1, correoClienteEmp1, direccionClienteEmp1);
			
			listaClientesEmpresa = superAndes.darVOClientesEmpresa ();
			assertEquals ("Debe haber un cliente Empresa  creado", 1, listaClientesEmpresa.size());
			assertEquals ("Deben coincidir los datos del ClienteEmpresa ", clienteEmp1 , listaClientesEmpresa.get(0));
	
			//*****************************************************
			//RF4 - Registrar Sucursal (Este test esta dentro del RF2)
			//*****************************************************
			
			//*****************************************************
			//RF5 - Registrar Bodega a Sucursal(Este test esta dentro del RF2)
			//*****************************************************
			
			//*****************************************************
			//RF6 - Registrar Estante a Sucursal (Este test esta dentro del RF2)
			//*****************************************************

			//******************************************************
			//RF7 - Registrar una promocion
			//******************************************************
			
			List<VOPromocion> listaPromociones = superAndes.darVOPromociones();
			assertEquals ("No debe haber promociones creadas", 0 , listaPromociones.size());
			
			int tipoPromocion1 = 1;
			int nPromocion1 = 1;
			int mPromocion1 = 2;
			String fechaCaducidadPromocion1 = "10/01/2019";
			VOPromocion promocion1 = superAndes.req7RegistrarPromocion(tipoPromocion1, nPromocion1, mPromocion1, fechaCaducidadPromocion1);
			long idPromocion1 = promocion1.getIdPromocion();
			
			listaPromociones = superAndes.darVOPromociones();
			assertEquals ("Debe haber una Promocion creada", 1, listaPromociones.size());
			assertEquals ("Los datos de la promocion debe coincidir", promocion1, listaPromociones.get(0));
			
			//**********************************************************
			//RF8 - TerminarPromocion
			//**********************************************************
			
			superAndes.req8TerminarPromocion(idPromocion1);
			
			listaPromociones = superAndes.darVOPromociones();
			assertEquals ("No debe haber promociones creadas", 0, listaPromociones.size());
			
			//***********************************************************
			//RF9 - Registrar el pedido de un producto para una sucursal
			// **********************************************************
			
			List<VOOrdenPedido> listaOrdenesPedido = superAndes.darVOOrdenesPedido();
			assertEquals ("No debe haber ordenesPedido creados", 0 ,listaOrdenesPedido.size());
			
			String fechaEsperadaEntregaOrdenPedido1 = "01/12/2019";
			int cantidadOrdenPedido1 = 60;
			VOOrdenPedido ordenPedido1 = superAndes.req9RegistrarPedidoDeUnProductoAProveedorParaSucursal(fechaEsperadaEntregaOrdenPedido1, cantidadOrdenPedido1, idProductoProveedor1, idSucursal1);
			long idOrdenPedido1 = ordenPedido1.getIdOrdenPedido();
			
			listaOrdenesPedido = superAndes.darVOOrdenesPedido();
			assertEquals ("Debe haber un ordenPedido creado",1, listaOrdenesPedido.size());
			assertEquals ("Deben coincidir los datos de los ordenesPedido", ordenPedido1, listaOrdenesPedido.get(0));
			
			//***************************************************************
			//RF10 - Registrar la llegada de un OrdenPedido
			//****************************************************************
			
			double calificacionLLegadaOrdenPedido1 = 5;
			superAndes.req10RegistrarLlegadaOrdenPedido(idOrdenPedido1, calificacionLLegadaOrdenPedido1);
			
			productoProveedor1 = superAndes.darProductoProveedorPorId(idProductoProveedor1);

			assertEquals ("La cantidad de calificaciones de productoProveedor1 debe ser 1", 1, productoProveedor1.getNumeroCalificaciones());
			assertEquals ("La calidad de ProductoProveedor1 debe ser 5", 5, (int) productoProveedor1.getCalidad());
			assertEquals ("El la suma de calificaciones de productoProveedor1 debe ser 5", 5, (int) productoProveedor1.getSumaCalificaciones());
			
			productoSucursal1 = superAndes.darProductoSucursalPorId(idProductoSucursal1);
			
			assertEquals ("La cantidad en estante del productoSucursal1 debe ser 70", 70 , productoSucursal1.getCantidadEstante());
			assertEquals ("La cantidad en bodega del productoSucursal1 debe ser 15", 15 , productoSucursal1.getCantidadBodega());
			
			//****************************************************************
			//RF12 - SolicitarCarritoCompras
			//***************************************************************
			
			//Primero se agregan unos carritos
			VOCarrito carrito1 = superAndes.adicionarCarrito(0, idSucursal1);
			long idCarrito1 = carrito1.getIdCarrito();
			
			carrito1 = superAndes.solicitarCarritoRF12(idSucursal1);
			
			assertEquals ("la ocupacion del carro debe ser 1" ,1 ,carrito1.getOcupado());
			
			//*****************************************************************
			//RF13- Adicionar producto al carrito de comrpas
			//*****************************************************************
			
			List<VODentroCarrito> listaDentroCarrito = superAndes.darVODentroCarrito();
			assertEquals ("No deben haber productos dentro carritos", 0 , listaDentroCarrito.size());
			
			superAndes.adicionarProductoAlCarritoRF13(idCarrito1, idProductoSucursal1, 8);
			
			listaDentroCarrito = superAndes.darVODentroCarrito();
			assertEquals ("Debe haber un producto metido en un carrito",1, listaDentroCarrito.size() );
			assertEquals ("Deben haber 62 productos en el estante1", 62, superAndes.darProductoSucursalPorId(idProductoSucursal1).getCantidadEstante());
			
			//**************************************************************
			//RF14- Devolver un producto del carrito de compras
			//*************************************************************
			
			superAndes.devolverProductoDelCarritoRF14(idCarrito1, idProductoSucursal1, 3);
			
			listaDentroCarrito = superAndes.darVODentroCarrito();
			assertEquals ("Debe haber un producto metido en un carrito",1, listaDentroCarrito.size() );
			assertEquals ("Deben haber 65 productoSucursal1 en el estante1", 65, superAndes.darProductoSucursalPorId(idProductoSucursal1).getCantidadEstante());
			
			//****************************************************************
			//RF15 - Pagar la compra
			//*******************************************************************
			List<VOCompra> listaCompras = superAndes.darVOCompras();
			assertEquals ("No deben haber compras hechas", 0, listaCompras.size());
			
			superAndes.pagarCompraRF15(idCarrito1, idClienteInd1);
			
			listaCompras = superAndes.darVOCompras();
			assertEquals ("Debe haber una compra", 1, listaCompras.size());
			VOCompra compra1 = listaCompras.get(0);
			long idCompra1 = compra1.getIdCompra();
			
			assertEquals ("El cliente de la compra no coincide", idClienteInd1, compra1.getCliente());
			assertEquals ("El prodcuto de la compra no coincide", idProductoSucursal1 , compra1.getProductoSucursal());
			assertEquals ("La cantidad de la compra no coincide", 5, compra1.getCantidad());
			assertEquals ("El estado de ocupacion del carrito deberia ser libre", 0, superAndes.darCarritoPorId(idCarrito1).getOcupado());
			
			//******************************************************************
			//RF16 - Abandonar Carrito
			//*************************************************************
			List<VOCarrito> listaCarritosAbandonados = superAndes.darVOCarritosAbandonadosSucursal(idSucursal1);
			assertEquals ("No deben haver carritos abandonados", 0, listaCarritosAbandonados.size());
			
			superAndes.solicitarCarritoRF12(idSucursal1);
			superAndes.adicionarProductoAlCarritoRF13(idCarrito1, idProductoSucursal1, 5);
			superAndes.abandonarCarritoRF16(idCarrito1);
			
			listaCarritosAbandonados = superAndes.darVOCarritosAbandonadosSucursal(idSucursal1);
			assertEquals ("Debe haber un carrito abandonado",1 , listaCarritosAbandonados.size());
			
			//**********************************************************************
			//RF17 - Recolectar Productos abandoandos
			//*********************************************************************
			
			superAndes.recolectarProductosAbandonadosRF17(idSucursal1);
			
			assertEquals ("Debe haber 65 productoSucursal1", 65 , superAndes.darProductoSucursalPorId(idProductoSucursal1).getCantidadEstante());
			assertEquals ("El estado del carrito debio cambiar a 0", 0, superAndes.darCarritoPorId(idCarrito1).getOcupado());

		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD e INTEGRIDADsobre la tabla ProductoSucursal.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD e INTEGRIDAD sobre la tabla productoSucursal");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "TipoBebidaTest", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}	

}
