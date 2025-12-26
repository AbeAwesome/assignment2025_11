package rec11;

public class FindDirty {
	public static int comparisons = 0;		
	
	public static int findInDirtyArray(char [] a, char element) {
		
		return findHelper (a, element, 0, findUpper(a));
	}

	private static int findUpper(char[]a){
		return findUpper(a,1);
	}
	
	private static int findUpper(char[]a,int i){
		if (i>=a.length||a[i]=='&'){
			return i-1;
		}
		return findUpper(a,i*2);
	}

	private static int findHelper(char[]a,char x,int left, int right){
		if(left>right){
			return-1;
		}
		int mid=(left+right)/2;
		comparisons++;
		if(a[mid]==x){
			return mid;
		}

		comparisons++;
		if(a[mid]<x){
			return findHelper(a,x,mid+1,right);
		}
		else{
			return findHelper(a,x,left,mid-1);
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