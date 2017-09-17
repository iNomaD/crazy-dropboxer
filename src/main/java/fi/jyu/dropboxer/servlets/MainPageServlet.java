package fi.jyu.dropboxer.servlets;

import fi.jyu.dropboxer.client.DropboxClient;
import fi.jyu.dropboxer.models.AccountInfo;
import fi.jyu.dropboxer.models.SharesInfo;
import fi.jyu.dropboxer.models.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/MainPageServlet")
@MultipartConfig
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
    if(request.getPart("file")!=null) {
        Part filePart = request.getPart("file");
        if (filePart != null) {
            String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();

            try {
                DropboxClient dropboxClient = DropboxClient.getInstance();
                Token token = (Token) request.getSession().getAttribute("token");

                if (token != null) {
                    request.getSession().setAttribute("currentPath", dropboxClient.uploadFile(token.getAccessToken(), fileContent, name));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    }
}
