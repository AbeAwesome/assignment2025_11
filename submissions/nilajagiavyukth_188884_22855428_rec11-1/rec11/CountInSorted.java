package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	public static int cs_sub(int[] sortedA, int l, int r, int target, boolean start) {
		if (l > r) {
			return -1;
		}
		
		int mid = (l + r) / 2;
		
		if (start) {
			if (sortedA[mid] == target) {
				if (mid-1 < 0 || sortedA[mid-1] < target) {
					return mid;
				}
				else {
					return cs_sub(sortedA, l, mid-1, target, start);
				}
			}
			else if (sortedA[mid] > target) {
				return cs_sub(sortedA, l, mid-1, target, start);
			}
			else if (sortedA[mid] < target) {
				return cs_sub(sortedA, mid+1, r, target, start);
			}
		} else {
			if (sortedA[mid] == target) {
				if (mid + 1 >= sortedA.length || sortedA[mid+1] > target) {
					return mid;
				}
				else {
					return cs_sub(sortedA, mid+1, r, target, start);
				}
			}
			else if (sortedA[mid] > target) {
				return cs_sub(sortedA, l, mid-1, target, start);
			}
			else if (sortedA[mid] < target) {
				return cs_sub(sortedA, mid+1, r, target, start);
			}
		}
		
		return -1;
	}
	
	public static int countSorted (int [] sortedA, int element) {
		//TODO: implement this using recursion to run in time O(log N)
		// every time you perform comparison: increment `comparisons` variable
		int start_idx = cs_sub(sortedA, 0, sortedA.length - 1, element, true);
		int end_idx = cs_sub(sortedA, 0, sortedA.length - 1, element, false);
		
		if (start_idx == -1 || end_idx == -1) {
			return 0;
		}
		
		return (end_idx - start_idx) + 1;		
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