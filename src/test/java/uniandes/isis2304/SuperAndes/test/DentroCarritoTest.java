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
import uniandes.isis2304.SuperAndes.negocio.VODentroCarrito;
import uniandes.isis2304.SuperAndes.negocio.VOEstante;
import uniandes.isis2304.SuperAndes.negocio.VOProductoSucursal;
import uniandes.isis2304.SuperAndes.negocio.VOPromocion;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class DentroCarritoTest {
	
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
	public void unicidadEIntegridadDentroCarritoTest() {
		try
		{
			log.info ("Probando la unicidad e integridad sobre DentroCarrito");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad e Integridad de DentroCarrito incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad e Integridad de DentroCarrito incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VODentroCarrito> lista = superAndes.darVODentroCarrito();
			assertEquals ("No debe haber DentroCarritos creadas!!", 0, lista.size ());
			
			String ciudadSucursal = "Zipaquira";
			String direccion = "Cra 6#1-39";
			String nombreSucursal = "ZipaAndes";
			VOSucursal sucursal = superAndes.adicionarSucursal(ciudadSucursal, direccion, nombreSucursal);
			long idSucursal = sucursal.getIdSucursal();
			
			double volumenBodega = 5000;
			double pesoBodega = 5000;
			String tipoBodega = "carne";
			VOBodega bodega = superAndes.adicionarBodega(volumenBodega, pesoBodega, tipoBodega, idSucursal);
			
			double volumenBodega2 = 5000;
			double pesoBodega2 = 5000;
			String tipoBodega2 = "galleta";
			VOBodega bodega2 = superAndes.adicionarBodega(volumenBodega2, pesoBodega2, tipoBodega2, idSucursal);
			
			double volumenEstante = 2500;
			double pesoEstante = 2500;
			String tipoEstante = "carne";
			int nivelAprovisionamiento = 15;
			VOEstante estante = superAndes.adicionarEstante(volumenEstante, pesoEstante, tipoEstante, nivelAprovisionamiento, idSucursal);
			
			double volumenEstante2 = 2500;
			double pesoEstante2 = 2500;
			String tipoEstante2 = "galleta";
			int nivelAprovisionamiento2 = 20;
			VOEstante estante2 = superAndes.adicionarEstante(volumenEstante2, pesoEstante2, tipoEstante2, nivelAprovisionamiento2, idSucursal);
			
			int tipoPromocion = 2;
			double n = 20;
			double m = 20;
			String fechaCaducidad = "12/12/2018";
			VOPromocion promocion = superAndes.adicionarPromocion(tipoPromocion, n, m, fechaCaducidad);
			long idPromocion = promocion.getIdPromocion();

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String nombre = "Carne de Res";
			String marca = "SuperVaca";
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
			int cantidadBodega = 50;
			int cantidadEstante = 20;
			double precioUnidadMedida = 5;
			long idBodega = bodega.getIdBodega();
			long idEstante = estante.getIdEstante();
			VOProductoSucursal productoSucursal1 = superAndes.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, null);
			long idProductoSucursal1 = productoSucursal1.getIdProductoSucursal();
			
			String nombre2 = "Galletas";
			String marca2 = "Oreo";
			String presentacion2 = "6 paquetes de 100 gr cada uno";
			double cantidadPresentacion2 = 600;
			String unidadMedida2 = "gramos";
			double volumenEmpaque2 = 300;
			double pesoEmpaque2 = 600;
			long codigoBarras2 = 13516;
			String categoria2 = "perecedero";
			String tipo2 = "galleta";
			String fechaVencimiento2 = "15/12/2018";
			int nivelReorden2 = 40;
			double precioUnitario2 = 2500;
			int cantidadBodega2 = 40;
			int cantidadEstante2 = 30;
			double precioUnidadMedida2 = 4.16;
			long idBodega2 = bodega2.getIdBodega();
			long idEstante2 = estante2.getIdEstante();
			VOProductoSucursal productoSucursal2 = superAndes.adicionarProductoSucursal(nombre2, marca2, presentacion2, cantidadPresentacion2, unidadMedida2, volumenEmpaque2, pesoEmpaque2, codigoBarras2, categoria2, tipo2, fechaVencimiento2, nivelReorden2, precioUnitario2, cantidadBodega2, cantidadEstante2, precioUnidadMedida2, idBodega2, idEstante2, null);
			long idProductoSucursal2 = productoSucursal2.getIdProductoSucursal();
			
			int ocupadoCarrito = 0;
			VOCarrito carrito1 = superAndes.adicionarCarrito(ocupadoCarrito, idSucursal);
			
			VODentroCarrito dentroCarrito = superAndes.adicionarDentroCarrito(carrito1.getIdCarrito(), idProductoSucursal1, 3);
			lista = superAndes.darVODentroCarrito();
			assertEquals("Debe haber un Dentrocarrito creado!!", 1, lista.size());
			
			VODentroCarrito dentroCarrito2 = superAndes.adicionarDentroCarrito(carrito1.getIdCarrito(), idProductoSucursal2, 6);
			lista = superAndes.darVODentroCarrito();
			assertEquals("Debe haber dos Dentrocarrito creado!!", 2, lista.size());
			
			superAndes.modificarCantidadDentroCarrito(carrito1.getIdCarrito(), idProductoSucursal1, -2);
			dentroCarrito = superAndes.darDentroCarritosPorIds(carrito1.getIdCarrito(), idProductoSucursal1);
			assertEquals("Debe haber solo un producto dentro del carrito", 1, dentroCarrito.getCantidad());
			
			superAndes.modificarCantidadDentroCarrito(carrito1.getIdCarrito(), idProductoSucursal2, 2);
			dentroCarrito2 = superAndes.darDentroCarritosPorIds(carrito1.getIdCarrito(), idProductoSucursal2);
			assertEquals("Debe haber 8 productos dentro del carrito", 8, dentroCarrito2.getCantidad());
			
			VODentroCarrito dentroCarrito3 = superAndes.adicionarDentroCarrito(carrito1.getIdCarrito()+2, idProductoSucursal2, 5);
			assertNull("No se puede meter productos en carritos que no existan", dentroCarrito3);
			
			VODentroCarrito dentroCarrito4 = superAndes.adicionarDentroCarrito(carrito1.getIdCarrito(), idProductoSucursal2+12, 2);
			assertNull("No se puede meter productos que no existan en un carrito", dentroCarrito4);
			
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD e INTEGRIDADsobre la tabla DentroCarrito.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD e INTEGRIDAD sobre la tabla DentroCarrito");
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
