
var kartyakod='';

function addChar(c){
	kartyakod=kartyakod+c;
	console.log(kartyakod);
	var csillagok="";
	for(var i=0;i <(kartyakod.length);i++){
		csillagok+="*";
	}
	$('#lbld')[0].innerHTML=csillagok;
	if(kartyakod.length===5){
		/*var gomb = document.getElementById('submitkkod');
 		form.submit();*/
		passkkodToInputHidden();
		$('#submitkkod\\:cmdBtn').click();
 		del();
	}
}

function del(){
	console.log("del()");
	kartyakod='';
	$('#lbld')[0].innerHTML=kartyakod;
}

function passkkodToInputHidden(){
	$('#submitkkod\\:inputHidden').val(kartyakod);
}