import java.util.ArrayList;
import java.util.Scanner;
//import java.io.FileInputStream;

package CodingTest_KOSA;

public class ct_1223_1 {

	   static int AnswerN=0;
	   static int N = 10;

	   public static void main(String args[]) throws Exception {
	      //System.setIn(new FileInputStream("C:\sample_input.txt"));
	      Scanner sc = new Scanner(System.in);
	      int row=0;
	      int column=0;
	      int tmprow=0;
	      int tmpcolumn=0;
	      int T = sc.nextInt();
	      ArrayList<String[]> arrL = new ArrayList();
	      String str[];
	      String tmp="";
	      for(int test_case = 1; test_case <= T; test_case++) {
	         
	         N = sc.nextInt();
	         str=new String[N];
	         arrL.add(str);
	         tmp=sc.nextLine();
	         for(int i=0;i<N;i++)
	         {
	            arrL.get(test_case-1)[i]=sc.nextLine();
	            //System.out.println(arrL.get(test_case-1)[i]);
	            for(int j=0;j<N;j++)
	            {
	               if(arrL.get(test_case-1)[i].charAt(j)=='X')
	               {
	                  row=i;
	                  column=j;
	               }
	            }
	         }
	         tmprow=row;
	         tmpcolumn=column;
	         //System.out.println("X : " + row + "," + column);
	         //System.out.println((arrL.get(test_case-1)[row].charAt(column)));
	         while((arrL.get(test_case-1)[row].charAt(column))!='Y')
	         {
	            
	            if(column==0)
	            {
	               break;
	            }
	            //System.out.println((arrL.get(test_case-1)[row].charAt(column)));
	            if((arrL.get(test_case-1)[row].charAt(column))=='H')
	            {
	               if((arrL.get(test_case-1)[row].charAt(column-2))=='H')
	               {
	                  AnswerN++;
	                  //System.out.println("count++");
	                  break;
	               }
	            }
	            
	            column-=2;
	            
	         }
	         //System.out.println("left end");
	         row=tmprow;
	         column=tmpcolumn;
	         while((arrL.get(test_case-1)[row].charAt(column))!='Y')
	         {
	            if(column>=(2*N)-2)
	            {
	               break;
	            }
	            //System.out.println((arrL.get(test_case-1)[row].charAt(column)));
	            
	            if((arrL.get(test_case-1)[row].charAt(column))=='H')
	            {
	               if((arrL.get(test_case-1)[row].charAt(column+2))=='H')
	               {
	                  AnswerN++;
	                  //System.out.println("count++");
	                  break;
	               }
	            }
	            
	            column+=2;
	            
	         }
	         //System.out.println("right end");
	         row=tmprow;
	         column=tmpcolumn;
	         while((arrL.get(test_case-1)[row].charAt(column))!='Y' && row>=1)
	         {
	            if(row==0)
	            {
	               break;
	            }
	            //System.out.println((arrL.get(test_case-1)[row].charAt(column)));
	            
	            if((arrL.get(test_case-1)[row].charAt(column))=='H')
	            {
	               if((arrL.get(test_case-1)[row-1].charAt(column))=='H')
	               {
	                  AnswerN++;
	                  //System.out.println("count++");
	                  break;
	               }
	            }
	      
	            row--;
	            
	         }
	         //System.out.println("up end");
	         row=tmprow;
	         column=tmpcolumn;
	         while((arrL.get(test_case-1)[row].charAt(column))!='Y' && row<=N-1)
	         {
	            if(row==N-1)
	            {
	               break;
	            }
	            //System.out.println((arrL.get(test_case-1)[row].charAt(column)));
	            
	            if((arrL.get(test_case-1)[row].charAt(column))=='H')
	            {
	               if((arrL.get(test_case-1)[row+1].charAt(column))=='H')
	               {
	                  AnswerN++;
	                  break;
	               }
	            }
	            
	            row++;
	            
	         }
	         //System.out.println("down end");
	         //System.out.println();
	         System.out.println("#"+test_case+" "+AnswerN);
	         AnswerN=0;
	      }
	   }
}
