package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.SuperAndes.negocio.SuperAndes;
import uniandes.isis2304.SuperAndes.negocio.VOBodega;
import uniandes.isis2304.SuperAndes.negocio.VOCompra;
import uniandes.isis2304.SuperAndes.negocio.VOEstante;
import uniandes.isis2304.SuperAndes.negocio.VOOrdenPedido;
import uniandes.isis2304.SuperAndes.negocio.VOProductoProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOProductoSucursal;
import uniandes.isis2304.SuperAndes.negocio.VOPromocion;
import uniandes.isis2304.SuperAndes.persistencia.PersistenciaSuperAndes;

public class InterfazApp extends JFrame
{
	private PanelDatos panelDatos;

	private banner imagen;

	private MenuBarra menu;

	private static Logger log = Logger.getLogger(InterfazApp.class.getName());

	private SuperAndes superAndes;

	private JsonObject tableConfig;

	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	public InterfazApp()
	{
		//		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		//		superAndes = new SuperAndes (tableConfig);

		setSize(800,430);
		setLocationRelativeTo(null);
		setTitle("SuperAndes");
		setLayout( new BorderLayout());

		imagen = new banner();
		panelDatos = new PanelDatos();

		add(panelDatos, BorderLayout.SOUTH);
		add(imagen,BorderLayout.NORTH);

		menu = new MenuBarra(this);
		this.setJMenuBar(menu);

	}

	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//				e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	public void agregarCliente(String pNombre, String pCorreo, String pDireccion)
	{

		if(pNombre.equalsIgnoreCase("")|| pCorreo.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos nombre y correo no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			if(pDireccion.equalsIgnoreCase(""))
			{
				superAndes.adicionarClienteIndividuo(pNombre, pCorreo);
			}
			else
			{
				superAndes.adicionarClienteEmpresa(pNombre, pCorreo, pDireccion);
			}
		}
	}

	public void agregarFactura(String pDescripcion)
	{
		if(pDescripcion.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			superAndes.adicionarFactura(pDescripcion);
		}
	}

	public void agregarSucursal(String pnombre, String pCiudad,String pDireccion)
	{
		if(pnombre.equalsIgnoreCase("")|| pCiudad.equalsIgnoreCase("") || pDireccion.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			superAndes.adicionarSucursal(pCiudad, pDireccion, pnombre);
		}
	}

	public void agregarProveedor(String pnombre)
	{
		if(pnombre.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			superAndes.adicionarProveedor(pnombre);
		}
	}

