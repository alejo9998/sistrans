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
import uniandes.isis2304.SuperAndes.negocio.VOEstante;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class EstanteTest {
	
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(EstanteTest.class.getName());
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
	public void unicidadEIntegridadEstanteTest() {
		try
		{
			log.info ("Probando la unicidad e integridad sobre Estante");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad e Integridad de Estante incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad e Integridad de Estante incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOEstante> lista = superAndes.darVOEstantes();
			assertEquals ("No debe haber estantes creadas!!", 0, lista.size ());
			
			String ciudad1 = "Zipaquira";
			String direccion1 = "cra 6 #1-39";
			String nombre1 = "SuperAndino";
			VOSucursal sucursal1 = superAndes.adicionarSucursal(ciudad1, direccion1, nombre1);

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String tipo1 = "carne";
			double peso1 = 1;
			double volumen1 = 2.6;
			int nivelAprovisionamiento1 = 50;
			long idSucursal1 = sucursal1.getIdSucursal();
			VOEstante estante1 = superAndes.adicionarEstante(volumen1, peso1, tipo1, nivelAprovisionamiento1 ,sucursal1.getIdSucursal());
			lista = superAndes.darVOEstantes();
			assertEquals ("Debe haber una estante creada!!", 1, lista.size ());

			VOEstante estante2 = superAndes.adicionarEstante2(estante1.getIdEstante(),volumen1, peso1, tipo1, nivelAprovisionamiento1 ,sucursal1.getIdSucursal());
			assertNull ("No puede adicionar dos estantes con el mismo id !!", estante2);
			
			VOEstante estante3 = superAndes.adicionarEstante(volumen1, peso1, tipo1, nivelAprovisionamiento1, idSucursal1 + 2);
			assertNull ("No puede adicionar estantes con un idSucursal que no existe en la tabla Sucursal !!", estante3);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD e INTEGRIDADsobre la tabla Estante.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD e INTEGRIDAD sobre la tabla estante");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void restriccionesChequeoEstanteTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre Estante");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de Estante incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de Estante incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOEstante> lista = superAndes.darVOEstantes();
			assertEquals ("No debe haber estantes creadas!!", 0, lista.size ());
			
			String ciudad1 = "Zipaquira";
			String direccion1 = "cra 6 #1-39";
			String nombre1 = "SuperAndino";
			VOSucursal sucursal1 = superAndes.adicionarSucursal(ciudad1, direccion1, nombre1);

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String tipo1 = "carne";
			double peso1 = 1;
			double volumen1 = 2.6;
			int nivelAprovisionamiento1 = 50;
			VOEstante estante1 = superAndes.adicionarEstante(volumen1, peso1, tipo1, nivelAprovisionamiento1 ,sucursal1.getIdSucursal());
			lista = superAndes.darVOEstantes();
			assertEquals ("Debe haber una estante creada!!", 1, lista.size ());

			VOEstante estante2 = superAndes.adicionarEstante(0, peso1, tipo1, nivelAprovisionamiento1 ,sucursal1.getIdSucursal());
			assertNull ("El el volumen del estante debe ser mayor a 0 !!", estante2);
			
			VOEstante estante3 = superAndes.adicionarEstante(volumen1, 0, tipo1, nivelAprovisionamiento1 ,sucursal1.getIdSucursal());
			assertNull ("El el peso del estante debe ser mayor a 0 !!", estante3);
			
			VOEstante estante4 = superAndes.adicionarEstante(volumen1, peso1, null, nivelAprovisionamiento1 ,sucursal1.getIdSucursal());
			assertNull ("El tipo del estante no puede ser null !!", estante4);
			
			VOEstante estante5 = superAndes.adicionarEstante(volumen1, peso1, tipo1, 0 ,sucursal1.getIdSucursal());
			assertNull ("El nivelAprovisionamiento debe ser mayor a 0 !!", estante5);
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de CHEQUEO sobre la tabla Estante.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla Estante");
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
