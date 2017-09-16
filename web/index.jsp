<%@ page import="fi.jyu.dropboxer.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Crazy Dropboxer</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type='text/javascript' src='scripts/AJAXlib.js'></script>
    <script type='text/javascript' src='scripts/script.js'></script>
  </head>
  </head>
  <body>
    <h1>Login to Crazy Dropboxer</h1>
    <%
        String requestURL = request.getRequestURL().toString();
        String code = request.getParameter("code");

        String location = "https://www.dropbox.com/1/oauth2/authorize";
        location += "?response_type=code";
        location += "&client_id=" + Config.appKey;
        location += "&redirect_uri=" + requestURL;
    %>
        <div class="connectButton" onclick="location.href = '<%=location%>'">Connect</div>
    <%
        if(code != null){
    %>
        <div class="accessButton" onclick="location.href = '/MainPageServlet?code=<%=code%>&redirect_uri=<%=requestURL%>'">Access</div>
    <%
        }
    %>
  </body>
</html>
