var HOST = 'SecondWebApp';

function ajaxErrorHandle(res, a, b) {
    if(res.status == 401) {
        window.location.href='/' + HOST + '/page/notlogin.html';
    } else {
        alert(res.status + ":" + a + ":" + b);
    }
}