import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST <K extends Comparable<K>, V> {
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getVal() {
            return val;
        }

        public void setVal(V val) {
            this.val = val;
        }
    }

    private Node root;
    private int size = 0;


    public Iterable<K> iterator() {
        return () -> new Iterator<K>() {
            private final Stack<Node> stack = new Stack<>();
            private Node current = root;

            @Override
            public boolean hasNext() {
                return current != null || !stack.isEmpty();
            }

            @Override
            public K next() {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                if (stack.isEmpty()) {
                    throw new NoSuchElementException();
                }

                Node node = stack.pop();
                K key = node.key;
                current = node.right;

                return key;
            }
        };
    }

    public void put(K key, V val){
        if(key == null || val == null){
            throw new IllegalArgumentException("Key or value cannot be null");
        }

        if (root == null){
            root = new Node(key, val);
            size++;
            return;
        }

        Node current = root;

        while(true){
            int cmp = key.compareTo(current.key);
            if (cmp < 0){
                if (current.left == null){
                    current.left = new Node(key, val);
                    size++;
                    return;
                }
                current = current.left;
            }

            else if (cmp > 0){
                if (current.right == null){
                    current.right = new Node(key, val);
                    size++;
                    return;
                }
                current = current.right;
            }

            else {
                current.val = val;
                return;
            }
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        Node current = root;

        while(current != null){
            int cmp = key.compareTo(current.key);

            if (cmp < 0){
                current = current.left;
            } else if (cmp > 0){
                current = current.right;
            } else {
                return current.val;
            }
        }

        return null;
    }

    public void delete(K key){
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        Node parent = null;
        Node current = root;

        while(current != null){
            int cmp = key.compareTo(current.key);

            if (cmp < 0){
                parent = current;
                current = current.left;
            } else if (cmp > 0){
                parent = current;
                current = current.right;
            } else{
                break;
            }
        }

        if(current == null){
            throw new NoSuchElementException("Key not found");
        }

        if (current.left == null && current.right == null) {
            if (parent == null) {
                root = null;
            } else if (parent.left == current) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            size--;
        } else if (current.left == null || current.right == null) {
            Node child = (current.left != null) ? current.left : current.right;
            if (parent == null) {
                root = child;
            } else if (parent.left == current) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            size--;
        } else {
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.key = successor.key;
            current.val = successor.val;

            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
            size--;
        }
    }
}
