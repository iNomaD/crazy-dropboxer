package fi.jyu.dropboxer.servlets;

import fi.jyu.dropboxer.client.DropboxClient;
import fi.jyu.dropboxer.models.AccountInfo;
import fi.jyu.dropboxer.models.Token;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

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
        Part path = request.getPart("path");
        String name = request.getParameter("name");
        System.out.println(path);

        OutputStream out = null;
        InputStream filecontent = null;
        final PrintWriter writer = response.getWriter();

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + name));
            filecontent = path.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            writer.println("New file " + name + " created at " + path);
//            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}",
//                    new Object[]{fileName, path});
        } catch (FileNotFoundException fne) {
            writer.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            writer.println("<br/> ERROR: " + fne.getMessage());

//            LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}",
//                    new Object[]{fne.getMessage()});
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
        }
        System.out.println(out);
        try {
            DropboxClient dropboxClient = DropboxClient.getInstance();
            Token token = (Token) request.getSession().getAttribute("token");

            if(token != null) {
                dropboxClient.uploadFile(token.getAccessToken(),out.toString(),name);//fixd path
                System.out.println("pass");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
