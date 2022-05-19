package main;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import javax.swing.SwingUtilities;

import dao.DaoFactory;
import modelo.Desayuno;
import vista.Layout;

public class Main {

	public static void main(String[] args) {
		
		try {
			DaoFactory.getInstance().eligeArchivo();
			ArrayList<Desayuno> desayunos =DaoFactory.getInstance().getleeXML();
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("No se ha podido leer el xml");
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Layout nuevo = new Layout();
			}
		});

	}
} 