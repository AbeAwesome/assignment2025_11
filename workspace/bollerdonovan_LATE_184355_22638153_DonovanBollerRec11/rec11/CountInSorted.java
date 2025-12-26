package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	public static int countSorted (int [] sortedA, int element) {
		//TODO: implement this using recursion to run in time O(log N)
		// every time you perform comparison: increment `comparisons` variable
		int left = findLeft(sortedA, 0, sortedA.length - 1, element);
        int right = findRight(sortedA, 0, sortedA.length - 1, element);

        if (left == -1)
        	return 0;
        return right-left+1;
        	
	}
	
	public static int findLeft (int [] sortedA, int low, int high, int element) {
		if (low > high) 
			return -1;
		int mid = low + (high-low)/2;
		comparisons++;
		if (sortedA[mid] < element)
			return findLeft(sortedA, mid+1, high, element);
		
		if (sortedA[mid] > element)
			return findLeft(sortedA, low, mid-1, element);
		
		if (mid == 0 || sortedA[mid - 1] != element)
            return mid;

        return findLeft(sortedA, low, mid - 1, element);
	}
	
	public static int findRight (int [] sortedA, int low, int high, int element) {
		if (low > high) 
			return -1;
		int mid = low + (high-low)/2;
		comparisons++;
		if (sortedA[mid] < element)
			return findRight(sortedA, mid+1, high, element);
		
		if (sortedA[mid] > element)
			return findRight(sortedA, low, mid-1, element);
		
		if (mid == sortedA.length - 1 || sortedA[mid + 1] != element)
            return mid;

        return findRight(sortedA, mid + 1, high, element);
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