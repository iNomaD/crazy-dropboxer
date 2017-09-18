currentState = false;

function changeState() {
    if(currentState){
        hideInformation()
    }
    else{
        showInformation();
    }
    currentState = !currentState;
}

function showInformation(){
    document.getElementById('informationBlock').style.display = 'block';
    document.getElementById('stateButton').innerHTML = 'Hide Information';
}

function hideInformation(){
    document.getElementById('informationBlock').style.display = 'none';
    document.getElementById('stateButton').innerHTML = 'Show Information';
}

function fileSelected() {
    document.getElementById('upload').style.display = 'block';
    document.getElementById('selectedFileName').innerHTML = document.getElementById('file').value;
}

function fileUploaded(){
    document.getElementById('upload').style.display = 'none';
    document.getElementById('selectedFileName').innerHTML = "Select new file";
}

$(function () {
    $('#uploadFile').ajaxForm({
        success:function (msg) {
            fileUploaded();
            hideLinks(false);
            showSharedLink(false)
            alert("uploaded!");
        },error:function (msg) {
            fileUploaded();
            alert("error");
        }
    });
});

function shares() {
    var share = "share=true";
    doAjax("ShareServlet",share,'doQuery_back','post',0);
}

function showSharedLink(condition) {
    if(condition){
        document.getElementById('shareLink').style.display = 'block';
        document.getElementById('shares').style.display = 'none';
    }else{
        document.getElementById('shareLink').style.display = 'none';
        document.getElementById('shares').style.display = 'block';
    }
}

function doQuery_back(result)
{
    if (result.substring(0,5)=='error'){
        window.document.getElementById('shareLink').style.display = 'block';
        window.document.getElementById('shareLink').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
    }else{
        window.document.getElementById('shareLink').innerHTML= result;
        window.document.getElementById('shareLink').style.color="#000";
        showSharedLink(true);
    }
}

function hideLinks(condition){
    if(condition)
        window.document.getElementById('links').style.display = 'none';
    else
        window.document.getElementById('links').style.display = 'block';
}