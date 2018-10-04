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
import uniandes.isis2304.SuperAndes.negocio.VOBodega;
import uniandes.isis2304.SuperAndes.negocio.VOPromocion;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class BodegaTest {
	
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(BodegaTest.class.getName());
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
	public void unicidadEIntegridadBodegaTest() {
		try
		{
			log.info ("Probando la unicidad e integridad sobre Bodega");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad e Integridad de Bodega incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad e Integridad de Bodega incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOBodega> lista = superAndes.darVOBodegas();
			assertEquals ("No debe haber bodegas creadas!!", 0, lista.size ());
			
			String ciudad1 = "Zipaquira";
			String direccion1 = "cra 6 #1-39";
			String nombre1 = "SuperAndino";
			VOSucursal sucursal1 = superAndes.adicionarSucursal(ciudad1, direccion1, nombre1);

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String tipo1 = "CARNE";
			double peso1 = 1;
			double volumen1 = 2.6;
			VOBodega bodega1 = superAndes.adicionarBodega(volumen1, peso1, tipo1 ,sucursal1.getIdSucursal());
			lista = superAndes.darVOBodegas();
			assertEquals ("Debe haber una bodega creada!!", 1, lista.size ());

			VOBodega bodega2 = superAndes.adicionarBodega2(bodega1.getIdBodega(),volumen1, peso1, tipo1 ,sucursal1.getIdSucursal());
			assertNull ("No puede adicionar dos bodegas con el mismo id !!", bodega2);
			
			VOBodega bodega3 = superAndes.adicionarBodega(volumen1, peso1, tipo1, 7777);
			assertNull ("No puede adicionar bodegas con un idSucursal que no existe en la tabla Sucursal !!", bodega3);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD e INTEGRIDADsobre la tabla Bodega.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD e INTEGRIDAD sobre la tabla bodega");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void restriccionesChequeoBodegaTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre Bodega");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de Bodega incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de Bodega incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOBodega> lista = superAndes.darVOBodegas();
			assertEquals ("No debe haber bodegas creadas!!", 0, lista.size ());
			
			String ciudad1 = "Zipaquira";
			String direccion1 = "cra 6 #1-39";
			String nombre1 = "SuperAndino";
			VOSucursal sucursal1 = superAndes.adicionarSucursal(ciudad1, direccion1, nombre1);

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String tipo1 = "CARNE";
			double peso1 = 1;
			double volumen1 = 2.6;
			VOBodega bodega1 = superAndes.adicionarBodega(volumen1, peso1, tipo1 ,sucursal1.getIdSucursal());
			lista = superAndes.darVOBodegas();
			assertEquals ("Debe haber una bodega creada!!", 1, lista.size ());

			VOBodega bodega2 = superAndes.adicionarBodega(0, peso1, tipo1 ,sucursal1.getIdSucursal());
			assertNull ("El el volumen de la Bodega debe ser mayor a 0 !!", bodega2);
			
			VOBodega bodega3 = superAndes.adicionarBodega(volumen1, 0, tipo1 ,sucursal1.getIdSucursal());
			assertNull ("El el peso de la Bodega debe ser mayor a 0 !!", bodega3);
			
			VOBodega bodega4 = superAndes.adicionarBodega(volumen1, peso1, null ,sucursal1.getIdSucursal());
			assertNull ("El tipo de la bodega no puede ser null !!", bodega4);
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de CHEQUEO sobre la tabla Bodega.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla Bodega");
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
