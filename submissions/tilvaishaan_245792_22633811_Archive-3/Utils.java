import java.util.*;

public class Utils {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvxyz";
	
	//produces a sample list of n random int elements
	public static Node sampleList (int n) {
		Node head = new Node(Integer.valueOf((int) (Math.random() * 20)));
		Node tail = head;
		for (int i=0; i<n-1; i++) {
			Node node = new Node(Integer.valueOf((int) (Math.random() * 20)));
			tail.setNext(node);
			tail = node;
		}
		return head;
	}
	
	// produces a sample list of first n integers (sorted)
	public static Node sampleSortedList (int n) {
		Node head = new Node(0);
		Node tail = head;
		for (int i=0; i<n-1; i++) {
			Node node = new Node(i+1);
			tail.setNext(node);
			tail = node;
		}
		return head;
	}
	
	// prints linked list defined by its head
	public static void printList(Node head) {
		Node current = head;
		while (current!=null) {
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
		System.out.println();
	}
	
	// array of n random ints from 0 to maxInt
	public static int [] getRandIntArray(int n, int maxInt) {
		int [] data = new int [n];
		
		for (int i=0; i< n; i++)
			data [i] =  Integer.valueOf((int) (Math.random() * maxInt));
		
		return data;
	}
	
	//produces an int array with n random elements where last m elements are repeated=r
	public static int [] getRandIntArrayWithRepeats(int n, int m, int r) {
		int [] data = new int [n];
		
		for (int i=0; i< n - m; i++)
			data [i] =  Integer.valueOf((int) (Math.random() * 200));
		
		for (int i=n - m; i< n ; i++)
			data [i] =r;
		
		return data;
	}
	
	// used to print an array
	public static String arrToString (int [] a) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i< a.length - 1; i++)
			sb.append(a[i] + ", ");
		sb.append(a[a.length -1]);
		return sb.toString();
	}
	
	/**
	 * This generates an array of characters that have some (small) amount of 
	 * elements that are characters of the alphabet a-x
	 * they are located in the beginning of the array and they are sorted
	 * The rest of the array is filled with "junk": character &
	 * The number of actual non-junk elements is not known
	 * @return sample array of characters with junk at the end
	 */
	public static char [] getDirtyCharArray() {
		int n = 10000 + Integer.valueOf((int) (Math.random() * 20000));
		char [] total = new char [n];
		
		int m = n/1000;
		
		
		Random r = new Random();
		
		char [] data = new char [m];
		for (int i=0; i< m; i++)
			data [i] = ALPHABET.charAt(r.nextInt(ALPHABET.length()));
		Arrays.sort(data);
		
		int i=0;
		for (; i< m; i++)
			total[i] = data[i];
		
		for (; i<n; i++)
			total[i] = '&';
		return total;
	}
	
	public static char getRandChar() {
		return ALPHABET.charAt((int)(Math.random() * ALPHABET.length()));
	}
}