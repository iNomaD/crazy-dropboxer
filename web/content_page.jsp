<%@ page import="fi.jyu.dropboxer.models.Token" %>
<%@ page import="fi.jyu.dropboxer.models.AccountInfo" %>
<%@ page import="fi.jyu.dropboxer.Config" %>
<%@ page import="fi.jyu.dropboxer.models.Metadata" %>
<%@ page import="fi.jyu.dropboxer.client.DropboxClient" %>
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

        DropboxClient dropboxClient = DropboxClient.getInstance();
        Metadata metadata = dropboxClient.getMetadata(accessToken, Config.dropboxDir);
        System.out.println(metadata);
    %>
    <h1>Contents of the folder "<%=Config.dropboxDir%>"</h1>

    <table class="simple-little-table">
        <tr>
            <td></td>
            <td>Path</td>
            <td>Size</td>
        </tr>
    <% for(Metadata content : metadata.getContents()){ %>
        <tr>
            <td><%=content.getIsDir()?"folder":"file"%></td>
            <td><%=content.getPath()%></td>
            <td><%=content.getSize()%></td>
        </tr>
    <% } %>
    </table>

</body>
</html>
