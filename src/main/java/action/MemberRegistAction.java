package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberRegistService;
import vo.ActionForward;
import vo.MemberVO;

public class MemberRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail(email);
		memberVO.setId(id);
		memberVO.setName(name);
		memberVO.setPasswd(passwd);
		
		MemberRegistService memberRegistService
		= new MemberRegistService();
		
		boolean registSuccess = 
				memberRegistService.registMember(memberVO);
		ActionForward forward = null;
		
		if(registSuccess) {
			forward = new ActionForward();
			forward.setUrl("loginForm.jsp");
			forward.setRedirect(true);
			HttpSession session = request.getSession();
			session.setAttribute("id", memberVO.getId());
					
			
		}
		else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('��� ����')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
