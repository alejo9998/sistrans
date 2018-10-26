package uniandes.isis2304.SuperAndes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.SuperAndes.negocio.SuperAndes;
import uniandes.isis2304.SuperAndes.negocio.VOOrdenPedido;
import uniandes.isis2304.SuperAndes.negocio.VOProductoProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class OrdenPedidoTest {
	

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
	public void unicidadEIntegridadOrdenPedidoTest() {
		try
		{
			log.info ("Probando la unicidad e integridad sobre OrdenPedido");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad e Integridad de OrdenPedido incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad e Integridad de OrdenPedido incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOOrdenPedido> lista = superAndes.darVOOrdenesPedido();
			assertEquals ("No debe haber ordenesPedido creados!!", 0, lista.size ());
			
			String nombreProveedor1 = "SuperAndino";
			VOProveedor proveedor1 = superAndes.adicionarProveedor(nombreProveedor1);

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombre = "Papas fritas";
			String marca = "SuperRicas";
			String presentacion = "paqueton de 5 paquetes de 200 gr. cada uno";
			double cantidadPresentacion = 1000;
			String unidadMedida = "gramos";
			double volumenEmpaque = 300.5;
			double pesoEmpaque = 1000;
			long codigoBarras = 13515;
			String categoria = "PERECEDERO";
			String tipo = "PAQUETE";
			String fechaVencimiento = "15/12/2018";
			double calidad = 3;
			double precio = 8000;
			int numeroCalificaciones =5 ;
			double sumaCalificaciones = 15;
			long idProveedor = proveedor1.getNit();
			VOProductoProveedor productoProveedor1 = superAndes.adicionarProductoProveedor(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
			long idProductoProveedor = productoProveedor1.getIdProductoProveedor();
			
			String ciudad1 = "Zipaquira";
			String direccion1 = "cra 6 #1-39";
			String nombre1 = "Extoto";
			VOSucursal sucursal1 = superAndes.adicionarSucursal(ciudad1, direccion1, nombre1);
			long idSucursal = sucursal1.getIdSucursal();
			
			double precioOrdenPedido = 80000;
			String fechaEntregaOrdenPedidoNULL = null;
			String fechaEsperadaEntregaOrdenPedido = "11/12/2018";
			Double calificacionOrdenPedidoNULL = null;
			int entregadoOrdenPedido0 = 0;
			int cantidadOrdenPedido = 10;
			
			VOOrdenPedido ordenPedido1 = superAndes.adicionarOrdenPedido(precioOrdenPedido, fechaEntregaOrdenPedidoNULL, fechaEsperadaEntregaOrdenPedido, calificacionOrdenPedidoNULL, entregadoOrdenPedido0, cantidadOrdenPedido, idProductoProveedor, idSucursal);	
			lista = superAndes.darVOOrdenesPedido();
			assertEquals ("Debe haber un OrdenPedido creado!!", 1, lista.size ());
			
			long idOrdenPedido1 = ordenPedido1.getIdOrdenPedido(); 

			VOOrdenPedido ordenPedido2 = superAndes.adicionarOrdenPedido2(idOrdenPedido1,precioOrdenPedido, fechaEntregaOrdenPedidoNULL, fechaEsperadaEntregaOrdenPedido, calificacionOrdenPedidoNULL, entregadoOrdenPedido0, cantidadOrdenPedido, idProductoProveedor, idSucursal);	
			assertNull ("No puede adicionar dos ordenesPedido con el mismo id !!", ordenPedido2);
			
			VOOrdenPedido ordenPedido3 = superAndes.adicionarOrdenPedido(precioOrdenPedido, fechaEntregaOrdenPedidoNULL, fechaEsperadaEntregaOrdenPedido, calificacionOrdenPedidoNULL, entregadoOrdenPedido0, cantidadOrdenPedido, idProductoProveedor+21, idSucursal);	
			assertNull ("No puede adicionar dos ordenesPedido con un productoProveedor que no exista en la base de datos en la tabla ProductoProveedor!!", ordenPedido3);
			
			VOOrdenPedido ordenPedido4 = superAndes.adicionarOrdenPedido(precioOrdenPedido, fechaEntregaOrdenPedidoNULL, fechaEsperadaEntregaOrdenPedido, calificacionOrdenPedidoNULL, entregadoOrdenPedido0, cantidadOrdenPedido, idProductoProveedor, idSucursal+21);	
			assertNull ("No puede adicionar dos ordenesPedido con una sucursal que no exista en la tabla SUCURSAL  en la base de datos!!", ordenPedido4);
			
			
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD e INTEGRIDADsobre la tabla OrdenPedido.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD e INTEGRIDAD sobre la tabla OrdenPedido");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void restriccionesChequeoOrdenPedidoTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre OrdenPedido");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de OrdenPedido incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de OrdenPedido incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOOrdenPedido> lista = superAndes.darVOOrdenesPedido();
			assertEquals ("No debe haber ordenesPedido creados!!", 0, lista.size ());
			
			String nombreProveedor1 = "SuperAndino";
			VOProveedor proveedor1 = superAndes.adicionarProveedor(nombreProveedor1);

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombre = "Papas fritas";
			String marca = "SuperRicas";
			String presentacion = "paqueton de 5 paquetes de 200 gr. cada uno";
			double cantidadPresentacion = 1000;
			String unidadMedida = "gramos";
			double volumenEmpaque = 300.5;
			double pesoEmpaque = 1000;
			long codigoBarras = 13515;
			String categoria = "PERECEDERO";
			String tipo = "PAQUETE";
			String fechaVencimiento = "15/12/2018";
			double calidad = 3;
			double precio = 8000;
			int numeroCalificaciones =5 ;
			double sumaCalificaciones = 15;
			long idProveedor = proveedor1.getNit();
			VOProductoProveedor productoProveedor1 = superAndes.adicionarProductoProveedor(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
			long idProductoProveedor = productoProveedor1.getIdProductoProveedor();
			
			String ciudad1 = "Zipaquira";
			String direccion1 = "cra 6 #1-39";
			String nombre1 = "Extoto";
			VOSucursal sucursal1 = superAndes.adicionarSucursal(ciudad1, direccion1, nombre1);
			long idSucursal = sucursal1.getIdSucursal();
			
			double precioOrdenPedido = 80000;
			String fechaEntregaOrdenPedidoNULL = null;
			String fechaEsperadaEntregaOrdenPedido = "11/12/2018";
			Double calificacionOrdenPedidoNULL = null;
			int entregadoOrdenPedido0 = 0;
			int cantidadOrdenPedido = 10;
			
			VOOrdenPedido ordenPedido1 = superAndes.adicionarOrdenPedido(precioOrdenPedido, fechaEntregaOrdenPedidoNULL, fechaEsperadaEntregaOrdenPedido, calificacionOrdenPedidoNULL, entregadoOrdenPedido0, cantidadOrdenPedido, idProductoProveedor, idSucursal);	
			lista = superAndes.darVOOrdenesPedido();
			assertEquals ("Debe haber un OrdenPedido creado!!", 1, lista.size ());

			VOOrdenPedido ordenPedido2 = superAndes.adicionarOrdenPedido(0, fechaEntregaOrdenPedidoNULL, fechaEsperadaEntregaOrdenPedido, calificacionOrdenPedidoNULL, entregadoOrdenPedido0, cantidadOrdenPedido, idProductoProveedor, idSucursal);	
			assertNull ("El precio del OrdenPedido tiene que ser mayor a cero!!", ordenPedido2);
			
			VOOrdenPedido ordenPedido3 = superAndes.adicionarOrdenPedido(precioOrdenPedido, fechaEntregaOrdenPedidoNULL, null, calificacionOrdenPedidoNULL, entregadoOrdenPedido0, cantidadOrdenPedido, idProductoProveedor, idSucursal);	
			assertNull ("La fecha de espera del OrdenPedido no puede ser null!!", ordenPedido3);
			
			VOOrdenPedido ordenPedido4 = superAndes.adicionarOrdenPedido(precioOrdenPedido, fechaEntregaOrdenPedidoNULL, fechaEsperadaEntregaOrdenPedido, calificacionOrdenPedidoNULL, 2, cantidadOrdenPedido, idProductoProveedor, idSucursal);	
			assertNull ("La entrega del orden pedido debe ser o 1 o 0!!", ordenPedido4);
			
			VOOrdenPedido ordenPedido5 = superAndes.adicionarOrdenPedido(precioOrdenPedido, fechaEntregaOrdenPedidoNULL, fechaEsperadaEntregaOrdenPedido, calificacionOrdenPedidoNULL, entregadoOrdenPedido0, 0, idProductoProveedor, idSucursal);	
			assertNull ("La cantidad del OrdenPedido debe ser mayor a 0!!", ordenPedido5);
			
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de CHEQUEO sobre la tabla OrdenPedido.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla OrdenPedido");
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
