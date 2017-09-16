<%@ page import="fi.jyu.dropboxer.client.DropboxClient" %>
<%@ page import="fi.jyu.dropboxer.models.Token" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Crazy Dropboxer</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type='text/javascript' src='scripts/AJAXlib.js'></script>
    <script type='text/javascript' src='scripts/script.js'></script>
</head>
<body>
    <%
        Token token = (Token) session.getAttribute("token");
    %>
    <h1>Welcome, user<%=token.getUid()%></h1>
</body>
</html>
