package dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import modelo.Desayuno;

public class guardaObjeto {

	private ArrayList<Desayuno> elegidos;
	private String destino;
	
	public guardaObjeto(ArrayList<Desayuno> elegidos) {
		this.elegidos = elegidos;
		this.destino = "src/serializado/elegidos.dat";
	}
	public void guardar() throws FileNotFoundException, IOException {
		
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(destino));
			
			os.writeObject(elegidos);
			os.close();
	}
}
