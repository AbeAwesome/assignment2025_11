package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;
	
	private static int getLeft (int [] a, int element, int low , int high) {
		if (low > high)
			return -1; // interval shrank - element not found
		int mid = (low + high)/2;
		comparisons ++;
		if (a[mid] == element) {
			if (mid == 0)  // first position of a
				return mid;
			if (a[mid-1] != element)  //found leftmost occurrence
				return mid;
			return getLeft (a, element, low, mid-1) ;  // continue searching for the leftmost occurrence 
		}
		
		// regular binary search
		if (element > a[mid])
			return getLeft (a, element, mid + 1, high) ;  
		
		// element < a[mid]
		return getLeft (a, element, low, mid-1) ;
	}
	
	private static int getRight (int [] a, int element, int low , int high) {
		if (low > high)
			return -1; // interval shrank - element not found
		int mid = (low + high)/2;
		comparisons ++;
		if (a[mid] == element) {
			if (mid == a.length - 1)  // last position of a
				return mid;
			if (a[mid+1] != element)  //found rightmost occurrence
				return mid;
			return getRight (a, element, mid+1, high) ;  // continue searching for the leftmost occurrence 
		}
		
		// regular binary search
		if (element > a[mid])
			return getRight (a, element, mid + 1, high) ;  
		
		// element < a[mid]
		return getRight (a, element, low, mid-1) ;
	}
	
	public static int countSorted (int [] sortedA, int element) {
		
		int leftIndex = getLeft (sortedA, element, 0, sortedA.length -1);
		if (leftIndex == -1)
			return 0;
		int rightIndex = getRight (sortedA, element, 0, sortedA.length -1);
		return (rightIndex - leftIndex + 1);
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