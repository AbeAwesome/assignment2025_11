package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	public static int countSorted (int [] sortedA, int element) {
		//TODO: implement this using recursion to run in time O(log N)
		// every time you perform comparison: increment `comparisons` variable
		
		
//		if (sortedA.length == 1) {
//			comparisons++;
//			if (sortedA[0] == element) return 1;
//		}
		int first = getFirstOccurrence(sortedA, element, 0, sortedA.length - 1);
		if (first == -1) return 0;
		int last = getLastOccurrence(sortedA, element, 0, sortedA.length - 1);
		
		
		
		return last-first +1;
	}
	
	private static int getFirstOccurrence(int[] sortedA, int element, int start, int end) {
		// returns the index of the first occurrence of element in sortedA
		if (start > end) {return -1;}
		
		int middle = start + (end-start)/2;
		
//		System.out.println("Middle: " + middle + " | Start : " + start + " | End: " + end);

		comparisons++;
		if (sortedA[middle] == element || sortedA[middle] > element) { // check left side then
			if (middle==start) {return middle;} // first occurrence ( because it is the only value
			if (sortedA[middle - 1] < element) return middle;// first occurrence (because previous is less than target)
			return getFirstOccurrence(sortedA, element, start, middle-1); // search left half
		} else { // check right side
			return getFirstOccurrence(sortedA, element, middle +1, end);
		}
		
	}
	
	private static int getLastOccurrence(int[] sortedA, int element, int start, int end) {
		// returns the index of the first occurrence of element in sortedA
		if (start > end) {return -1;}
		
		int middle = start + (end-start)/2;
		
//		System.out.println("Middle: " + middle + " | Start : " + start + " | End: " + end);

		comparisons++;
		if (sortedA[middle] == element || sortedA[middle] < element) { 
			if (middle==end) {return middle;} 
			if (sortedA[middle + 1] > element) return middle;
			return getLastOccurrence(sortedA, element, middle + 1, end); 
		} else { // check right side
			return getLastOccurrence(sortedA, element, start, middle - 1);
		}
		
	}
	
	public static void main (String [] args) {
		int n = 10;
		int [] a = Utils.getRandIntArray(n, 10);
		Arrays.sort(a);
		System.out.println("Input array of size  " + n + ": "+ Utils.arrToString(a));
		
		// test correctness first
		for (int i=0; i < a.length; i++)
			System.out.println("Element " + a[i] + " occurs " + countSorted(a, a[i]) + " times.");
		
		System.out.println("Element -3 occurs " + countSorted(a, -3) + " times.");
		System.out.println("Element 100 occurs " + countSorted(a, 100) + " times.");
		
		n = 1024;
		// now see if the complexity is log n
		int element = -3;
		a = Utils.getRandIntArray(n, 10);
		Arrays.sort(a);
		comparisons = 0;
		System.out.println("\n Counting in array of size 1024: item not found");
		System.out.println("Element " + element + " occurs " + countSorted(a, element) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 21: 2 logn + 1]");
		
		a = Utils.getRandIntArrayWithRepeats(n, 512, element);
		Arrays.sort(a);
		comparisons = 0;
		System.out.println("\n Counting in array of size 1024 with 512 repeats");
		System.out.println("Element " + element + " occurs " + countSorted(a, element) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 19: 2 logn + 1]");
		
		a = Utils.getRandIntArrayWithRepeats(n, 1024, element);
		Arrays.sort(a);
		comparisons = 0;
		System.out.println("\n Counting in array of size 1024 with 1024 repeats");
		System.out.println("Element " + element + " occurs " + countSorted(a, element) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 21: 2 logn + 1]");
	}
}