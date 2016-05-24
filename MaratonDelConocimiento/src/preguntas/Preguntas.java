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
	private final static int espacioEntreTexto = 200;
	private File archivo = new File("src/preguntas/FicheroPreguntas.dat");
	private File archivo2 = new File("src/preguntas/Opciones.dat");
	// Numeros aleatorios sin repeticion
	private ArrayList<Integer> list;
	private Random rand = new Random(System.nanoTime());

	// Obtener preguntas
	private BufferedReader bReader;
	private ArrayList<String> listaPreguntas;
	// Obtener respuestas
	private ArrayList<String> listaOpciones;

	private final static int NUM_PREGUNTAS = 25;
	private final static int NUM_RESPUESTAS = NUM_PREGUNTAS * 3;

	public Preguntas() {
		list = new ArrayList<Integer>();
		listaPreguntas = new ArrayList<String>();
		listaOpciones = new ArrayList<String>();
	}

	public void crearFichero() {
		try {
			archivo.createNewFile();
			archivo2.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void escribirFichero() {
		try {
			int indexPregunta = 0;
			file = new RandomAccessFile(archivo, "rw");

			for (int i = 1; i <= NUM_PREGUNTAS; i++) {
				file.seek(i * espacioEntreTexto);
				file.writeUTF(listaPreguntas.get(indexPregunta));
				// System.out.println(listaPreguntas.get(indexPregunta));
				indexPregunta++;

			}

			file = new RandomAccessFile(archivo2, "rw");

			int indexOpciones = 0;
			for (int i = 1; i <= NUM_RESPUESTAS; i++) {
				file.seek(i * espacioEntreTexto);
				file.writeUTF(listaOpciones.get(indexOpciones));
				indexOpciones++;
			}

			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String ExtraerPregunta(int numeroAleatorio) {
		try {

			file = new RandomAccessFile(archivo, "rw");
			file.seek(numeroAleatorio * espacioEntreTexto);
			String pregunta = file.readUTF();
			file.close();
			return pregunta;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<String> ExtraerOpciones(int numeroAleatorio) {

		try {
			ArrayList<String> tempOP = new ArrayList<String>();

			int aux = numeroAleatorio * 3;
			int dis = 0;

			file = new RandomAccessFile(archivo2, "rw");
			for (int i = 1; i <= 3; i++) {
				file.seek((aux - dis) * espacioEntreTexto);
				tempOP.add(file.readUTF());
				dis++;
			}
			file.close();

			return tempOP;
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

			bReader = new BufferedReader(new FileReader(new File(
					"src/preguntas/respuestas")));

			linea = bReader.readLine();
			while (linea != null) {
				listaOpciones.add(linea);
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
			numeroAleatorio = rand.nextInt(NUM_PREGUNTAS) + 1;

			if (list.isEmpty()) {
				list.add(numeroAleatorio);
			} else {
				if (list.contains(numeroAleatorio)) {
					numeroAleatorio = rand.nextInt(NUM_PREGUNTAS) + 1;
				} else {
					list.add(numeroAleatorio);
				}
			}

		} while (list.size() < NUM_PREGUNTAS);

		return list;
	}

}
