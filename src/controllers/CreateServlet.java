package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Message;
import models.validators.MessageValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){

            EntityManager em = DBUtil.createEntityManager();

            Message m = new Message();

            String content = request.getParameter("content");

            m.setContent(content);

            Timestamp tim = new Timestamp(System.currentTimeMillis());
            m.setCreated_at(tim);
            m.setUpdatad_at(tim);

            List<String> errors = MessageValidator.validate(m);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token" , request.getSession().getId());
                request.setAttribute("mesaage" , m);
                request.setAttribute("error" , errors);//属性名の"error"をjsptest="${error != null}">と入力する

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/messages/new.jsp");
                rd.forward(request , response);
            } else {
                em.getTransaction().begin();
                em.persist(m);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush" , "登録が完了しました");
                em.close();

                response.sendRedirect(request.getContextPath() + "/index");
            }

     /*１回目em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            request.getSession().setAttribute("flush" , "登録しました");
            em.close();

            response.sendRedirect(request.getContextPath() + "/index");*/
        }
    }

}
