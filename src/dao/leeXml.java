package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modelo.Desayuno;

public class leeXml {

	private ArrayList<Desayuno> desayunos;
	File archivoElegido;
	
	public leeXml(ArrayList<Desayuno> desayunos,File archivoElegido) {
		this.desayunos = desayunos;
		this.archivoElegido = archivoElegido;
	}
	
	public ArrayList<Desayuno> leer() throws SAXException, IOException, ParserConfigurationException {

		int numCarta = 0;
		String name = "";
		double price = 0.0;
		String description = "";
		int calories = 0;
		Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(archivoElegido);
		NodeList root = documento.getElementsByTagName("breakfast_menu");
		
		for(int i = 0; i<root.getLength();i++) {

			NodeList root_hijos = root.item(i).getChildNodes();
			
			for(int m = 0; m<root_hijos.getLength();m++) {
				if(root_hijos.item(m).hasAttributes()) {
					NamedNodeMap atributos = root_hijos.item(m).getAttributes();

					
					for(int a = 0 ; a<atributos.getLength();a++) {
						if(atributos.item(a).getNodeName().equals("numCarta")) {
							numCarta = Integer.parseInt(atributos.item(a).getTextContent());
						}
					}
				}
				if(root_hijos.item(m).getNodeType() == Node.ELEMENT_NODE) {
					NodeList food_hijos = root_hijos.item(m).getChildNodes();
					
					for(int h = 0; h<food_hijos.getLength(); h++) {
						if(food_hijos.item(h).getNodeType() == Node.ELEMENT_NODE) {
							if(food_hijos.item(h).getNodeName().equals("name")) {
								name = food_hijos.item(h).getTextContent();
							}
							if(food_hijos.item(h).getNodeName().equals("price")) {
								String aux = food_hijos.item(h).getTextContent();

								int indice = aux.indexOf("$");
								aux = aux.substring(indice +1);
								price = Double.valueOf(aux);
								
							}
							if(food_hijos.item(h).getNodeName().equals("description")) {
								description = food_hijos.item(h).getTextContent();
							}
							if(food_hijos.item(h).getNodeName().equals("calories")) {
								calories = Integer.parseInt(food_hijos.item(h).getTextContent());
							}
						}
					}
					desayunos.add(new Desayuno(numCarta,name,price,description,calories));
				}
			}
		}
		return desayunos;
	}
}
