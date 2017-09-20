var xmlhttp = new getXMLObject();	//xmlhttp holds the ajax object

function getXMLObject(){ //XML OBJECT
    var xmlHttp = false;
    try{
	       xmlHttp = new ActiveXObject("Msxml2.XMLHTTP") // For Old Microsoft Browsers
    }
	catch(e1){
		try{
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") // For Microsoft IE 6.0+
		}
		catch (e2){
			xmlHttp = false // No Browser accepts the XMLHTTP Object then false
		}
	}
	if(!xmlHttp && typeof XMLHttpRequest != 'undefined'){
	          xmlHttp = new XMLHttpRequest(); //For Mozilla, Opera Browsers
    }
    return xmlHttp; // Mandatory Statement returning the ajax object created
}

var tata;
function handleServerResponse(){
    if(xmlhttp.readyState == 4){
        if(xmlhttp.status == 200){
            tata=xmlhttp.responseText.trim();
            show(tata);
        }
    }
}

function done(data){
    if(xmlhttp){
        xmlhttp.open("POST","/go",true); //getname will be the servlet name
        // after create cos /create/ will be the result and this is how it is mapped
        xmlhttp.onreadystatechange  = handleServerResponse;
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlhttp.send("fname="+data);
        // posting code, input, lang
        // encoding for sending special characters
   }
}

function go() {
    var data = document.getElementById('fname').value.trim();

    var y =data.split("\\");
    var key = y.length-1;
    
    done(y[key]);
}



function httpPOST(theUrl)
{
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
    xmlHttp.send( null );
    return xmlHttp.responseText;
}




function show(a){


    var lat_phar;
    var long_phar;

    var long_user;
    var lat_user;

    var tata2=a.split("#");
    var ip_user=tata2[0];
    var med = tata2[1];

    //alert(med);

    var phar=med.split("+");

    var result = httpPOST("http://freegeoip.net/json/"+ip_user);

    var obj1=JSON.parse(result);
    long_user = obj1.longitude;
    lat_user = obj1.latitude;

    var glat= [];
    var glong = [];
    var data = [];

    var j=0

    for(var i=0;i<phar.length;i++){

      var phar2=phar[i].split("|");

      data[j]=phar2[0]+" "+phar2[1]+" "+phar2[2]+" "+phar2[3]+" "+phar2[4];
      key10=phar2.length-1;

      var x1 = httpPOST("http://freegeoip.net/json/"+phar2[key10]);
      var obj1=JSON.parse(x1);
      long_phar = parseFloat(obj1.longitude);
      lat_phar = parseFloat(obj1.latitude);
      glat[j]=lat_phar;
      glong[j] = long_phar;
      j++;
    }



  showmap(glat, glong, data, j);
}
 


function showmap(glat, glong, data, n){

  var locations = [];


    for(var i=0;i<n;i++){
       var a = [data[i],glat[i],glong[i]];
       locations[i]= a;
    }

//alert(locations);

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 10,
      center: new google.maps.LatLng(28.68, 77.13),
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    for (i = 0; i < n-3; i++) {  
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        map: map
      });

      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infowindow.setContent(locations[i][0]);
          infowindow.open(map, marker);
        }
      })(marker, i));
    }

}





function handleServerResponse2(){
    if(xmlhttp.readyState == 4){
        if(xmlhttp.status == 200){
            var a = xmlhttp.responseText.trim();
            alert(a);
        }
    }
}

function done2(data){

    var name = document.getElementById('vname').value.trim();
    var time = document.getElementById('vtime').value.trim();
    var med = document.getElementById('vmed').value.trim();
    var quant = document.getElementById('vquant').value.trim();
    var prize = document.getElementById('vprize').value.trim();

    if(xmlhttp){
        xmlhttp.open("POST","/store",true); //getname will be the servlet name
        // after create cos /create/ will be the result and this is how it is mapped
        xmlhttp.onreadystatechange  = handleServerResponse2;
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlhttp.send("name="+name+"&timing="+time+"&medicine="+med+"&quantity="+quant+"&prize="+prize);
        // posting code, input, lang
        // encoding for sending special characters
   }
}



function store() {
    
    done2();
}









// for fading effect;
var showf,hidef, showt, hidet;


$(document).ready(function(){

    hidef = function(){        $("#final").hide();
    }

    showf = function(){
        $("#final").slideDown("slow");
    }

    hidet = function(){
        $("#fdata").hide();
    }

    showt = function(){
        $("#fdata").slideDown("slow");
    }


})
