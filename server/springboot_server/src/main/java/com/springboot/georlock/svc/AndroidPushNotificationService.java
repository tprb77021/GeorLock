package com.springboot.georlock.svc;

import com.springboot.georlock.config.HeaderRequestInterceptor;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/*SpringBoot와 FCM 연동*/
/*
 * string 변수 firebase_servere_key 값에 firebase project 서버 키 값을 가져와
 * 붙여넣기만 하면 된다.
 *   console firebase > project > 프로젝트 설정 > 클라우드 메시징 > 서버키
 * */
@Service
public class AndroidPushNotificationService {

    private static final String firebase_server_key = "AAAAxMM9LDw:APA91bGmNtrDJFtj3u0ldlcz74MgyauMlc6P9G6S_nEa1WL3YSxLmbs5nxhNnYkA8Q2mJ-Rkx4D8rUbPVQZB49_2Ypq9gn6vSasQmdCNq_iL61D0opnjsY2y8-oQd7AycbEMa1lwCUHW";

    /*FCM HTTP를 호출하는 URL*/
    private static final String firebase_api_url = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        /*요청을 보낼 때마다 헤더를 설정하지 않으려면 RestTemplate에
         * 인터셉터 "ClientHttpRequestInterceptor"를 설정할 수 있습니다.*/
        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + firebase_server_key));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json; UTF-8"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(firebase_api_url, entity, String.class);
        System.out.println(firebaseResponse);
        return CompletableFuture.completedFuture(firebaseResponse);
    }
}

/*@Service :
 *   해당 클래스가 Service라는 것을 알리기 위해서 붙여주는 어노테이션
 *
 * final :
 *   final은 해당 entity가 오로지 한 번 할당될 수 있음을 의미합니다.
 *
 * fianl 변수
 *   - 해당 변수가 생성자나 대입연산자를 통해 한 번만 초기화 가능함을 의미합니다.
 *   - 상수를 만들 때 응용합니다.
 *
 * final 메소드
 *   - 해당 메소드를 오버라이드하거나 숨길 수 없음을 의미합니다..
 *
 * final 클래스
 *   - 해당 클래스는 상속할 수 없음을 의미합니다. 문자 그대로 상속 계층 구조에서 '마지막' 클래스입니다.
 *   - 보안과 효율성을 얻기 위해 자바 표준 라이브러리 클래스에서 사용할 수 있는데
 *       대표적으로 java.lang.System, java.lang.String 등이 있습니다.
 */

/*
 * @Async :
 *   스프링을 사용한 @Async 어노테이션을 이용해서 위의 Java 처럼 메서드마다 반복적으로
 *   thread를 관리 할 필요 없이 간단하게 처리할 수 있습니다.
 *   메소드 위에 @Async 어노테이션만 추가하면 method는 비동기로 처리됩니다.
 *
 * @Async 제약사항 :
 *   - @Async로 명시된 메소드는 반드시 public으로 선언되어야 한다. 메소드가 public이어야
 *       프록시가 될 수 있기 때문이다.
 *   - 같은 클래스 내에서 해당 메소드를 호출할 경우 비동기로 작동하지 않는다. 셀프호출은 프록시를
 *       우회하고 해당 메소드를 직접 호출하기 때문이다.
 * */

/*
 * 동기와 비동기의 차이 :
 * 동기 - 1. 요청과 결과가 한 자리에서 동시에 일어남
 *       2. A노드와 B노드 사이의 작업 처리 단위(transaction)를 동시에 맞춤
 * 비동기 - 1. 요청한 그 자리에서 결과가 주어지지 않음
 *        2. 노드 사이의 작업 처리 단위를 동시에 맞추지 않아도 된다.
 *
 * 동기방식은 설계가 매우 간단하고 직관적이지만 결과가 주어질 때까지 아무것도 못하고 대기해야 하는 단점
 * 비동기방식은 동기보다 복잡합지만 결과가 주어지는데 시간이 걸리더라도 그 시간 동안 다른 작업을
 * 할 수 있으므로 자원을 효율적으로 사용할 수 있는 장점이 있다.
 * */

/*CompletableFuture - 완전한 비동기식 병렬 프로그래밍
 *   "미래에 처리할 업무(Task)로서, Task 결과가 완료되었을 때 값을 리턴하거나, 다른 Task
 *   가 실행되도록 발화(trigger)시키는 Task."
 *
 * 장점 :
 *   1) 명시적 쓰레드 선언없이 쓰레드를 사용할 수 있다.
 *   2) 함수형 프로그래밍방식으로 비동기적으로 동시성/병렬 프로그래밍을 가능하게 함으로서
 *       의도를 명확하게 드러내는 함축적인 프로그래밍을 가능하게 한다.
 *   3) 각 Task마다 순서적 연결을 할 수도 있고, Task의 예외처리도 가능하다.
 */

/*
 * RestTemplate :
 *   스프링에서 제공하는 http 통신에 유용하게 쓸 수 있는 템플릿이며,
 *   HTTP 서버와의 통신을 단순화하고 Restful 원칙을 지킨다. jdbcTemplate 처럼 RestTemplate도
 *   기계적이고 반복적인 코드들을 깔끔하게 정리해준다.
 * 특징 :
 *   1) 기계적이고 반복적인 코드를 최대한 줄여줌
 *   2) Resstful 형식에 맞춤
 *   3) json, xml 를 쉽게 응답받음
 * */

/*
 * Interceptor :
 *   컨트롤러에서 메서드에 매핑된 특정 URI에 접근하면서 무언가를 제어할 필요가 있을 때 사용합니다.
 *   정확히는 컨트롤러에 접근하기 전과 후로 나뉘는데요, 예를 들어 회원제로 이루어지는 시스템이 있다고
 *   가정했을 때 로그인이나 계정의 권한과 같은 처리는 인터셉터를 사용해서 처리합ㄴ니다.
 * */