public class MyHashTable<K, V> {
    public class HashNode<K, V> {
        private K key;
        private V value;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        this(11);
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) {
        if(key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % M;
    }

    private void put(K key, V value){
        if(key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }

        int hash = hash(key);
        chainArray[hash] = new HashNode<>(key, value);
        size++;
    }

    private V get(K key){
        if(key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int hash = hash(key);
        HashNode<K, V> node = chainArray[hash];

        if(node == null){
           return null;
        }

        return node.value;
    }

    private V remove(K key){
        if(key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int hash = hash(key);
        HashNode<K, V> node = chainArray[hash];

        if(node == null){
            return null;
        }

        if(node.key.equals(key)){
            V value = node.value;
            chainArray[hash] = null;
            size--;
        }

        return node.value;
    }

    public boolean contains(K key){
        if(key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int hash = hash(key);
        HashNode<K, V> node = chainArray[hash];

        if(node == null){
            return false;
        }

        return node.key.equals(key);
    }

    public K getKey(V value){
        if(value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        for(HashNode<K, V> node : chainArray){
            if(node != null && node.value.equals(value)){
                return node.key;
            }
        }

        return null;
    }
}
