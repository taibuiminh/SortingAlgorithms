/*** Name of class or program (SortingAlgorithms)
 * * COMP 2140 SECTION D01
 * * INSTRUCTOR    Matheson (D01)
 * * ASSIGNMENT  2
 * * Tai Bui Minh 7877396
 * * 12/06/2020
 * ** PURPOSE: sorting algorithms
 * */
import java.util.*;
import java.io.*;


public class SortingAlgorithms {

    // Control the testing
    private static final int ARRAY_SIZE = 10000;
    private static final int SAMPLE_SIZE = 300; // The number of trials in each experiment.

    // Control the randomness
    private static final int NUM_SWAPS = ARRAY_SIZE / 2;
    private static Random generator = new Random( System.nanoTime() );

    // Control the base cases for hybrid quick sort:
    private static final int BREAKPOINT = 50;

    // Controls which sort is tried.
    private static final int INSERTION_SORT = 0;
    private static final int BUBBLE_SORT = 1;
    private static final int SELECTION_SORT = 2;
    private static final int MERGE_SORT = 3;
    private static final int QUICK_SORT = 4;
    private static final int HYBRID_QUICK_SORT = 5;
    private static final int SHELL_SORT = 6;

/*********** main and the method it calls *************************/

    /*******************************************************************
     * main
     *
     * Purpose: Print out "bookend" messages and call the method that
     *          does all the testing of the sorting algorithms.
     *
     ******************************************************************/
    public static void main( String[] args ) {
        System.out.println( "\n\nCOMP 2140 A2Q1 Sorting Test --- Summer 2020\n" );

        testSorts();

        System.out.println( "\nProcessing ends normally\n" );
    } // end main


    /*******************************************************************
     * testSorts
     *
     * Purpose: Run each sorting algorithm SAMPLE_SIZE times,
     *          on an array of size ARRAY_SIZE with NUM_SWAPS
     *          random swaps performed on it.
     *          Compute the arithmetic mean of the timings for each sorting algorithm.
     *
     *          Print the results.
     *
     ******************************************************************/
    private static void testSorts() {

        // Arrays used in timing experiments (create these arrays once)
        int[] array = new int[ARRAY_SIZE]; // array to be sorted
        long[] sortTime = new long[ SAMPLE_SIZE ]; // store timings for multiple runs
        // of a single sorting method
        // Fill array to be sorted with the numbers 0 to ARRAY_SIZE.
        // (The numbers will be randomly swapped before each sort.)
        fillArray( array );

        // Now run the experiments on all the sorts
        System.out.println("Array size: " + ARRAY_SIZE + "\nNumber of swaps: " + NUM_SWAPS);
        System.out.println("Number of trials of each sort: " + SAMPLE_SIZE );

        // Stats for each run
        System.out.println("\nInsertion sort mean: "
                + tryOneSort( array, sortTime, INSERTION_SORT )
                + " ns" );
        System.out.println("Bubble sort mean: "
                + tryOneSort( array, sortTime, BUBBLE_SORT )
                + " ns" );
        System.out.println("Selection sort mean: "
                + tryOneSort( array, sortTime, SELECTION_SORT )
                + " ns" );
        System.out.println("Merge sort mean: "
                + tryOneSort( array, sortTime, MERGE_SORT )
                + " ns" );
        System.out.println("Quick sort mean: "
                + tryOneSort( array, sortTime, QUICK_SORT )
                + " ns" );
        System.out.println("Hybrid quick sort mean: "
                + tryOneSort( array, sortTime, HYBRID_QUICK_SORT )
                + " ns" );
        System.out.println("Shell sort mean: "
                + tryOneSort( array, sortTime, SHELL_SORT )
                + " ns" );

    } // end testSorts

/*********** methods called by testSorts *************************/

