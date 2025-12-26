package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		//TODO: implement this using recursion to run in time O(log N)
		// treat array a as unlimited, assume that its size is unknown
		int last = findJunk(a);
		return findChar(a,element,0,last-1);
	}


	public static int findChar (char []A, int x, int l, int r) {
		if (l>r)
			return -1;
		int m = l+(r-l)/2;
		comparisons++;
		if (A[m]==x) {
			return m;
		}
		if (A[m]>x) {
			return findChar(A,x,l,m-1);
		}
		return findChar(A,x,m+1,r);
	}
		

	public static int findJunk(char [] A) {
		int i=1;
		while (i<A.length) {
			comparisons++;
			if (A[i]=='&')
				return i;
			i*=2;
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