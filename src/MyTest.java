public class MyTest {
    private int id;
    private String name;

    public MyTest(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int hashCode(){
        int hash = 17;
        hash = 31 * hash + id;
        for(char c : name.toCharArray()) {
            hash = 31 * hash + c;
        }
        return hash;
    }

    @Override
    public String toString() {
        return "MyTest{id=" + id + ", name='" + name + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTest other = (MyTest) obj;
        return id == other.id && name.equals(other.name);
    }
}