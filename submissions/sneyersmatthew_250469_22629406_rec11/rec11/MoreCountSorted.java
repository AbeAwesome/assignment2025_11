package rec11;

import java.util.Arrays;

public class MoreCountSorted {
	public static int comparisons = 0;	
	
	
	public static int binRight (int[] sortedA, int element, int low, int high) {
		int mid = low + (high-low)/2;

		if (low > high){
			return high;
		}
		
		comparisons++;
		if (sortedA[mid] <= element) {
			return binRight(sortedA, element,  mid + 1, high);
		}
		
		return binRight(sortedA, element, low, mid - 1);

	}
	
	public static int binLeft (int[] sortedA, int element, int low, int high) {
		int mid = low + (high-low)/2;
		

		if (low > high){
			return low;
		}
		
		comparisons++;
		if (sortedA[mid] >= element) {
			return binLeft(sortedA, element, low, mid - 1);
		}
		

		return binLeft(sortedA, element,  mid + 1, high);

	}
	
	public static int countSorted (int [] sortedA, int element, int low, int high) {
		//TODO: implement this using recursion to run in time O(log N)
		// every time you perform comparison: increment `comparisons` variable
		
		
		return binRight(sortedA, element, low, high) - binLeft(sortedA, element, low, high) + 1;
		
	}
	
	public static void main (String [] args) {
		int n = 10;
		int [] a = Utils.getRandIntArray(n, 10);
		Arrays.sort(a);
		System.out.println("Input array of size  " + n + ": "+ Utils.arrToString(a));
		
		// test correctness first
		for (int i=0; i < a.length; i++)
			System.out.println("Element " + a[i] + " occurs " + countSorted(a, a[i], 0, a.length-1) + " times.");
		
		System.out.println("Element -3 occurs " + countSorted(a, -3, 0, a.length-1) + " times.");
		System.out.println("Element 100 occurs " + countSorted(a, 100, 0, a.length-1) + " times.");
		
		n = 1024;
		// now see if the complexity is log n
		int element = -3;
		a = Utils.getRandIntArray(n, 10);
		Arrays.sort(a);
		comparisons = 0;
		System.out.println("\n Counting in array of size 1024: item not found");
		System.out.println("Element " + element + " occurs " + countSorted(a, element, 0, a.length-1) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 21: 2 logn + 1]");
		
		a = Utils.getRandIntArrayWithRepeats(n, 512, element);
		Arrays.sort(a);
		comparisons = 0;
		System.out.println("\n Counting in array of size 1024 with 512 repeats");
		System.out.println("Element " + element + " occurs " + countSorted(a, element, 0, a.length-1) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 19: 2 logn + 1]");
		
		a = Utils.getRandIntArrayWithRepeats(n, 1024, element);
		Arrays.sort(a);
		comparisons = 0;
		System.out.println("\n Counting in array of size 1024 with 1024 repeats");
		System.out.println("Element " + element + " occurs " + countSorted(a, element, 0, a.length-1) + " times.");
		System.out.println("Total: " + comparisons + " comparisons. [cannot be more than 21: 2 logn + 1]");
	}
}