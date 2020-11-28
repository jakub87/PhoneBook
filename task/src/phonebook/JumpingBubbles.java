package phonebook;

import java.util.List;

public class JumpingBubbles implements SearchAlgorithm, SortAlgorithm{
    private long timeLimit;
    private List <String> sortedNameList;
    private boolean sortedIsFinished = true;

    @Override
    public void sort(List<String> names) {
        sortedNameList = names;
        long startSort = System.currentTimeMillis();
        long endSort;

        for (int i = 0; i < sortedNameList.size(); i++) {
            for (int j = 0; j < sortedNameList.size()-1-i; j++) {
                if (sortedNameList.get(j).compareTo(sortedNameList.get(j + 1)) > 0 ) {
                    String temp = sortedNameList.get(j);
                    sortedNameList.set(j,sortedNameList.get(j+1));
                    sortedNameList.set(j+1,temp);
                }
            }
            endSort = System.currentTimeMillis();
            if (endSort - startSort > timeLimit * 10) {
                sortedIsFinished = false;
                break;
            }
        }
    }

    @Override
    public int search(List<String> phoneBookList, List<String> findList) {
        int count = 0;
        if (!sortedIsFinished) {
            return 0;
        } else {
            for (String searchValue: findList) {
                if (jumpSearch(phoneBookList.toArray(String[]::new),searchValue)) {
                    count++;
                }
            }
            return count;
        }
    }

    private boolean jumpSearch(String array[], String value) {
        int leftBorder = 0, rightBorder = 0;
        if (array[leftBorder].contains(value)) {
            return true;
        }
        int jump = (int) Math.sqrt(array.length);
        while(rightBorder < array.length -1) {
            leftBorder = rightBorder+1;
            rightBorder = Math.min(rightBorder + jump, array.length-1);
            if (array[rightBorder].contains(value)) {
                return true;
            } else if (array[rightBorder].compareTo(value) > 0) {
                //lineral search
                for (int i = rightBorder-1 ; i >= leftBorder ; i--) {
                    if (array[i].contains(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }
}
