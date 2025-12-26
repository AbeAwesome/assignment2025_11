package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	// find the first and last instance of element in the array and use that to get the count (works because it is sorted)
	// **I know this algorithm is O(logn) as I had tested it (more in the readme.txt)**
	public static int countSorted (int[] sortedA, int element) {
		int firstIndex = binSearchExtreme(sortedA, element, 0, sortedA.length - 1, true);
		// if no element is found, then can immediately return 0 for the count
		if (firstIndex < 0)
			return 0;
		int lastIndex = binSearchExtreme(sortedA, element, firstIndex, sortedA.length - 1, false);
		return lastIndex - firstIndex + 1; // need +1 to include both of the endpts
	}
	
	// recursive binary search to get first or last occurrence
	// end determines which end to get, left with true or right end with false
	private static int binSearchExtreme(int[] sortedA, int element, int min, int max, boolean end) {
		if (max < min) {
			return -1;
		}
		int mid = min + (max - min) / 2;
		int sub = sortedA[mid] - element; // separated the subtraction
		comparisons++;
		if (sub < 0) {
			return binSearchExtreme(sortedA, element, mid + 1, max, end);
		}
		if (sub > 0) {
			return binSearchExtreme(sortedA, element, min, mid - 1, end);
		}
		
		// now we know mid index has element, but we don't know which end it is if any
		// so search left or right depending on end
		int foundExtreme; 
		if (end)
			foundExtreme = binSearchExtreme(sortedA, element, min, mid - 1, end);
		else 
			foundExtreme = binSearchExtreme(sortedA, element, mid + 1, max, end);
		
		// if nothing is found on that side, then we know we have found the extreme element at mid, so return it
		// otherwise, return the extreme index that we would've just found
		return (foundExtreme == -1) ? mid : foundExtreme;
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
		
		a = Utils.getRandIntArrayWithRepeats(n << 10, n << 10, element);
		Arrays.sort(a);
		comparisons = 0;
		System.out.printf("\n Counting in array of size %d with %d repeats\n", n << 10, n << 10);
		System.out.println("Element " + element + " occurs " + countSorted(a, element) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 41: 2 logn + 1]");
	}
}