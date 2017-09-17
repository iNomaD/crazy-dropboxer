<%@ page import="fi.jyu.dropboxer.models.Token" %>
<%@ page import="fi.jyu.dropboxer.Config" %>
<%@ page import="fi.jyu.dropboxer.models.Metadata" %>
<%@ page import="fi.jyu.dropboxer.client.DropboxClient" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crazy Dropboxer</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/simple_table.css" />
    <script type='text/javascript' src='scripts/AJAXlib.js'></script>
    <script type='text/javascript' src='scripts/scripts.js'></script>
</head>
<body>
    <%
        Token token = (Token) session.getAttribute("token");
        String accessToken = token.getAccessToken();
        String path = request.getParameter("path");

        DropboxClient dropboxClient = DropboxClient.getInstance();
        Metadata metadata = dropboxClient.getMetadata(accessToken, path);
    %>
    <h1>Contents of the folder "<%=metadata.getPath()%>"</h1>

    <%
        List<Metadata> revContents = metadata.getContents();
        if(revContents.isEmpty()){
            %>
                <h3>Folder is empty</h3>
            <%
        }
        else{
            %>
                <table class="simple-little-table">
                <tr>
                    <td></td>
                    <td>Path</td>
                    <td>Size</td>
                </tr>
            <%
            Collections.reverse(revContents);
            for(Metadata content : revContents){ %>
                <tr>
                    <td>
                        <img src="<%= content.getIsDir()?"images/folder.jpg":"images/file.png" %>" alt="some image" class="icon">
                    </td>
                    <td>
                        <a href="<%=content.getIsDir()?"content_page.jsp":"MediaLinkServlet"%>?path=<%=content.getPath()%>"><%=content.getPath()%></a>
                    </td>
                    <td>
                        <%=content.getIsDir() ? "" : content.getSize()%>
                    </td>
                </tr>
            <% } %>
            </table>
        <% } %>
</body>
</html>