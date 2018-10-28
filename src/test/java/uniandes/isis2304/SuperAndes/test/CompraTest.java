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
import uniandes.isis2304.SuperAndes.negocio.VOCliente;
import uniandes.isis2304.SuperAndes.negocio.VOCompra;
import uniandes.isis2304.SuperAndes.negocio.VOEstante;
import uniandes.isis2304.SuperAndes.negocio.VOFactura;
import uniandes.isis2304.SuperAndes.negocio.VOProductoSucursal;
import uniandes.isis2304.SuperAndes.negocio.VOPromocion;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;

public class CompraTest {
	
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
	public void unicidadCompraTest() {
		try
		{
			log.info ("Probando la unicidad sobre Compra");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Unicidad de Sucursal incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Unicidad de Compra incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			
			List <VOCompra> lista = superAndes.darVOCompras();
			assertEquals ("No debe haber compras creados!!", 0, lista.size ());

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
			long idProductoSucursal = productoSucursal1.getIdProductoSucursal();
			
			String nombreClienteInd1 = "Alejandro";
			String correoClienteInd1 = "chonal77@hotmail.com";
			VOCliente clienteInd1 = superAndes.adicionarClienteIndividuo(nombreClienteInd1, correoClienteInd1);
			long idCliente= clienteInd1.getIdentificacion();
			
			int cantidadCompra = 5;
			double totalPagadoComora  = 25000;
			String fechaCompra = "12/12/2018";
			
			String descripcion = "a";
			VOFactura factura1 = superAndes.adicionarFactura(descripcion);
			long idFactura = factura1.getIdFactura();
			
			VOCompra compra1 = superAndes.adicionarCompra(fechaCompra, cantidadCompra, totalPagadoComora, idProductoSucursal, idCliente, idFactura);
			lista = superAndes.darVOCompras();
			assertEquals ("Debe haber una compra creada !!", 1, lista.size ());
			
			long idCompra1= compra1.getIdCompra();
			VOCompra compra2= superAndes.adicionarCompra2(idCompra1,fechaCompra, cantidadCompra, totalPagadoComora, idProductoSucursal, idCliente, idFactura);
			assertNull ("No se puede adicionar una compra con un Id que ya exista!!", compra2);
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Compra.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas de UNICIDAD sobre la tabla compra");
		}    				
		finally
		{
			superAndes.limpiarSuperAndes ();
			superAndes.cerrarUnidadPersistencia ();    		
		}
	}
	
	@Test
	public void restriccionesChequeoCompraTest() {
		try
		{
			log.info ("Probando RESTRICCIONES DE CHEQUEO sobre Compra");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
			superAndes.limpiarSuperAndes();
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			log.info ("Prueba de chequeo de Compra incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de chequeo de Compra incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOCompra> lista = superAndes.darVOCompras();
			assertEquals ("No debe haber compras creados!!", 0, lista.size ());

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
			long idProductoSucursal = productoSucursal1.getIdProductoSucursal();
			
			String nombreClienteInd1 = "Alejandro";
			String correoClienteInd1 = "chonal77@hotmail.com";
			VOCliente clienteInd1 = superAndes.adicionarClienteIndividuo(nombreClienteInd1, correoClienteInd1);
			long idCliente= clienteInd1.getIdentificacion();
			
			int cantidadCompra = 5;
			double totalPagadoComora  = 25000;
			String fechaCompra = "12/12/2018";
			
			String descripcion = "a";
			VOFactura factura1 = superAndes.adicionarFactura(descripcion);
			long idFactura = factura1.getIdFactura();
			
			VOCompra compra1 = superAndes.adicionarCompra(fechaCompra, cantidadCompra, totalPagadoComora, idProductoSucursal, idCliente, idFactura);
			lista = superAndes.darVOCompras();
			assertEquals ("Debe haber una compra creada !!", 1, lista.size ());
			
			VOCompra compra2 = superAndes.adicionarCompra(fechaCompra, 0, totalPagadoComora, idProductoSucursal, idCliente, idFactura);
			assertNull ("La cantidadCompra de la Compra debe ser mayor a 0 !!", compra2);
			
			VOCompra compra3 = superAndes.adicionarCompra(fechaCompra, cantidadCompra, 0, idProductoSucursal, idCliente, idFactura);
			assertNull ("El totalPagado de la Compra debe ser mayor a 0 !!", compra3);
			
			
		}
		catch (Exception e)
		{
			//				e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de CHEQUEO sobre la tabla Compra.\n";
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

