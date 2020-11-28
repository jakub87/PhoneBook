package phonebook;


import java.util.Objects;

public class HashTable {
    private String [] keys;
    private String [] values;
    private int size;

    public HashTable(int size) {
        this.size = size;
        keys = new String[size];
        values = new String[size];
    }

    public void add(String key, String value) {
        int pos = Math.abs(key.hashCode() % size);
        while (keys[pos % size] != null) {
            pos++;
        }
        keys[pos % size] = key;
        values[pos % size] = value;
    }

    public boolean find(String elem) {
        int pos = Math.abs(elem.hashCode() % size);
        while (!Objects.equals(keys[pos % size], elem) && keys[pos % size] != null) {
            pos++;
        }
        return Objects.equals(keys[pos % size], elem);
    }

}
