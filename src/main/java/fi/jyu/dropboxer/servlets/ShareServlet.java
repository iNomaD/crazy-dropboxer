package fi.jyu.dropboxer.servlets;

import fi.jyu.dropboxer.Config;
import fi.jyu.dropboxer.client.DropboxClient;
import fi.jyu.dropboxer.models.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

@WebServlet("/ShareServlet")
public class ShareServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("share").equals("true")){
            String cPath = (String) request.getSession().getAttribute("currentPath");
            Token token = (Token) request.getSession().getAttribute("token");
            if(cPath == null){
                cPath = Config.dropboxDir;
            }
            if(cPath.equals("")){
                request.getSession().setAttribute("shareLink",null);
                return;
            }
            DropboxClient dropboxClient = DropboxClient.getInstance();
            PrintWriter out = response.getWriter();
            try {
                out.write(dropboxClient.createShareableUrl(token.getAccessToken(),cPath).getUrl());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            out.flush();
            out.close();
        }

    }
}
