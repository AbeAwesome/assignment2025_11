package rec11;

public class FindDirty {
	public static int comparisons = 0;
	
	private static int findJunkBoundary (char [] junkArr) {
		int i=1;
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
		return binarySearchInDirty (a, element, mid+1, high);		
	}
	
	public static int findInDirtyArray(char [] a, char element) {
		int m = findJunkBoundary(a);
		return binarySearchInDirty (a, element, 0, m-1);
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