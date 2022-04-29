package hello.core.singleton;

public class StatefulService {

//    private int price; //상태를 유지하는 필드

//    public void order(String name, int price) {
//        System.out.println("name = " + name + "price = " + price);
//        this.price = price; //문제가 되는 부분
//    }

    //setter를 쓰지않고 그냥 반환해서 해결한다.
    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
//        this.price = price;
        return price;
    }

//    public int getPrice() {
////        return price;
//    }

}
