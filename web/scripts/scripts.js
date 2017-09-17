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

$(function () {
    $('#uploadFile').ajaxForm({
        success:function (msg) {
            alert("uploaded!");
        },error:function (msg) {
            alert("error");
        }
    });
});

function shares() {
    var share = "share=true";
    doAjax("ShareServlet",share,'doQuery_back','post',0);
}
function doQuery_back(result)
{
    if (result.substring(0,5)=='error'){
        window.document.getElementById('shareLink').style.display = 'block';
        window.document.getElementById('shareLink').innerHTML="<p style='color:red;'><b>"+result.substring(6)+"</b></p>";
    }else{
        window.document.getElementById('shareLink').innerHTML= result;
        window.document.getElementById('shareLink').style.color="#000";
    }
}