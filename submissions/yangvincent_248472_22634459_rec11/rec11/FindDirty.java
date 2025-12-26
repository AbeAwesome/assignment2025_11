package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		
		return binarySearch(a, element, 0, findCloseM(a));
	}
	
	public static int findCloseM(char[] a) {
		int i = 1;
		while(a[i]!='&' && i<a.length) {
			comparisons++;
			i = i*2;
		}
		return i;
	}
	
	public static int binarySearch(char[] a, char element, int start, int last) {
		if(start>last) {
			return -1;
		}
		
		int mid = (last+start) / 2;
		
		comparisons++;
		if(a[mid]==element) {
			return mid;
		}else if(a[mid]<element) {
			return binarySearch(a,element, mid+1, last);
		}else {
			return binarySearch(a,element, start, mid-1);
		}
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