function main() {
    $("#menu").load("menu.html");
    $("#footer").load("footer.html");
    route();
    $(window).bind('hashchange', function () {
        route();
    });
}
function route() {
    var url = document.URL;
    if (url.indexOf('#!') > 0) {
        var path;
        if (url.indexOf('?') > 0)
            path = url.substring(url.indexOf('#!') + 1, url.indexOf('?'));
        else path = url.substring(url.indexOf('!') + 1);
        loadToContent(path);
    } else loadToContent('home')
}
function loadToContent(page) {
    var content = $("#content");
    content.empty();
    content.load(page + ".html");
}
function drawContent(phones, tpl, to) {
    $('#' + tpl).tmpl(phones).appendTo('#' + to);
}

function getJson(url, callback) {
    $.ajax({
        url: url,
        type: 'GET',
        success: callback
    });
}