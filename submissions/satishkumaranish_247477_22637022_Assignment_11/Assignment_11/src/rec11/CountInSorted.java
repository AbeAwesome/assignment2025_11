package rec11;

import java.util.Arrays;

public class CountInSorted {
	public static int comparisons = 0;
	public static int low = 0;
	public static int high = -1;
	public static int mid = -1;
	
	public static int countSorted (int [] sortedA, int element) {
		//TODO: implement this using recursion to run in time O(log N)
		// every time you perform comparison: increment `comparisons` variable
		comparisons ++;
		if ((element < sortedA[0]) || (element > sortedA[sortedA.length - 1]))
		{
			return 0;
		}
		//binary search for any instance of element
//		if (pos == -1)
//		{
//			int low = 0;
//			int mid = sortedA[sortedA.length / 2];
//			int high = sortedA.length - 1;
//			while (pos == -1)
//			{
//				if (element == sortedA[mid])
//				{
//					pos = mid;
//				}
//				else if (element < mid)
//				{
//					high = mid;
//					mid = mid / 2;
//				}
//				else
//				{
//					low = mid;
//					mid += (high - low) / 2;
//				}
//			}
//		}
		//look before and after first sighting of element to count how often it appears in sorted array// WILL NOT BE RECURSIVE DONT USE.
		
		//try making smaller section of the array which contains all instances of element
		//can try using binary search-like methods to find the first and last occurrence of element
		
		//if value is not within bounds
		
		//if array length == 1
		if ((high - low) == 0)
		{
			if (sortedA[low] == element)
			{
				return 1;
			}
			high = -1;
			low = 0;
			return 0;
		}
		
		//first run must assign value to high
		if (high == -1)
		{
			high = sortedA.length - 1;
			comparisons = 0;
		}

		//if all values in array are element
		int count = 0;
		if (sortedA[low] == element)
		{
			if (sortedA[high] == element)
			{
				count = (high - low) + 1;
				high = -1;
				low = 0;
				return count;
			}
		}
		
		//assignment of value to mid (midpoint of range)
		mid = ((high - low) / 2) + low;
//		if (mid == low)
//		{
//			if (sortedA[high] == element)
//			{
//				count++;
//			}
//			if (sortedA[low] == element)
//			{
//				count++;
//			}
//			return count;
//		}
		
		//check where elements are compared to midpoint
		if (element == sortedA[mid])
		{
			//lower bound check
			int tempMid = mid;
			int tempHigh = high;
			high = mid;
			count += countSorted(sortedA, element);
			
			//upper bound check
			high = tempHigh;
			low = tempMid + 1;
			count += countSorted(sortedA, element);
			high = -1;
			low = 0;
			return count;
		}
		if (element < sortedA[mid])
		{
			high = mid;
			count = countSorted(sortedA, element);
			high = -1;
			low = 0;
			return count;
		}
		if (element > sortedA[mid])
		{
			low = mid + 1;
			count = countSorted(sortedA, element);
			high = -1;
			low = 0;
			return count;
		}
		
		System.out.println("Error Occured in method. please fix idiot");
		return -1;
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