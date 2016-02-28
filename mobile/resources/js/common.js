function main(){
	$( "#menu" ).load("menu.html");
	$( "#footer" ).load("footer.html");
	$( "#menu" ).load("menu.html");
	this.route();
	$(window).bind('hashchange', function() {
		this.route();
	});
}
function route(){
	var url = document.URL;
	if(url.indexOf('#!')>0){
		if (url.indexOf('?')>0) var path = url.substring(url.indexOf('#!')+1, url.indexOf('?')) 
			else var path = url.substring(url.indexOf('!')+1);
		this.loadToContent(path);
	} else loadToContent('home')
}
function loadToContent(page){
	$( "#content" ).empty();
	$( "#content" ).load(page+".html");
}
function drawContent(phones, tpl, to){
	$('#'+tpl).tmpl(phones).appendTo('#'+to);
}

function getJson(url, callback){
	$.ajax({
		url: url,
		type:'GET',
		success: callback
	});
}