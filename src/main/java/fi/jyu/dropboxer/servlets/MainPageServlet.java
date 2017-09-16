package fi.jyu.dropboxer.servlets;

import fi.jyu.dropboxer.client.DropboxClient;
import fi.jyu.dropboxer.models.AccountInfo;
import fi.jyu.dropboxer.models.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MainPageServlet")
public class MainPageServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        String redirectUri = request.getParameter("redirect_uri");

        try {
            DropboxClient dropboxClient = DropboxClient.getInstance();
            Token token = dropboxClient.getAccessToken(code, redirectUri);


            if(token != null) {
                AccountInfo accountInfo = dropboxClient.getAccountInfo(token.getAccessToken());
                if(accountInfo != null){
                    request.getSession().setAttribute("token", token);
                    request.getSession().setAttribute("account_info", accountInfo);

                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.sendRedirect("/main_page.jsp");
                    return;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.sendRedirect("/error_page.jsp");
    }
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        String name = request.getParameter("name");
        System.out.println(path);
        try {
            DropboxClient dropboxClient = DropboxClient.getInstance();
            Token token = (Token) request.getSession().getAttribute("token");

            if(token != null) {
                dropboxClient.uploadFile(token.getAccessToken(),"D://F//079.PNG",name);//fixd path
                System.out.println("pass");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
