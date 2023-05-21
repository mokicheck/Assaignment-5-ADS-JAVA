# Assaignment-5-ADS-JAVA

---

# Main [Link](main.java)

```java

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    putKeyValue(tree, scanner);
                    break;
                case 2:
                    getValueByKey(tree, scanner);
                    break;
                case 3:
                    removeByKey(tree, scanner);
                    break;
                case 4:
                    checkValueExists(tree, scanner);
                    break;
                case 5:
                    getKeyByValue(tree, scanner);
                    break;
                case 6:
                    getSize(tree);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            System.out.println();
        }

        System.out.println("Good Bye!");
    }

    public static void displayMenu() {
        System.out.println("Enter your choice:");
        System.out.println("1. Put (key, value)\n2. Get value by key\n3. Remove by key\n4. Check if value exists\n5. Get key by value\n6. Get size of Tree");
        System.out.println("7. Exit");
    }

    public static void putKeyValue(BST<Integer, String> tree, Scanner scanner) {
        System.out.println("Enter key:");
        int key = scanner.nextInt();
        System.out.println("Enter value:");
        String value = scanner.next();
        tree.put(key, value);
        System.out.println("Value inserted.");
    }

    public static void getValueByKey(BST<Integer, String> tree, Scanner scanner) {
        System.out.println("Enter key:");
        int key = scanner.nextInt();
        String result = tree.get(key);

        if (result != null) {
            System.out.println("Value: " + result);
        } else {
            System.out.println("Key not found.");
        }
    }

    public static void removeByKey(BST<Integer, String> tree, Scanner scanner) {
        System.out.println("Enter key:");
        int key = scanner.nextInt();
        String removedValue = tree.get(key);
        tree.delete(key);

        if (removedValue != null) {
            System.out.println("Removed value: " + removedValue);
        } else {
            System.out.println("Key not found.");
        }
    }

    public static void checkValueExists(BST<Integer, String> tree, Scanner scanner) {
        System.out.println("Enter value:");
        String value = scanner.next();
        boolean contains = tree.containsValue(value);
        System.out.println("Value exists: " + contains);
    }

    public static void getKeyByValue(BST<Integer, String> tree, Scanner scanner) {
        System.out.println("Enter value:");
        String value = scanner.next();
        Integer foundKey = tree.getKey(value);

        if (foundKey != null) {
            System.out.println("Key: " + foundKey);
        } else {
            System.out.println("No such value.");
        }
    }

    public static void getSize(BST<Integer, String> tree) {
        int size = tree.size();
        System.out.println("Size equals to: " + size);
    }
}
```

The provided code is a simple console-based application that allows the user to interact with a binary search tree (BST). Here's a brief description of the code:

- The code begins by importing the necessary classes and defining the `main` method in the `Main` class.
- Inside the `main` method, a `BST<Integer, String>` object named `tree` is created to hold key-value pairs, and a `Scanner` object named `scanner` is created to read user input.
- The `exit` variable is set to `false` to control the main loop of the program.
- The program enters a `while` loop that continues until the user chooses to exit.
- In each iteration of the loop, the `displayMenu` method is called to show the available options to the user.
- The user's choice is read using the `nextInt` method of the `scanner` object.
- Based on the user's choice, a corresponding method is called to perform the desired operation on the binary search tree.
- The program includes several methods such as `putKeyValue`, `getValueByKey`, `removeByKey`, `checkValueExists`, `getKeyByValue`, and `getSize` that handle the specific operations chosen by the user.
- After executing the selected operation, the program prints an empty line for better readability.
- If the user chooses option 7, the `exit` variable is set to `true`, and the program exits the main loop.
- Finally, a "Good Bye!" message is displayed to the user.

Overall, this code provides a basic console interface for interacting with a binary search tree by allowing the user to insert key-value pairs, retrieve values by keys, remove entries by keys, check if a value exists, retrieve a key by value, and get the size of the tree.


---

# BST [Link](BST.java)

```java
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

```

The provided code is an implementation of a binary search tree (BST) in Java. Here's a brief description of the code:

- The code defines a generic class `BST` that represents a binary search tree.
- The `BST` class uses generics to allow keys (`K`) and values (`V`) of different types as long as they implement the `Comparable` interface, which enables comparison between objects of those types.
- The `BST` class has a private inner class `Node` that represents a node in the binary search tree. Each node contains a key, a value, and references to its left and right child nodes.
- The `BST` class also has an instance variable `root` that holds the reference to the root node of the tree.
- The class provides methods such as `put`, `get`, `delete`, `getKey`, `containsValue`, and `size` to perform common operations on the binary search tree.
- The `put` method inserts a key-value pair into the tree. If the key already exists, the method updates the value associated with the key.
- The `get` method retrieves the value associated with a given key.
- The `delete` method removes a node with the specified key from the tree.
- The `getKey` method retrieves the key associated with a given value.
- The `containsValue` method checks if a given value exists in the tree.
- The `size` method returns the number of nodes in the tree.
- The `BST` class also includes a nested private class `BSTIterator` that implements the `Iterator` interface for iterating over the keys in ascending order.
- The iterator follows an in-order traversal of the tree, maintaining a stack to track the current node and its left children.
- The `hasNext` method of the iterator checks if there are more keys to iterate over.
- The `next` method returns the next key in the iteration.
- The `remove` method is not implemented and throws an `UnsupportedOperationException`.

Overall, this code provides a basic implementation of a binary search tree with functionality for inserting, retrieving, deleting, and iterating over key-value pairs.

# Iterator [Link](Iterator.java)

```java
public interface Iterator<E>{
    E next();
    boolean hasNext();
    void remove();
}
```
The provided code is an interface called `Iterator<E>`, which defines the contract for iterating over a collection of elements of type `E`. Here's a brief description of the code:

- The `Iterator<E>` interface declares three methods: `next()`, `hasNext()`, and `remove()`.
- The `next()` method is responsible for returning the next element in the iteration. The specific type of the element is defined by the type parameter `E`.
- The `hasNext()` method checks if there are more elements available in the iteration. It returns a boolean value indicating whether there is a next element.
- The `remove()` method is responsible for removing the current element from the collection being iterated. The specific behavior of this method may vary depending on the implementation.

In summary, the `Iterator<E>` interface provides a standardized way to iterate over a collection of elements, allowing the retrieval of the next element, checking for the presence of more elements, and the optional removal of elements from the collection. Implementations of this interface are responsible for defining the specific behavior for these methods based on the underlying data structure or collection being iterated.
