package dao;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import modelo.Desayuno;

public class escribeXml {

	private ArrayList<Desayuno> elegidos;
	
	
	public escribeXml(ArrayList<Desayuno> elegidos) {
		this.elegidos = elegidos;
	}
	public void escribir() throws ParserConfigurationException, TransformerException {
		Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element breakfast_menu = documento.createElement("breakfast_menu");
		documento.appendChild(breakfast_menu);
		
		for(Desayuno d : elegidos) {
			Element food = documento.createElement("food");
			Attr atributo = documento.createAttribute("numCarta");
			atributo.setTextContent(String.valueOf(d.getNumCarta()));
			food.setAttributeNode(atributo);
			
			Element name = documento.createElement("name");
			name.setTextContent(d.getName());
			
			Element price = documento.createElement("price");
			price.setTextContent(String.valueOf(d.getPrice()));
			
			Element description = documento.createElement("description");
			description.setTextContent(d.getDescription());
			
			Element calories = documento.createElement("calories");
			calories.setTextContent(String.valueOf(d.getCalories()));
			
			food.appendChild(name);
			food.appendChild(price);
			food.appendChild(description);
			food.appendChild(calories);
			
			breakfast_menu.appendChild(food);
		}
		
		
		JFileChooser nuevo = new JFileChooser();
		nuevo.showDialog(nuevo, "Escribe y elige la ruta donde guardarlo");
		File destino = nuevo.getSelectedFile();
		
		TransformerFactory transformerF = TransformerFactory.newInstance();
		Transformer transformer = transformerF.newTransformer();
		transformer.transform(new DOMSource(documento), new StreamResult(destino));
		
	}
}
