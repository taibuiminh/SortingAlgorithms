# SORTING ALGORITHMS

In this program I was writing different sorting algorithms: Insertion Sort, Bubble Sort, Selection Sort, Merge Sort, Quick Sort, Hybrid Quick Sort, Shell Sort.

Each Test is properly tested and demonstrated.

After each Test output of the program provides timing which can help me to get better understanding of statistic of these algorithms.



## REPORT

* Array size: 10000
* Number of swaps: 5000
* Number of trials of each sort: 300

* Insertion sort mean: 9515975.726666667 ns
* Bubble sort mean: 9.995804910666667E7 ns
* Selection sort mean: 1.750486619333333E7 ns
* Merge sort mean: 936651.08 ns
* Quick sort mean: 682101.98 ns
* Hybrid quick sort mean: 512584.5366666667 ns
* Shell sort mean: 3797355.15 ns

## 1) Was insertion sort faster than selection sort? Why or why not?

 * Insertion sort was faster over selection sort. In report we can see that the difference between those algorithms is huge.
 * insertion sort has 7 digit from the left of the decimal point. And
 * selection sort's running time has 8 digits
 * to the left of the decimal point. This algorithms are almost the same, they have outer loops which proceeds the same amount of unsorted items
 * but in insertion sort inner loop does not always have to process all of the sorted part.
 * In the same time selection sort's inner loop has to process all unsorted part.


## 2) Was quick sort faster than insertion sort? Why or why not?

 * Definitely quick sort is faster than insertion sort.
 * Quick sort has O(n log n) running time. It gives us advantage over insertion sort which has O(n^2) running time.
 * Quick sort every time chooses the pivot, which makes this algorithm fit to different arrays of data.

## 3)Was hybrid quick sort faster than the quick sort? Why or why not?

 * The advantage of the hybrid quick sort that it uses insertion sort on the small list of data. This point makes hybrid sort quicker than usual quick sort.

## 4)Which sort would you recommend to others, and why would you recommend that one?

 * In my opinion I would recommend hybrid quick sort because it is easy for implementation and it is one of the fastest algorithm

## 5)Which sort would you warn others against using, and why?

 * Definitely avoid selection sort because of it's number of swaps. Selection Sort will win only in the case of the almost sorted list
 
 
 
 
