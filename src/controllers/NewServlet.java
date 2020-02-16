package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*１回目
        EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();
        Message m = new Message();

        String content = "ok?";
        m.setContent(content);

        Timestamp cuTi = new Timestamp(System.currentTimeMillis());
        m.setCreated_at(cuTi);
        m.setUpdatad_at(cuTi);

        em.persist(m);
        em.getTransaction().commit();

        response.getWriter().append(Integer.valueOf(m.getId()).toString());*/
/*２回目*/
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("message", new Message());
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/new.jsp");
        rd.forward(request, response);
    }

}
