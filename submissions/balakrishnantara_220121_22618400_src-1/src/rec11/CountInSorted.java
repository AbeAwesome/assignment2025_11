package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	public static int countSorted (int [] sortedA, int element) {
		

		
		
		int leftIndex = findLeftIndex(sortedA, element, 0, sortedA.length-1);
		if (leftIndex == -1) {
			return 0;
		}
		
		int rightIndex = findRightIndex(sortedA, element, 0, sortedA.length-1);

		return rightIndex - leftIndex + 1;		
	}
	
	// use to find left-most index of specific element
	private static int findLeftIndex(int [] sortedA, int element, int left, int right) {
		if (right < left || element < sortedA[left] || element > sortedA[right]) {
			return -1;
		}
		int mid = left + (right - left)/2;
		comparisons++;
		if (sortedA[mid] < element) {
			return findLeftIndex(sortedA, element, mid+1, right); 
		} else if (sortedA[mid]> element) {
			return findLeftIndex(sortedA, element, 0, mid -1);
		} else { //  if sortedA[mid] == element
			int l = findLeftIndex(sortedA, element, 0, mid -1);
			return (l == -1)? mid: l; // if l equals -1, return mid, else return l
		}

	}
	// use to find right-most index of specific element
	private static int findRightIndex(int [] sortedA, int element, int left, int right) {
		if (right < left || element < sortedA[left] || element > sortedA[right]) {
			return -1;
		}
		int mid = left + (right - left)/2;
		comparisons++;
		if (sortedA[mid] > element) {
			return findRightIndex(sortedA, element, 0, mid-1);
		}
		if (sortedA[mid] < element) {
			return findRightIndex(sortedA, element, mid+1, right);
		}
		else {
			int r = findRightIndex(sortedA, element, mid+1, right);
			return (r == -1)? mid: r; // if r equals -1 return mid, else return -1
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