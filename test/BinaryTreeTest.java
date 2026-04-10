import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    private BinaryTree<String, String> tree;

    @BeforeEach
    public void setUp() {
        tree = new BinaryTree<>();
    }

    @Test
    public void testInsertRaizCorrecta() {
        tree.insert("ballon", "balon");
        assertEquals("ballon", tree.getRoot().getData().getKey());
    }

    @Test
    public void testInsertHijoIzquierdo() {
        tree.insert("match", "partido");
        tree.insert("ballon", "balon"); 
        assertEquals("ballon", tree.getRoot().getLeft().getData().getKey());
    }

    @Test
    public void testInsertHijoDerecho() {
        tree.insert("ballon", "balon");
        tree.insert("stade", "estadio"); 
        assertEquals("stade", tree.getRoot().getRight().getData().getKey());
    }

    @Test
    public void testInsertDuplicadoNoAgrega() {
        tree.insert("but", "gol");
        tree.insert("but", "gol");
        assertNull(tree.getRoot().getLeft());
        assertNull(tree.getRoot().getRight());
    }

    @Test
    public void testSearchPalabraExiste() {
        tree.insert("joueur", "jugador");
        assertEquals("jugador", tree.search("joueur"));
    }

    @Test
    public void testSearchPalabraNoExiste() {
        tree.insert("joueur", "jugador");
        assertNull(tree.search("dragon"));
    }

    @Test
    public void testSearchEnArbolVacio() {
        assertNull(tree.search("ballon"));
    }

    @Test
    public void testSearchVariosElementos() {
        tree.insert("equipe", "equipo");
        tree.insert("arbitre", "arbitro");
        tree.insert("stade", "estadio");
        tree.insert("but", "gol");

        assertEquals("equipo", tree.search("equipe"));
        assertEquals("arbitro", tree.search("arbitre"));
        assertEquals("estadio", tree.search("stade"));
        assertEquals("gol", tree.search("but"));
    }
}
