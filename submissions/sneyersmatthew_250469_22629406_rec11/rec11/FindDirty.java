package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int binSearch(char [] a, char element, int low, int high) {
		if (high < low) 
			return -1;
		
		
		
		int mid = low + (high-low)/2;
		
		comparisons++;
		if (a[mid] == element) 
			return mid;
		
		
		
		if (a[mid] == '&' || a[mid] > element) 
			return binSearch(a, element, low, mid-1);
		

		return binSearch(a, element, mid+1, high);
			
		
	}
	
	public static int findInDirtyArray(char [] a, char element, int low, int high) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		
		comparisons++;
		if (!(a[high] == '&' || a[high] > element)) {
			if (a[high] == element)
				return high;
			
			return findInDirtyArray(a,element, low, high*2);
		}
		
		else {
			return binSearch(a, element, low, high);
		}
		

		
	}
	
	public static void main (String [] args) {
		char [] a = Utils.getDirtyCharArray();
		int n = a.length;
		
		char element = Utils.getRandChar();
		System.out.println("Searching for element <" + element + "> in a dirty array of size " + n);
		comparisons = 0;
		int pos = findInDirtyArray (a, element, 0, 1);
		if (pos != -1)
			System.out.println("Found element <" + element + "> at position " + pos);
		
		System.out.println("Used " + comparisons + " comparisons.");
	}
}