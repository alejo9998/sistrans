package uniandes.isis2304.SuperAndes.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class PanelDatos extends JPanel
{
  
	private JTextArea textArea;

   
    public PanelDatos ()
    {
        setBorder (new TitledBorder ("Panel de información"));
        setLayout( new BorderLayout( ) );
        
        setPreferredSize(new Dimension(800, 150));
        
        textArea = new JTextArea("Aquí sale el resultado de las operaciones solicitadas");
        textArea.setEditable(false);
        add (new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void actualizarInterfaz (String texto)
    {
    	textArea.setText(texto);
    }

}
