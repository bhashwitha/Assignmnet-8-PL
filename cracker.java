import java.util.*;

public class Cracker{

	final public static boolean DEBUG = false;
	public static HashMap<Integer, Integer > last;

	final public static int[] A = {-1, -1, 0, 0, 1, 1};
	final public static int[] B = {-1, 0, -1, 1, 0, 1};
	
	public static boolean on(int C, int D) 
	{
			return (C & (1<<D)) != 0;
	}
    public static boolean ib(int k, int l) 
	{
			return k >= 0 && k < 5 && l >= 0 && l <= k;
	}
	public static ArrayList<Integer> buildpath(HashMap<Integer, Integer> last, int final1)
	 {
            ArrayList<Integer> res = new ArrayList<Integer>();
			int present = final1;
			while(last.get(present) != present)
			 {
				res.add(0, present);
				present = last.get(present);
			 }
			return res;
	 }
	public static int getlastpos(int row, int col) 
		{
			return (1<<(5*row+col));
		}
	public static ArrayList<Integer> nextposition(int C) 
	{
			ArrayList<Integer> position = new ArrayList<Integer>();
			for(int ra = 0; ra<5; ra++)
			{
				for (int ca = 0; ca <= 5; ca++)
				{
					for(int dir1 = 0; dir1<A.length; dir1++)
					{
						if(!ib(ra+2*A[dir1], ca+2*B[dir1])) continue;
						if(on(C, 5*ra+ca) && on(C,5*(ra+A[dir1]) + ca + B[dir1]) && !on(C, 5*(ra+2*A[dir1]) + ca + 2*B[dir1])) 
						{
							int np = app(C, dir1, ra, ca);
							position.add(np);
						}
					}
				}

			}
			return position;
		}

	public static int app(int C, int dir1, int ra, int ca)
		{
			int start = 5*ra +ca;
			int mid = 5* (ra+A[dir1]) + ca + B[dir1];
			int final1 = 5 *(ra+2*A[dir1]) + ca + 2*B[dir1];
			return C - (1<<start) - (1<<mid) + (1<<final1);
		}
	public static void show(int C)
	 {
			for(int i = 0; i < 5; i++)
			{
				for(int j = 0; j <5-1-i;j++) System.out.print(" ");
				for(int j = 0; j<=i;j++) 
				{
					if(on(C, 5*i+j)) System.out.print("x ");
					else System.out.print(". ");
				}
				System.out.println();
			}
			System.out.println();
	 }

	public static int board(int row, int col)
		{
			int C = 0;
			for(int i = 0; i < 5; i++)
			{
				for (int j = 0; j <=i;j++)
				{
					C = C | (1<<(5*i+j));
				
				}
			}
			return C - (1<<(5*row+col));
		}
public static void main(String[] args) 
	{
		int begin = board(0, 0);
		int end = getlastpos(4, 2);
    	last = new HashMap<Integer,Integer>();
		last.put(begin, begin);
		System.out.println();
		System.out.print(" ====0==== ");
		System.out.println();
		System.out.print("    . ");
		System.out.println();
		System.out.print("   x x");
		System.out.println();
		System.out.print("  x x x");
		System.out.println();
		System.out.print(" x x x x");		
		System.out.println();
		System.out.print("x x x x x");
		System.out.println();
		System.out.println();
		LinkedList<Integer> hold = new LinkedList<Integer>();
		hold.offer(begin);
		while(hold.size() > 0)
		{
			int present = hold.poll();
			ArrayList<Integer> nextList = nextposition(present);
			for(int i = 0; i < nextList.size(); i++)
			{
				if(!last.containsKey(nextList.get(i)))
				 {
					last.put(nextList.get(i), present);
					hold.offer(nextList.get(i));
			     }
		    }
		}
		if(last.containsKey(end)) 
		{
			ArrayList<Integer> path = buildpath(last, end);
			for(int i = 0; i <path.size(); i++) 
			{
				show(path.get(i));
				System.out.println();
			}
        }

	}
} 