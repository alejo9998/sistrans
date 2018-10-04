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
import uniandes.isis2304.SuperAndes.negocio.VOEstante;
import uniandes.isis2304.SuperAndes.negocio.VOProductoProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class ProductoProveedorTest {
	

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
	public void unicidadEIntegridadProductoProveedorTest() {
		try
		{
			log.info ("Probando la unicidad e integridad sobre ProductoProveedor");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad e Integridad de ProductoProveedor incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad e Integridad de ProductoProveedor incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOProductoProveedor> lista = superAndes.darVOProductosProveedor();
			assertEquals ("No debe haber productosProveedor creadas!!", 0, lista.size ());
			
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
			lista = superAndes.darVOProductosProveedor();
			assertEquals ("Debe haber un ProductoProveedor creada!!", 1, lista.size ());

			VOProductoProveedor productoProveedor2 = superAndes.adicionarProductoProveedor2(productoProveedor1.getIdProducto(),nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
			assertNull ("No puede adicionar dos productosProveedor con el mismo id !!", productoProveedor2);
			
			VOProductoProveedor productoProveedor3 = superAndes.adicionarProductoProveedor(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor + 3);
			assertNull ("No se puede adicionar productos con in idProveedor que no exista en la tabla Proveedor !!", productoProveedor3);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD e INTEGRIDADsobre la tabla ProductoProveedor.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD e INTEGRIDAD sobre la tabla productoProveedor");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	/**
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
			String tipo1 = "CARNE";
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
	*/
	
	
	
	
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
