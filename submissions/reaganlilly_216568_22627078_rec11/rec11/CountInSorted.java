package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	
	
	public static int countSorted (int [] sortedA, int element) {
		//TODO: implement this using recursion to run in time O(log N)
        int first = findFirst(sortedA, element, 0, sortedA.length - 1);
        if (first == -1)
            return 0;
        int last = findLast(sortedA, element, 0, sortedA.length - 1);
        return last - first + 1;	
	}

    private static int findFirst(int[] a, int x, int low, int high) {
        if (low > high) 
            return -1; 
        int mid =(high + low)/2;
        comparisons++;   
        if (a[mid] == x) {
            if (mid == low || a[mid - 1] != x)
                return mid; 
            else 
                return findFirst(a, x, low, mid - 1);
        } 
        else if (a[mid] < x) 
            return findFirst(a, x, mid + 1, high);
        else 
            return findFirst(a, x, low, mid - 1);
    }

    private static int findLast(int[] a, int x, int low, int high) {
        if (low > high)
            return -1;
        int mid =(high + low)/2;
        comparisons++;
        if (a[mid] == x) {
            if (mid == high || a[mid + 1] != x)
                return mid;
            else 
                return findLast(a, x, mid + 1, high);
        }
        else if (a[mid] < x) 
            return findLast(a, x, mid + 1, high);
        else 
            return findLast(a, x, low, mid - 1);
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