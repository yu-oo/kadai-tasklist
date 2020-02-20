package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*1回目
        EntityManager em  = DBUtil.createEntityManager();
        List<Message> messages = em.createNamedQuery("getMessage",Message.class)
                        .getResultList();//←メソッド  問い合わせ結果をリスト形式で習得
        response.getWriter().append(Integer.valueOf(messages.size()).toString());
        em.close();*/

/*２回目
        EntityManager em = DBUtil.createEntityManager();
        List<Message> messages = em.createNamedQuery("getMessage" , Message.class)
                        .getResultList();
        em.close();
        request.setAttribute("message", messages);

        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush" , request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);*/

/*３回目*/
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;

        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch (NumberFormatException e) {}

        List<Message> messages = em.createNamedQuery("getMessage" , Message.class)
                                 .setFirstResult(15 * (page - 1))
                                 .setMaxResults(15)
                                 .getResultList();

        long message_count = (long)em.createNamedQuery("getMessageCount" , Long.class)
                             .getSingleResult();

        em.close();

        request.setAttribute("message", messages);
        request.setAttribute("message_count" , message_count);
        request.setAttribute("page" , page);

        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush" , request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/index.jsp");
        rd.forward(request, response);

    }

}
