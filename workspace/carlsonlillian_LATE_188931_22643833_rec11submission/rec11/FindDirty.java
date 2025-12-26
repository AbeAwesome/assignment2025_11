package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
    public static int findInDirtyArray(char[] a, char element) {
        // Step 1: find high index containing real letters
        int high = findHighIndex(a, 1);
        return binarySearch(a, element, 0, high);
    }

    private static int findHighIndex(char[] a, int high) {
        if (a[high] == '&')
            return high - 1;
        else
            return findHighIndex(a, high * 2);
    
    }

    private static int binarySearch(char[] a, char element, int low, int high) {
        if (low > high) return -1;

        int mid = low + (high - low) / 2;

        if (a[mid] == '&') {
            int newMid = mid;
            while (newMid > low && a[newMid] == '&') 
            	newMid--;
            if (newMid < low) return -1;
            	mid = newMid;
        }

        if (a[mid] == element) 
        	return mid;
        if (a[mid] < element) 
        	return binarySearch(a, element, mid + 1, high);
        
        return binarySearch(a, element, low, mid - 1);
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