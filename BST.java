import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V extends Comparable<V>> {
    private Node root;

    public BST() {
        root = null;
    }

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private Node putHelper(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {
            node.left = putHelper(node.left, key, value);
        } else if (comparison > 0) {
            node.right = putHelper(node.right, key, value);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            V smallestValue = findSmallestValue(node.right);
            K keyOfSmallestValue = getKey(smallestValue);
            node.key = keyOfSmallestValue;
            node.value = smallestValue;
            node.right = deleteHelper(node.right, keyOfSmallestValue);
        }

        return node;
    }

    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(Node node, K key) {
        if (node == null) {
            return null;
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {
            return getHelper(node.left, key);
        } else if (comparison > 0) {
            return getHelper(node.right, key);
        } else {
            return node.value;
        }
    }

    public K getKey(V value) {
        return getKeyHelper(root, value);
    }

    private K getKeyHelper(Node node, V value) {
        if (node == null) {
            return null;
        }

        int comparison = value.compareTo(node.value);

        if (comparison < 0) {
            return getKeyHelper(node.left, value);
        } else if (comparison > 0) {
            return getKeyHelper(node.right, value);
        } else {
            return node.key;
        }
    }

    public void delete(K key) {
        root = deleteHelper(root, key);
    }

    private Node deleteHelper(Node node, K key) {
        if (node == null) {
            return null;
        }

        int comparison = key.compareTo(node.key);

        if (comparison < 0) {
            node.left = deleteHelper(node.left, key);
        } else if (comparison > 0) {
            node.right = deleteHelper(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            V smallestValue = findSmallestValue(node.right);
            K keyOfSmallestValue = getKey(smallestValue);
            node.key = keyOfSmallestValue;
            node.value = smallestValue;
            node.right = deleteHelper(node.right, keyOfSmallestValue);
        }

        return node;
    }

    private V findSmallestValue(Node node) {
        if (node.left == null) {
            return node.value;
        }
        return findSmallestValue(node.left);
    }

    public boolean containsValue(V value) {
        return containsValueHelper(root, value);
    }

    private boolean containsValueHelper(Node node, V value) {
        if (node == null) {
            return false;
        }
        int comparison = value.compareTo(node.value);
        if (comparison == 0) {
            return true;
        }
        return containsValueHelper(node.left, value) || containsValueHelper(node.right, value);
    }

    public int size() {
        return sizeHelper(root);
    }

    private int sizeHelper(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    public Iterable<K> iterator() {
        return (Iterable<K>) new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        private Node current;
        private Stack<Node> stack;
        private Stack<K> keyStack;

        public BSTIterator() {
            current = root;
            stack = new Stack<>();
            keyStack = new Stack<>();

            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }


        public boolean hasNext() {
            return !stack.isEmpty();
        }


        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node node = stack.pop();
            K key = node.key;
            keyStack.push(key);

            current = node.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            return key;
        }


        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
