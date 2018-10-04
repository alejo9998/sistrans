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
import uniandes.isis2304.SuperAndes.negocio.VOFactura;
import uniandes.isis2304.SuperAndes.negocio.VOPromocion;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class PromocionTest {
	
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PromocionTest.class.getName());
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
	public void unicidadPromocionTest() {
		try
		{
			log.info ("Probando la unicidad sobre Promocion");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad de Promocion incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad de Promocion incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOPromocion> lista = superAndes.darVOPromociones();
			assertEquals ("No debe haber Promociones creadas!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			int tipo1 = 1;
			int n1 = 1;
			int m1 = 2;
			String fechaCaducidad1 = "15/12/2018";
			VOPromocion promocion1 = superAndes.adicionarPromocion(tipo1, n1, m1 ,fechaCaducidad1);
			lista = superAndes.darVOPromociones();
			assertEquals ("Debe haber una promocion creada!!", 1, lista.size ());

			VOPromocion promocion2 = superAndes.adicionarPromocion2(promocion1.getIdPromocion(), tipo1, n1, m1, fechaCaducidad1);
			assertNull ("No puede adicionar dos promociones con el mismo id !!", promocion2);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Promocion.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD sobre la tabla promocion");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void restriccionesChequeoPromocionTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre Promocion");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de Promocion incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de Promocion incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOPromocion> lista = superAndes.darVOPromociones();
			assertEquals ("No debe haber promociones creados!!", 0, lista.size ());

			int tipo1 = 1;
			int n1 = 1;
			int m1 = 2;
			String fechaCaducidad1 = "15/12/2018";
			VOPromocion promocion1 = superAndes.adicionarPromocion(tipo1, n1, m1 ,fechaCaducidad1);
			lista = superAndes.darVOPromociones();
			assertEquals ("Debe haber una promocion creada!!", 1, lista.size ());

			VOPromocion promocion2 = superAndes.adicionarPromocion(5, n1, m1, fechaCaducidad1);
			assertNull ("El tipo de las promociones deben estar entre 1<=4 !!", promocion2);
			
			VOPromocion promocion3 = superAndes.adicionarPromocion(3, 0, m1, fechaCaducidad1);
			assertNull ("El n de la promociones debe ser mayor a 0 !!", promocion3);
			
			VOPromocion promocion4 = superAndes.adicionarPromocion(3, n1, 0, fechaCaducidad1);
			assertNull ("El m de la promociones debe ser mayor a 0 !!", promocion4);
			
			VOPromocion promocion5 = superAndes.adicionarPromocion(3, n1, m1, null);
			assertNull ("La fecha de caducidad de las promociones no puede ser null!!", promocion5);
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de CHEQUEO sobre la tabla Promocion.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla Promocion");
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
