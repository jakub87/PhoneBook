package phonebook;

import java.util.List;

public class LinearSearch implements SearchAlgorithm {

    @Override
    public int search(List<String> phoneBookList, List<String> findList) {
        int counter = 0;
        for (String searchValue : findList) {
            for (String allList: phoneBookList) {
                if (searchValue.equals(allList)) {
                   counter++;
                   break;
                }
            }
        }
        return counter;
    }
}
