package assignment;

public class Test03 {
	public static void main(String[] args) {
		int inx = 0;
		while(inx <= 6) {  //while -> for문으로 바꾸기 
			int jnx = 0;
			while(jnx <= inx) { //while -> for문으로 바꾸기 
				jnx++;
			}
			System.out.println("@");
			inx++;
		}
	} 

}


//for(int inx = o; inx<=6; jnx <= inx);