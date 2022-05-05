package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//빈 생명주기 (초기화, 소멸하는 인터페이스를 상속받아 사용하기)
public class NetworkClient {

    //빈 생명주기 콜백
    private String url;

    //접속 url 출력, 생성자 호출
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메세지");
    }

    //외부에서 넣을수 있는 url setter
    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    //연결이된 상태에서 call메서드로 message를 던짐
    public void call(String message) {
        System.out.println("call " + url + "message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

//    InitializingBean
//    의존관계주입이 끝나면 호출하는 메서드  (의존관계 완료를 알려줌)
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메세지");
//    }

    @PostConstruct  //어노테이션 활용
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    //    //DisposableBean
//    //빈이 종료될때 호출
//    @Override
//    public void destroy() throws Exception {
//        disconnect();
//    }
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

}
