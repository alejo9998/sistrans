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
import uniandes.isis2304.SuperAndes.negocio.VOCliente;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class SucursalTest {
	
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SucursalTest.class.getName());
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
	public void unicidadSucursalTest() {
		try
		{
			log.info ("Probando la unicidad sobre Sucursal");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad de Sucursal incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad de Sucursal incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOSucursal> lista = superAndes.darVOSucursales();
			assertEquals ("No debe haber sucursales creadas!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String ciudad1 = "Zipaquira";
			String direccion1 = "cra 6 #1-39";
			String nombre1 = "SuperAndino";
			VOSucursal sucursal1 = superAndes.adicionarSucursal(ciudad1, direccion1, nombre1);
			lista = superAndes.darVOSucursales();
			assertEquals ("Debe haber una sucursal creada!!", 1, lista.size ());

			VOSucursal sucursal2 = superAndes.adicionarSucursal2(sucursal1.getIdSucursal(), ciudad1, direccion1, nombre1);
			assertNull ("No puede adicionar dos sucursales con el mismo id !!", sucursal2);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Sucursal.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD sobre la tabla sucursal");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void restriccionesChequeoSucursalTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre Sucursal");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de Sucursal incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de Sucursal incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOSucursal> lista = superAndes.darVOSucursales();
			assertEquals ("No debe haber sucursales creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String ciudad1 = "Zipaquira";
			String direccion1 = "cra 6 #1-39";
			String nombre1 = "SuperAndino";
			VOSucursal sucursal1 = superAndes.adicionarSucursal(ciudad1, direccion1, nombre1);
			lista = superAndes.darVOSucursales();
			assertEquals ("Debe haber una sucursal creada!!", 1, lista.size ());

			VOSucursal sucursal2 = superAndes.adicionarSucursal("San Andres", "Cra6#2-32", null);
			assertNull ("No puede adicionar sucursales con nombre null !!", sucursal2);
			
			VOSucursal sucursal3 = superAndes.adicionarSucursal("San Andres", null, "El castigo");
			assertNull ("No puede adicionar sucursales con direccion null !!", sucursal3);
			
			VOSucursal sucursal4 = superAndes.adicionarSucursal(null, "Cra6#2-32", "El castigo");
			assertNull ("No puede adicionar clientes con nombre null !!", sucursal4);
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de CHEQUEO sobre la tabla Sucursal.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla sucursal");
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
