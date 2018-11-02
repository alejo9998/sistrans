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

import uniandes.isis2304.SuperAndes.negocio.SuperAndes;
import uniandes.isis2304.SuperAndes.negocio.VOBodega;
import uniandes.isis2304.SuperAndes.negocio.VOCarrito;
import uniandes.isis2304.SuperAndes.negocio.VOEstante;
import uniandes.isis2304.SuperAndes.negocio.VOProductoSucursal;
import uniandes.isis2304.SuperAndes.negocio.VOPromocion;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class CarritoTest {
	
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(ProductoProveedorTest.class.getName());
	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD tambi�n
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
	public void unicidadEIntegridadCarritoTest() {
		try
		{
			log.info ("Probando la unicidad e integridad sobre Carrito");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad e Integridad de Carrito incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad e Integridad de Carrito incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vac�a
			List <VOCarrito> lista = superAndes.darVOCarritos();
			assertEquals ("No debe haber Carritos creadas!!", 0, lista.size ());
			
			String ciudadSucursal = "Zipaquira";
			String direccion = "Cra 6#1-39";
			String nombreSucursal = "ZipaAndes";
			VOSucursal sucursal = superAndes.adicionarSucursal(ciudadSucursal, direccion, nombreSucursal);
			long idSucursal = sucursal.getIdSucursal();
			
			double volumenBodega = 5000;
			double pesoBodega = 5000;
			String tipoBodega = "carne";
			VOBodega bodega = superAndes.adicionarBodega(volumenBodega, pesoBodega, tipoBodega, idSucursal);
			
			double volumenEstante = 2500;
			double pesoEstante = 2500;
			String tipoEstante = "carne";
			int nivelAprovisionamiento = 1000;
			VOEstante estante = superAndes.adicionarEstante(volumenEstante, pesoEstante, tipoEstante, nivelAprovisionamiento, idSucursal);
			
			int tipoPromocion = 2;
			double n = 20;
			double m = 20;
			String fechaCaducidad = "12/12/2018";
			VOPromocion promocion = superAndes.adicionarPromocion(tipoPromocion, n, m, fechaCaducidad);
			long idPromocion = promocion.getIdPromocion();

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombre = "Papas fritas";
			String marca = "SuperRicas";
			String presentacion = "paqueton de 5 paquetes de 200 gr. cada uno";
			double cantidadPresentacion = 1000;
			String unidadMedida = "gramos";
			double volumenEmpaque = 20;
			double pesoEmpaque = 1000;
			long codigoBarras = 13515;
			String categoria = "perecedero";
			String tipo = "carne";
			String fechaVencimiento = "15/12/2018";
			int nivelReorden = 50;
			double precioUnitario = 5000;
			int cantidadBodega = 80;
			int cantidadEstante = 20;
			double precioUnidadMedida = 5;
			long idBodega = bodega.getIdBodega();
			long idEstante = estante.getIdEstante();
			VOProductoSucursal productoSucursal1 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			
			int ocupadoCarrito = 0;
			VOCarrito carrito1 = superAndes.adicionarCarrito(ocupadoCarrito, idSucursal);
			lista = superAndes.darVOCarritos();
			assertEquals("Debe haber un carrito creado!!", 1, lista.size());
			
			List<VOCarrito> lista2 = superAndes.darVOCarritosLibresSucursal(idSucursal);
			assertEquals("Debe haber un carrito libre para la sucursal", 1, lista2.size());
			
			superAndes.modificarEstadoOcupacionCarrito(carrito1.getIdCarrito(), 1);
			lista2 = superAndes.darVOCarritosOcupadosSucursal(idSucursal);
			assertEquals("Debe haber un carrito ocupado para la sucursal", 1, lista2.size());
			
			superAndes.modificarEstadoOcupacionCarrito(carrito1.getIdCarrito(), -1);
			lista2 = superAndes.darVOCarritosAbandonadosSucursal(idSucursal);
			assertEquals("Debe haber un carrito abandonado para la sucursal", 1, lista2.size());

			VOCarrito carrito2 = superAndes.adicionarCarrito(ocupadoCarrito, idSucursal+2);
			assertNull("No se puede agregar un carrito a una sucursal que no existe", carrito2);
			
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de UNICIDAD e INTEGRIDADsobre la tabla Carrito.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD e INTEGRIDAD sobre la tabla Carrito");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void restriccionesChequeoCarritoTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre Carrito");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de Carrito incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de Carrito incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vac�a
			List <VOCarrito> lista = superAndes.darVOCarritos();
			assertEquals ("No debe haber Carritos creadas!!", 0, lista.size ());
			
			String ciudadSucursal = "Zipaquira";
			String direccion = "Cra 6#1-39";
			String nombreSucursal = "ZipaAndes";
			VOSucursal sucursal = superAndes.adicionarSucursal(ciudadSucursal, direccion, nombreSucursal);
			long idSucursal = sucursal.getIdSucursal();
			
			double volumenBodega = 5000;
			double pesoBodega = 5000;
			String tipoBodega = "carne";
			VOBodega bodega = superAndes.adicionarBodega(volumenBodega, pesoBodega, tipoBodega, idSucursal);
			
			double volumenEstante = 2500;
			double pesoEstante = 2500;
			String tipoEstante = "carne";
			int nivelAprovisionamiento = 1000;
			VOEstante estante = superAndes.adicionarEstante(volumenEstante, pesoEstante, tipoEstante, nivelAprovisionamiento, idSucursal);
			
			int tipoPromocion = 2;
			double n = 20;
			double m = 20;
			String fechaCaducidad = "12/12/2018";
			VOPromocion promocion = superAndes.adicionarPromocion(tipoPromocion, n, m, fechaCaducidad);
			long idPromocion = promocion.getIdPromocion();

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombre = "Papas fritas";
			String marca = "SuperRicas";
			String presentacion = "paqueton de 5 paquetes de 200 gr. cada uno";
			double cantidadPresentacion = 1000;
			String unidadMedida = "gramos";
			double volumenEmpaque = 20;
			double pesoEmpaque = 1000;
			long codigoBarras = 13515;
			String categoria = "perecedero";
			String tipo = "carne";
			String fechaVencimiento = "15/12/2018";
			int nivelReorden = 50;
			double precioUnitario = 5000;
			int cantidadBodega = 80;
			int cantidadEstante = 20;
			double precioUnidadMedida = 5;
			long idBodega = bodega.getIdBodega();
			long idEstante = estante.getIdEstante();
			VOProductoSucursal productoSucursal1 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			
			int ocupadoCarrito = 0;
			VOCarrito carrito1 = superAndes.adicionarCarrito(ocupadoCarrito, idSucursal);
			lista = superAndes.darVOCarritos();
			assertEquals("Debe haber un carrito creado!!", 1, lista.size());
			
			VOCarrito carrito2 = superAndes.adicionarCarrito(2, idSucursal);
			assertNull ("La ocupacion debe ser (-1,0,1)", carrito2);
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de CHEQUEO sobre la tabla Carrito.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla Carrito");
		}    			
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
		

	}
	
	/* ****************************************************************
	 * 			M�todos de configuraci�n
	 *****************************************************************/
	/**
	 * Lee datos de configuraci�n para la aplicaci�n, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraci�n deseada
	 * @param archConfig - Archivo Json que contiene la configuraci�n
	 * @return Un objeto JSON con la configuraci�n del tipo especificado
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
			log.info ("Se encontr� un archivo de configuraci�n de tablas v�lido");
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontr� un archivo de configuraci�n v�lido");			
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de tablas v�lido: ", "TipoBebidaTest", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}	

}
