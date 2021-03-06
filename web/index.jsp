<%@ page import="fi.jyu.dropboxer.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Crazy Dropboxer</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type='text/javascript' src='scripts/AJAXlib.js'></script>
    <script type='text/javascript' src='scripts/scripts.js'></script>
  </head>
  </head>
  <body>
    <h1>Login to Crazy Dropboxer</h1>
    <%
        String contextPath = request.getContextPath();
        String requestURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +  request.getRequestURI();
        String code = request.getParameter("code");

        String location = "https://www.dropbox.com/1/oauth2/authorize";
        location += "?response_type=code";
        location += "&client_id=" + Config.appKey;
        location += "&redirect_uri=" + requestURL;
    %>
        <div class="connectButton" onclick="location.href = '<%=location%>'">Connect</div>
        <img src="images/gnomePenguinDropBox.jpg" alt="some image" id="penguin">
    <%
        if(code != null){
    %>
        <BR>
        <div class="accessButton" onclick="location.href = '<%=contextPath%>/MainPageServlet?code=<%=code%>&redirect_uri=<%=requestURL%>'">Access</div>
    <%
        }
    %>
  </body>
</html>
