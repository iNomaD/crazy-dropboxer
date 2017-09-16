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

function uploadImage(){
    var Files = document.getElementById("select").files;

    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(Files[0]);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(Files[0]);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(Files[0]);
    }
    var content = document.getElementById("imgContent");
    content.src=url;
    document.getElementById('imgContent').style.display = 'block';

    var file = "path="+url+"&name="+Files[0].name;
    doAjax('MainPageServlet',file,'doQuery_back','post',0);
}