package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.List;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import oracle.sql.DATE;
import uniandes.isis2304.SuperAndes.negocio.Bodega;
import uniandes.isis2304.SuperAndes.negocio.Carrito;
import uniandes.isis2304.SuperAndes.negocio.Estante;
import uniandes.isis2304.SuperAndes.negocio.Sucursal;
import uniandes.isis2304.SuperAndes.negocio.SuperAndes;
import uniandes.isis2304.SuperAndes.negocio.VOBodega;
import uniandes.isis2304.SuperAndes.negocio.VOCarrito;
import uniandes.isis2304.SuperAndes.negocio.VOCliente;
import uniandes.isis2304.SuperAndes.negocio.VOCompra;
import uniandes.isis2304.SuperAndes.negocio.VOEstante;
import uniandes.isis2304.SuperAndes.negocio.VOFactura;
import uniandes.isis2304.SuperAndes.negocio.VOOrdenPedido;
import uniandes.isis2304.SuperAndes.negocio.VOProductoProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOProductoSucursal;
import uniandes.isis2304.SuperAndes.negocio.VOPromocion;
import uniandes.isis2304.SuperAndes.negocio.VOProveedor;
import uniandes.isis2304.SuperAndes.negocio.VOSucursal;


public class InterfazAdministrador extends JFrame
{
	private PanelDatos panelDatos;

	private banner imagen;

	private MenuBarra menu;

	private static Logger log = Logger.getLogger(InterfazAdministrador.class.getName());

	private SuperAndes superAndes;

