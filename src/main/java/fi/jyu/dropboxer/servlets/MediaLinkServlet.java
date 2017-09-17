package fi.jyu.dropboxer.servlets;

import fi.jyu.dropboxer.client.DropboxClient;
import fi.jyu.dropboxer.models.Media;
import fi.jyu.dropboxer.models.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MediaLinkServlet")
public class MediaLinkServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        Token token = (Token)request.getSession().getAttribute("token");
        DropboxClient dropboxClient = DropboxClient.getInstance();
        try {
            Media media = dropboxClient.getMedia(token.getAccessToken(), path);
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.sendRedirect(media.getUrl());
        }
        catch (Exception e){
            e.printStackTrace();
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.sendRedirect("/error_page.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
