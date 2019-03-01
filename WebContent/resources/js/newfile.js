		var uzemegyseg="";
		
		function utarol(element){
			uzemegyseg=$("option:selected",element).text();
			console.log(uzemegyseg);
			
			$("#tablazat\\:dtable tbody tr").filter(function(){
				$(this).toggle(true);
				
				uzemSzur(this);
			})
			
			bSomSzur();
			
			betuk=new Array();
			$("#tablazat\\:dtable tbody tr").filter(function(){
				if($(this).css("display")!="none"){
					betuk.push($("td:nth-of-type(1) input",this).val().charAt(0))
				}
				})
			betuk=uniq(betuk);
		}
		
		function uzemSzur(element){
				var uzemRegex=new RegExp('^'+uzemegyseg+"$");	 
				if(uzemRegex.test($("td:nth-of-type(3)",$(element)).text())==false){
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
			b=uniq(b);
			
			$("#somForm2Id\\:beosztas option").filter(function(){
				$(this).toggle(true);
				
				if(b.includes(this.value)==false){
					$(this).toggle(false);
				}
			})
		}
		
		function uniq(a) {
		    var seen = {};
		    return a.filter(function(item) {
		        return seen.hasOwnProperty(item) ? false : (seen[item] = true);
		    });
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
				beosztasSzur(this);
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
				document.getElementById("betukfacet").innerHTML+='<button type="button" onclick="betuSzures('+botu+');">'+botu+'</button>';
			})
			
		}

		function beosztasSzur(element){
			console.log("beosztasSzur meghívodik");
			var beosztasRegex=new RegExp('^'+beosztas+"$");	 
			  if(beosztasRegex.test($("td:nth-of-type(4)",$(element)).text())==false){
				  $(element).toggle(false);
			  } else{
				  console.log($("td:nth-of-type(4)",$(element)).text());
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
							
						var betuRegex=new RegExp('^'+betu+'.*'); 
						if(betuRegex.test($("td:nth-of-type(1) input",this).val())==false){
						 $(this).toggle(false);
						} else{
						 if(uzemegyseg!=""){
						  uzemSzur(this);
						
						  if(beosztas!=""){
							  beosztasSzur(this);}
						 }
						 
						}
			})
	    }
	   
	    var kartyakod='';
		
		function addChar(c){
			kartyakod=kartyakod+c;
			console.log(kartyakod);
			if(kartyakod.length===5){
			 var form = document.getElementById('cForm');
			 form.submit();
			}
		}

		function del(){
			kartyakod='';
		}