package action;

import java.io.PrintWriter;

//controller
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.LoginService;
import vo.ActionForward;
import vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		LoginService loginService
		= new LoginService();
		

		
		MemberVO loginMember = loginService.getLoginMember(id, passwd);

		
		
		
		ActionForward forward = null;
		if(loginMember == null) { // �α��� ����
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 실패')");
			out.println("history.back()");
			out.println("</script>");			
		}
		else {
			forward = new ActionForward();
			forward.setUrl("index.jsp");
			forward.setRedirect(true);
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
			
			//response.sendRedirect("loginSuccess.jsp");
			 
			response.sendRedirect("index.jsp");
			
		}
		return forward;
	}

}
