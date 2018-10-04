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
import uniandes.isis2304.SuperAndes.negocio.VOFactura;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;


public class FacturaTest {
	
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(FacturaTest.class.getName());
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
	public void unicidadFacturaTest() {
		try
		{
			log.info ("Probando la unicidad sobre Factura");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad de Factura incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad de Factura incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vac�a
			List <VOFactura> lista = superAndes.darVOFacturas();
			assertEquals ("No debe haber facturas creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String descripcion1 = "3 paquetes de papas de 1000 pesos";
			VOFactura factura1 = superAndes.adicionarFactura(descripcion1);
			lista = superAndes.darVOFacturas();
			assertEquals ("Debe haber una factura creado !!", 1, lista.size ());

			VOFactura factura2 = superAndes.adicionarFactura2 (factura1.getIdFactura(), descripcion1);
			assertNull ("No puede adicionar dos facturas con el mismo id !!", factura2);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de UNICIDAD sobre la tabla Factura.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD sobre la tabla Factura");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void restriccionesChequeoFacturaTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre Factura");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de Factura incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de Factura incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vac�a
			List <VOFactura> lista = superAndes.darVOFacturas();
			assertEquals ("No debe haber facturas creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
            String descripcion1 = "3 Pollos, total: 10000";
			VOFactura factura1 = superAndes.adicionarFactura(descripcion1);
			lista = superAndes.darVOFacturas();
			assertEquals ("Debe haber una factura creada!!", 1, lista.size ());

			VOFactura factura2 = superAndes.adicionarFactura(null);
			assertNull ("No puede adicionar facturas con descripcion null !!", factura2);
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de CHEQUEO sobre la tabla Factura.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla factura");
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
