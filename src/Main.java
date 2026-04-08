import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        
        System.out.println(" Cargando diccionario");
        Dictionary dictionary = new Dictionary();
        dictionary.loadDictionary("../data/diccionario.txt");
        System.out.println();
        
        System.out.println("Recorrido in-order ");
        dictionary.printInOrder();
        System.out.println();

        System.out.println("Traducción de texto.txt ");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("../data/texto.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    System.out.println("Frances:  " + line);
                    System.out.println("Español:  " + dictionary.translateText(line));
                    System.out.println();
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("Error al leer texto.txt: " + e.getMessage());
        }
    }
}