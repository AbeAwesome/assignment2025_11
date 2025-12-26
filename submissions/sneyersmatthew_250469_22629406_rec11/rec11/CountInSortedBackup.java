package rec11;

import java.util.Arrays;

public class CountInSortedBackup {
public static int comparisons = 0;	
	
	
	public static int binLeft (int[] sortedA, int element, int low, int high) {
		int mid = low + (high-low)/2;
		
		if (low == mid || high == mid){//(high - low <= 1) {
			comparisons++;
			if (sortedA[low] == element) {
				comparisons++;
				return low;
			}
			else if (sortedA[high] == element){
				comparisons+=2;
				return high;
			}
			else {
				comparisons+=2;
				return high+1;			
			}
		}
		
		else if (element == sortedA[mid]) {
			comparisons+=2;
			return binLeft(sortedA, element, low, mid - 1);
		}
		
		else {
			//one off
			comparisons+=2;
			return binLeft(sortedA, element,  mid + 1, high);
		}
		

	}
	
	public static int binRight (int[] sortedA, int element, int low, int high) {
		int mid = low + (high-low)/2;
		

		if (low == mid || high == mid){//(high - low <= 1) {
			comparisons++;
			if (sortedA[high] == element) {
				comparisons++;
				return high;
			}
			else if (sortedA[low] == element){
				comparisons+=2;
				return low;
			}
			else {
				comparisons+=2;
				return low-1;
			}
		}
		
		
		//if (high <= low)
			//return mid;
			
		
		else if (element != sortedA[mid]) {
			comparisons +=2;
			return binRight(sortedA, element, low, mid - 1);
		}
		
		else {
			comparisons+=2;
			// one off
			return binRight(sortedA, element,  mid + 1, high);
		}
	}
	
	public static int countSorted (int [] sortedA, int element, int low, int high) {
		//TODO: implement this using recursion to run in time O(log N)
		// every time you perform comparison: increment `comparisons` variable
		comparisons++;
		if (high < low)
			return 0;
			
		int mid = low + (high-low)/2;
		
		if (element == sortedA[mid]) {
			comparisons++;
			return binRight(sortedA, element, mid, sortedA.length-1) - binLeft(sortedA, element, 0, mid) + 1;
		}
		
		else if (element < sortedA[mid ]) {
			comparisons+=2;
			return countSorted(sortedA,element,  low, mid - 1);
		}
		
		else {
			comparisons+=2;
			return countSorted(sortedA, element,  mid + 1, high);
		}
		
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