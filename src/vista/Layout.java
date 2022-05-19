package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import dao.DaoFactory;
import modelo.Desayuno;

public class Layout extends JFrame implements ActionListener, ListSelectionListener {

	JPanel principal, asideIz, panelJlist, footer;
	JButton salir, crearXML, crearFichero;
	JTextArea contenido;
	JLabel resultado;
	JList<String> jlist;
	ArrayList<Desayuno> desayunos;
	ArrayList<Desayuno> elegidosXML;
	String salida = "";

	public Layout() {
		this.setTitle("Elige Desayuno");
		this.setBounds(400, 500, 800, 600);

		asideIz = new JPanel();
		panelJlist = new JPanel();
		desayunos = DaoFactory.getInstance().getDesayunos();

		ArrayList<Integer> cartas = new ArrayList<>();
		for (Desayuno d : desayunos) {
			cartas.add(d.getNumCarta());
		}
		jlist = new JList(cartas.toArray());
		jlist.setPreferredSize(new Dimension(100, 500));
		jlist.addListSelectionListener(this);
		JScrollPane miscroll = new JScrollPane(jlist);
		panelJlist.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panelJlist.add(miscroll);

		contenido = new JTextArea();
		contenido.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JScrollPane miscroll2 = new JScrollPane(contenido);
		contenido.setEditable(false);

		salir = new JButton("Salir");
		crearXML = new JButton("Generar XML");
		crearXML.addActionListener(this);
		crearFichero = new JButton("Guardar Objeto");
		crearFichero.addActionListener(this);
		footer = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
		footer.add(salir);
		footer.add(crearXML);
		footer.add(crearFichero);
		resultado = new JLabel("");
		footer.add(resultado);

		principal = new JPanel(new BorderLayout(10, 10));
		principal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		principal.add(panelJlist, BorderLayout.WEST);
		principal.add(miscroll2, BorderLayout.CENTER);
		principal.add(footer, BorderLayout.SOUTH);

		this.add(principal);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		elegidosXML = new ArrayList<>();
		Object[] elegidos = jlist.getSelectedValuesList().toArray();
		if (e.getActionCommand().equals("Generar XML")) {
			for (Desayuno d : desayunos) {
				for (Object elegido : elegidos) {
					String aux = elegido.toString();
					int ele = Integer.parseInt(aux);
					if (ele == d.getNumCarta()) {
						elegidosXML.add(d);
					}
				}
			}
			try {
				DaoFactory.getInstance().getEscribeXmlElegido(elegidosXML);
				resultado.setText("Archivo XML generado con éxito");
			} catch (ParserConfigurationException | TransformerException e1) {
				resultado.setText("No se ha podido generar el xml");
			}
		} else if (e.getActionCommand().equals("Guardar Objeto")) {
			try {
				DaoFactory.getInstance().getGuardaElegidos(elegidosXML);
				resultado.setText("Guardado el Objeto en la ruta del proyecto");
			} catch (IOException e1) {
				resultado.setText("No se ha podido guardar el objeto");
			}
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		Object[] elegidos = jlist.getSelectedValuesList().toArray();
		salida = "";

		for (Desayuno d : desayunos) {
			for (Object elegido : elegidos) {
				String aux = elegido.toString();
				int ele = Integer.parseInt(aux);
				if (ele == d.getNumCarta()) {
					salida += d;
				}
			}
		}
		contenido.setText(salida);
	}

}
