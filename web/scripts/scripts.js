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
    alert("NOT IMPLEMENTED")
}