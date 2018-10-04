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
import uniandes.isis2304.SuperAndes.negocio.VOProveedor;

public class ProveedorTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(ProveedorTest.class.getName());
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

	/* ****************************************************************
	 * 			M�todos de prueba para la tabla Proveedor - adicion y obtencion de datos
	 *****************************************************************/
	@Test
	public void CRDProveedorTest() 
	{
		// Probar primero la conexi�n a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Proveedor");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de CRD de SuperAndes incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());
			String msg = "Prueba de CRD de Proveedor incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}

		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los tipos de bebida con la tabla vac�a
			List <VOProveedor> lista = superAndes.darVOProveedores();
			assertEquals ("No debe haber proveedores creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombreProveedor1 = "Oreo";
			VOProveedor proveedor1 = superAndes.adicionarProveedor (nombreProveedor1);
			lista = superAndes.darVOProveedores();
			assertEquals ("Debe haber un proveedor creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", proveedor1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String nombreProveedor2 = "Babaria";
			VOProveedor proveedor2 = superAndes.adicionarProveedor (nombreProveedor2);
			lista = superAndes.darVOProveedores();
			assertEquals ("Debe haber dos proveedores creados !!", 2, lista.size ());
			assertTrue ("El primer proveedor adicionado debe estar en la tabla", proveedor1.equals (lista.get (0)) || proveedor1.equals (lista.get (1)));
			assertTrue ("El segundo proveedor adicionado debe estar en la tabla", proveedor2.equals (lista.get (0)) || proveedor2.equals (lista.get (1)));

			long idProveedor1 = proveedor1.getNit();
			long idProveedor2 = proveedor2.getNit();
			VOProveedor pruebaProveedor1 = (VOProveedor) superAndes.darProveedorPorNit(idProveedor1);
			VOProveedor pruebaProveedor2 = (VOProveedor) superAndes.darProveedorPorNit(idProveedor2);
			assertEquals("Los dos objetos deberian ser iguales 1", proveedor1, pruebaProveedor1);
			assertEquals("Los dos objetos deberian ser iguales 2", proveedor2, pruebaProveedor2);


		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de operaciones sobre la tabla Proveedor.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

			fail ("Error en las pruebas sobre la tabla Proveedor");
		}
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}

	@Test
	public void unicidadProveedorTest() {
		try
		{
			log.info ("Probando la unicidad sobre Proveedor");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad de Proveedor incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());
			String msg = "Prueba de Unicidad de Proveedor incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vac�a
			List <VOProveedor> lista = superAndes.darVOProveedores();
			assertEquals ("No debe haber proveedores creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombreProveedor1 = "Oreo";
			VOProveedor proveedor1 = superAndes.adicionarProveedor (nombreProveedor1);
			lista = superAndes.darVOProveedores();
			assertEquals ("Debe haber un proveedor creado !!", 1, lista.size ());

			VOProveedor proveedor2 = superAndes.adicionarProveedor2 (proveedor1.getNit(), nombreProveedor1);
			assertNull ("No puede adicionar dos proveedores con el mismo nit !!", proveedor2);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de UNICIDAD sobre la tabla Proveedor.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD sobre la tabla proveedor");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}

	@Test
	public void restriccionesChequeoProveedorTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre Proveedor");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de Proveedor incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de Proveedor incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vac�a
			List <VOProveedor> lista = superAndes.darVOProveedores();
			assertEquals ("No debe haber proveedores creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombreProveedor1 = "Oreo";
			VOProveedor proveedor1 = superAndes.adicionarProveedor (nombreProveedor1);
			lista = superAndes.darVOProveedores();
			assertEquals ("Debe haber un proveedor creado !!", 1, lista.size ());

			VOProveedor proveedor2 = superAndes.adicionarProveedor ( null);
			assertNull ("No puede adicionar proveedores con nombre null !!", proveedor2);
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de CHEQUEO sobre la tabla Proveedor.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla proveedor");
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
