package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		
		int i=1;
		
		while (i < a.length && a[i] != '&') {
			comparisons++;
			i = 2 * i;
		}
		
		int clean = i;
		
		return binarySearch(a, 0, clean-1, element);
	}
	
	public static int binarySearch(char [] a, int low, int high, char element) {
		if (low > high)
			return -1;
		
		int mid = low + (high - low) / 2;
		
		comparisons ++;
		
		if (a[mid] == element)
			return mid;
		
		if (element < a[mid] || a[mid] == '&')
			return binarySearch(a,low, mid-1, element);
		
		// element > a[mid]
		return binarySearch(a,mid+1, high, element);
	}
	
	public static void main (String [] args) {
		char [] a = Utils.getDirtyCharArray();
		int n = a.length;
		
		char element = Utils.getRandChar();
		System.out.println("Searching for element <" + element + "> in a dirty array of size " + n);
		comparisons = 0;
		int pos = findInDirtyArray (a, element);
		if (pos != -1)
			System.out.println("Found element <" + element + "> at position " + pos);
		
		System.out.println("Used " + comparisons + " comparisons.");
	}
}