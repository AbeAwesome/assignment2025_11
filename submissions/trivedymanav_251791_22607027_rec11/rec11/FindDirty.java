package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char[] a, char element) {
		// special cases for less than 2 elements as the upper bound search assumes there is more than one element (at least one)
		// not technically needed as the array should be large, ("infinite")
		if (a.length < 2) {
			if (a.length == 1 && a[0] == element)
				return 0;
			return -1;
		}
		
		// call the recursive function with the upper bound found (ub-1 as we know ub is actually an & by the ub search, so can safely reduce it)
		return binSearch(a, element, 0, upperBoundBinSearch(a, 1) - 1);
	}
	
	/**
	 * Standard binary search for char specifically (assumes sorted) but must also consider the &
	 * @param a Array to search
	 * @param element Element to find
	 * @param min Smallest element to consider
	 * @param max Largest element to consider
	 * @return The index of an element in an array or -1 if not found (assumes the array is sorted)
	 */
	private static int binSearch(char[] a, char element, int min, int max) {
		if (min > max)
			return -1;
		int mid = min + (max - min) / 2;
		comparisons++;
		if (a[mid] > element || a[mid] == '&')
			return binSearch(a, element, min, mid - 1);
		else if (a[mid] < element)
			return binSearch(a, element, mid + 1, max);
		return mid;
	}
	
	/**
	 * Gets the upper bound of the sorted portion recursively, O(logn)
	 * @param a Array to find the upper bound for
	 * @param index Index to consider
	 * @return Returns the upper bound for search in the sorted portion of the array (doesn't have to be exactly the end)
	 */
	private static int upperBoundBinSearch(char[] a, int index) {
		// base case is if spills over end of array, then just select the last element
		if (index >= a.length)
			return a.length - 1;
		comparisons++;
		// if &, we know we shot past the end of the sorted portion
		// in a real array, we actually wouldn't know if it was in the sorted or unsorted portion that way
		// so must check if the elements are in correct order, but that never comes up in this specific problem as we use &
		if (a[index] == '&' || a[index] < a[index - 1]) // stops when reaching the unsorted portion (for our purposes it is the &)
			return index;
		return upperBoundBinSearch(a, index * 2); // multiply index by 2 to get O(logn) rather than O(n)
	}
	
	public static void main (String [] args) {
		char [] a = Utils.getDirtyCharArray();
		for (char c : a)
			System.out.print(c);
		System.out.println();
		int n = a.length;
		
		for (int i = 0; i < 10; i++) {
			comparisons = 0;
			char element = Utils.getRandChar();
			System.out.println("Searching for element <" + element + "> in a dirty array of size " + n);
			comparisons = 0;
			int pos = findInDirtyArray (a, element);
			if (pos != -1)
				System.out.println("\tFound element <" + element + "> at position " + pos);
			else
				System.out.println("\t<" + element + "> not found.");
			
			System.out.println("\tUsed " + comparisons + " comparisons.");
		}
	}
}