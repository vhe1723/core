package hello.core.singleton;

public class SingletonService {

    //static으로 선언하면 클래스레벨로 올라가서 단 하나만 존재하게된다.
    private static final SingletonService instance = new SingletonService();

    //싱글톤 호출을 위한 getter
    public static SingletonService getInstance() {
        return instance;
    }

    //생성자를 private으로 선언해서 다른곳에서 객체생성 막기
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