    /*******************************************************************
     * tryOneSort:
     *
     * Purpose: Get an average run time for a sorting algorithm.
     *
     * Methodology: Run the chosen sorting algorithm SAMPLE_SIZE times,
     *          on an array of size ARRAY_SIZE with NUM_SWAPS
     *          random swaps performed on it.
     *          Return the arithmetic mean of the timings.
     *
     ******************************************************************/
    private static double tryOneSort( int[] array, long[] sortTime, int whichSort ) {

        long start, stop, elapsedTime;  // Time how long each sort takes.

        start = stop = 0; // because the compiler complains that they might
        // not have been initialized inside the for-loop

        for ( int i = 0; i < SAMPLE_SIZE; i++ ) {
            randomizeArray( array, NUM_SWAPS );
            if ( whichSort == INSERTION_SORT ) {
                start = System.nanoTime();
                insertionSort( array );
                stop = System.nanoTime();
                checkArray(array, "Insertion sort");
            } else if ( whichSort == BUBBLE_SORT ) {
                start = System.nanoTime();
                bubbleSort( array );
                stop = System.nanoTime();
                checkArray(array, "Bubble sort");
            } else if ( whichSort == SELECTION_SORT ) {
                start = System.nanoTime();
                selectionSort( array );
                stop = System.nanoTime();
                checkArray(array, "Selection sort");
            } else if ( whichSort == MERGE_SORT ) {
                start = System.nanoTime();
                mergeSort( array );
                stop = System.nanoTime();
                checkArray(array, "Merge sort");
            } else if ( whichSort == QUICK_SORT ) {
                start = System.nanoTime();
                quickSort( array );
                stop = System.nanoTime();
                checkArray(array, "Quick sort");
            } else if ( whichSort == HYBRID_QUICK_SORT ) {
                start = System.nanoTime();
                hybridQuickSort( array );
                stop = System.nanoTime();
                checkArray(array, "Hybrid quick sort");
            } else if ( whichSort == SHELL_SORT ) {
                start = System.nanoTime();
                shellSort( array );
                stop = System.nanoTime();
                checkArray(array, "Shell sort");
            }
            elapsedTime = stop - start;
            sortTime[i] = elapsedTime;
        } // end for

        return arithmeticMean( sortTime );
    } // end tryOneSort


/*************** Sorting methods *********************/


    /****************INSERTION SORT***********************
     *
     * Insertion sort is a sorting algorithm that treats the input as two parts, a sorted part and an unsorted part,
     * and repeatedly inserts the next value from the unsorted part into the correct location in the sorted part.
     *
     ****************************************************/

    //Insertion sort algorithm
    public static void insertionSort(int[] array) {
        insertionSort(array, 0, array.length -1);
    }

    //private helper insertion sort method
    private static void insertionSort(int[] array, int start, int end) {
        //variables for sorting
        int temp;
        int j;

        //assume that item at index 0 is sorted
        //then start to run our loop from second item
        for ( int i = start; i < end + 1; i++ ){
            //assign temp variable to the second item in the list
            temp = array[i];
            j = i-1;
            //while j is bigger than first index of the list and temp variable is less than previous one
            while ( j >= start && array[j] > temp ){
                //swap elements in the list
                array[j+1] = array[j];
                //swap j back to check all elements on that condition
                j--;
            }
            //swap elements in the list
            array[j+1] = temp;
        }
    }



    /****************SELECTION SORT***********************
     *
     * Selection sort is a sorting algorithm that treats the input as two parts,
     * a sorted part and an unsorted part, and repeatedly selects the proper next
     * value to move from the unsorted part to the end of the sorted part.
     *
     ****************************************************/
    //selection sort algorithm is consisted from main public method and private helper FindMin method
    public static void selectionSort(int[] array) {

        for(int i = 0; i < array.length; i++) {
            //we are going to find min start from the i index
            int index = findMin(array, i, array.length);

            int min = array[index];
            int currValue = array[i];
            array[index] = currValue;
            array[i] = min;
        }// end for

    }//selectionSort

    //finding index of the smallest value in array which is bounded
    private static int findMin( int[] array, int start, int end){
        int min = array[start];
        int index = start;
        for(int i = start; i < end; i++)
        {
            if(array[i] < min)
            {
                min = array[i];
                index = i;
            }
        }// end for
        return index;
    }


    /*******************MERGE SORT***********************
     *
     * Merge sort is a sorting algorithm that divides a list into two halves,
     * recursively sorts each half, and then merges the sorted halves to produce a sorted list.
     * The recursive partitioning continues until a list of 1 element is reached,
     * as list of 1 element is already sorted.
     *
     ***************************************************/
    public static void mergeSort(int[] array) {
        //Merge sort the (full) array a.
        int[] temp = new int[array.length]; //Get some scratch space
        mergeSort(array,0,array.length-1,temp); //Call the recursive version
    }

    //Merge sort a[start]..a[end], using temp[start]..temp[end] as scratch storage.
    private static void mergeSort(int[] array, int start, int end, int[] temp) {

        if(end <= start)
            return;

        //Copy
        System.arraycopy(array, start, temp, start,end - start + 1);

        //Sort two separate halves of temp, using merge sorts
        int mid = (start + end) / 2;

        mergeSort(temp, start, mid, array);
        mergeSort(temp,mid + 1, end, array);

        //Merge the two sorted pieces together
        merge(temp, start, mid, temp,mid + 1, end, array, start);
    }

