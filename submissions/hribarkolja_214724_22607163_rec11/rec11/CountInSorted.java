package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	public static int countSorted (int [] sortedA, int element) {
		//TODO: implement this using recursion to run in time O(log N)
		// every time you perform comparison: increment `comparisons` variable
		 int n = sortedA.length;
	     if (n == 0) 
	    	 return 0;

	     int first;

	     comparisons++;
	     if (sortedA[0] > element)
	            return 0;
	        
	        if (sortedA[0] == element)
	            first = 0;
	        else
	            first = firstTime(sortedA, 0, n - 1, element);
	        
	        if (first == -1) 
	        	return 0;
	        
	        int last = upperBound(sortedA, first + 1, n, element);

	        return last - first;
	    }

	    private static int firstTime(int[] A, int low, int high, int element) {
	        if (low > high) return -1;

	        int mid = low + (high - low) / 2;

	        comparisons++;

	        if (A[mid] < element)
	            return firstTime(A, mid + 1, high, element);
	        else if (A[mid] > element)
	            return firstTime(A, low, mid - 1, element);
	        else {
	            if (mid == 0)
	                return mid;

	            comparisons++;
	            if (A[mid - 1] == element)
	                return firstTime(A, low, mid - 1, element);
	            else
	                return mid;
	        }
	    }

	    private static int upperBound(int[] A, int low, int high, int element) {
	        if (low >= high) return low;

	        int mid = low + (high - low) / 2;

	        comparisons++;

	        if (A[mid] <= element)
	            return upperBound(A, mid + 1, high, element);
	        else
	            return upperBound(A, low, mid, element);
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