	public void agregarPromocion(int ptipo,String p,String lleve,String fechaCad)
	{
		if( p.equalsIgnoreCase("")|| lleve.equalsIgnoreCase("")|| fechaCad.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Double pague =0.0;
			Double llev=0.0;
			try
			{
				pague = Double.parseDouble(p); 
				llev = Double.parseDouble(lleve);

				if(ptipo==1 && pague>llev)
				{
					JOptionPane.showMessageDialog(null, "No puede llevar menos cantidad de la que paga", "SuperAndes",JOptionPane.ERROR_MESSAGE);
				}
				if(ptipo==2 && p.equalsIgnoreCase(lleve))
				{
					VOPromocion a = superAndes.adicionarPromocion(ptipo, pague, llev, fechaCad);
					String mensaje =a.toString();
					panelDatos.actualizarInterfaz(mensaje);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Lleve y pague tiene que ser iguales para aplicar descuento", "SuperAndes",JOptionPane.ERROR_MESSAGE);
				}

				if(ptipo==3 && pague>llev)
				{
					JOptionPane.showMessageDialog(null, "No puede llevar menos cantidad de la que paga", "SuperAndes",JOptionPane.ERROR_MESSAGE);
				}
				else if(ptipo==4 && pague>llev)
				{
					JOptionPane.showMessageDialog(null, "No puede llevar menos cantidad de la que paga", "SuperAndes",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					VOPromocion a = superAndes.adicionarPromocion(ptipo, pague, llev, fechaCad);
					String mensaje =a.toString();
					panelDatos.actualizarInterfaz(mensaje);
				}

			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	public void agregarBodega(String pTipo, String pVol,String pPeso,String idSuc)
	{

		if(pTipo.equalsIgnoreCase("")|| pVol.equalsIgnoreCase("") || pPeso.equalsIgnoreCase("") || idSuc.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Double peso =0.0;
			Double volum=0.0;
			Long idSucu= 0L;
			try
			{
				System.out.println(pTipo);
				peso = Double.parseDouble(pPeso); 

				volum = Double.parseDouble(pVol);

				idSucu = Long.parseLong(idSuc);

				VOBodega a =superAndes.adicionarBodega(volum, peso, pTipo, idSucu);
				String mensaje =a.toString();
				panelDatos.actualizarInterfaz(mensaje);

			}
			catch(Exception e)
			{

				JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
			}

		}

	}
	public void agregarEstante(String pTipo,String pPeso, String volum,String nivAprovi,String idSuc)
	{
		if(pTipo.equalsIgnoreCase("")|| pPeso.equalsIgnoreCase("")|| volum.equalsIgnoreCase("")|| nivAprovi.equalsIgnoreCase("")|| idSuc.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Double volumen=0.0;
			Double peso=0.0;
			int niveApr =0;
			Long idSucu = 0L;
			try
			{
				volumen = Double.parseDouble(volum);
				peso = Double.parseDouble(pPeso);
				niveApr = Integer.parseInt(nivAprovi);
				idSucu =Long.parseLong(idSuc);
				VOEstante a=superAndes.adicionarEstante(volumen, peso, pTipo, niveApr, idSucu);
				String mensaje =a.toString();
				panelDatos.actualizarInterfaz(mensaje);

			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void agregarProductoSucursal(String pNombre, String pMarca,String pPresentacion, String cantidadPresentacion,String unidadmedida,String VolumenEmpaquetado,
			String pPesoEmpaquetado,String pCodBarras, String pCategoria, String pTipo,String pFechaVencimiento, String pNivelReorden, String pPrecioUnitario, String cantiBodega, String cantEstante,
			String pPrecioUnidadMedida,String idProm, String idEstante, String idBodega)
	{
		if(pNombre.equalsIgnoreCase("")|| pMarca.equalsIgnoreCase("")|| pPresentacion.equalsIgnoreCase("")|| cantidadPresentacion.equalsIgnoreCase("")|| unidadmedida.equalsIgnoreCase("")|| VolumenEmpaquetado.equalsIgnoreCase("")||
				pPesoEmpaquetado.equalsIgnoreCase("")|| pCodBarras.equalsIgnoreCase("")||pCategoria.equalsIgnoreCase("")||pTipo.equalsIgnoreCase("")||pFechaVencimiento.equalsIgnoreCase("")||pNivelReorden.equalsIgnoreCase("")||pPrecioUnitario.equalsIgnoreCase("")||cantiBodega.equalsIgnoreCase("")|| cantEstante.equalsIgnoreCase("")||
				pPrecioUnidadMedida.equalsIgnoreCase("")||idProm.equalsIgnoreCase("")|| idEstante.equalsIgnoreCase("") || idBodega.equalsIgnoreCase(""))
		{

			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			double cantPresentacion = 0.0;
			double volEmpaque = 0.0;
			double pesoEmpaque= 0.0;
			long codigoBarras =0L;
			int nivelReorden =0;
			double precioUnitario= 0.0;
			int cantidadBodega =0;
			int cantidadEstante =0;
			double precioUnidadMedida= 0.0;
			long idBod =0L;
			long idEst=0L;
			long idPromocion=0L;

			try
			{

				cantPresentacion =Double.parseDouble(cantidadPresentacion);
				volEmpaque =Double.parseDouble(VolumenEmpaquetado);
				pesoEmpaque =Double.parseDouble(pPesoEmpaquetado);
				codigoBarras = Long.parseLong(pCodBarras);
				nivelReorden= Integer.parseInt(pNivelReorden);
				precioUnitario = Double.parseDouble(pPrecioUnitario);
				cantidadBodega=Integer.parseInt(cantEstante);
				cantidadEstante=Integer.parseInt(cantiBodega);
				precioUnidadMedida=Double.parseDouble(pPrecioUnidadMedida);
				idBod= Long.parseLong(idBodega);
				idEst=Long.parseLong(idEstante);
				idPromocion=Long.parseLong(idProm);

				VOProductoSucursal a=superAndes.adicionarProductoSucursal(pNombre, pMarca, pPresentacion, cantPresentacion, unidadmedida, volEmpaque, pesoEmpaque,
						codigoBarras, pCategoria, pTipo, pFechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida,
						idBod, idEst, idPromocion);
				String mensaje =a.toString();
				panelDatos.actualizarInterfaz(mensaje);

			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void agregarCompra(String pFecha, String pCantidad, String pIdProduSucu, String pIdCliente, String pidFactura )
	{
		if(pFecha.equalsIgnoreCase("")|| pCantidad.equalsIgnoreCase("")|| pIdProduSucu.equalsIgnoreCase("")||pIdCliente.equalsIgnoreCase("") || pidFactura.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}

		else
		{
			try
			{

				int cant=0;
				Long idProd =0L;
				Long idClie=0L;
				Long idFac=0L;

				cant=Integer.parseInt(pCantidad);
				idProd=Long.parseLong(pIdProduSucu);
				idClie=Long.parseLong(pIdCliente);
				idFac=Long.parseLong(pidFactura);

				VOCompra a =superAndes.adicionarCompra(pFecha, cant, idProd, idClie, idFac);
				String mensaje =a.toString();
				panelDatos.actualizarInterfaz(mensaje);

			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void agergarProductoProveedor(String pNombre, String pMarca,String pPresentacion, String cantidadPresentacion,String unidadmedida,String VolumenEmpaquetado,
			String pPesoEmpaquetado,String pCodBarras, String pCategoria, String pTipo,String pFechaVencimiento,String pCalidad,String pPrecio,String pNumCal, String sumCal
			, String pIdprovee)
	{

		if(pNombre.equalsIgnoreCase("")|| pMarca.equalsIgnoreCase("")|| pPresentacion.equalsIgnoreCase("")|| cantidadPresentacion.equalsIgnoreCase("")|| unidadmedida.equalsIgnoreCase("")|| VolumenEmpaquetado.equalsIgnoreCase("")||
				pPesoEmpaquetado.equalsIgnoreCase("")|| pCodBarras.equalsIgnoreCase("")||pCategoria.equalsIgnoreCase("")||pTipo.equalsIgnoreCase("")||pFechaVencimiento.equalsIgnoreCase("")||pCalidad.equalsIgnoreCase("")||
				pPrecio.equalsIgnoreCase("")||pNumCal.equalsIgnoreCase("")|| sumCal.equalsIgnoreCase("")||pIdprovee.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			double cantPresentacion = 0.0;
			double volEmpaque = 0.0;
			double pesoEmpaque= 0.0;
			long codigoBarras =0L;
			double calidad=0.0;
			double precio=0.0;
			int numeroDeCalificiaciones =0;
			double sumaCalificaciones =0;
			long idProveedor =0L;

			try
			{

				cantPresentacion =Double.parseDouble(cantidadPresentacion);
				volEmpaque =Double.parseDouble(VolumenEmpaquetado);
				pesoEmpaque =Double.parseDouble(pPesoEmpaquetado);
				codigoBarras = Long.parseLong(pCodBarras);				
				calidad = Double.parseDouble(pCalidad);
				precio = Double.parseDouble(pPrecio);
				numeroDeCalificiaciones = Integer.parseInt(pNumCal);
				sumaCalificaciones= Double.parseDouble(sumCal);
				idProveedor= Long.parseLong(pIdprovee);


				VOProductoProveedor a= superAndes.adicionarProductoProveedor(pNombre, pMarca, pPresentacion, cantPresentacion,
						unidadmedida, volEmpaque, pesoEmpaque, codigoBarras, pCategoria, pTipo, pFechaVencimiento,
						calidad, precio, numeroDeCalificiaciones, sumaCalificaciones, idProveedor);
				String mensaje =a.toString();
				panelDatos.actualizarInterfaz(mensaje);

			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void agregarOrdenPedido(String fechEntrega,String fechEsp,String cali,String idProv, String idSucu,String prec,String entre,String cantid)
	{ 
		if(fechEntrega.equalsIgnoreCase("")|| fechEsp.equalsIgnoreCase("")|| cali.equalsIgnoreCase("")|| idProv.equalsIgnoreCase("")||idSucu.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try
			{
				Long idsuc =0L;
				Long idPro =0L;
				int cant =0;
				double preci = 0.0;
				int calif= Integer.parseInt(cali);
				int entergado = 0;
				if(entre.equalsIgnoreCase(DialogoOrdenPedido.ENTREGADO))
				{
					entergado=1;
				}

				idsuc=Long.parseLong(idSucu);
				idPro=Long.parseLong(idProv);
				cant = Integer.parseInt(cantid);
				preci = Double.parseDouble(prec);

				VOOrdenPedido a = superAndes.adicionarOrdenPedido(preci, fechEntrega, fechEsp, calif, entergado, cant, idPro, idsuc);
				String mensaje =a.toString();
				panelDatos.actualizarInterfaz(mensaje);
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfazApp inter = new InterfazApp();
		inter.setVisible(true);
	}

}