	private JsonObject tableConfig;

	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	public InterfazAdministrador()
	{
		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		superAndes = new SuperAndes (tableConfig);

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
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "SuperAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}
	public Object[] darProveedores()
	{
		
		 return superAndes.darProveedores().toArray();
	}
	
	public Object[] darOcupados(long idSucursal)
	{
		return superAndes.darCarritosOcupadosSucursal(idSucursal).toArray();
	}
	public Object[] darAbandonados(long idSucursal)
	{
		return superAndes.darCarritosAbandonadosSucursal(idSucursal).toArray();
	}
	public void requerimientoConsulta1() 
	{
		
	}
	public void eliminarCarrito(Long idCarrito,long idSucursal)
	{
		superAndes.recolectarProductosAbandonadosRF17(idSucursal);
		superAndes.eiminarCarrito(idCarrito);
	}

	public void ocuparCarro(long idCarrito)
	{
		superAndes.modificarEstadoOcupacionCarrito(idCarrito, 1);
	}

	public ArrayList<Carrito> darCarritos (Long idsuc)
	{
		ArrayList<Carrito> x = new ArrayList<>();
		Object[] aux = superAndes.darCarritos().toArray();
		for (int i =0; i<aux.length;i++)
		{
			Carrito aux2 = (Carrito) aux[i];
			if(aux2.getSucursal()==idsuc)
			{
				x.add(aux2);
			}
		}
		return x;
	}

	public ArrayList<Carrito> darCarritoLibre(long idSucursal)
	{
		ArrayList<Carrito> x = new ArrayList<>();
		Object[] aux = superAndes.darCarritosLibresSucursal(idSucursal).toArray();
		for (int i =0; i<aux.length;i++)
		{
			Carrito aux2 = (Carrito) aux[i];
			if(aux2.getSucursal()==idSucursal)
			{
				x.add(aux2);
			}
		}
		return x;

	}

	public void agregarCarrito(Long idSucursal)
	{
		VOCarrito a =superAndes.adicionarCarrito(0, idSucursal);
		String mensaje = a.toString();
		panelDatos.actualizarInterfaz(mensaje);
	}

	public void agregarCliente(String pNombre, String pCorreo, String pDireccion,String iden)
	{
		long identificacion = Long.parseLong(iden);
		if(pNombre.equalsIgnoreCase("")|| pCorreo.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos nombre y correo no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			if(pDireccion.equalsIgnoreCase(""))
			{
				VOCliente a =superAndes.adicionarClienteIndividuo2(identificacion, pNombre, pCorreo);
				String mensaje =a.toString();
				panelDatos.actualizarInterfaz(mensaje);
			}
			else
			{
				VOCliente a = superAndes.adicionarClienteEmpresa2(identificacion,pNombre, pCorreo, pDireccion);
				String mensaje =a.toString();
				panelDatos.actualizarInterfaz(mensaje);
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
			VOFactura a = superAndes.adicionarFactura(pDescripcion);
			String mensaje =a.toString();
			panelDatos.actualizarInterfaz(mensaje);
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
			VOSucursal a =superAndes.adicionarSucursal(pCiudad, pDireccion, pnombre);

			String mensaje =a.toString();
			panelDatos.actualizarInterfaz(mensaje);
		}
	}

	public void verificarSucursal(String id) throws Exception
	{
		System.out.println(id);
		Long idV =0L;
		idV= Long.parseLong(id);
		Sucursal esta = superAndes.darSucursalPorId(idV);

		if(esta == null)
		{
			throw new Exception("No hay una sucursal con ese id");
		}

	}

	public Object[] darProductosProveedor()
	{
		return superAndes.darProductosProveedor().toArray();
	}

	public Object[] darPorudctos()
	{
		return superAndes.darProductosSucursal().toArray();
	}

	public Object[] darCliente()
	{
		return superAndes.darClientes().toArray();
	}
	public Estante darEstante(Long idEstante)
	{
		return superAndes.darEstantePorId(idEstante);
	}
	public void agregarProveedor(String pnombre)
	{
		if(pnombre.equalsIgnoreCase(""))
		{
			JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "SuperAndes",JOptionPane.ERROR_MESSAGE);
		}
		else
		{

			VOProveedor a =superAndes.registrarProveedoresRF1(pnombre);
			String mensaje =a.toString();
			panelDatos.actualizarInterfaz(mensaje);
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
				else if (ptipo==2 && !(p.equals(llev)))
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
					VOPromocion a = superAndes.req7RegistrarPromocion(ptipo, pague, llev, fechaCad);
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

	public void dentroCarrito(long idCarrito,long idProductoSucursal, int cantidad)
	{
		superAndes.adicionarDentroCarrito(idCarrito, idProductoSucursal, cantidad);
		
	}
	public void eliminarDentroCarrito(long idCarrito,long idProductoSucursal)
	{
		superAndes.eliminarDentroCarrito(idCarrito, idProductoSucursal);
	}
	
	public void abandonarCarrito(long idCarrito)
	{
		superAndes.abandonarCarritoRF16(idCarrito);
	}
	public void recogerProductos(long idSucursal)
	{

		superAndes.recolectarProductosAbandonadosRF17(idSucursal);
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

				peso = Double.parseDouble(pPeso); 

				volum = Double.parseDouble(pVol);

				idSucu = Long.parseLong(idSuc);


				if(peso<0||volum<0||idSucu<0)
				{

				}
				else
				{

					System.out.println(volum +" "+peso+" "+" "+pTipo+" "+idSucu);
					VOBodega a =superAndes.registrarBodegaASucursalRF5(volum, peso, pTipo, idSucu);
					String mensaje =a.toString();
					panelDatos.actualizarInterfaz(mensaje);

				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());

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
				if(volumen<0|| peso<0||niveApr<0||idSucu<0)
				{
					JOptionPane.showMessageDialog(null, "Los valores deben ser mayor a 0", "SuperAndes",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					VOEstante a=superAndes.registrarEstanteASucursalRF6(volumen, peso, pTipo, niveApr, idSucu);
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
	public void agregarProductoSucursal(String pNombre, String pMarca,String pPresentacion, String cantidadPresentacion,String unidadmedida,String VolumenEmpaquetado,
			String pPesoEmpaquetado,String pCodBarras, String pCategoria, String pTipo,String pFechaVencimiento, String pNivelReorden, String pPrecioUnitario, String cantiBodega, String cantEstante,
			String pPrecioUnidadMedida,String idProm, String idEstante, String idBodega)
	{
		if(pNombre.equalsIgnoreCase("")|| pMarca.equalsIgnoreCase("")|| pPresentacion.equalsIgnoreCase("")|| cantidadPresentacion.equalsIgnoreCase("")|| unidadmedida.equalsIgnoreCase("")|| VolumenEmpaquetado.equalsIgnoreCase("")||
				pPesoEmpaquetado.equalsIgnoreCase("")|| pCodBarras.equalsIgnoreCase("")||pCategoria.equalsIgnoreCase("")||pTipo.equalsIgnoreCase("")||pFechaVencimiento.equalsIgnoreCase("")||pNivelReorden.equalsIgnoreCase("")||pPrecioUnitario.equalsIgnoreCase("")||cantiBodega.equalsIgnoreCase("")|| cantEstante.equalsIgnoreCase("")||
				pPrecioUnidadMedida.equalsIgnoreCase("")|| idEstante.equalsIgnoreCase("") || idBodega.equalsIgnoreCase(""))
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
			Long idPromocion=0L;

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
				if(idProm.equalsIgnoreCase(""))
				{
					System.out.println("null de promocion");
					idPromocion=null;

				}
				else
				{
					idPromocion=Long.parseLong(idProm);
				}
				System.out.println(pNombre+" "+ pMarca+" "+ pPresentacion+" "+ cantPresentacion+" "+ unidadmedida+" "+ volEmpaque+" "+ pesoEmpaque+" "+
						codigoBarras+" "+ pCategoria+" "+ pTipo+" "+ pFechaVencimiento+" "+nivelReorden+" "+ precioUnitario+" "+ cantidadBodega+" "+ cantidadEstante+" "+ precioUnidadMedida+" "+
						idBod+" "+ idEst+" "+ idPromocion);
				if(cantPresentacion<0||volEmpaque<0||pesoEmpaque<0||codigoBarras<0||nivelReorden<0||precioUnitario<0||cantidadBodega<0
						||cantidadEstante<0||precioUnidadMedida<0||idBod<0||idEst<0)
				{
					JOptionPane.showMessageDialog(null, "Los valores tienen que ser mayor a 0", "SuperAndes",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					VOProductoSucursal a=superAndes.registrarProductoSucursalRF2(pNombre, pMarca, pPresentacion, cantPresentacion, unidadmedida, volEmpaque, pesoEmpaque,
							codigoBarras, pCategoria, pTipo, pFechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida,
							idBod, idEst,
							idPromocion);
					System.out.println(a);
					String mensaje =a.toString();
					panelDatos.actualizarInterfaz(mensaje);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "SuperAndes",JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	
	public void pagar(long idCarrito,long idCliente)
	{
		try
		{
			superAndes.pagarCompraRF15(idCarrito, idCliente);	
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "SuperAndes",JOptionPane.ERROR_MESSAGE);	
		}
		
	}

	public void agregarCompra( long idCliente,long idCarrito )
	{

		try
		{
			superAndes.pagarCompraRF15(idCarrito, idCliente);
		}

		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
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

				if(cantPresentacion<0||volEmpaque<0||pesoEmpaque<0||codigoBarras<0||calidad<0||precio<0||numeroDeCalificiaciones<0||sumaCalificaciones<0
						||idProveedor<0)
				{
					JOptionPane.showMessageDialog(null, "Los valores deben ser mayores a 0", "SuperAndes",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					VOProductoProveedor a= superAndes.registrarProductoProveedorRF2(pNombre, pMarca, pPresentacion, cantPresentacion,
							unidadmedida, volEmpaque, pesoEmpaque, codigoBarras, pCategoria, pTipo, pFechaVencimiento,
							calidad, precio, numeroDeCalificiaciones, sumaCalificaciones, idProveedor);
					System.out.println(a);
					String mensaje =a.toString();
					panelDatos.actualizarInterfaz(mensaje);
				}

			}
			catch(Exception e)
			{
				
				JOptionPane.showMessageDialog(null, "Introduzca Valores Validos" + e.getMessage(), "SuperAndes",JOptionPane.ERROR_MESSAGE);
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
				double calif= Double.parseDouble(cali);
				int entergado = 0;
				if(entre.equalsIgnoreCase(DialogoOrdenPedido.ENTREGADO))
				{
					entergado=1;
				}

				idsuc=Long.parseLong(idSucu);
				idPro=Long.parseLong(idProv);
				cant = Integer.parseInt(cantid);
				preci = Double.parseDouble(prec);

				if(idsuc<0||idPro<0||cant<0||preci<0)
				{

					JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					VOOrdenPedido a = superAndes.req9RegistrarPedidoDeUnProductoAProveedorParaSucursal(fechEntrega, cant, idPro, idsuc);
					System.out.println(a);
					String mensaje =a.toString();
					panelDatos.actualizarInterfaz(mensaje);

				}
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Introduzca Valores Validos", "SuperAndes",JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void estantes()
	{
		String nombre = JOptionPane.showInputDialog( this, "Introduzca el id de la sucursal", "Dar los estantes de una sucursal", JOptionPane.INFORMATION_MESSAGE );
		Long idS= Long.parseLong(nombre);
		Object[]a =superAndes.darEstantes().toArray();
		String mensaje = "Los estantes son:";
		for(int i=0;i<a.length;i++)
		{
			Estante d = (Estante)a[i];
			if(idS==d.getSucursal())
			{
				mensaje= mensaje +"\n"+ d.getIdEstante();
			}
		}
		JOptionPane.showMessageDialog( this, mensaje, "Dar los estantes de una sucursal", JOptionPane.INFORMATION_MESSAGE);
	}

	public Bodega[] darBodegas(Long idS)
	{


		Object[]a =superAndes.darBodegas().toArray();
		ArrayList<Bodega> b = new ArrayList<>();
		for(int i=0;i<a.length;i++)
		{
			Bodega d = (Bodega)a[i];
			if(idS==d.getSucursal())
			{
				b.add( d);
			}
		}

		Bodega [] bod= new Bodega[b.size()];
		for(int i=0; i<b.size();i++)
		{
			bod[i]=b.get(i);
		}
		return bod;
	}

	public Estante[] darEstantes(Long idS)
	{


		Object[]a =superAndes.darEstantes().toArray();
		ArrayList<Estante> b = new ArrayList<>();
		for(int i=0;i<a.length;i++)
		{
			Estante d = (Estante)a[i];
			if(idS==d.getSucursal())
			{
				b.add( d);
			}
		}

		Estante [] est= new Estante[b.size()];
		for(int i=0; i<b.size();i++)
		{
			est[i]=b.get(i);
		}
		return est;
	}
	public void bodegas()
	{
		String nombre = JOptionPane.showInputDialog( this, "Introduzca el id de la sucursal", "Dar las Bodegas de una sucursal", JOptionPane.INFORMATION_MESSAGE );
		Long idS= Long.parseLong(nombre);
		Object[]a =superAndes.darBodegas().toArray();
		String mensaje = "Las Bodegas son:";
		for(int i=0;i<a.length;i++)
		{
			Bodega d = (Bodega)a[i];
			if(idS==d.getSucursal())
			{
				mensaje= mensaje +"\n"+ d.getIdBodega();
			}
		}
		JOptionPane.showMessageDialog( this, mensaje, "Dar las Bodegas de una sucursal", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfazAdministrador inter = new InterfazAdministrador();
		inter.setVisible(true);
	}

}
