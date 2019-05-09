		var uzemegyseg="";
		
		$( window ).on( "load", function() {
			uzemegyseg="Büro";
			utarol($('#somFormId\\:terulet')[0]);
		})
		
		function utarol(element){
			uzemegyseg=$("option:selected",element).text();
			console.log(uzemegyseg);
			
			$("#tablazat\\:dtable tbody tr").filter(function(){
				$(this).toggle(true);
				
				uzemSzur(this);
			})
			
			bSomSzur();
			$('#somForm2Id\\:beosztas').val("");
			
			betuk=new Array();
			$("#tablazat\\:dtable tbody tr").filter(function(){
				if($(this).css("display")!="none"){
					betuk.push($("td:nth-of-type(1) input",this).val().charAt(0))
				}
				})
				
			betuk=uniq(betuk);
			betuk.sort();
			document.getElementById("betukfacet").innerHTML="";
			betuk.forEach(function(botu){
				document.getElementById("betukfacet").innerHTML+='<button type="button" onclick="betuSzures(\''+botu+'\');">'+botu+'</button>';
			})
		}
		
		function uzemSzur(element){
				//var uzemRegex=new RegExp('^'+uzemegyseg+"$");	 
				//if(uzemRegex.test($("td:nth-of-type(3)",$(element)).text())==uzemegyseg){
				if($("td:nth-of-type(3)",$(element)).text()!=uzemegyseg){
				 $(element).toggle(false);
				}
				
				
		}
		function bSomSzur(){
			var b=new Array();
			//var b=new Array();
			$("#tablazat\\:dtable tbody tr").filter(function(){
				if($(this).css("display")!="none"){
					b.push($("td:nth-of-type(4)",$(this)).text());
				}
			})
			b=uniq(b); //Kigyűjtöm a táblázatból a beosztasokat
			b.push("...");
			//Kiszedem az optionsból ami nem az üzemrészhez tartozó beosztások
			$("#somForm2Id\\:beosztas option").filter(function(){
				$(this).toggle(true);
				
				if(b.includes(this.value)==false){//Ha táblázatból kigyűjtött+"..." beosztas nem tartalmazza akkor elrejtem
					$(this).toggle(false);
				}
			})
		}
		
		function uniq(a) {
		    let set=new Set(a);
		    return Array.from(set);
		}
		
		
		var beosztas="";
		
		var betuk=new Array();
		
		function btarol(element){
			$("#tablazat\\:dtable tbody tr").filter(function(){
				$(this).toggle(true);
				
				uzemSzur(this);
			})
			
			beosztas=$("option:selected",element).text();
			$("#tablazat\\:dtable tbody tr").filter(function(){
				if(beosztas!="..."){
				beosztasSzur(this);
				}
			})
			
			betuk=new Array();
			$("#tablazat\\:dtable tbody tr").filter(function(){
				if($(this).css("display")!="none"){
					betuk.push($("td:nth-of-type(1) input",this).val().charAt(0))
				}
				})
				
			betuk=uniq(betuk);
			betuk.sort();
			document.getElementById("betukfacet").innerHTML="";
			betuk.forEach(function(botu){
				document.getElementById("betukfacet").innerHTML+='<button type="button" onclick="betuSzures(\''+botu+'\');">'+botu+'</button>';
			})
			
		}

		function beosztasSzur(element){
			//var beosztasRegex=new RegExp('^'+beosztas+".*$");
			  if($("td:nth-of-type(4)",$(element)).text()!=beosztas){
				$(element).toggle(false);
			  }
		}
		
		//$('#somFormId\\:terulet option:selected').text()
	    function foo(element) {
	        var $element = $(element);
	        return $element.children(0).text();
	    }

	   function betuSzures(betu){
			  $("#tablazat\\:dtable tbody tr").filter(function(){
		
						$(this).toggle(true);
						uzemSzur(this);

						if(optionoklistaba().includes(beosztas)){
							beosztasSzur(this);
						}
						//Ha nem volt btarol()==kivalasztva a som-ból, akkor beosztas=""  és nincs mire a betuszurest elvégezni
						var betuRegex=new RegExp('^'+betu+'.*'); 
						if(betuRegex.test($("td:nth-of-type(1) input",this).val())==false){
						 $(this).toggle(false);
						}
						
			})
	    }
	   
	   //->beosztasok Dropdown.t listába-> majd betuszuresben beosztasSzur(this)-t csak akkor hajtsa végre ha beosztas benne van a dropdownba
	   function optionoklistaba(){
		   var optionok=new Array();
		   $("#somForm2Id\\:beosztas option").filter(function(){
			   if($(this).css("display")!="none"){
			   optionok.push($(this).val());
			   }
		   });
		   return optionok;
	   }
	   
	    var kartyakod='';
		
		function addChar(c){
			kartyakod=kartyakod+c;
			console.log(kartyakod);
			$('#lbld')[0].innerHTML=kartyakod;
			if(kartyakod.length===5){
			 /*var form = document.getElementById('cForm');
			 form.submit();*/
			  $("#tablazat\\:dtable tbody tr").filter(function(){
					$(this).toggle(true);
					
					var dkodRegex=new RegExp('^'+kartyakod+'.*'); 
					if(dkodRegex.test($("td:nth-of-type(2)",this).text())==false){
					 $(this).toggle(false);
					}
			  });
			  kartyakod='';
			  $('#lbld')[0].innerHTML=kartyakod;
			}
		}

		function del(){
			kartyakod='';
			$('#lbld')[0].innerHTML=kartyakod;
			utarol($('#somFormId\\:terulet')[0])
		}