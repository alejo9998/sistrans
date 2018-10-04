package uniandes.isis2304.SuperAndes.negocio;


import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.SuperAndes.persistencia.PersistenciaSuperAndes;

public class SuperAndes {

	public static final String CATEGORIA_PERECEDERO = "perecedero";
	public static final String TIPO_CARNE = "carne";
	public static final String TIPO_GALLETA = "galleta";
	public static final String TIPO_FRUTA = "fruta";
	public static final String TIPO_TUBERCULO = "tuberculo";

	public static final String CATEGORA_ALBARROTE = "albarrote";
	public static final String TIPO_ESCOBA = "escoba";
	public static final String TIPO_BALDE = "balde";
	public static final String TIPO_BRILLADOR = "brillador";
	public static final String TIPO_TRAPEADOR = "trapeador";

	public static final String CATEGORIA_ASEOPERSONAL = "aseo personal";
	public static final String TIPO_CEPILLO = "cepillo";
	public static final String TIPO_DESODORANTE = "desodorante";
	public static final String TIPO_CREMA = "crema";
	public static final String TIPO_CERA = "cera";


	private static Logger log = Logger.getLogger(SuperAndes.class.getName());

	private PersistenciaSuperAndes pp;

	public SuperAndes() {
		pp = PersistenciaSuperAndes.getInstance();
	}

	public SuperAndes (JsonObject tableCongif) {
		pp = PersistenciaSuperAndes.getInstance(tableCongif);
	}

	public void cerrarUnidadPersistencia() {
		pp.cerrarUnidadPersistencia();
	}

	/* ****************************************************************
	 * 			Métodos para manejar los REQUERIMIENTOS FUNCIONALES
	 *****************************************************************/
	public Proveedor req1RegistrarProveedores(String nombre) {
		return this.adicionarProveedor(nombre);
	}

