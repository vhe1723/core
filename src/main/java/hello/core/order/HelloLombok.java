package hello.core.order;

import lombok.Data;

@Data
public class HelloLombok {

    private String a;
    private String b;

    public static void main(String[] args) {

        HelloLombok test = new HelloLombok();
        test.getA();
        test.getB();

        test.setA("TEST1");
        test.setB("TEST2");

        System.out.println("lombokTest = " + test.a + test.b);
    }
}
