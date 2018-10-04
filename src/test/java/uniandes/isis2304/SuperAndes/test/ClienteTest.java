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
import uniandes.isis2304.SuperAndes.negocio.VOProveedor;

public class ClienteTest {
	
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ClienteTest.class.getName());
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
	public void unicidadClienteTest() {
		try
		{
			log.info ("Probando la unicidad sobre Cliente");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad de Cliente incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad de Cliente incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOCliente> lista = superAndes.darVOClientes();
			assertEquals ("No debe haber clientes creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombreClienteInd1 = "Alejandro";
			String correoClienteInd1 = "chonal77@hotmail.com";
			VOCliente clienteInd1 = superAndes.adicionarClienteIndividuo(nombreClienteInd1, correoClienteInd1);
			lista = superAndes.darVOClientesIndividuos();
			assertEquals ("Debe haber un cliente individuo creado !!", 1, lista.size ());

			VOCliente clienteInd2 = superAndes.adicionarClienteIndividuo2 (clienteInd1.getIdentificacion(), nombreClienteInd1, correoClienteInd1);
			assertNull ("No puede adicionar dos clienteIndividuo con las misma identificacion !!", clienteInd2);
			
			String nombreClienteEmp1 = "Gran via";
			String correoClienteEmp1 = "granvia@hotmail.com";
			String direccionClienteEmp1 = "Cra6#1-39";
			VOCliente clienteEmp1 = superAndes.adicionarClienteEmpresa(nombreClienteInd1, correoClienteInd1, direccionClienteEmp1);
			lista = superAndes.darVOClientesEmpresa();
			assertEquals ("Debe haber un cliente Empresa creado !!", 1, lista.size ());

			VOCliente clienteEmp2 = superAndes.adicionarClienteEmpresa2 (clienteEmp1.getIdentificacion(), nombreClienteEmp1, correoClienteEmp1, direccionClienteEmp1);
			assertNull ("No puede adicionar dos clienteEmpresa con la misma identificacion !!", clienteInd2);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Cliente.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD sobre la tabla cliente");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}

	@Test
	public void restriccionesChequeoClienteTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre Cliente");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de Cliente incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de Cliente incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOCliente> lista = superAndes.darVOClientes();
			assertEquals ("No debe haber clientes creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombreClienteI1 = "Alejandro";
			String coreoClienteI1 = "chonal@hotmail.com";
			VOCliente clienteI1 = superAndes.adicionarClienteIndividuo (nombreClienteI1, coreoClienteI1);
			lista = superAndes.darVOClientesIndividuos();
			assertEquals ("Debe haber un clienteindividuo creado !!", 1, lista.size ());

			VOCliente cliente2 = superAndes.adicionarClienteIndividuo ( null, "meLaPela@hotmail.com");
			assertNull ("No puede adicionar clientes con nombre null !!", cliente2);
			
			VOCliente cliente3 = superAndes.adicionarClienteIndividuo ( "melaPela", null);
			assertNull ("No puede adicionar clientes con correo null !!", cliente3);
			
			VOCliente cliente4 = superAndes.adicionarClienteEmpresa ( null, "meLaPela@hotmail.com", "cra2#1-23");
			assertNull ("No puede adicionar clientes con nombre null !!", cliente4);
			
			VOCliente cliente5 = superAndes.adicionarClienteEmpresa ( "melaPela", null,"cra2#1-23");
			assertNull ("No puede adicionar clientes con correo null !!", cliente3);
			
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de CHEQUEO sobre la tabla Cliente.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla cliente");
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
