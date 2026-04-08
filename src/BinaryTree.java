public class BinaryTree<K extends Comparable<K>, V> {

    private BSTNode<Association<K, V>> root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(K key, V value) {
        Association<K, V> newAssoc = new Association<>(key, value);
        root = insertRec(root, newAssoc);
    }

    private BSTNode<Association<K, V>> insertRec(BSTNode<Association<K, V>> node,
            Association<K, V> assoc) {
        if (node == null) {
            return new BSTNode<>(assoc);
        }

        int cmp = assoc.getKey().compareTo(node.getData().getKey());

        if (cmp < 0) {
            node.setLeft(insertRec(node.getLeft(), assoc));
        } else if (cmp > 0) {
            node.setRight(insertRec(node.getRight(), assoc));
        }

        return node;
    }

    public V search(K key) {
        return searchRec(root, key);
    }

    private V searchRec(BSTNode<Association<K, V>> node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.getData().getKey());

        if (cmp == 0) {
            return node.getData().getValue();
        } else if (cmp < 0) {
            return searchRec(node.getLeft(), key);
        } else {
            return searchRec(node.getRight(), key);
        }
    }

    public void inOrder() {
        System.out.print("Diccionario en orden: ");
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(BSTNode<Association<K, V>> node) {
        if (node == null)
            return;
        inOrderRec(node.getLeft());
        System.out.print(node.getData() + " ");
        inOrderRec(node.getRight());
    }

    public BSTNode<Association<K, V>> getRoot() {
        return root;
    }
}