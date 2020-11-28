package phonebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Main {

    public static List<String> names = new ArrayList<>();
    public static List<String> numbers = new ArrayList<>();
    public static List<String> phoneFind;

    public static void initialize() {
        Path pathPhoneFind = Paths.get("C:\\Users\\A593191\\OneDrive - Atos\\Desktop\\IDEA files\\find.txt");
        Path pathPhoneBookList = Paths.get("C:\\Users\\A593191\\OneDrive - Atos\\Desktop\\IDEA files\\PhoneList.txt");
        try {
            phoneFind = Files.readAllLines(pathPhoneFind);

            List<String> phonebook = Files.readAllLines(pathPhoneBookList);
            phonebook.forEach( line -> {
                numbers.add(line.substring(0, line.indexOf(" ")));
                names.add(line.substring(line.indexOf(" ") + 1));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long linearAlgorithmRun() {
        System.out.println("Start searching (linear search)...");
        LinearSearch linearSearch = new LinearSearch();
        long startSearch = System.currentTimeMillis();
        int search = linearSearch.search(names, phoneFind);
        long endSearch = System.currentTimeMillis();
        System.out.println("Found "+search+ " / 500 entries. "+Time.calculateTime(endSearch - startSearch));
        return endSearch - startSearch;
    }

    public static void jumpSearchAndJumpSearchAlgorithmsRun (long linearTime) {
        System.out.println("\nStart searching (bubble sort + jump search)...");
        JumpingBubbles jumpingBubbles = new JumpingBubbles();
        jumpingBubbles.setTimeLimit(linearTime);
        long startSorting = System.currentTimeMillis();
        jumpingBubbles.sort(names);
        long endSorting = System.currentTimeMillis();

        long startSearch = System.currentTimeMillis();
        long endSearch;
        int search = jumpingBubbles.search(names, phoneFind);
        if ( search == 0) {
            int counter = new LinearSearch().search(names, phoneFind);
            endSearch = System.currentTimeMillis();
            System.out.println("Found "+counter+" / 500 entries. "+Time.calculateTime((endSorting - startSorting) + (endSearch - startSearch)));
            System.out.println("Sorting time: "+Time.calculateTime(endSorting - startSorting)+" - STOPPED, moved to linear search");
            System.out.println("Searching time: "+Time.calculateTime(endSearch - startSearch));
        } else {
            jumpingBubbles.search(names,phoneFind);
            endSearch = System.currentTimeMillis();
            System.out.println("Found "+search+" / 500 entries. "+Time.calculateTime((endSorting - startSorting) + (endSearch - startSearch)));
            System.out.println("Sorting time: "+Time.calculateTime(endSorting - startSorting));
            System.out.println("Searching time: "+Time.calculateTime(endSearch - startSearch));
        }
    }

    public static void quickSortAndBinarySearchjumpAlgorithmsRun() {
        System.out.println("\nStart searching (quick sort + binary search)...");
        QuickBinary quickBinary = new QuickBinary();
        long startSorting = System.currentTimeMillis();
        quickBinary.sort(names);
        long endSorting = System.currentTimeMillis();
        long startSearch = System.currentTimeMillis();
        int search = quickBinary.search(names, phoneFind);
        long endSearch = System.currentTimeMillis();
        System.out.println("Found "+search+" / 500 entries. "+Time.calculateTime((endSorting - startSorting) + (endSearch - startSearch)));
        System.out.println("Sorting time: "+Time.calculateTime(endSorting - startSorting));
        System.out.println("Searching time: "+Time.calculateTime(endSearch - startSearch));
    }

    public static void hashTableAlgorithmRun() {
        System.out.println("\nStart searching (hash table)...");
        HashTableSearch hashTableSearch = new HashTableSearch();
        hashTableSearch.setNumbers(numbers);
        long startCreating = System.currentTimeMillis();
        hashTableSearch.sort(names);
        long endCreating = System.currentTimeMillis();
        long startSearch = System.currentTimeMillis();
        int search = hashTableSearch.search(names, phoneFind);
        long endSearch = System.currentTimeMillis();
        System.out.println("Found "+search+" / 500 entries. "+Time.calculateTime((endCreating - startCreating) + (endSearch - startSearch)));
        System.out.println("Creating time: "+Time.calculateTime(endCreating - startCreating));
        System.out.println("Searching time: "+Time.calculateTime(endSearch - startSearch));
    }

    public static void main(String[] args) {
        initialize();
        long linearTime = linearAlgorithmRun();
        jumpSearchAndJumpSearchAlgorithmsRun(linearTime);
        quickSortAndBinarySearchjumpAlgorithmsRun();
        hashTableAlgorithmRun();
    }
}
