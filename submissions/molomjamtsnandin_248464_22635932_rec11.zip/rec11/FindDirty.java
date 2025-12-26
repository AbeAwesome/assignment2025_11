package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		int limit= findLim(a,1,'&');
		return binarySearch(a, 0, limit, element);
		
	}
	public static int findLim(char [] a, int pos, char element) {
		if(a[pos]==element) {
			return pos-1;
		}
		comparisons++;
		return findLim(a, pos*2, element);
	}
	public static int binarySearch(char [] a, int low,  int high, char element) {
		if(low>high)
			return -1;

        int mid = (low + high) / 2;
        
        comparisons++;
        if (a[mid] == element)
            return mid;

        comparisons++;
        if (a[mid] < element)
            return binarySearch(a, mid + 1, high, element);

        return binarySearch(a, low, mid - 1, element);
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