<%@ page import="fi.jyu.dropboxer.models.Token" %>
<%@ page import="fi.jyu.dropboxer.models.AccountInfo" %>
<%@ page import="fi.jyu.dropboxer.Config" %>
<%@ page import="fi.jyu.dropboxer.models.SharesInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Crazy Dropboxer</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/simple_table.css" />
    <script type='text/javascript' src='scripts/AJAXlib.js'></script>
    <script type='text/javascript' src='scripts/jquery-3.2.1.min.js'></script>
    <script type="application/javascript" src="scripts/jquery.form.min.js"></script>
    <script type='text/javascript' src='scripts/scripts.js'></script>
</head>
<body>
    <%
        Token token = (Token) session.getAttribute("token");
        String accessToken = token.getAccessToken();
        AccountInfo accountInfo = (AccountInfo) session.getAttribute("account_info");
    %>
    <h1>Welcome, <%=accountInfo.getDisplayName()%></h1>

    <div class="connectButton" onclick="location.href = 'content_page.jsp?path=<%=Config.dropboxDir%>'">List of uploaded images</div>
    <BR>

    <div>
        <div class="connectButton" id="shares" onclick="shares()">Create a share link</div>
        <div id="shareLink">Shared Link</div>
    </div>
    <BR>

    <form enctype="multipart/form-data" id="uploadFile" action="MainPageServlet" method="post">
        <div class="connectButton" style="position: relative">
            <input type="file" accept="image/png,image/gif,image/jpg" id="file" name="file" onchange="fileSelected()">
            <div id="selectedFileName">Select an Image</div>
        </div>
        <input type="submit" class="accessButton" id="upload" value="Send">
    </form>
    <BR>

    <div class="connectButton" id="stateButton" onclick="changeState()">Account Information</div>
    <BR>

    <div id="informationBlock">
        <table class="simple-little-table">
            <tr>
                <td>Given name</td>
                <td><%=accountInfo.getNameDetails().getGivenName()%></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><%=accountInfo.getNameDetails().getSurname()%></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><%=accountInfo.getEmail()%></td>
            </tr>
            <tr>
                <td>Uid</td>
                <td><%=accountInfo.getUid()%></td>
            </tr>
            <tr>
                <td>County</td>
                <td><%=accountInfo.getCountry()%></td>
            </tr>
            <tr>
                <td>Locale</td>
                <td><%=accountInfo.getLocale()%></td>
            </tr>
            <tr>
                <td>Referral link</td>
                <td><%=accountInfo.getReferralLink()%></td>
            </tr>
        </table>
        <table class="simple-little-table">
            <tr>
                <td>Quota outside of shared folders (bytes)</td>
                <td><%=accountInfo.getQuotaInfo().getNormal()%></td>
            </tr>
            <tr>
                <td>Quota in shared folders (bytes)</td>
                <td><%=accountInfo.getQuotaInfo().getShared()%></td>
            </tr>
            <tr>
                <td>Total quota (bytes)</td>
                <td><%=accountInfo.getQuotaInfo().getQuota()%></td>
            </tr>
        </table>
    </div>
    <img src="" id="imgContent">
</body>
</html>
