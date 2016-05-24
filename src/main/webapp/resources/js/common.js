function main() {
    $("#menu").load("menu.html");
    $("#footer").load("footer.html");
    route();
    $(window).bind('hashchange', function () {
        route();
    });
}

host = "http://" + location.hostname + ":" + 8080;

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
    try {
        var content = $("#content");
        content.empty();
        unload();
        unload = function () {
        };
        content.load(page + ".html");
    } catch (err) {
        console.log(err);
    }

}
unload = function () {

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
Controller = {
    get: function (url, callback) {
        $.ajax({
            url: host + url,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            success: callback
        });
    },
    post: function (url, data, callback) {
        $.ajax({
            url: host + url,
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: callback
        });
    },
    put: function (url, data, callback) {
        $.ajax({
            url: host + url,
            type: 'PUT',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: callback
        });
    },
    del: function (url, data, callback) {
        $.ajax({
            url: host + url,
            type: 'DELETE',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: callback
        });
    }
}
