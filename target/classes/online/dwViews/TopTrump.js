
var category;


function selectAI(){
	var aiPlayer=document.getElementById("mySelect");
	var value = aiPlayer.options[aiPlayer.selectedIndex].value;
	alert(value);
	// return value;
}

function selectDisable() {
  document.getElementById("mySelect").disabled = true;
}

var click;
function cargoButton() {
        click = 4;
        alert("You selected Cargo.");
}

function firepowerButton() {
        click = 3;
        alert("You selected Firepower.");
}

function rangeButton() {
        click = 2;      
        alert("You selected Range.");
}

function speedButton() {
        click = 1;
        alert("You selected Speed.");
}

function sizeButton() {
        click = 0;
        alert("You selected Size.");
}