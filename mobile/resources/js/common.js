function main(){
	$( "#menu" ).load("menu.html");
	$( "#footer" ).load("footer.html");
	$( "#menu" ).load("menu.html");
	route();
	$(window).bind('hashchange', function() {
		route();
	});

}
function route(){
	var url = document.URL;
	if(url.indexOf('#!')>0){
		if (url.indexOf('?')>0) var path = url.substring(url.indexOf('#!')+1, url.indexOf('?')) 
			else var path = url.substring(url.indexOf('!')+1);
		loadToContent(path);
	} else loadToContent('home')
}
function loadToContent(page){
	$( "#content" ).empty();
	$( "#content" ).load(page+".html");
}
function drawContent(phones, tpl, to){
	$('#'+tpl).tmpl(phones).appendTo('#'+to);
}
function sendEmail(html){
	$.ajax({
	  type: "POST",
	  url: "https://mandrillapp.com/api/1.0/messages/send.json",
	  data: {
	    key: 'plv6707RFJSqLan-KWMYFg',
	    message: {
	      from_email: 'chdavi@ya.ru',
	      to: [
	          {
	            email: 'chdavi@ya.ru',
	            name: 'RECIPIENT NAME (OPTIONAL)',
	            type: 'to'
	          }
	        ],
	      autotext: 'true',
	      subject: 'Заявка',
	      html:  html
	    }
	  }
	 }).done(function(response) {
	   console.log(response); // if you're into that sorta thing
	   if(response && response[0].status == "sent"){
	   		location.href = "#!send_email_succ";
	   } else {
	   		location.href = "#!send_email_err";
	   }
	
	$('#home').click(function(){
		window.location = '/';
	});

	 });
}