$(document).ready(function () {
    $('#example').DataTable();
});

function addElementImage() {
    var soluong = document.getElementById("number-of-image").value;
    var text = "";
    for (var i = 1; i <= soluong; i++) {
        text += '<label>Image ' + i + '</label><br><input type="file" name = "file" class="btn btn-default">';
    }
    var div = document.getElementById('images');
    div.innerHTML = text;
    //console.log(text);
}

function addDetail() {
    var row = document.getElementById("row-data");
    var color = document.getElementById("color").value;
    var sizee = document.getElementById("size").value;
    var quantity = document.getElementById("quantity").value;
    //alert(color+sizee+quantity);
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "add-detail?color=" + color + "&size=" + sizee + "&quantity=" + quantity);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //console.log(this.responseText);
            if (this.responseText === "DeleteDetail") {
                document.getElementById("info").style.display = "block";
                setTimeout(a, 1000);
            } else {
                document.getElementById("info").style.display = "none";
                row.innerHTML += this.responseText;
            }
        }
    };
}
setTimeout(a, 3000);
var a = function () {
    document.getElementById("info").style.display = "none";
};
function addMoreDetail() {
    var row = document.getElementById("row-data");
    var color = document.getElementById("color").value;
    var sizee = document.getElementById("size").value;
    var quantity = document.getElementById("quantity").value;
    //alert(color+sizee+quantity);
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "add-more-detail?color=" + color + "&size=" + sizee + "&quantity=" + quantity);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            // console.log(this.responseText);
            if (this.responseText === "DeleteDetail") {
                document.getElementById("returntext").innerHTML = "Pealse update quantity or delete detail";
                document.getElementById("info").style.display = "block";
            } else {
                document.getElementById("info").style.display = "none";
                row.innerHTML += this.responseText;
            }
        }
    };
}

function deleteMoreDetail(idHtml) {
    var color = document.getElementById("tdcolor" + idHtml).innerHTML;
    var sizee = document.getElementById("tdsize" + idHtml).innerHTML;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "delete-more-detail?color=" + color + "&size=" + sizee);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var element = document.getElementById(idHtml);
            element.parentNode.removeChild(element);
            //console.log(this.responseText);
        }
    };
}

function updateQuantityDetail(idHtml) {
    var color = document.getElementById("oldtdcolor" + idHtml).innerHTML;
    var sizee = document.getElementById("oldtdsize" + idHtml).innerHTML;
    var quantity = document.getElementById("newquantity" + idHtml).value;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "update-more-detail?color=" + color + "&size=" + sizee + "&quantity=" + quantity);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var newquantyti = document.getElementById("view" + idHtml).innerHTML;
            if (this.responseText === "false") {

            } else {
                var soluong = parseInt(this.responseText, 10);
                document.getElementById("view" + idHtml).innerHTML = soluong;
            }
            //console.log(this.responseText);
        }
    };
}

function deleteDetail(idHtml) {
    var color = document.getElementById("tdcolor" + idHtml).innerHTML;
    var sizee = document.getElementById("tdsize" + idHtml).innerHTML;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "delete-detail?color=" + color + "&size=" + sizee);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            var element = document.getElementById(idHtml);
            element.parentNode.removeChild(element);
            //console.log(this.responseText);
        }
    };
}

function deleteImage(imageId, name) {
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "../delete-image?id=" + imageId + "&name=" + name);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {

            document.getElementById("image" + imageId).src = this.responseText;
            //console.log(this.responseText);
        }
    };
}



