package hello.core.Test;

public class V2 extends V1{

    @Override
    public void testV11() {
        System.out.println("다형성예제");
    }

    @Override
    public void testV1() {
        super.testV1();
    }

    void test22(){
        System.out.println("a");
    }
}
