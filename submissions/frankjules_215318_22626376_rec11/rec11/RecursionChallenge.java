package rec11;


public class RecursionChallenge {	
	//exercise 1: return the sum of int numbers from 1 to n inclusive
	public static int sumN(int n) {
		//TODO: solve this using recursion
		if(n>0)
			return n+ sumN(n-1);
		return 0;
	}
	
	//exercise 2: return sum of the data in a linked list of numbers
	public static int sumList (Node node) {
		//TODO: solve this using recursive list traversal
		if (node!=null)
			return node.getData()+sumList(node.getNext());
		return 0;		
	}
	
	//exercise 3: checks if all the elements of the list (defined by head node) are sorted
	public static boolean isSorted (Node node) {
		//TODO: solve this using recursive list traversal
		if (node.getNext()!=null)
			return (node.getData()<node.getNext().getData()) && isSorted(node.getNext());
		 return true;
	}
	
	//exercise 4: returns new string where character '*' is inserted between each character of a string
	public static String allStar(String astring) {
		int len = astring.length();
		if (len>2) {
			return astring.charAt(0)+"*"+allStar(astring.substring(1,len-1))+"*"+astring.charAt(len-1);
		}
		if (len==2)
			return astring.charAt(0)+"*"+astring.charAt(1);
		return astring;

	}
	
	//exercise 5: returns a new string in which all characters 'x' are removed
	public static String noX(String astring) {
		//TODO: solve this using recursion
		if (astring.length()>0) {
			if (astring.charAt(0)=='x')
				return noX(astring.substring(1));
			else
				return astring.charAt(0)+noX(astring.substring(1));}
		return "";
	}	
	
	//exercise 6: output the product of the two integers n and m. 
	// do not use multiplication operator 
	//limited to using addition/subtraction and negation  
	public static int mult (int n, int m) {
		//TODO: solve this using recursion
		if (n>0)
			return m+mult(n-1,m);
		if (n<0)
			return -m+mult(n+1,m);
	    return 0;	   
	}
	
	public static void main (String [] args) {
		int n = 5;
		
		System.out.print("Testing Ex. 1: sum of first " + n + " integers: ");
		System.out.println(sumN(n)); // should be 15 
		
		Node list = Utils.sampleList(n);
		System.out.print("\nTesting Ex. 2: sum list " + n + " rand integers: ");
		Utils.printList(list);
		System.out.println("sum = " + sumList(list));
		
		list = Utils.sampleSortedList(n);
		System.out.print("\nTesting Ex. 2: sum list " + n + " sorted integers: ");
		Utils.printList(list);
		System.out.println("sum = " + sumList(list)); //should be 10
		
		list = Utils.sampleList(n);		
		System.out.println("\nTesting Ex. 3: input list:");
		Utils.printList(list);
		System.out.println("is the list sorted? "+ isSorted(list)); // false
		
		list = Utils.sampleSortedList(n);
		System.out.println("Testing Ex. 3: sorted input list:");
		Utils.printList(list);
		System.out.println("is the list sorted? "+ isSorted(list)); // true
		
		String astring = "hello";
		System.out.println("\nTesting Ex. 4: allStar input string: '" + astring 
				+ "' output string: '" + allStar(astring) +"'" ); //'h*e*l*l*o'
		
		astring = "a";
		System.out.println("Testing Ex. 4: allStar input string: '" + astring 
				+ "' output string: '" + allStar(astring) +"'"  ); // a
		
		astring = "rex pex fex";
		System.out.println("\nTesting Ex. 5: noX input string: '" + astring 
				+ "' output string: '" + noX(astring)  +"'" );	//'re pe fe'	
		
		int x = 8;
		int y = 7;
		
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
		x = - 8;
		y = 7;
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
		x = - 8;
		y = - 7;
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
		x = 8;
		y = - 7;
		System.out.println("\nTesting Ex. 6: mult: " + x +
				"*"+ y + "=" + mult(x, y));
		
	}
}

