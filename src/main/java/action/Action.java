package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

//각 요청을 처리하는 Action 클래스들의 규격을 정의하는 인터페이스
public interface Action {

    public  ActionForward execute(HttpServletRequest request,
                                  HttpServletResponse response) throws Exception;
}
