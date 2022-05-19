package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import modelo.Desayuno;

public class DaoFactory {

	public static DaoFactory daoFactory;
	private File archivoElegido;
	private ArrayList<Desayuno> desayunos;

	public DaoFactory() {
		desayunos = new ArrayList<>();
	}

	public static DaoFactory getInstance() {
		if (daoFactory == null) {
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}

	public void eligeArchivo() {
		JFileChooser eligeArchivo = new JFileChooser();
		eligeArchivo.showDialog(eligeArchivo, "Elige fichero");
		archivoElegido = eligeArchivo.getSelectedFile();

	}

	public ArrayList<Desayuno> getleeXML() throws SAXException, IOException, ParserConfigurationException {
		leeXml nuevo = new leeXml(desayunos, archivoElegido);
		desayunos = nuevo.leer();
		return desayunos;
	}

	public void getEscribeXmlElegido(ArrayList<Desayuno> elegidos)
			throws ParserConfigurationException, TransformerException {
		escribeXml nuevo = new escribeXml(elegidos);
		nuevo.escribir();
	}

	public void getGuardaElegidos(ArrayList<Desayuno> elegidos) throws FileNotFoundException, IOException {
		guardaObjeto nuevo = new guardaObjeto(elegidos);
		nuevo.guardar();
	}

	public ArrayList<Desayuno> getDesayunos() {
		return desayunos;
	}

}