    public static void merge(int[] arrayA, int startA, int endA,
                             int[] arrayB, int startB, int endB,
                             int[] entire, int startAll){
        int nextA = startA;
        int nextB = startB;
        int nextAll = startAll;
        //Do the main merge operation
        while(nextA <= endA && nextB <= endB) {
            if (arrayA[nextA] <= arrayB[nextB])
                entire[nextAll++] = arrayA[nextA++];
            else
                entire[nextAll++] = arrayB[nextB++];
        }// end while

        //Clean up any leftovers
        while(nextA <= endA) {
            entire[nextAll++] = arrayA[nextA++];
        }// end while
        while(nextB <= endB) {
            entire[nextAll++] = arrayB[nextB++];
        }// end while
    }


    /*******************BUBBLE SORT***********************
     *
     * Bubble sort is a sorting algorithm that iterates through a list,
     * comparing and swapping adjacent elements if the second element is less than the first element.
     ****************************************************/

    public static void bubbleSort(int array[]) {
        int size = array.length - 1;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size - i; j++) {
                if (array[j] > array[j + 1]) {
                    // swap arr[j+1] and arr[i]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }


    /*******************QUICK SORT***********************
     *
     * Quick Sort is a sorting algorithm that repeatedly partitions the input into
     * low and high parts (each part unsorted),
     * and then recursively sorts each of those parts.
     *
     ***************************************************/
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }


    private static void quickSort(int[] array, int start, int end){

        if(end < start + 1)
            return;

        int pivot = array[start]; //Just for convenience and clarity
        int first = start+1; //Process the rest from start+1
        int last = end;   //to max
        do {

            while(last >= first && array[last] > pivot)
                last--;

            while(last >= first && array[first] <= pivot)
                first++;


            if(last > first) swap(array, first, last);
        }
        while(last > first); //Keep going until they do cross over.

        swap(array, start, last); //Puts the pivot in the middle
        quickSort(array, start,last - 1); //Sort the bottom half
        quickSort(array,last + 1, end); //Sort the top half
    }




    /*******************SHELL SORT***********************
     *
     * Shell sort is a sorting algorithm that treats the input as a collection
     * of interleaved lists, and sorts each list individually with a variant of the insertion sort algorithm.
     *
     * Knuth’s sequence
     *
     ***************************************************/

    public static void shellSort(int array[]) {
        //variables for method
        int gap = 1;
        int temp;
        //while gap is less than the size
        while (gap <= array.length) {
            //Knuth’s sequence
            gap = gap * 3 + 1;
        }

        while (gap > 0){
            //outer loop
            for(int i = gap; i < array.length; i++){
                temp = array[i];
                int j = i;
                //inner loop
                while (j > gap - 1 && array[j -1] >= temp){
                    array[j] = array[j - gap];
                    j -= gap;
                }
                //swapping the items
                array[j] = temp;
            }
            //decrease the gap again
            gap = (gap - 1)/3;
        }
    }




    public static void hybridQuickSort( int[] array ) {

        hybridQuickSort( array, 0, array.length );
    }

    private static void hybridQuickSort( int[] array, int start, int end ) {
        int pivotPosition;

        //we are using insertion sort in case if difference between end and start is less than break point
        if ( (end - start) < BREAKPOINT ) {
            insertionSort( array, start, end-1 );
        } else { // Recursive case
            choosePivot( array, start, end );
            pivotPosition = partition( array, start, end );
            hybridQuickSort( array, start, pivotPosition );
            hybridQuickSort( array, pivotPosition+1, end );
        }
    }

    private static void choosePivot( int[] array, int start, int end ) {
        //start from choosing the mid point in the array
        int mid = start + (end - start) / 2;

        //if value at mid point is less than the first element
        if ( array[mid] <= array[start] ) {
            //the last element is less than mid value
            if ( array[end-1] <= array[mid] ) {
                //swap first value with the middle one
                swap( array, start, mid );
                //the last element is less than first value
            }else if ( array[end-1] <= array[start] ) {
                //swap fist wih the last one
                swap( array, start, end-1 );
            }
        }else {
            //if value at mid point is less than the last element
            if ( array[mid] <= array[end-1] ) {
                //swap first with the mid value
                swap( array, start, mid );
                //the last element is bigger than first value
            } else if ( array[start] <= array[end-1] ) {

                //swap first with the last
                swap( array, start, end-1 );
            }
        } // end if-else
    } // end choosePivot

    private static int partition( int[] array, int start, int end ) {
        int bigStart = start+1;
        int pivot = array[start];

        for ( int curr = start+1; curr < end; curr++ ) {
            if ( array[curr] < pivot ) {
                swap( array, bigStart, curr );
                bigStart++;
            }
        }
        swap( array, start, bigStart-1 );
        return bigStart-1;
    } // end partition

/****************** Other miscellaneous methods ********************/

    /*******************************************************************
     * swap
     *
     * Purpose: Swap the items stored in positions i and j in array.
     *
     ******************************************************************/
    private static void swap( int[] array, int i, int j ) {
        int temp = array[ i ];
        array[ i ] = array[ j ];
        array[ j ] = temp;
    } // end swap


    /*******************************************************************
     * isSorted
     *
     * Purpose: Return true if the input array is sorted into
     *          ascending order; return false otherwise.
     *
     * Idea: If every item is <= to the item immediately after it,
     *       then the whole list is sorted.
     *
     ******************************************************************/
    public static boolean isSorted( int[] array ) {
        boolean sorted = true;

        // Loop through all adjacent pairs in the
        // array and check if they are in proper order.
        // Stops at first problem found.
        for ( int i = 1; sorted && (i < array.length); i++ )
            sorted = array[i-1] <=  array[i];
        return sorted;
    } // end method isSorted

    /*******************************************************************
     * checkArray
     *
     * Purpose: Print an error message if array is not
     *          correctly sorted into ascending order.
     *          (If the array is correctly sorted, checkArray does nothing.)
     *
     ******************************************************************/
    private static void checkArray(int[] array, String sortType) {
        if ( !isSorted( array ) )
            System.out.println( sortType + " DID NOT SORT CORRECTLY *** ERROR!!" );
    }

    /*******************************************************************
     * fillArray
     *
     * Purpose: Fills the given array with the numbers 0 to array.length-1.
     *
     ******************************************************************/
    public static void fillArray( int[] array ) {

        for ( int i = 0; i < array.length; i++ ) {
            array[i] = i;
        } // end for

    } // end fillArray

    /*******************************************************************
     * randomizeArray
     *
     * Purpose: Does numberOfSwaps swaps of randomly-chosen positions
     *          in the given array.
     *
     ******************************************************************/
    public static void randomizeArray( int[] array, int numberOfSwaps ) {
        for ( int count = 0; count < numberOfSwaps; count++ ) {
            int i = generator.nextInt( array.length );
            int j = generator.nextInt( array.length );
            swap( array, i, j );
        }
    } // end randomizeArray


    /*******************************************************************
     * arithmeticMean
     *
     * Purpose: Compute the average of long values.
     *          To avoid long overflow, use type double in the computation.
     *
     ******************************************************************/
    public static double arithmeticMean(long data[]) {
        double sum = 0;
        for (int i = 0; i < data.length; i++)
            sum += (double)data[i];
        return sum / (double)data.length;
    } // end arithmeticMean

} // end class A2Q1SortingTemplate

/*******************************************************************
 *
 * REPORT
 *
 * Array size: 10000
 * Number of swaps: 5000
 * Number of trials of each sort: 300
 *
 * Insertion sort mean: 9515975.726666667 ns
 * Bubble sort mean: 9.995804910666667E7 ns
 * Selection sort mean: 1.750486619333333E7 ns
 * Merge sort mean: 936651.08 ns
 * Quick sort mean: 682101.98 ns
 * Hybrid quick sort mean: 512584.5366666667 ns
 * Shell sort mean: 3797355.15 ns
 *
 *
 * 1) Was insertion sort faster than selection sort? Why or why not?
 *
 * Insertion sort was faster over selection sort. In report we can see that the difference between those algorithms is huge.
 * insertion sort has 7 digit from the left of the decimal point. And
 * selection sort's running time has 8 digits
 * to the left of the decimal point. This algorithms are almost the same, they have outer loops which proceeds the same amount of unsorted items
 * but in insertion sort inner loop does not always have to process all of the sorted part.
 * In the same time selection sort's inner loop has to process all unsorted part.
 *
 * 2) Was quick sort faster than insertion sort? Why or why not?
 *
 * Definitely quick sort is faster than insertion sort.
 * Quick sort has O(n log n) running time. It gives us advantage over insertion sort which has O(n^2) running time.
 * Quick sort every time chooses the pivot, which makes this algorithm fit to different arrays of data.
 *
 * 3)Was hybrid quick sort faster than the quick sort? Why or why not?
 *
 * The advantage of the hybrid quick sort that it uses insertion sort on the small list of data. This point makes hybrid sort quicker than usual quick sort.
 *
 *
 * 4)Which sort would you recommend to others, and why would you recommend that one?
 *
 * In my opinion I would recommend hybrid quick sort because it is easy for implementation and it is one of the fastest algorithm
 *
 * 5)Which sort would you warn others against using, and why?
 *
 * Definitely avoid selection sort because of it's number of swaps. Selection Sort will win only in the case of the almost sorted list
 *
 *
 *
 ******************************************************************/
