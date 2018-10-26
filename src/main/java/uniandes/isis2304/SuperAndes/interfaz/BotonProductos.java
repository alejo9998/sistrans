package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import jdk.nashorn.internal.runtime.ListAdapter;


public class BotonProductos extends JPanel implements ActionListener 
{
	private JList lista;

	private String[] a = {"Carne","Arroz","Huevos"};

	private JScrollPane scrollLista;

	private JButton agregarProducto;

	public BotonProductos()
	{
		setBorder(new TitledBorder("Productos"));
		setLayout(new BorderLayout());
		lista = new JList<String>();
		scrollLista= new JScrollPane(lista);
		scrollLista.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
		scrollLista.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		lista.setListData(a);

		agregarProducto = new JButton("Agergar Producto");
		add(scrollLista,BorderLayout.CENTER);
		add(agregarProducto,BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
