package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	public static int countSorted (int [] sortedA, int element) {
		int first = findLB(sortedA, element, 0, sortedA.length);
		int last = findUB(sortedA, element, first, sortedA.length);
		return last - first;	
	}
	
	private static int findUB(int[] a, int target, int low, int high) {
		if (low == high) 
		{
			return low;
		}
		
		int mid = low + (high - low) / 2;
		comparisons++;
		
		if (a[mid] <= target) 
		{
			return findUB(a, target, mid + 1, high);
		} else {
			return findUB(a, target, low, mid);
		}
	}
	
	private static int findLB(int[] a, int target, int low, int high) {
		if (low == high) 
		{
			return low;
		}
		
		int mid = low + (high - low) / 2;
		comparisons++; 
		if (a[mid] < target) 
		{
			return findLB(a, target, mid + 1, high);
		} else {
			return findLB(a, target, low, mid);
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