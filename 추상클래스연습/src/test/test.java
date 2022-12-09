package test;

interface A {

}

interface B {

}

public class MyTest extends Object implements A, B {

}

//interface는 객체 간에 결합도를 낮출 수 있어요, interface간에는 상속이 가능하다.
//interface를 쓰고 마지막에 implements를 쓰면 class가 A와B 둘다 상속이 가능하다.
//인터페이스는 상속과 비슷한데 구현이라는 단어로 쓸 뿐임
