package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		int low = 0;
		int high = 0;
		
		comparisons ++;
		if(a[high] == element) {
			return high;
		}
		else if(a[high] > element) {
		
		}
		
		low = high + 1;
		high = high * 2;
		
		return binarySearch(a, element, high, low);
	}
	
	public static int binarySearch(char[] a, char element, int low, int high) {
        while (low <= high) {
            comparisons++;  
            int mid = low + (high - low) / 2;
            try {
                if (a[mid] == element) {
                    return mid; 
                } else if (a[mid] > element) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                high = mid - 1; 
            }
        }
        return -1;  
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