public class Main {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(50, "root");
        tree.put(30, "left child");
        tree.put(70, "right child");
        tree.put(20, "left left child");
        tree.put(40, "left right child");
        tree.put(60, "right left child");
        tree.put(80, "right right child");

        System.out.println("get 30: " + tree.get(30));
        System.out.println("get 70: " + tree.get(70));
        System.out.println("get 100 (not exist): " + tree.get(100));

        System.out.println("\ninorder: ");
        for (var key : tree.iterator()) {
            System.out.println(key);
        }

        System.out.println("\ndelete 30");
        tree.delete(30);

        System.out.println("\nafter deletion 30");
        for (var key : tree.iterator()) {
            System.out.println(key);
        }
    }
}