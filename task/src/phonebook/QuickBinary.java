package phonebook;

import java.util.List;

public class QuickBinary implements SortAlgorithm, SearchAlgorithm {
    private List<String> sortedNameList;

    @Override
    public void sort(List<String> names) {
        sortedNameList = names;
        quickSort(sortedNameList,0,sortedNameList.size()-1);
    }

    private void quickSort(List<String> list, int left, int right) {
        if (left < right) {
        String pivot = list.get(right);
            int b = left;

            for (int i = left; i < right; i++) {
                if (list.get(i).compareTo(pivot) < 0) {
                    swap(list, i, b);
                    b++;
                }
            }
            swap(list, b, right);
            quickSort(list, left, b - 1);
            quickSort(list, b + 1, right);
        }
    }

    private void swap(List<String> list,int indexA, int indexB) {
       String temp = list.get(indexA);
       list.set(indexA,list.get(indexB));
       list.set(indexB,temp);
    }

    @Override
    public int search(List<String> phoneBookList, List<String> findList) {
        int counter = 0;
        for (int i = 0; i < findList.size(); i++) {
            String valueToSearch = findList.get(i);
            int left = 0;
            int right = phoneBookList.size()-1;
            int middle;
            do {
                middle = (left+right)/2;
                if (phoneBookList.get(middle).equals(valueToSearch)) {
                    counter++;
                    break;
                } else if (valueToSearch.compareTo(phoneBookList.get(middle)) > 0) {
                    left = middle + 1;
                } else {
                    right = middle -1;
                }
            }while(left <= right);
        }
        return counter;
    }
}



