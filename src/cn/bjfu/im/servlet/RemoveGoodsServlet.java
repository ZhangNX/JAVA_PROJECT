package cn.bjfu.im.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bjfu.im.dao.MemberDAO;

/**
 * Servlet implementation class RemoveGoodsServlet
 */
@WebServlet("/removeGoods.do")
public class RemoveGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = null;
		String msg = null;
		int gid = 0;
		String rdPgae = "cart.jsp";
		HttpSession session = request.getSession();
		Map<Integer, Integer> cartMap = null;
		
		msg = request.getParameter("msg");
		if (msg!=null) {
			if(msg.equals("clear")){//清空购物车
				System.out.println("clear");
				session.removeAttribute("GOODS_IN_CART");
				if(session.getAttribute("GOODS_IN_CART")==null){
					System.out.println("null!!!!");
				}
				
			}
		}else if(session.getAttribute("GOODS_IN_CART")!=null){
			sid = request.getParameter("id");
			gid = Integer.parseInt(sid);
			cartMap = (Map<Integer, Integer>)session.getAttribute("GOODS_IN_CART");
			int gnum = 0;
			gnum = cartMap.get(gid);
			if(gnum>0){//数量减一
				gnum--;
				cartMap.put(gid, gnum);
				if(gnum==0){
					cartMap.remove(gid);
				}
			}
			//将购物车map写入session
			session.setAttribute("GOODS_IN_CART", cartMap);
		}
		
		// 设置无缓存
		response.addHeader("progma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addDateHeader("Expires", 0);
		//跳转页面
		RequestDispatcher rd = request.getRequestDispatcher(rdPgae);
		rd.forward(request, response);
	}

}
