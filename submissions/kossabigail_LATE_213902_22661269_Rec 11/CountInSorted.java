package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;	

	public static int countSorted (int [] sortedA, int element) {
		//TODO: implement this using recursion to run in time O(log N)
				// every time you perform comparison: increment `comparisons` variable
		
		
		//handles empty input
		if (sortedA == null || sortedA.length == 0) {      
			return 0;                                       
		}

		//finds first occurrence
		int first = firstIndex(sortedA, element, 0, sortedA.length - 1);   

		if (first == -1) {                                  
			return 0;                                       
		}

		//finds last occurrence
		int last = lastIndex(sortedA, element, 0, sortedA.length - 1);     

		//number of occurrences
		return last - first + 1;     
		
		//return 0;
	}
	

	
	// ADDED; finds first index
	private static int firstIndex(int[] a, int x, int left, int right) {    
		if (left > right) {                                                 
			return -1;                                                      
		}

		int mid = (left + right) / 2;                                       
		comparisons++;                                                      

		if (a[mid] == x) {                                                 
			// check if this is the first occurrence
			if (mid == 0 || a[mid - 1] != x) {                              
				return mid;                                                 
			}
			return firstIndex(a, x, left, mid - 1);                          
		}
		else if (a[mid] < x) {                                              
			return firstIndex(a, x, mid + 1, right);                         
		}
		else {                                                              
			return firstIndex(a, x, left, mid - 1);                         
		}
	}
	
	// helper; finds last index
	private static int lastIndex(int[] a, int x, int left, int right) {     
		if (left > right) {                                                 
			return -1;                                                     
		}

		int mid = (left + right) / 2;                                      
		comparisons++;                                                   

		if (a[mid] == x) {                                                
			// check if this is the last occurrence
			if (mid == a.length - 1 || a[mid + 1] != x) {                 
				return mid;                                                 
			}
			return lastIndex(a, x, mid + 1, right);                         
		}
		else if (a[mid] < x) {                                            
			return lastIndex(a, x, mid + 1, right);                        
		}
		else {                                                           
			return lastIndex(a, x, left, mid - 1);                           
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