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
import uniandes.isis2304.SuperAndes.negocio.VOProductoProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOProductoSucursal;
import uniandes.isis2304.SuperAndes.negocio.VOPromocion;
import uniandes.isis2304.SuperAndes.negocio.VOProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class ProductoSucursalTest {

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
	public void unicidadEIntegridadProductoSucursalTest() {
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
			List <VOProductoSucursal> lista = superAndes.darVOProductosSucursal();
			assertEquals ("No debe haber productosSucursal creadas!!", 0, lista.size ());
			
			String ciudadSucursal = "Zipaquira";
			String direccion = "Cra 6#1-39";
			String nombreSucursal = "ZipaAndes";
			VOSucursal sucursal = superAndes.adicionarSucursal(ciudadSucursal, direccion, nombreSucursal);
			long idSucursal = sucursal.getIdSucursal();
			
			double volumenBodega = 5000;
			double pesoBodega = 5000;
			String tipoBodega = "Carne";
			VOBodega bodega = superAndes.adicionarBodega(volumenBodega, pesoBodega, tipoBodega, idSucursal);
			
			double volumenEstante = 2500;
			double pesoEstante = 2500;
			String tipoEstante = "Carne";
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
			String categoria = "PERECEDERO";
			String tipo = "CARNE";
			String fechaVencimiento = "15/12/2018";
			int nivelReorden = 50;
			double precioUnitario = 5000;
			int cantidadBodega = 80;
			int cantidadEstante = 20;
			double precioUnidadMedida = 5;
			long idBodega = bodega.getIdBodega();
			long idEstante = estante.getIdEstante();
			
			VOProductoSucursal productoSucursal1 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			lista = superAndes.darVOProductosSucursal();
			assertEquals("Debe haber un productoSucursal creado!!", 1 , lista.size());

			VOProductoSucursal productoSucursal2 = superAndes.adicionarProductoSucursal2(productoSucursal1.getIdProductoSucursal(), nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion);
			assertNull("No se puede adicionar dosProductoSucursal con el mismo Id!!", productoSucursal2);

			VOProductoSucursal productoSucursal3 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega+2, idEstante, idPromocion);
			assertNull("No se puede adicionar un productoSucursal con un idBodega que no existe en la tabla Bodega!!", productoSucursal3);

			VOProductoSucursal productoSucursal4 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante+2, idPromocion);
			assertNull("No se puede adicionar un productoSucursal con un idEstante que no existe en la tabla Estante!!", productoSucursal4);

			VOProductoSucursal productoSucursal5 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion+2);
			assertNull("No se puede adicionar un productoSucursal con un idPromocion que no existe en la tabla Promocion!!", productoSucursal5);
			
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD e INTEGRIDADsobre la tabla ProductoSucursal.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD e INTEGRIDAD sobre la tabla productoSucursal");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void restriccionesChequeoProductoSucursalTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre ProductoSucursal");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de ProductoSucursal incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de ProductoSucursal incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOProductoSucursal> lista = superAndes.darVOProductosSucursal();
			assertEquals ("No debe haber productosSucursal creadas!!", 0, lista.size ());
			
			String ciudadSucursal = "Zipaquira";
			String direccion = "Cra 6#1-39";
			String nombreSucursal = "ZipaAndes";
			VOSucursal sucursal = superAndes.adicionarSucursal(ciudadSucursal, direccion, nombreSucursal);
			long idSucursal = sucursal.getIdSucursal();
			
			double volumenBodega = 5000;
			double pesoBodega = 5000;
			String tipoBodega = "Carne";
			VOBodega bodega = superAndes.adicionarBodega(volumenBodega, pesoBodega, tipoBodega, idSucursal);
			
			double volumenEstante = 2500;
			double pesoEstante = 2500;
			String tipoEstante = "Carne";
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
			String categoria = "PERECEDERO";
			String tipo = "CARNE";
			String fechaVencimiento = "15/12/2018";
			int nivelReorden = 50;
			double precioUnitario = 5000;
			int cantidadBodega = 80;
			int cantidadEstante = 20;
			double precioUnidadMedida = 5;
			long idBodega = bodega.getIdBodega();
			long idEstante = estante.getIdEstante();
			
			VOProductoSucursal productoSucursal1 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			lista = superAndes.darVOProductosSucursal();
			assertEquals("Debe haber un productoSucursal creado!!", 1 , lista.size());

			VOProductoSucursal productoSucursal2 = superAndes.adicionarProductoSucursal(null, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("El nombre del producto no puede ser null!!", productoSucursal2);
			
			VOProductoSucursal productoSucursal3 = superAndes.adicionarProductoSucursal(nombre, null, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("El marca del producto no puede ser null!!", productoSucursal3);
			
			VOProductoSucursal productoSucursal4 = superAndes.adicionarProductoSucursal(nombre, marca, null, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("El presentacion del producto no puede ser null!!", productoSucursal4);
			
			VOProductoSucursal productoSucursal5 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, 0, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("la cantidad de la presentacion debe ser mayor a cero!!", productoSucursal5);
			
			VOProductoSucursal productoSucursal6 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, null, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("El unidad de medida del producto no puede ser null!!", productoSucursal6);
			
			VOProductoSucursal productoSucursal7 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, null, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("El cateogira del producto no puede ser null!!", productoSucursal7);
			
			VOProductoSucursal productoSucursal8 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, null, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("El tipo del producto no puede ser null!!", productoSucursal8);
			
			VOProductoSucursal productoSucursal9 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, null, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("El fechaVencimiento del producto no puede ser null!!", productoSucursal9);
			
			VOProductoSucursal productoSucursal10 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, 0, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("El nivelReorden dle producto debe ser mayor o igual a cero!!", productoSucursal10);
			
			VOProductoSucursal productoSucursal11 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, 0, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("El precio unitario del productoSucursal debe ser mayor a cero!!", productoSucursal11);
			
			VOProductoSucursal productoSucursal12 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, -1, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("La cantidadBodega del productoSucursal debe ser mayor o igual a 0!!", productoSucursal12);
			
			VOProductoSucursal productoSucursal13 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, -1, precioUnidadMedida, idBodega, idEstante, null);
			assertNull ("La cantidadEstante del productoSucursal debe ser mayor o igual a 0!!", productoSucursal13);
			
			VOProductoSucursal productoSucursal14 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, 0, idBodega, idEstante, null);
			assertNull ("El precioUnidad medida del productoSucursal debe ser mayor a cero!!", productoSucursal14);
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de CHEQUEO sobre la tabla ProductoProveedor.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de CHEQUEO sobre la tabla ProductoProveedor");
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
