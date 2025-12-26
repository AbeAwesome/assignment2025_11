package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	public static int countSorted (int [] sortedA, int element) {
		int low = findLow(sortedA, element, 0, sortedA.length-1);
		if (low==-1) {
			return 0; // could not be found, no occurrences
		}
		int high = findHigh(sortedA, element, low, sortedA.length-1);
		if (high==-1) {
			return 0; // could not be found, no occurrences
		}
		return high-low + 1;
	}
	
	public static int findLow(int[] sortedA, int element, int low, int high) {
		if (low>high) {
			return -1; // couldn't be found
		}
		int mid = (high+low)/2;
		comparisons++;
		if (sortedA[mid]==element) { // base case
			if (mid==0) { // is first value in array
				return mid;
			}
			if (sortedA[mid-1] != element) { // element to the left is not also target element
				return mid;
			}
			return findLow(sortedA, element, low, mid-1); // early return, keep searching left to find leftmost occurrence of element
		}
		if (sortedA[mid]<element) {
			return findLow(sortedA, element, mid+1, high); // binary search right side
		}
		return findLow(sortedA, element, low, mid-1); // binary search left side
	}
	
	public static int findHigh(int[] sortedA, int element, int low, int high) {
		if (low>high) {
			return -1; // couldn't be found
		}
		int mid = (high+low)/2;
		comparisons++;
		if (sortedA[mid]==element) { // base case
			if (mid==sortedA.length-1) { // last value in array
				return mid;
			}
			if (sortedA[mid+1] != element) { // element to the right is not also target element
				return mid;
			}
			return findHigh(sortedA, element, mid+1, high); // keep searching to the right to find rightmost occurrence of element
		}
		if (sortedA[mid]<element) { // binary search right side
			return findHigh(sortedA, element, mid+1, high);
		}
		return findHigh(sortedA, element, low, mid-1); // binary search left side
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