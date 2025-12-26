package rec11;

public class RecursionChallenge 
{	
	//exercise 1: return the sum of int numbers from 1 to n inclusive
	public static int sumN(int n) 
	{
		if (n == 0)
			return 0;
		//base case
		
		return sumN(n-1) + n;
		//recursive case
		//(n-1) take n which is 5, at first, and turn our 5 into 4. then add to the 5 then 4 is new n
		//result in 5+4 then keeps going until we reach our base case of 0
	}
	
	//exercise 2: return sum of the data in a linked list of numbers
	public static int sumList (Node node) 
	{		
		if (node == null)
			return 0;
		//base case, null stop
		
		return node.getData() + sumList(node.getNext());
		//get the nodes data then add it to the sum of the rest by calling sumList(node.getNext())
	}
	
	//exercise 3: checks if all the elements of the list (defined by head node) are sorted
	public static boolean isSorted (Node node) 
	{
		if (node == null)
			return true;
			//base case, null stop
			else if (node.getNext() == null)
				return true;
					//only one node with data then it is sorted
				else if(node.getData() >= node.getNext().getData())
					return false;
					//if not least to greated sort then return false
					else
						return isSorted(node.getNext());
							//keeps going through the nodes goes to top of if statements, recursive case
	}
	
	//exercise 4: returns new string where character '*' is inserted between each character of astring
	public static String allStar(String astring) 
	{
		if (astring.length() <= 1)
			return astring;
			// if one char
			char currentC  = astring.charAt(0);
			//gives our recursive method a current char at the first character of string
			String bstring = astring.substring(1);
			//splits one character from the string but keeps rest
		
			return currentC + "*" + allStar(bstring);
			//final product, starts at char(0) concatenates * between each char
	}
	
	//exercise 5: returns a new string in which all characters 'x' are removed
	public static String noX(String astring) 
	{
		char charRemoved = 'x';
		//identify removed char
		
		if (astring.length() == 0)
			return "";
			//base case, empty string without nothing
		
		char letter  = astring.charAt(0);
		//gives our recursive method a current char at the first character of string
		String bstring = astring.substring(1);
		//splits one character from the string but keeps rest
		
		if(letter == charRemoved)
			return noX(bstring);
			//skips letter x and recurse the rest
		else
			return letter + noX(bstring);
			// goes through each char and appends recursive new string
	}	
	
	//exercise 6: output the product of the two integers n and m. 
	// do not use multiplication operator 
	//limited to using addition/subtraction and negation  
	public static int mult (int n, int m) 
	{
		if(n == 0 || m == 0)
			return 0;
			//0 product case
		else if (n < 0 && m < 0)
			return mult(-n,-m);
			//both n and m are - in this case product is +
		else if (n < 0)
			return -mult(-n,m);
			// only n is - so output -
		else if (m < 0 )
			return -mult(n,-m);
			//output - because m is -
		else 
			return m + mult(n - 1, m);
			// recursive case add m  shrink n by -1 repeat addition
			//we do all of this m times or -m times
	}
	
	public static void main (String [] args) 
	{
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