	public ProductoSucursal req2RegistrarProductoSucursal(String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, String fechaVencimiento , int nivelReorden, double precioUnitario, int cantidadBodega, int cantidadEstante, double precioUnidadMedida
			, long idBodega, long idEstante, long idPromocion) throws Exception{
		if ( this.darBodegaPorId(idBodega) == null ) {
			throw new Exception ("La bodega con el " + idBodega + " no existe");
		}
		else if (this.darEstantePorId(idEstante) == null) {
			throw new Exception ("El Estante con el " + idEstante + " no existe");
		}else {

			if (categoria.equalsIgnoreCase(CATEGORIA_PERECEDERO)) {
				if(tipo.equalsIgnoreCase(TIPO_CARNE) || tipo.equalsIgnoreCase(TIPO_FRUTA) || tipo.equalsIgnoreCase(TIPO_GALLETA) || tipo.equals(TIPO_TUBERCULO)) {
					return this.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion);
				}
			}
			else if (categoria.equalsIgnoreCase(CATEGORA_ALBARROTE)) {
				if(tipo.equalsIgnoreCase(TIPO_ESCOBA) || tipo.equalsIgnoreCase(TIPO_TRAPEADOR) || tipo.equalsIgnoreCase(TIPO_BALDE) || tipo.equals(TIPO_BRILLADOR)) {
					return this.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion);
				}
			}
			else if (categoria.equalsIgnoreCase(CATEGORIA_ASEOPERSONAL)) {
				if(tipo.equalsIgnoreCase(TIPO_CEPILLO) || tipo.equalsIgnoreCase(TIPO_DESODORANTE) || tipo.equalsIgnoreCase(TIPO_CREMA) || tipo.equals(TIPO_CERA)) {
					return this.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion);
				}
			}
		}
		throw new Exception ("La categoria del productoSucursal no coincide con un tipo valido; Categoria: " + categoria + " tipo: " + tipo);
	}

	public ProductoProveedor req2RegistrarProductoProveedor(String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, String fechaVencimiento , double calidad, double precio, int numeroCalificaciones, double sumaCalificaciones, long idProveedor) throws Exception {
		if ( this.darProveedorPorNit(idProveedor) == null)  {
			throw new Exception ("El proveedor con nit " + idProveedor + " no existe");
		}
		else if ( calidad < 0 || calidad > 5) {
			throw new Exception ("la calidad es invalidad: 0<=calidad<=5, y la calidad fue: " + calidad);
		}
		else {
			if (categoria.equalsIgnoreCase(CATEGORIA_PERECEDERO)) {
				if(tipo.equalsIgnoreCase(TIPO_CARNE) || tipo.equalsIgnoreCase(TIPO_FRUTA) || tipo.equalsIgnoreCase(TIPO_GALLETA) || tipo.equals(TIPO_TUBERCULO)) {
					return this.adicionarProductoProveedor(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
				}
			}
			else if (categoria.equalsIgnoreCase(CATEGORA_ALBARROTE)) {
				if(tipo.equalsIgnoreCase(TIPO_ESCOBA) || tipo.equalsIgnoreCase(TIPO_TRAPEADOR) || tipo.equalsIgnoreCase(TIPO_BALDE) || tipo.equals(TIPO_BRILLADOR)) {
					return this.adicionarProductoProveedor(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
				}
			}
			else if (categoria.equalsIgnoreCase(CATEGORIA_ASEOPERSONAL)) {
				if(tipo.equalsIgnoreCase(TIPO_CEPILLO) || tipo.equalsIgnoreCase(TIPO_DESODORANTE) || tipo.equalsIgnoreCase(TIPO_CREMA) || tipo.equals(TIPO_CERA)) {
					return this.adicionarProductoProveedor(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
				}
			}
		}
		throw new Exception ("La categoria del productoProveedor no coincide con un tipo valido; Categoria: " + categoria + " tipo: " + tipo);
	}

	public Cliente req3RegistrarClienteIndividuo(String nombre, String correo) {
		return this.adicionarClienteIndividuo(nombre, correo);
	}

	public Cliente req3RegistrarClienteEmpresa(String nombre, String correo, String direccion) {
		return this.adicionarClienteEmpresa(nombre, correo, direccion);
	}

	public Sucursal req4RegistrarSucursal(String ciudad, String direccion, String nombre) {
		return this.adicionarSucursal(ciudad, direccion, nombre);
	}

	public Bodega req5RegistrarBodegaASucursal (double volumen, double peso, String tipo, long sucursal) throws Exception {
		if (this.darSucursalPorId(sucursal) == null ) {
			throw new Exception ("La sucursal con id: " + sucursal + " no existe");
		}
		else {
			if (tipo.equalsIgnoreCase(TIPO_BALDE) || tipo.equalsIgnoreCase(TIPO_BRILLADOR) || tipo.equalsIgnoreCase(TIPO_CARNE) || tipo.equalsIgnoreCase(TIPO_CEPILLO) || tipo.equalsIgnoreCase(TIPO_CERA) || tipo.equalsIgnoreCase(TIPO_CREMA) || tipo.equalsIgnoreCase(TIPO_DESODORANTE) || tipo.equalsIgnoreCase(TIPO_ESCOBA) || tipo.equalsIgnoreCase(TIPO_FRUTA) || tipo.equalsIgnoreCase(TIPO_GALLETA) || tipo.equalsIgnoreCase(TIPO_TRAPEADOR) || tipo.equalsIgnoreCase(TIPO_TUBERCULO) ) {
				return this.adicionarBodega(volumen, peso, tipo, sucursal);
			}
			else {
				throw new Exception ("El tipo de la bodega no es valido");
			}
		}
	}

	public Estante req6RegistrarEstanteASucursal(double volumen, double peso, String tipo,int nivelAprovisionamiento, long sucursal) throws Exception  {
		if (this.darSucursalPorId(sucursal) == null ) {
			throw new Exception ("La sucursal con id: " + sucursal + " no existe");
		}
		else {
			if (tipo.equalsIgnoreCase(TIPO_BALDE) || tipo.equalsIgnoreCase(TIPO_BRILLADOR) || tipo.equalsIgnoreCase(TIPO_CARNE) || tipo.equalsIgnoreCase(TIPO_CEPILLO) || tipo.equalsIgnoreCase(TIPO_CERA) || tipo.equalsIgnoreCase(TIPO_CREMA) || tipo.equalsIgnoreCase(TIPO_DESODORANTE) || tipo.equalsIgnoreCase(TIPO_ESCOBA) || tipo.equalsIgnoreCase(TIPO_FRUTA) || tipo.equalsIgnoreCase(TIPO_GALLETA) || tipo.equalsIgnoreCase(TIPO_TRAPEADOR) || tipo.equalsIgnoreCase(TIPO_TUBERCULO) ) {
				return this.adicionarEstante(volumen, peso, tipo, nivelAprovisionamiento, sucursal);
			}
			else {
				throw new Exception ("El tipo de la estante no es valido");
			}
		}
	}

	public Promocion req7RegistrarPromocion(int tipo, double n, double m, String fechaCaducidad) throws Exception {
		if (tipo ==1) {
			if ( n>=m || n == 0 || m == 0) {
				throw new Exception ("La promocion tipo 1 no tiene entradas validas; n>=m || n==0 || n==0:  n=" + n + ", m=" + m);
			}
			else {
				return this.adicionarPromocion(tipo, n, m, fechaCaducidad);
			}
		}
		else if ( tipo == 2) {
			if ( n!=m || n==0 || m==0) {
				throw new Exception ("La promocion tipo2 no tiene entradas validas; n!=m||n==0||m==0:  n=" + n + ", m=" + m);
			}
			else {
				return this.adicionarPromocion(tipo, n, m, fechaCaducidad);
			}
		}
		else if ( tipo == 3) {
			if (n>=m || n==0 || m==0) {
				throw new Exception ("La promocion tipo 3 no tiene entradas validas; n>=m||n==0||m==0: n=" + n + ", m=" + m);
			}
			else {
				return this.adicionarPromocion(tipo, n, m, fechaCaducidad);
			}
		}
		else if ( tipo == 4) {
			if (n==0 || m==0 ) {
				throw new Exception ("La promocion tipo 4 no tiene entradas validas; n==0||m==0: n=" + n + ", m=" + m);
			}
			else {
				return this.adicionarPromocion(tipo, n, m, fechaCaducidad);
			}
		}
		else {
			throw new Exception ("El tipo de la promocion no es valida: 1<=tipo<=4 y el tipo es: " + tipo);
		}
	}

	//REQ 8 HACE FALTA

	public OrdenPedido req9RegistrarPedidoDeUnProductoAProveedorParaSucursal ( String fechaEsperadaEntrega, int cantidad, long idProductoProveedor, long idSucursal) throws Exception {
		if (cantidad <=0 ) {
			throw new Exception ("No se puede hacer pedidos con cantidades <= 0: la cantidad solicitada fue: "+ cantidad);
		}
		else if (this.darProductoProveedorPorId(idProductoProveedor) == null) {
			throw new Exception ("El producto proveedor del ordenPedido no existe: idProductoProveedor = " + idProductoProveedor );
		}
		else {
			ProductoProveedor p = this.darProductoProveedorPorId(idProductoProveedor);
			double precioPorUnidad = p.getPrecio();
			double precioTotal = precioPorUnidad * cantidad;
			return this.adicionarOrdenPedido(precioTotal, null, fechaEsperadaEntrega, 0, 0, cantidad, idProductoProveedor, idSucursal);
		}
	}

	public void req10RegistrarLlegadaOrdenPedido(long idOrdenPedido, double calificacion) throws Exception {
		if ( this.darOrdenPedidoPorId(idOrdenPedido) == null) {
			throw new Exception ("La ordenPedido solicitada no existe: idOrdenPedido: " + idOrdenPedido);
		}
		else if ( calificacion < 0 || calificacion > 5) {
			throw new Exception ("Calificacion invalida: calificacion: " + calificacion);
		}
		OrdenPedido orden = this.darOrdenPedidoPorId(idOrdenPedido);
		long idProductoACalificar = orden.getProductoProveedor();
		this.aumentarNumeroCalificaciones(idProductoACalificar);
		this.aumentarSumaCalificaciones(idProductoACalificar, calificacion);
		ProductoProveedor producto = this.darProductoProveedorPorId(idProductoACalificar);
		double nuevaCalificacion = producto.getSumaCalificaciones()/producto.getNumeroCalificaciones();
		this.modificarCalidadProductoProveedor(idProductoACalificar, nuevaCalificacion);
		this.calificarOrdenPedido(idOrdenPedido, calificacion);
		this.recibirFechaOrdenPedido(idOrdenPedido);
		this.recibirEntregadoOrdenPedido(idOrdenPedido);
	}

	public Compra req11RegistrarUnaVentaDeUnProducto(String fecha, int cantidad, long idProductoSucursal, long idCliente, long idFactura) throws Exception {
		if ( this.darProductoSucursalPorId(idProductoSucursal) == null) {
			throw new Exception ("El producto que intenta comprar no existe");
		}
		if(this.darClientePorIdentificacion(idCliente) == null) {
			throw new Exception ("El cliente que intenta comprar no existe");
		}
		if(cantidad <= 0) {
			throw new Exception ("El valor de cantidad no es valido: cantidad: " + cantidad);
		}
		ProductoSucursal productoSucursal = this.darProductoSucursalPorId(idProductoSucursal);
		if ( productoSucursal.getCantidadEstante()+productoSucursal.getCantidadBodega() < cantidad) {
			throw new Exception ("La cantidad de producto en la sucursal no es suficiente");
		}
		double precio = productoSucursal.getPrecioUnitario();
		double precioPagar = precio * cantidad;
		if(this.darFacturaPorId(idFactura) == null) {
			String descripcion = "Factura con id " + idFactura + '\n' + "ProductoComprado " + productoSucursal.getNombre() + '\n' + "cantidad " + cantidad + '\n' + "precioTotal: " + precioPagar;
			this.adicionarFactura(descripcion);
		}
		Estante estanteProducto = this.darEstantePorId(productoSucursal.getEstante());
		ProductoProveedor producProve = this.darProductosProveedorPorCodigoBarras(productoSucursal.getCodigoBarras()).get(0);
		int nivelEstante = estanteProducto.getNivelAprovisionamiento();
		if ( cantidad>productoSucursal.getCantidadEstante()) {
			int sobrante = cantidad - productoSucursal.getCantidadEstante();
			this.disminuirCantidadBodegaProductoSucursal(idProductoSucursal, sobrante);
			this.aumentarCantidadEstanteProductoSucursal(idProductoSucursal, sobrante);
			this.disminuirCantidadEstanteProductoSucursal(idProductoSucursal, cantidad);
			
			
		}
		return null;


	}


	/* ****************************************************************
	 * 			Métodos para manejar los Proveedores
	 *****************************************************************/

	public Proveedor adicionarProveedor(String nombre) {
		log.info("Adicionando Proveedor: " + nombre);
		Proveedor proveedor = pp.adicionarProveedor(nombre);
		log.info("Adicionando Proveedor: " + proveedor);
		return proveedor;
	}
	
	public Proveedor adicionarProveedor2(long nit, String nombre) {
		log.info("Adicionando Proveedor: " + nombre);
		Proveedor proveedor = pp.adicionarProveedor2(nit, nombre);
		log.info("Adicionando Proveedor: " + proveedor);
		return proveedor;
	}

	public Proveedor darProveedorPorNombre (String nombre) {
		log.info("Buscando proveedor por nombre : " + nombre);
		List<Proveedor> tb = pp.darProveedoresPorNombre(nombre);
		return !tb.isEmpty() ? tb.get(0) : null;

	}

	public Proveedor darProveedorPorNit (long nit) {
		log.info("Dar informacion de un proveedor por nit: " + nit);
		Proveedor proveedor = pp.darProveedorPorNit(nit);
		log.info("Buscando proveedor por nit : " + nit != null ? proveedor : "NO EXISTE");
		return proveedor;
	}
	
	public long eliminarProveedrPorNit(long nit) {
		return pp.eliminarProveedorPorNit(nit);
	}

	public List<Proveedor> darProveedores() {
		log.info("Listando Proveedores");
		List<Proveedor> proveedores = pp.darProveedores();
		log.info("Listando Proveedores: "+ proveedores.size() + " proveedores existentes");
		return proveedores;
	}

	public List<VOProveedor> darVOProveedores(){
		log.info("Generando los VO de Proveedores");
		List<VOProveedor> voProveedores = new LinkedList<VOProveedor>();
		for(Proveedor proveedor : pp.darProveedores()) {
			voProveedores.add(proveedor);
		}
		log.info("Generando los VO de Proveedores: " + voProveedores.size() + " proveedores existentes");
		return voProveedores;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las Sucursales
	 *****************************************************************/

	public Sucursal adicionarSucursal(String ciudad, String direccion, String nombre) {
		log.info("Adicionando sucursal : " + nombre);
		Sucursal sucursal = pp.adicionarSucursal(ciudad, direccion, nombre);
		log.info("Adicionando sucursal: " + sucursal);
		return sucursal;
	}
	
	public Sucursal adicionarSucursal2(long idSucursal, String ciudad, String direccion, String nombre) {
		log.info("Adicionando sucursal : " + nombre);
		Sucursal sucursal = pp.adicionarSucursal2(idSucursal,ciudad, direccion, nombre);
		log.info("Adicionando sucursal: " + sucursal);
		return sucursal;
	}

	public List<Sucursal> darSucursales(){
		log.info("Listando sucursales");
		List<Sucursal> sucursales = pp.darSucursales();
		log.info("Listando sucursales: " + sucursales.size() + " sucursales existentes");
		return sucursales;
	}

	public List<VOSucursal> darVOSucursales(){
		log.info("Generando los VO de Sucursales");
		List<VOSucursal> voSucursales = new LinkedList<VOSucursal> ();
		for (Sucursal sucursal : pp.darSucursales()) {
			voSucursales.add(sucursal);
		}
		log.info("Generando los VO de Sucursales: " + voSucursales.size() + " sucursales existentes");
		return voSucursales;

	}

	public Sucursal darSucursalPorId ( long idSucursal) {
		log.info("Dar informacion completa de una sucursal por id: " + idSucursal);
		Sucursal sucursal = pp.darSucursalPorId(idSucursal);
		log.info("Buscando sucursal por id: " + sucursal != null ? sucursal : "NO EXISTE");
		return sucursal;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las Cliente
	 *****************************************************************/

	public Cliente adicionarClienteIndividuo (String nombre, String correo ) {
		log.info("Adicionando clienteIndividuo : " + nombre);
		Cliente clienteIndividuo = pp.adicionarClienteIndividuo(nombre, correo);
		log.info("Adicionando clienteIndividuo: " + clienteIndividuo);
		return clienteIndividuo;
	}
	
	public Cliente adicionarClienteIndividuo2 (long identificacion, String nombre, String correo ) {
		log.info("Adicionando clienteIndividuo : " + nombre);
		Cliente clienteIndividuo = pp.adicionarClienteIndividuo2(identificacion, nombre, correo);
		log.info("Adicionando clienteIndividuo: " + clienteIndividuo);
		return clienteIndividuo;
	}

	public Cliente adicionarClienteEmpresa(String nombre, String correo, String direccion) {
		log.info("Adicionando clienteEmpresa : " + nombre);
		Cliente clienteEmpresa = pp.adicionarClienteEmpresa(nombre, correo, direccion);
		log.info("Adicionando clienteEmpresa : " + clienteEmpresa);
		return clienteEmpresa;
	}
	
	public Cliente adicionarClienteEmpresa2(long identificacion, String nombre, String correo, String direccion) {
		log.info("Adicionando clienteEmpresa : " + nombre);
		Cliente clienteEmpresa = pp.adicionarClienteEmpresa2(identificacion, nombre, correo, direccion);
		log.info("Adicionando clienteEmpresa : " + clienteEmpresa);
		return clienteEmpresa;
	}

	public List<Cliente> darClientesIndividuos() {
		log.info("Listando clientes individuos");
		List<Cliente> clientesIndividuo = pp.darClientesIndividuos();
		log.info("Listando clientesIndividuo " + clientesIndividuo.size() + " clientesIndividuo existentes");
		return clientesIndividuo;
	}

	public List<VOCliente> darVOClientesIndividuos(){
		log.info("Generando los VO de clientesIndividuos");
		List<VOCliente> voClientesIndividuo = new LinkedList<VOCliente>();
		for (Cliente cliente : pp.darClientesIndividuos()) {
			voClientesIndividuo.add(cliente); 
		}
		log.info("Generando los VO de ClientesIndividuo: " + voClientesIndividuo.size() + " clientesIndividuo existentes");
		return voClientesIndividuo;
	}

	public List<Cliente> darClientesEmpresa() {
		log.info("Listando clientes Empresa");
		List<Cliente> clientesEmpresa = pp.darClientesEmpresa();
		log.info("Listando clientesEmpresa " + clientesEmpresa.size() + " clientesEmpresa existentes");
		return clientesEmpresa;
	}

	public List<VOCliente> darVOClientesEmpresa(){
		log.info("Generando los VO de clientesEmpresa");
		List<VOCliente> voClientesEmpresa = new LinkedList<VOCliente>();
		for (Cliente cliente : pp.darClientesEmpresa()) {
			voClientesEmpresa.add(cliente); 
		}
		log.info("Generando los VO de ClientesEmpresa: " + voClientesEmpresa.size() + " clientesEmpresa existentes");
		return voClientesEmpresa;
	}

	public List<Cliente> darClientes(){
		log.info("Listando clientes");
		List<Cliente> clientes = pp.darClientes();
		log.info("Listando clientes " + clientes.size() + " clientes existentes");
		return clientes;
	}

	public List<VOCliente> darVOClientes(){
		log.info("Generando los VO de clientes");
		List<VOCliente> voClientes = new LinkedList<VOCliente>();
		for (Cliente cliente : pp.darClientes()) {
			voClientes.add(cliente); 
		}
		log.info("Generando los VO de Clientes: " + voClientes.size() + " clientes existentes");
		return voClientes;
	}

	public Cliente darClientePorIdentificacion (long identificacion) {
		log.info("Dar informacion de un cliente por identificacion : " + identificacion);
		Cliente cliente = pp.darClientePorIdentificacion(identificacion);
		log.info("Buscando cliente por identificacion : " + cliente != null ? cliente : "NO EXISTE");
		return cliente;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las FACTURA
	 *****************************************************************/

	public Factura adicionarFactura (String descripcion ) {
		log.info("Adicionando Factura: "+ descripcion);
		Factura factura = pp.adicionarFactura(descripcion);
		log.info("Adicionando factura: " + factura);
		return factura;
	}
	
	public Factura adicionarFactura2 (long idFactura, String descripcion ) {
		log.info("Adicionando Factura: "+ descripcion);
		Factura factura = pp.adicionarFactura2(idFactura, descripcion);
		log.info("Adicionando factura: " + factura);
		return factura;
	}

	public Factura darFacturaPorId(long idFactura) {
		log.info("Dar informacion de una factrua por id: " + idFactura);
		Factura factura = pp.darFacturaPorId(idFactura);
		log.info("Buscando factura por id :" + factura != null ? factura : "NO EXISTE");
		return factura;
	}

	public List<Factura> darFacturas(){
		log.info("Listando Facturas");
		List<Factura> facturas = pp.darFacturas();
		log.info("Listando Facturas: " + facturas.size() + " facturas existentes");
		return facturas;
	}

	public List<VOFactura> darVOFacturas(){
		log.info("Generando los VO de Factura");
		List<VOFactura> voFacturas = new LinkedList<VOFactura>();
		for (Factura factura : pp.darFacturas()) {
			voFacturas.add(factura); 
		}
		log.info("Generando los VO de Facturas: " + voFacturas.size() + " facturas existentes");
		return voFacturas;
	}

	/* ****************************************************************
	 * 			Métodos para manejar el productoProveedor
	 *****************************************************************/
	public ProductoProveedor adicionarProductoProveedor(String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, String fechaVencimiento , double calidad, double precio, int numeroCalificaciones, double sumaCalificaciones, long idProveedor) {
		log.info("Adicionando ProductoProveedor: " + nombre);
		ProductoProveedor productoProveedor = pp.adicionarProductoProveedor(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
		log.info("Adicionando productoProveedor: " + productoProveedor);
		return productoProveedor;
	}
	
	public ProductoProveedor adicionarProductoProveedor2(long idProductoProveedor, String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, String fechaVencimiento , double calidad, double precio, int numeroCalificaciones, double sumaCalificaciones, long idProveedor) {
		log.info("Adicionando ProductoProveedor: " + nombre);
		ProductoProveedor productoProveedor = pp.adicionarProductoProveedor2(idProductoProveedor, nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, calidad, precio, numeroCalificaciones, sumaCalificaciones, idProveedor);
		log.info("Adicionando productoProveedor: " + productoProveedor);
		return productoProveedor;
	}

	public List<ProductoProveedor> darProductosProveedor(){
		log.info("Listando productosProveedor");
		List<ProductoProveedor> productosProveedor = pp.darProductosProveedor();
		log.info("Listando ProductosProveedor: " + productosProveedor.size() + " productosProveedor existentes");
		return productosProveedor;
	}

	public List<VOProductoProveedor> darVOProductosProveedor(){
		log.info("Generando los VO de ProductosProveedor");
		List<VOProductoProveedor> voProductosProveedor = new LinkedList<VOProductoProveedor>();
		System.out.println(pp.darProductosProveedor().size());
		for (ProductoProveedor productoProveedor : pp.darProductosProveedor()) {
			//System.out.println(productoProveedor);
			voProductosProveedor.add(productoProveedor); 
			//System.out.println("re");
		}
		log.info("Generando los VO de ProductosProveedor: " + voProductosProveedor.size() + " ProductosProveedor existentes");
		return voProductosProveedor;
	}

	public List<ProductoProveedor> darProductosProveedorPorCategoria(String categoria){
		log.info("Dar informacion de productosProveedor por categoria: " + categoria);
		List<ProductoProveedor> productosProveedores = pp.darProductosProveedorPorCategoria(categoria);
		log.info("Dar informacion de productosProveedor por categoria: " + productosProveedores.size() + " productosProveedor con esa categoria existentes");
		return productosProveedores;
	}

	public List<VOProductoProveedor> darVOProductosProveedorPorCategoria(String categoria){
		log.info("Generando los VO de ProductosProveedor por categoria");
		List<VOProductoProveedor> voProductosProveedor = new LinkedList<VOProductoProveedor>();
		for (ProductoProveedor productoProveedor : pp.darProductosProveedorPorCategoria(categoria)) {
			voProductosProveedor.add(productoProveedor); 
		}
		log.info("Generando los VO de ProductosProveedor por categoria: " + voProductosProveedor.size() + " ProductosProveedor existentes que pertenecen a la categoria");
		return voProductosProveedor;
	}
	
	public List<ProductoProveedor> darProductosProveedorPorCodigoBarras(long codigoBarras){
		log.info("Dar informacion de productosProveedor por codigoBarras: " + codigoBarras);
		List<ProductoProveedor> productosProveedor = pp.darProductosProveedorPorCodigoBarras(codigoBarras);
		log.info("Dar informacion de productosPorveedor por codigoBarras: " + productosProveedor.size() + " productosProveedor que cumplen con el codigoBarras");
		return productosProveedor;
	}
	
	public List<VOProductoProveedor> darVOProductosProveedorPorCodigoBarras(long codigoBarras){
		log.info("Generando los VO de ProductosProveedor por codigoBarras");
		List<VOProductoProveedor> voProductosProveedor = new LinkedList<VOProductoProveedor>();
		for (ProductoProveedor productoProveedor : pp.darProductosProveedorPorCodigoBarras(codigoBarras)) {
			voProductosProveedor.add(productoProveedor);
		}
		log.info("Generando los VO de productosProveedor por codigoBarras: " + voProductosProveedor.size() + " productosProveedor que cumplen con el codigoBarras");
		return voProductosProveedor;
	}

	public List<ProductoProveedor> darProductosProveedorPorTipo(String tipo){
		log.info("Dar informacion de productosProveedor por tipo: " + tipo);
		List<ProductoProveedor> productosProveedores = pp.darProductosProveedorPorTipo(tipo);
		log.info("Dar informacion de productosProveedor por tipo: " + productosProveedores.size() + " productosProveedor con ese tipo existentes");
		return productosProveedores;
	}

	public List<VOProductoProveedor> darVOProductosProveedorPorTipo(String tipo){
		log.info("Generando los VO de ProductosProveedor por tipo");
		List<VOProductoProveedor> voProductosProveedor = new LinkedList<VOProductoProveedor>();
		for (ProductoProveedor productoProveedor : pp.darProductosProveedorPorTipo(tipo)) {
			voProductosProveedor.add(productoProveedor); 
		}
		log.info("Generando los VO de ProductosProveedor por tipo: " + voProductosProveedor.size() + " ProductosProveedor existentes que pertenecen al tipo");
		return voProductosProveedor;
	}

	public ProductoProveedor darProductoProveedorPorId (long idProductoProveedor) {
		log.info("Dar informacion de un ProductoProveedor por id : " + idProductoProveedor);
		ProductoProveedor productoProveedor = pp.darProductoProveedorPorId(idProductoProveedor);
		log.info("Buscando productoProveedor por Id: " + productoProveedor != null ? productoProveedor : "NO EXISTE");
		return productoProveedor;
	}

	public long aumentarNumeroCalificaciones(long idProductoProveedor) {
		log.info("Aumentando numeroCalificaciones de ProductoProveedor: " + idProductoProveedor);
		long cambios = pp.aumentarNumeroCalificacionesProductoProveedor(idProductoProveedor);
		return cambios;
	}

	public long aumentarSumaCalificaciones (long idProductoProveedor, double suma) {
		log.info("Sumando a sumaCalificaciones de un ProductoProveedor por id: "+ idProductoProveedor);
		long cambios = pp.aumentarSumaCalificacionesProductoProveedor(idProductoProveedor, suma);
		return cambios;
	}

	public long modificarCalidadProductoProveedor (long idProductoProveedor, double calidad) {
		log.info("Modificando la calidad de un ProductoProveedor por id: " + idProductoProveedor);
		long cambios = pp.modificarCalidadProductoProveedor(idProductoProveedor, calidad);
		return cambios;
	}

	public long modificarFechaVencimientoProductoProvedor(long idProductoProveedor, String fecha) {
		log.info("Modificando la fecha de un ProductoProveedor por id: " + idProductoProveedor);
		long cambios = pp.modificarFechaVencimientoProductoProveedor(idProductoProveedor, fecha);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar el OrdenPedido
	 *****************************************************************/

	public OrdenPedido adicionarOrdenPedido(double precio, String fechaEntrega, String fechaEsperadaEntrega,double calificacion, int entregado, int cantidad, long idProductoProveedor, long idSucursal) {
		log.info("Adicionando OrdenPedido: " + idProductoProveedor);
		OrdenPedido ordenPedido = pp.adicionarOrdenPedido(precio, fechaEntrega, fechaEsperadaEntrega, calificacion, entregado, cantidad, idProductoProveedor, idSucursal);
		log.info("Adicionando OrdenPedido: " + ordenPedido);
		return ordenPedido;
	}
	
	public OrdenPedido adicionarOrdenPedido2(long idOrdenPedido, double precio, String fechaEntrega, String fechaEsperadaEntrega,double calificacion, int entregado, int cantidad, long idProductoProveedor, long idSucursal) {
		log.info("Adicionando OrdenPedido: " + idProductoProveedor);
		OrdenPedido ordenPedido = pp.adicionarOrdenPedido2(idOrdenPedido ,precio, fechaEntrega, fechaEsperadaEntrega, calificacion, entregado, cantidad, idProductoProveedor, idSucursal);
		log.info("Adicionando OrdenPedido: " + ordenPedido);
		return ordenPedido;
	}

	public OrdenPedido darOrdenPedidoPorId(long idOrdenPedido) {
		log.info("Dar informacion de un OrdenPedido por id: " + idOrdenPedido);
		OrdenPedido ordenPedido = pp.darOrdenPedidoPorId(idOrdenPedido);
		log.info("Buscando ordenPedido por id: " + ordenPedido != null ? ordenPedido : "NO EXISTE");
		return ordenPedido;
	}

	public List<OrdenPedido> darOrdenesPedido(){
		log.info("Listando OrdenesPedido");
		List<OrdenPedido> ordenesPedido = pp.darOrdenesPedido();
		log.info("Listando OrdenesPedido: " + ordenesPedido.size() + " ordenesPedido existente");
		return ordenesPedido;
	}

	public List<VOOrdenPedido> darVOOrdenesPedido(){
		log.info("Generando los VO de las OrdenesPedio");
		List<VOOrdenPedido> voOrdenesPedido = new LinkedList<VOOrdenPedido>();
		for( OrdenPedido orden : pp.darOrdenesPedido()) {
			voOrdenesPedido.add(orden);
		}
		log.info("Generando los VO de OrdenesPedido " + voOrdenesPedido.size() + " ordenesPedido exixstentes");
		return voOrdenesPedido;
	}

	public long calificarOrdenPedido(long idOrdenPedido, double calificacion ) {
		log.info("Calificando OrdenPedido con id: " + idOrdenPedido);
		long cambios = pp.calificarOrdenPedido(idOrdenPedido, calificacion);
		return cambios;
	}

	public long recibirEntregadoOrdenPedido(long idOrdenPedido) {
		log.info("Cambiando estado de OrdenPedido con id: " + idOrdenPedido);
		long cambios = pp.recibirEntregadoOrdenPedido(idOrdenPedido);
		return cambios;
	}

	public long recibirFechaOrdenPedido(long idOrdenPedido) {
		log.info("Generando la fecha del dia actual para asignarselo al OrdenPedido con id: " + idOrdenPedido);
		long cambios = pp.recibirFechaOrdenPedido(idOrdenPedido);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar BODEGA
	 *****************************************************************/

	public Bodega adicionarBodega(double volumen, double peso, String tipo, long sucursal) {
		log.info("Adicionando Bodega: " + tipo);
		Bodega bodega = pp.adicionarBodega(volumen, peso, tipo, sucursal);
		log.info("Adicionando bodega: " + bodega);
		return bodega;
	}
	
	public Bodega adicionarBodega2(long idBodega, double volumen, double peso, String tipo, long sucursal) {
		log.info("Adicionando Bodega: " + tipo);
		Bodega bodega = pp.adicionarBodega2(idBodega, volumen, peso, tipo, sucursal);
		log.info("Adicionando bodega: " + bodega);
		return bodega;
	}

	public Bodega darBodegaPorId(long idBodega) {
		log.info("Dar informacion de una Bodega por id: " + idBodega);
		Bodega bodega = pp.darBodegaPorId(idBodega);
		log.info("Buscando Bodega por id: " + bodega != null ? bodega : "NO EXISTE");
		return bodega;
	}

	public List<Bodega> darBodegasPorTipo(String tipo){
		log.info("Dar informacion de bodegas por tipo: " + tipo);
		List<Bodega> bodegas = pp.darBodegasPorTipo(tipo);
		log.info("Dar informacion de bodegas por tipo: " + bodegas.size() + " bodegas que cumplen con el tipo existentes");
		return bodegas;
	}

	public List<VOBodega> darVOBodegasPorTipo(String tipo){
		log.info("Generando VO de bodegas por tipo: "+ tipo);
		List<VOBodega> voBodegas = new LinkedList<VOBodega>();
		for(Bodega bodega : pp.darBodegasPorTipo(tipo)) {
			voBodegas.add(bodega);
		}
		log.info("Generando los VO de Bodegas: " + voBodegas.size() + " bodegas que cumplen con el tipo existentes");
		return voBodegas;
	}

	public List<Bodega> darBodegas(){
		log.info("Listando Bodegas");
		List<Bodega> bodegas = pp.darBodegas();
		log.info("Listando Bodegas: " + bodegas.size() + " bodegas existentes");
		return bodegas;
	}

	public List<VOBodega> darVOBodegas(){
		log.info("Generando VO de bodegas");
		List<VOBodega> voBodegas = new LinkedList<VOBodega>();
		for(Bodega bodega : pp.darBodegas()) {
			voBodegas.add(bodega);
		}
		log.info("Generando los VO de Bodegas: " + voBodegas.size() + " bodegas existentes");
		return voBodegas;
	}

	/* ****************************************************************
	 * 			Métodos para manejar Estante
	 *****************************************************************/

	public Estante adicionarEstante(double volumen, double peso, String tipo,int nivelAprovisionamiento, long sucursal) {
		log.info("Adicionando Estante: " + tipo);
		Estante estante = pp.adicionarEstante(volumen, peso, tipo,nivelAprovisionamiento,sucursal);
		log.info("Adicionando Estante: " + estante);
		return estante;
	}
	
	public Estante adicionarEstante2(long idEstante, double volumen, double peso, String tipo,int nivelAprovisionamiento, long sucursal) {
		log.info("Adicionando Estante: " + tipo);
		Estante estante = pp.adicionarEstante2(idEstante, volumen, peso, tipo,nivelAprovisionamiento,sucursal);
		log.info("Adicionando Estante: " + estante);
		return estante;
	}

	public Estante darEstantePorId(long idEstante) {
		log.info("Dar informacion de una Estante por id: " + idEstante);
		Estante estante = pp.darEstantePorId(idEstante);
		log.info("Buscando Estante por id: " + estante != null ? estante : "NO EXISTE");
		return estante;
	}

	public List<Estante> darEstantesPorTipo(String tipo){
		log.info("Dar informacion de estantes por tipo: " + tipo);
		List<Estante> estantes = pp.darEstantesPorTipo(tipo);
		log.info("Dar informacion de estantes por tipo: " + estantes.size() + " estantes que cumplen con el tipo existentes");
		return estantes;
	}

	public List<VOEstante> darVOEstantesPorTipo(String tipo){
		log.info("Generando VO de Estantes por tipo: "+ tipo);
		List<VOEstante> voEstantes = new LinkedList<VOEstante>();
		for(Estante estante : pp.darEstantesPorTipo(tipo)) {
			voEstantes.add(estante);
		}
		log.info("Generando los VO de Estantes: " + voEstantes.size() + " Estantes que cumplen con el tipo existentes");
		return voEstantes;
	}

	public List<Estante> darEstantes(){
		log.info("Listando Estantes");
		List<Estante> estantes = pp.darEstantes();
		log.info("Listando Estantes: " + estantes.size() + " Estantes existentes");
		return estantes;
	}

	public List<VOEstante> darVOEstantes(){
		log.info("Generando VO de Estantes");
		List<VOEstante> voEstantes = new LinkedList<VOEstante>();
		for(Estante estante : pp.darEstantes()) {
			voEstantes.add(estante);
		}
		log.info("Generando los VO de Estantes: " + voEstantes.size() + " Estantes existentes");
		return voEstantes;
	}

	/* ****************************************************************
	 * 			Métodos para manejar ProductoSucursal
	 *****************************************************************/

	public ProductoSucursal adicionarProductoSucursal(String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, String fechaVencimiento , int nivelReorden, double precioUnitario, int cantidadBodega, int cantidadEstante, double precioUnidadMedida
			, long idBodega, long idEstante, long idPromocion) {
		log.info("Adicionando ProductoSucursal: " + nombre);
		ProductoSucursal productoSucursal = pp.adicionarProductoSucursal(nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion);
		log.info("Adicionando productoSucursal: " + productoSucursal);
		return productoSucursal;
	}
	
	public ProductoSucursal adicionarProductoSucursal2(long idProductoSucursal, String nombre, String marca, String presentacion, double cantidadPresentacion, String unidadMedida,
			double volumenEmpaque, double pesoEmpaque, long codigoBarras, String categoria, String tipo, String fechaVencimiento , int nivelReorden, double precioUnitario, int cantidadBodega, int cantidadEstante, double precioUnidadMedida
			, long idBodega, long idEstante, long idPromocion) {
		log.info("Adicionando ProductoSucursal: " + nombre);
		ProductoSucursal productoSucursal = pp.adicionarProductoSucursal2(idProductoSucursal,nombre, marca, presentacion, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, codigoBarras, categoria, tipo, fechaVencimiento, nivelReorden, precioUnitario, cantidadBodega, cantidadEstante, precioUnidadMedida, idBodega, idEstante, idPromocion);
		log.info("Adicionando productoSucursal: " + productoSucursal);
		return productoSucursal;
	}

	public List<ProductoSucursal> darProductosSucursal(){
		log.info("Listando productosSucursal");
		List<ProductoSucursal> productosSucursal = pp.darProductosSucursal();
		log.info("Listando ProductosSucursal: " + productosSucursal.size() + " productosSucursal existentes");
		return productosSucursal;
	}

	public List<VOProductoSucursal> darVOProductosSucursal(){
		log.info("Generando los VO de ProductosSucursal");
		List<VOProductoSucursal> voProductosSucursal = new LinkedList<VOProductoSucursal>();
		for (ProductoSucursal productoSucursal : pp.darProductosSucursal()) {
			voProductosSucursal.add(productoSucursal); 
		}
		log.info("Generando los VO de ProductosSucursal: " + voProductosSucursal.size() + " ProductosSucursal existentes");
		return voProductosSucursal;
	}

	public List<ProductoSucursal> darProductosSucursalrPorCategoria(String categoria){
		log.info("Dar informacion de productosSucursal por categoria: " + categoria);
		List<ProductoSucursal> productosSucursal = pp.darProductosSucursalPorCategoria(categoria);
		log.info("Dar informacion de productosSucursal por categoria: " + productosSucursal.size() + " productosSucursal con esa categoria existentes");
		return productosSucursal;
	}

	public List<VOProductoSucursal> darVOProductosSucursalPorCategoria(String categoria){
		log.info("Generando los VO de ProductosSucursal por categoria");
		List<VOProductoSucursal> voProductosSucursal = new LinkedList<VOProductoSucursal>();
		for (ProductoSucursal productoSucursal: pp.darProductosSucursalPorCategoria(categoria)) {
			voProductosSucursal.add(productoSucursal); 
		}
		log.info("Generando los VO de ProductosSucursal por categoria: " + voProductosSucursal.size() + " ProductosSucursal existentes que pertenecen a la categoria");
		return voProductosSucursal;
	}
	
	public List<ProductoSucursal> darProductosSucursalPorCodigoBarras(long codigoBarras){
		log.info("Dar informacion de productosSucursal por codigoBarras: " + codigoBarras);
		List<ProductoSucursal> productosSucursal = pp.darProductosSucursalPorCodigoBarras(codigoBarras);
		log.info("Dar informacion de productosSucursal por codigoBarras: " + productosSucursal.size() + " productosSucursal que cumplen con el codigoBarras");
		return productosSucursal;
	}
	
	public List<VOProductoSucursal> darVOProductosSucursalrPorCodigoBarras(long codigoBarras){
		log.info("Generando los VO de ProductosSucursal por codigoBarras");
		List<VOProductoSucursal> voProductosSucursal = new LinkedList<VOProductoSucursal>();
		for (ProductoSucursal productoSucursal : pp.darProductosSucursalPorCodigoBarras(codigoBarras)) {
			voProductosSucursal.add(productoSucursal);
		}
		log.info("Generando los VO de productosSucursal por codigoBarras: " + voProductosSucursal.size() + " productosSucursal que cumplen con el codigoBarras");
		return voProductosSucursal;
	}

	public List<ProductoSucursal> darProductosSucursalPorTipo(String tipo){
		log.info("Dar informacion de productosSucursal por tipo: " + tipo);
		List<ProductoSucursal> productosSucursal = pp.darProductosSucursalPorTipo(tipo);
		log.info("Dar informacion de productosSucursal por tipo: " + productosSucursal.size() + " productosSucursal con ese tipo existentes");
		return productosSucursal;
	}

	public List<VOProductoSucursal> darVOProductosSucursalPorTipo(String tipo){
		log.info("Generando los VO de ProductosSucursal por tipo");
		List<VOProductoSucursal> voProductosSucursal = new LinkedList<VOProductoSucursal>();
		for (ProductoSucursal productoSucursal : pp.darProductosSucursalPorTipo(tipo)) {
			voProductosSucursal.add(productoSucursal); 
		}
		log.info("Generando los VO de ProductosSucursal por tipo: " + voProductosSucursal.size() + " ProductosSucursal existentes que pertenecen al tipo");
		return voProductosSucursal;
	}

	public ProductoSucursal darProductoSucursalPorId (long idProductoSucursal) {
		log.info("Dar informacion de un ProductoSucursal por id : " + idProductoSucursal);
		ProductoSucursal productoSucursal = pp.darProductoSucursalPorId(idProductoSucursal);
		log.info("Buscando productoSucursal por Id: " + productoSucursal != null ? productoSucursal : "NO EXISTE");
		return productoSucursal;
	}

	public long aumentarCantidadBodegaProductoSucursal(long idProductoSucursal, int cantidad) {
		log.info("Aumentando cantidadBodega de un ProductoSucursal por su id: " + idProductoSucursal);
		long cambios = pp.aumentarCantidadBodegaProductoSucursal(idProductoSucursal, cantidad);
		return cambios;
	}

	public long aumentarCantidadEstanteProductoSucursal(long idProductoSucursal, int cantidad) {
		log.info("Aumentando cantidadEstante de un ProductoSucursal por su id: " + idProductoSucursal);
		long cambios = pp.aumentarCantidadEstanteProductoSucursal(idProductoSucursal, cantidad);
		return cambios;
	}

	public long disminuirCantidadBodegaProductoSucursal(long idProductoSucursal, int cantidad) {
		log.info("Disminuyendo cantidadBodega de un ProductoSucursal por su id: " + idProductoSucursal);
		long cambios = pp.disminuirCantidadBodegaProductoSucursal(idProductoSucursal, cantidad);
		return cambios;
	}

	public long disminuirCantidadEstanteProductoSucursal(long idProductoSucursal, int cantidad) {
		log.info("Disminuyendo cantidadEstante de un ProductoSucursal por su id: " + idProductoSucursal);
		long cambios = pp.disminuirCantidadEstanteProductoSucursal(idProductoSucursal, cantidad);
		return cambios;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las PROMOCIONES
	 *****************************************************************/

	public Promocion adicionarPromocion(int tipo, double n, double m, String fechaCaducidad) {
		log.info("Adicionando promocion: " + tipo);
		Promocion promocion = pp.adicionarPromocion(tipo, n, m, fechaCaducidad);
		log.info("Adicionando promocion: " + promocion);
		return promocion;
	}
	
	public Promocion adicionarPromocion2(long idPromocion, int tipo, double n, double m, String fechaCaducidad) {
		log.info("Adicionando promocion: " + tipo);
		Promocion promocion = pp.adicionarPromocion2(idPromocion, tipo, n, m, fechaCaducidad);
		log.info("Adicionando promocion: " + promocion);
		return promocion;
	}

	public List<Promocion> darPromociones(){
		log.info("Listando promociones");
		List<Promocion> promociones = pp.darPromociones();
		log.info("Listando promociones: "  + promociones.size() + " promociones existentes");
		return promociones;
	}

	public List<VOPromocion> darVOPromociones(){
		log.info("Generando los VO de Promociones");
		List<VOPromocion> voPromociones = new LinkedList<VOPromocion>();
		for ( Promocion promocion : pp.darPromociones()) {
			voPromociones.add(promocion);
		}
		log.info("Generando los VO de promociones: "  + voPromociones.size() + " promociones existentes");
		return voPromociones;
	}

	public List<Promocion> darPromocionesPorTipo (int tipo){
		log.info("Dar informacion de promociones por tipo: " + tipo);
		List<Promocion> promociones = pp.darPromocionesPorTipo(tipo);
		log.info("Dar informacion de promociones por tipo: " + promociones.size() + " promociones existentes de ese tipo");
		return promociones;
	} 

	public List<VOPromocion> darVOPromocionesPorTipo(int tipo){
		log.info("Generando VO de promociones por tipo: " + tipo);
		List<VOPromocion> voPromociones = new LinkedList<VOPromocion>();
		for(Promocion promocion : pp.darPromocionesPorTipo(tipo)) {
			voPromociones.add(promocion);
		}
		log.info("Generando los VO de promociones: " + voPromociones.size() + " promociones existentes de ese tipo");
		return voPromociones;
	}

	public Promocion darPromocionPorId (long idPromocion) {
		log.info("Dar informacion de una Promocion por id: " + idPromocion);
		Promocion promocion = pp.darPromocionPorId(idPromocion);
		log.info("Buscando promocion por id: " + promocion != null ? promocion : "NO EXISTE");
		return promocion;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las VENTAS
	 *****************************************************************/

	public Compra adicionarCompra(String fecha, int cantidad, double totalPagado, long idProductoSucursal, long idCliente, long idFactura) {
		log.info("Adicionando compra: " + idProductoSucursal );
		Compra compra = pp.adicionarCompra(fecha, cantidad, totalPagado, idProductoSucursal, idCliente, idFactura);
		log.info("Adicionando compra: " + compra);
		return compra;
	}
	
	public Compra adicionarCompra2(long idCompra, String fecha, int cantidad, double totalPagado, long idProductoSucursal, long idCliente, long idFactura) {
		log.info("Adicionando compra: " + idProductoSucursal );
		Compra compra = pp.adicionarCompra2(idCompra, fecha, cantidad, totalPagado, idProductoSucursal, idCliente, idFactura);
		log.info("Adicionando compra: " + compra);
		return compra;
	}

	public List<Compra> darCompras(){
		log.info("Listando compras");
		List<Compra> compras = pp.darCompras();
		log.info("Listando compras: " + compras.size() + " compras existentes");
		return compras;
	}

	public List<VOCompra> darVOCompras(){
		log.info("Generando los VO de Compras");
		List<VOCompra> voCompras = new LinkedList<VOCompra>();
		for(Compra compra : pp.darCompras()) {
			voCompras.add(compra);
		}
		log.info("Generando los VO de Compras: " + voCompras.size() + " compras existentes");
		return voCompras;
	}

	public List<Compra> darComprasPorCliente (long idCliente){
		log.info("Dar informacion de Compras por identificacion del cliente: " + idCliente);
		List<Compra> compras = pp.darComprasPorCliente(idCliente);
		log.info("Dar informacion de Compras por identificacion del Cliente: " + compras.size() + " compras que hizo ese cliente");
		return compras;
	}

	public List<VOCompra> darVOComprasPorCliente(long idCliente){
		log.info("Generando VO de compras por identificacion del cliente: " + idCliente);
		List<VOCompra> voCompras = new LinkedList<VOCompra>();
		for (Compra compra : pp.darComprasPorCliente(idCliente)) {
			voCompras.add(compra);
		}
		log.info("Generando los VO de Compras: " + voCompras.size() + " compras que perteneces al cliente con esa identificacion");
		return voCompras;
	}

	public List<Compra> darComprasPorFactura (long idFactura){
		log.info("Dar informacion de Compras por idFactura: " + idFactura);
		List<Compra> compras = pp.darComprasPorFactura(idFactura);
		log.info("Dar informacion de Compras por identificacion de la Factura: " + compras.size() + " compras que perteneces a esa factura");
		return compras;
	}

	public List<VOCompra> darVOComprasPorFactura(long idFactura){
		log.info("Generando VO de compras por idFactura: " + idFactura);
		List<VOCompra> voCompras = new LinkedList<VOCompra>();
		for (Compra compra : pp.darComprasPorFactura(idFactura)) {
			voCompras.add(compra);
		}
		log.info("Generando los VO de Compras: " + voCompras.size() + " compras que perteneces a la factura con ese id");
		return voCompras;
	}

	public Compra darCompraPorId(long idCompra) {
		log.info("Dar informacion de una compra por id: " + idCompra);
		Compra compra = pp.darCompraPorId(idCompra);
		log.info("Buscando compra por id: " + compra != null ? compra : "NO EXISTE");
		return compra;
	}

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	public long [] limpiarSuperAndes ()
	{
		log.info ("Limpiando la BD de SuperAndes");
		long [] borrrados = pp.limpiarSuperAndes();	
		log.info ("Limpiando la BD de Parranderos: Listo!");
		return borrrados;
	}










}
