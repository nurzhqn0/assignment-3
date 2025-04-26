import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        This is Binary Search Tree
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

//        This is HashTable
        MyHashTable<MyTest, Student> table = new MyHashTable<>(100);

        Random rnd = new Random();

        for (int i = 0; i < 10_000; i++) {
            int id   = rnd.nextInt(1_000_000);
            String name = "Name" + rnd.nextInt(1_000_000);
            MyTest key   = new MyTest(id, name);
            Student val  = new Student("Student" + i);
            table.put(key, val);
        }

        int[] counts = new int[table.getM()];
        for (int i = 0; i < table.getM(); i++) {
            MyHashTable<MyTest, Student>.HashNode<MyTest, Student> node = table.getChainArray()[i];
            while (node != null) {
                counts[i]++;
                node = node.getNext();
            }
        }

        System.out.println("bucket distribution (10 000 entries):");
        for (int i = 0; i < counts.length; i++) {
            System.out.printf("Bucket %2d: %4d elements%n", i, counts[i]);
        }
    }
}