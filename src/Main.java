import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static void runProgram() {
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

    static void runProfiling() {
        System.out.println("\nProfiliing");
        System.out.println("Cantidad | Insert (ms) | Search (ms)");
        System.out.println("---------|-------------|------------");

        int[] cantidades = {100, 1000, 5000, 10000};

        for (int n : cantidades) {
            BinaryTree<String, String> tree = new BinaryTree<>();

            long inicio = System.nanoTime();
            for (int i = 0; i < n; i++) {
                tree.insert("palabra" + i, "traduccion" + i);
            }
            long tiempoInsert = (System.nanoTime() - inicio) / 1_000_000;

            inicio = System.nanoTime();
            for (int i = 0; i < n; i++) {
                tree.search("palabra" + i);
            }
            long tiempoSearch = (System.nanoTime() - inicio) / 1_000_000;

            System.out.printf("%8d | %11d | %11d%n", n, tiempoInsert, tiempoSearch);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 3) {
            System.out.println("  Diccionario Francés-Español");
            System.out.println("-------------------------------");
            System.out.println("1. Ejecutar programa principal");
            System.out.println("2. Ejecutar profiling del BST");
            System.out.println("3. Salir");
            System.out.print("Elige una opcion: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        runProgram();
                        break;
                    case 2:
                        runProfiling();
                        break;
                    case 3:
                        System.out.println("\nHasta luego!");
                        break;
                    default:
                        System.out.println("\nOpcion invalida. Intenta de nuevo.");
                }

            } else {
                System.out.println("\nPor favor ingresa un numero.");
                scanner.next();
            }

            System.out.println();
        }

        scanner.close();
    }
}