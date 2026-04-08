import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {

    private BinaryTree<String, String> tree;

    public Dictionary() {
        tree = new BinaryTree<>();
    }

    public void loadDictionary(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("(") && line.endsWith(")")) {
                    line = line.substring(1, line.length() - 1);
                    String[] parts = line.split(",");

                    if (parts.length == 2) {
                        String french = parts[0].trim().toLowerCase();
                        String spanish = parts[1].trim().toLowerCase();
                        tree.insert(french, spanish);
                    }
                }
            }

            reader.close();
            System.out.println("Diccionario cargado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public String translate(String word) {
        boolean startsUpper = Character.isUpperCase(word.charAt(0));
        String result = tree.search(word.toLowerCase());

        if (result != null) {
            if (startsUpper) {
                return Character.toUpperCase(result.charAt(0)) + result.substring(1);
            }
            return result;
        } else {
            return "*" + word + "*";
        }
    }

    public String translateText(String text) {
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String punctuation = "";

            if (!word.isEmpty()) {
                char last = word.charAt(word.length() - 1);
                if (last == '.' || last == ',' || last == '!' || last == '?') {
                    punctuation = String.valueOf(last);
                    word = word.substring(0, word.length() - 1);
                }
            }

            if (!word.isEmpty()) {
                result.append(translate(word));
            }
            result.append(punctuation);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public void printInOrder() {
        tree.inOrder();
    }

    public BinaryTree<String, String> getTree() {
        return tree;
    }
}