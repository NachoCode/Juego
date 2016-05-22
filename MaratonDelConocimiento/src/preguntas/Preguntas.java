package preguntas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	private ArrayList<Integer> list;
	private Random rand = new Random(System.nanoTime());

	// Obtener preguntas
	private BufferedReader bReader;
	private ArrayList<String> listaPreguntas;

	public Preguntas() {
		list = new ArrayList<Integer>();
		listaPreguntas = new ArrayList<String>();
	}

	public void crearFichero() {
		try {
			if (!archivo.exists()) {
				archivo.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void escribirFichero() {
		try {
			int indexPregunta = 0;
			file = new RandomAccessFile(archivo, "rw");
			for (int i = 1; i <= 10; i++) {
				file.seek(i * espacioEntrePregunta);
				file.writeUTF(listaPreguntas.get(indexPregunta));
				indexPregunta += 4;
			}
			System.out.println(listaPreguntas.get(0));
			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String leerFichero(int numeroAleatorio) {
		try {
			file = new RandomAccessFile(archivo, "rw");
			file.seek(numeroAleatorio * espacioEntrePregunta);
			return file.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void obtenerPreguntas() {
		try {
			bReader = new BufferedReader(new FileReader(new File(
					"src/preguntas/preguntasMaraton")));

			String linea = bReader.readLine();

			while (linea != null) {
				listaPreguntas.add(linea);
				linea = bReader.readLine();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> hacerNumerosAleatorios() {
		int numeroAleatorio;
		do {
			numeroAleatorio = rand.nextInt(10);

			if (list.isEmpty()) {
				list.add(numeroAleatorio);
			} else {
				if (list.contains(numeroAleatorio)) {
					numeroAleatorio = rand.nextInt(10);
				} else {
					list.add(numeroAleatorio);
				}
			}

		} while (list.size() < 10);

		return list;
	}
}
