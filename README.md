# teamProject
## proj 소개
 개인에게 입력된 시간동안에 원격개폐 기능과 개폐기록을 DB에 저장하여 언제 문이 열렸는지 
 실시간 확인이 가능함과 근무 및 정해진 시간 외의 출입을 제어함 그리고 관리자의 원격 제어로 
 필요시 출입 or 퇴실을 관리함으로써 보안을 강화할 수 있다. 
## EndUser 입장에서 보는 핵심 기능
1. 출입 요청 시 관리자에게 PUSH알림

2. 근무시간별로 출입 권한 부여

3. 외부손님 방문 시 원격 개페

## 개발환경

* SERVER : TOMCAT 9.0
* DATABASE : MariaDB
* Language & Framework : java, Spring, mybatis

## 사용한 기술셋
### RPI
### Android
### Springboot
* __로그인 화면(/)__
등록된 사원번호, 비밀번호 입력시 DB에서 값 확인 후 성공, 실패 (empNo, userPw -> 임의 설정 테이블명 : users)
   자동로그인 기능 : 쿠키 활용
   - 경로 : com.springboot.georlock.controller. LoginConroller

* __출입기록(/record)__
   라즈베리파이(NFC)에서 넘어온 값 받기 (No, intime, outtime -> 임의 설정 테이블명 : enteremp) 
   - 경로 : com.springboot.georlock.controller. RecordConroller

* __출입권한설정(/access)__
   수정, 삭제 기능 반영 (임의 설정 테이블명 : users)
   - 경로 : com.springboot.georlock.controller. AccessConroller


* __활용 클래스 및 인터페이스__
: controller, dto, mapper(+xml), service
### WEB FrontEnd
* __javascript__ : 회원권한 수정하기 페이지를 보기쉽게 사이즈를 조절하여 새창으로 띄운다.
* __jQuery__ : 로그인 실패시 "로그인 실패" 메세지를 띄운다.
* __HTML5__
* __CSS3__

## Local에서 돌려보는 메뉴얼
__Android__
1. com.example.georlock
   static클래스에서 server_url 값을 로컬의 아이피와 포트번호로 변경 ( http://아이피(ip):포트번호(port))

__Springboot__
1.  resources/application.properties 
   - spring.datasource.url=jdbc:mariadb://192.168.0.35:3306/employee
   - spring.datasource.username=root
   - spring.datasource.password=yadoc
   - spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
     로컬의 DB환경으로 설정  
   - server.port=8091
     port 번호 설정
   
__WebFront__
1. http:// 로컬ip : port 로 로그인화면 접속
