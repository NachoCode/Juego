package preguntas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Random;

public class Preguntas {

	private RandomAccessFile file;
	private final static int espacioEntrePregunta = 200;
	private final static int espacioDeRespuesta = 100;
	private File archivo = new File("src/preguntas/FicheroPreguntas.dat");

	// Numeros aleatorios sin repeticion
	private ArrayList<Integer> list = new ArrayList<Integer>();
	private Random rand = new Random(System.nanoTime());

	public void crearFichero() throws IOException {

		if (!archivo.exists()) {
			archivo.createNewFile();
			file = new RandomAccessFile(archivo, "rw");
		} else {
			file = new RandomAccessFile(archivo, "rw");

		}

		file.close();
	}

	public void escribirFichero() throws FileNotFoundException {
		file = new RandomAccessFile(archivo, "rw");
		ArrayList<String> listaPreguntas = new ArrayList<String>();

		try {
			System.out.println("Escribiendo...");

			file.seek(1 * espacioEntrePregunta);
			file.writeUTF("Cuál es la descripción que define mejor el concepto “clase” en POO?");

			file.seek(1 * (espacioEntrePregunta + espacioDeRespuesta));
			file.writeUTF("Es un modelo o plantilla a partir de la cuál creamos objetos");

			file.seek(2 * espacioEntrePregunta);
			file.writeUTF("Que elementos crees que definen un objeto?");
			file.seek(2 * (espacioEntrePregunta + espacioDeRespuesta));
			file.writeUTF("Sus atributos y sus métodos");

			// file.seek(2 * espacioEntrePregunta);
			// file.writeUTF("¿Que código de las siguientes tiene que ver con la herencia?");
			// file.seek(2 * (espacioEntrePregunta + espacioDeRespuesta));
			// file.writeUTF("public class componente extends producto");
			//
			// file.seek(3 * espacioEntrePregunta);
			// file.writeUTF("¿Que significa instanciar una clase?");
			// file.seek(3 * (espacioEntrePregunta + espacioDeRespuesta));
			// file.writeUTF("Crear un objeto a partir de la clase.");
			//
			// file.seek(4 * espacioEntrePregunta);
			// file.writeUTF("En Java, ¿a qué nos estamos refiriendo si hablamos de “Swing”?");
			// file.seek(4 * (espacioEntrePregunta + espacioDeRespuesta));
			// file.writeUTF("Una librería para construir interfaces graficas");
			file.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String leerFichero(int i) throws IOException {
		file = new RandomAccessFile(archivo, "rw");
		file.seek(i * (espacioEntrePregunta + espacioDeRespuesta));
		System.out.println(file.readUTF());
		return file.readUTF();
	}

	public void hacerNumerosAleatorios() {
		int numeroAleatorio;
		do {
			numeroAleatorio = rand.nextInt(5);

			if (list.isEmpty()) {
				list.add(numeroAleatorio);
			} else {
				if (list.contains(numeroAleatorio)) {
					numeroAleatorio = rand.nextInt(5);
				} else {
					list.add(numeroAleatorio);
				}
			}

		} while (list.size() < 5);

	}

	public ArrayList<Integer> getLista() {
		return this.list;
	}

}
