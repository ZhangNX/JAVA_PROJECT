package cn.bjfu.im.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cn.bjfu.im.vo.MemberVO;

import cn.bjfu.im.dao.MemberDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		MemberVO vo = new MemberVO(username, password);
		MemberDAO dao = new MemberDAO();
		HttpSession session = request.getSession();
		if(dao.findMember(vo)>0){
			session.setAttribute("userid", dao.findMember(vo));
			request.setAttribute("msg", "success");
		}else{
			request.setAttribute("msg", "fail");
		}
//      //ÎŞÊı¾İ¿â²âÊÔÓï¾ä		
//		if(true){
//			session.setAttribute("userid", vo);
//			request.setAttribute("msg", "success");
//		}else{
//			request.setAttribute("msg", "fail");
//		}
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

}
