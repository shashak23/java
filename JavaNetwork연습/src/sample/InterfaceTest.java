package sample;

interface myInterface{
   
   public abstract void aa();
   
}



public class InterfaceTest {

   public static void main(String[] args) {
   
      // myInterface a = new myInterface(); 안됨! interface-abstract 이므로 instance 생성 불가.
      // 항상 class를 만들 필요는 없다.
   myInterface a = new myInterface() {
      public void aa() {
   }
   }   ;
   }
}
