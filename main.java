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
