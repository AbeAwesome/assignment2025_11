package rec11;

/* PLEASE READ: The solution below is the one given by the professor. However, my own attempt is also
 * here, below this code, commented out. I envisioned a similar approach to detecting the junk 
 * boundary initially, but then continuing to use a similar strategy after that to find the position
 * of the element in question. I was encountering numerous annoying stack overflow bugs as I worked, 
 * so after an hour of writing the code for this part of the assignment and another hour of trying 
 * to debug it, I ended work on my solution.*/

public class FindDirty {
	public static int comparisons = 0;
	
	private static int findJunkBoundary (char [] junkArr) {
		int i=1;
		
		//probe positions 2, 4, 8 ... until found junk
		while (i < junkArr.length && junkArr[i] != '&') {
			comparisons++;
			i = 2 * i;
		}
		
		return i;
	}
	
	private static int binarySearchInDirty (char [] a, char element, int low, int high) {
		if (low > high)
			return -1;
		
		int mid = (low + high)/2;
		
		comparisons ++;
		
		if (a[mid] == element)
			return mid;
		
		if (element < a[mid] || a[mid] == '&')
			return binarySearchInDirty (a, element, low, mid-1);
		
		// element > a[mid]
		return binarySearchInDirty (a, element, mid+1, high);		
	}
	
	public static int findInDirtyArray(char [] a, char element) {
		
		int m = findJunkBoundary(a);
		
		return binarySearchInDirty (a, element, 0, m-1);
	}
	
/*public class FindDirty {
	public static int comparisons = 0;		
	public static int pos = 0;
	public static int step = 1;
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		return forwardSearch(a, element);
	}
	
	public static int forwardSearch(char [] a, char element){
		
		comparisons++;
		if (pos >= a.length) return -1;
		
		int cmp = Character.compare(a[pos], element);
		
		if (cmp > 0 || a[pos] == '&') {
			//search element is smaller than element at current pos
			step = 1;
			return backwardSearch(a, element);
		}
		
		if (cmp == 0)
			return pos;
		
		pos = pos + step;
		step = step*2;
		
		return forwardSearch(a, element);
	}
	
	public static int backwardSearch(char[] a, char element) {
		
		comparisons++;
		if (pos < 0) return -1;
		
		int cmp = Character.compare(a[pos], element);
		
		if (cmp < 0) { //search element is larger than element at current pos
			step = 1;
			return forwardSearch(a, element);
		}
		
		if (cmp == 0)
			return pos;
			
		pos = pos - step;
		step = step*2;
		
		return backwardSearch(a, element);
	}*/
	
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