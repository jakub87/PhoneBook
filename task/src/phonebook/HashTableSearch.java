package phonebook;

import java.util.Hashtable;
import java.util.List;

public class HashTableSearch implements SortAlgorithm,SearchAlgorithm{
    private Hashtable<String,String> hashtable = new Hashtable<>();
    private List<String> numbers;

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    @Override
    public int search(List<String> phoneBookList, List<String> findList) {
        int counter = 0;

        for (String value: findList) {
            if (hashtable.containsKey(value)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public void sort(List<String> names) {
        for (int i = 0; i < names.size(); i++) {
            hashtable.put(names.get(i),names.get(i));
        }
    }
}
