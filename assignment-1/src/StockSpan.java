import java.lang.*;
import java.io.*;
import java.util.*;
import java.text.*;

class StockSpan
{
	public static void main(String[] args)throws IOException
	{
		if (args[0].equals ("-n"))
		{
			List <Float> prices = new ArrayList <Float>();
			List <String> dates = new ArrayList <String>();

			Float[] pricesArr = prices.toArray(new Float[prices.size()]);
			String[] datesArr = dates.toArray(new String[dates.size()]);
			BufferedReader br = null;
			try
			{
				String s=br.readLine();
				br=new BufferedReader(new FileReader(args[1])); //""
				while ((s!= null))
				{
				  s=br.readLine();
				  String[] fields = s.split(",");
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  dates.add(fields[0]);
				  Float f = Float.parseFloat(fields[1]);
				  prices.add(f);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			int[] span1 = new int[prices.size()];//pinakas timwn
			int[] span2 = new int[prices.size()];//pinakas anoigmatwn2
			for(int i=0;i<span1.length;i++)
			{
				int k=1;
				boolean done = false;
				while((!done)&&(i-k>=0))
				{
					if(span1[i-k]<=span1[i])
					{
						k++;
					}
					else
					{
						done = true;
					}
				}
				span2[i] = k;
				System.out.println(/*dates*/+ span2[i]);
			}
		}
		else if (args[0].equals("-s"))
		{
			List<Float> prices = new ArrayList <Float>();
			List<String> dates = new ArrayList <String>();
			Float[] pricesArr = prices.toArray(new Float[prices.size()]);
			String[] datesArr = dates.toArray(new String[dates.size()]);

			BufferedReader br = null;
			try
			{
				br=new BufferedReader(new FileReader(args[1]));
				String s=br.readLine();
				while ((s!= null))
				{
				  s=br.readLine();
				  String[] fields = s.split(",");
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  dates.add(fields[0]);
				  Float f = Float.parseFloat(fields[1]);
				  prices.add(f);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			Stack<Integer> stack = new Stack<Integer>();
			int[] span1 = new int[prices.size()];
			int[] span2 = new int[prices.size()];
			span1[0]=1;
			stack.push(0);

			for(int i=1; i<span1.length;i++)
			{
				while(!stack.isEmpty() && span1[i] >= span1[stack.peek()] )
				{
					stack.pop();
				}
				if (stack.isEmpty())
				{
					span1[i]=i+1;
				}
				else
				{
					span1[i]=i-stack.peek();
				}

				stack.push(i);
				System.out.println(/*dates*/+ span2[i]);
			}
		}
		else if (args[0].equals("-b"))
		{
			List<Float> prices = new ArrayList <Float>();
			List<String> dates = new ArrayList <String>();
			Float[] pricesArr = prices.toArray(new Float[prices.size()]);
			String[] datesArr = dates.toArray(new String[dates.size()]);

			BufferedReader br = null;
			try
			{
				br=new BufferedReader(new FileReader(args[1]));
				String s=br.readLine();
				while ((s!= null))
				{
				  s=br.readLine();
				  String[] fields = s.split(",");
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				  dates.add(fields[0]);
				  Float f = Float.parseFloat(fields[1]);
				  prices.add(f);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			long startTime = System.nanoTime()/ 1000000;
			for(int i=0;i<100;i++)
			{
				int[] span1 = new int[prices.size()];//pinakas timwn
				int[] span2 = new int[prices.size()];//pinakas anoigmatwn2

				for(i=0;i<span1.length;i++)
				{
					int k=1;
					boolean done = false;
					while((!done)&&(i-k>=0))
					{
						if(span1[i-k]<=span1[i])
						{
							k++;
						}
						else
						{
							done = true;
						}
					}
					span2[i] = k;
				}
			}
			long endTime = System.nanoTime()/ 1000000;
			long m = endTime - startTime ;
			System.out.println("Naive implementation took:"+ m + "millis");

			startTime = System.nanoTime()/ 1000000;
			for(int i=0;i<100;i++)
			{
				Stack<Integer> stack = new Stack<Integer>();
				int[] span1 = new int[prices.size()];
				int[] span2 = new int[prices.size()];
				span1[0]=1;
				stack.push(0);

				for(i=1; i<span1.length;i++)
				{
					while(!stack.isEmpty() && span1[i] >= span1[stack.peek()] )
					{
						stack.pop();
					}
					if (stack.isEmpty())
					{
						span1[i]=i+1;
					}
					else
					{
						span1[i]=i-stack.peek();
					}

					stack.push(i);
				}
				endTime = System.nanoTime()/ 1000000;
				m = endTime - startTime;
				System.out.printf("Stack implementation took:"+m+ "millis");
				}
		}
}}