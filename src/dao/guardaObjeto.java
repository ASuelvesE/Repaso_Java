package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import modelo.Desayuno;

public class guardaObjeto {

	private ArrayList<Desayuno> elegidos;
	private String destino;
	
	public guardaObjeto(ArrayList<Desayuno> elegidos) {
		this.elegidos = elegidos;
	}
	public void guardar() throws FileNotFoundException, IOException {
		JFileChooser nuevo = new JFileChooser();
		nuevo.showDialog(nuevo, "Escribe y elige la ruta donde guardarlo");
		File elegido = nuevo.getSelectedFile();
		destino = elegido.getAbsolutePath();

		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destino));
			
			os.writeObject(elegidos);
			os.close();
	}
}
