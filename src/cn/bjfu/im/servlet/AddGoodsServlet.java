package cn.bjfu.im.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.bjfu.im.dao.GoodsInfoInitDAO;
import cn.bjfu.im.vo.*;

/**
 * Servlet implementation class AddGoodsServlet
 */
@WebServlet("/addGoods.do")
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodsServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String sid = request.getParameter("id");
	    String snum = request.getParameter("num");
	    int gid = Integer.parseInt(sid);
	    int gnum = Integer.parseInt(snum);
	    String page = "cart.jsp";
	    
	    System.out.println("sid="+sid+",sum="+snum);
	    
	    GoodsInfoVO vo = null;
	    GoodsInfoInitDAO dao = new GoodsInfoInitDAO();
	    vo = dao.getGoodsInfo(gid);
	    
	    if (vo ==  null) {
			page = "goodsNotExist.jsp";
		}else{
			Map<Integer, Integer> goods = null;
			HttpSession session = request.getSession();
			if(session.getAttribute("GOODS_IN_CART") != null) {
				goods = (Map<Integer, Integer>)session.getAttribute("GOODS_IN_CART");
				if(goods.containsKey(gid)) {//购物车中已经包含了该物品(已包含id)
					goods.put(gid, goods.get(gid)+gnum);//加上新加入的数量
				} else {//购物车不为空，但之前没有买过该物品
					goods.put(gid, gnum);
				}	
			} else {//购物车为空，需要初始化map
				 goods = new HashMap<Integer, Integer>();
				 goods.put(gid, gnum);
			}
			//把goods对象对应的购物车信息存储到session
			session.setAttribute("GOODS_IN_CART", goods);
			//设置无缓存
			   response.addHeader("progma","no-cache");   
			   response.addHeader("Cache-Control","no-cache");   
			   response.addDateHeader("Expires",0);
		}
	    
		RequestDispatcher rd = request.getRequestDispatcher(page);
	    rd.forward(request, response);
	}

}
