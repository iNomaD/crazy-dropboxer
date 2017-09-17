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
    var Files = document.getElementById("select").files[0];
    // var reader = new FileReader();
    // var imgFile;
    // var content = document.getElementById("imgContent");
    // // reader.onload=function(e) {
    // //     imgFile = e.target.result;
    // //     console.log(imgFile);
    // //     content.src=imgFile;
    // // };
    // // reader.readAsDataURL(Files);
    // var url = null ;
    // // 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
    // if (window.createObjectURL!=undefined) { // basic
    //     url = window.createObjectURL(Files) ;
    // } else if (window.URL!=undefined) { // mozilla(firefox)
    //     url = window.URL.createObjectURL(Files) ;
    // } else if (window.webkitURL!=undefined) { // webkit or chrome
    //     url = window.webkitURL.createObjectURL(Files) ;
    // }
    // content.src=url;
    // document.getElementById('imgContent').style.display = 'block';
    //
    // // var src = content.src.split(",")[1];

    var formData = new FormData();
    formData.append('file',Files);
    var file = "path="+formData+"&name="+Files.name;
    doAjax('MainPageServlet',file,'doQuery_back','post',0);
}