package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DialogoProductoSucursal extends JDialog implements ActionListener
{

	private InterfazAdministrador inte;

	private JTextField nombre;


	private JTextField marca;

	private JTextField presentacion;

	private JTextField cantidadPresentacion;

	private JTextField unidadMedida;

	private JTextField volumenEmpaque;

	private JTextField pesoEmpaque;

	private JTextField codigoDeBarras;

	private JComboBox<String> categoria;

	private JComboBox<String> tipo;

	private JTextField fechadeVencimiento;

	private JTextField nivelReorden;

	private JTextField precioUnitario;

	private JTextField cantidadBodega;

	private JTextField cantidadEstante;

	private JTextField precioUnidadMedida;

	private JTextField promocion;

	private JTextField bodega;

	private JTextField estante;

	private JButton aceptar;
	private final static String ACEPTAR="ACEPTAR";

	private JButton cancelar;
	private final static String CANCELAR="CANCELAR";


	private final static String actualiza="ACTUALIZAR";

	public DialogoProductoSucursal(InterfazAdministrador inter) 
	{
		inte= inter;
		setTitle("Agregar Producto Sucursal");
		setLayout(new BorderLayout());
		setSize(900,700);
		setLocationRelativeTo(null);

		JPanel campos = new JPanel( );
		campos.setLayout( new GridLayout( 11, 4, 3, 3 ) );
		campos.setBorder( new EmptyBorder( 15, 15, 15, 15 ) );
		add( campos, BorderLayout.CENTER );

		JLabel nom = new JLabel("Nombre del producto: ");
		JLabel marc= new JLabel("Marca: ");
		JLabel presen= new JLabel("Presentación: ");
		JLabel cantPresen = new JLabel("Cantidad de presentacion: ");
		JLabel unMed = new JLabel("Unidad de medida: ");
		JLabel volEmp = new JLabel("Volumen del Empaque: ");
		JLabel pesEmp = new JLabel("Peso del Empaque: ");
		JLabel codBar= new JLabel("Codigo de Barras: ");
		JLabel cat= new JLabel("Categoria: ");
		JLabel tip = new JLabel("Tipo: ");
		JLabel fechaVen = new JLabel("Fecha de Vencimiento: ");
		JLabel nivRe = new JLabel("Nivel de Reorden: ");
		JLabel precUni = new JLabel("Precio Unitario: ");
		JLabel cantBod = new JLabel("Cantidad en Bodega: ");
		JLabel cantEst= new JLabel("Cantidad en Estante: ");
		JLabel precUnMed= new JLabel("Precio Unidad/Medida: ");
		JLabel prom = new JLabel("Id Promoción: ");
		JLabel bod = new JLabel("Id Bodega: ");
		JLabel est = new JLabel("Id Estante: ");


		nombre= new JTextField();

		marca= new JTextField();

		presentacion= new JTextField();

		cantidadPresentacion= new JTextField();

		unidadMedida= new JTextField();

		volumenEmpaque= new JTextField();

		pesoEmpaque= new JTextField();

		codigoDeBarras= new JTextField();

		String [] categorias={"Albarrotes","Perecederos","Aseo Personal"};
		categoria= new JComboBox<>(categorias);
		categoria.addActionListener(this);
		categoria.setActionCommand(actualiza);

		fechadeVencimiento = new JTextField();

		tipo = new JComboBox<>();
		
				
		nivelReorden= new JTextField();

		precioUnitario= new JTextField();

		cantidadBodega= new JTextField();

		cantidadEstante= new JTextField();

		precioUnidadMedida= new JTextField();

		promocion= new JTextField();

		bodega= new JTextField();

		estante = new JTextField();

		aceptar = new JButton("Aceptar");
		aceptar.addActionListener(this);
		aceptar.setActionCommand(ACEPTAR);
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		cancelar.setActionCommand(CANCELAR);

		JLabel vacio1 = new  JLabel();
		JLabel vacio2 = new  JLabel();
		JLabel vacio3 = new  JLabel();
		JLabel vacio4 = new  JLabel();



		campos.add(nom);
		campos.add(nombre);
		campos.add(marc);
		campos.add(marca);
		campos.add(presen);
		campos.add(presentacion);
		campos.add(cantPresen);
		campos.add(cantidadPresentacion);
		campos.add(unMed);
		campos.add(unidadMedida);
		campos.add(volEmp);
		campos.add(volumenEmpaque);
		campos.add(pesEmp);
		campos.add(pesoEmpaque);
		campos.add(codBar);
		campos.add(codigoDeBarras);
		campos.add(cat);
		campos.add(categoria);
		campos.add(tip);
		campos.add(tipo);
		campos.add(fechaVen);
		campos.add(fechadeVencimiento);
		campos.add(nivRe);
		campos.add(nivelReorden);
		campos.add(precUni);
		campos.add(precioUnitario);
		campos.add(cantBod);
		campos.add(cantidadBodega);
		campos.add(cantEst);
		campos.add(cantidadEstante);
		campos.add(precUnMed);
		campos.add(precioUnidadMedida);
		campos.add(prom);
		campos.add(promocion);
		campos.add(bod);
		campos.add(bodega);
		campos.add(est);
		campos.add(estante);
		campos.add(vacio1);
		campos.add(vacio3);
		campos.add(vacio4);
		campos.add(aceptar);
		campos.add(cancelar);
		campos.add(vacio2);

	}
	
	public void crearTipo()
	{
		if(((String)categoria.getSelectedItem()).equalsIgnoreCase("Albarrotes"))
		{
			tipo.removeAllItems();
			tipo.addItem("Escoba");
			tipo.addItem("Trapeador");
			tipo.addItem("balde");
			tipo.addItem("brillador");
			
		}
		else if(((String)categoria.getSelectedItem()).equalsIgnoreCase("Aseo Personal"))
		{
		
			tipo.removeAllItems();
			tipo.addItem("Cepillo");
			tipo.addItem("Desodorante");
			tipo.addItem("Crema");
			tipo.addItem("Cera");
					
		}
		else if(((String)categoria.getSelectedItem()).equalsIgnoreCase("Perecederos"))
		{
			tipo.removeAllItems();
			tipo.addItem("Carne");
			tipo.addItem("Galleta");
			tipo.addItem("Fruta");
			tipo.addItem("Tuberculo");
			
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String mensaje = e.getActionCommand();
		if(mensaje.equalsIgnoreCase(ACEPTAR))
		{
			String nom = nombre.getText();
			String mar = marca.getText();
			String pres = presentacion.getText();
			String cantiPres = cantidadPresentacion.getText();
			String unidMed = unidadMedida.getText();
			String volEmp= volumenEmpaque.getText();
			String pesoEmp = pesoEmpaque.getText();
			String codBarr = codigoDeBarras.getText();
			String cat = (String)categoria.getSelectedItem();
			String fech = fechadeVencimiento.getText();
			String tip = (String)tipo.getSelectedItem();
			String nvlreord = nivelReorden.getText();
			String prcUni = precioUnitario.getText();
			String cantBod = cantidadBodega.getText();
			String cantEst = cantidadEstante.getText();
			String precUnMed = precioUnidadMedida.getText();
			String idProm = promocion.getText();
			String idBod = bodega.getText();
			String  idest = estante.getText();
			inte.agregarProductoSucursal(nom, mar, pres, cantiPres, unidMed, volEmp, pesoEmp, codBarr, cat, tip, fech, 
					nvlreord, prcUni, cantBod, cantEst, precUnMed, idProm, idest, idBod);


			dispose();
		}
		else if(mensaje.equalsIgnoreCase(CANCELAR))
		{
			dispose();
		}
		else if ( mensaje.equals(actualiza))
		{
			
			crearTipo();
		
		}

	}

}
