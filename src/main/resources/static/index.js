const url = "http://localhost:8081";
const $site = document.querySelector('#site')
let table = null;

function createTable(titleRow) {
    if (table != null) {
        table.innerHTML = "";
        table.remove();
    }
    table = document.createElement("table");
    document.getElementById("table").appendChild(table);
    table.setAttribute("class", "table table-hover");
    addDataToTable(titleRow, "");
}

function addDataToTable(product, button) {
    row = table.insertRow();
    for (let key in product) {
        cell = row.insertCell();
        cell.innerHTML = product[key];
    }

    if (button != "") {
        cell = row.insertCell();
        cell.innerHTML = button;
    }
}

function getProducts() {
    createTable({"id": "ID", "title": "Title", "price": "Price", "action": "Action"});

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (this.status === 200 && this.readyState === 4) {
            JSON.parse(xmlHttp.responseText).forEach(data => {
                addDataToTable(data, getButton("addToCart", [data], "To cart"));
            });

            document.getElementById("title").innerHTML = "Products Page";

            var button = document.getElementById("mainButton");
            button.innerHTML = "Cart";
            button.setAttribute('onclick','getCart()')
        };
    };
    xmlHttp.open("GET", url + "/products", false);
    xmlHttp.send(null);
}

function getCart() {
    createTable({"id": "ID", "title": "Title", "price": "Price", "count": "Count", "totalPrice": "Total price", "action": "Action"});

    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (this.status === 200 && this.readyState === 4) {
        console.log(xmlHttp.responseText);
            JSON.parse(xmlHttp.responseText).forEach(data => {
                addDataToTable(data, getButton("deleteFromCartById", [data["id"]], "Delete"));
            });

            document.getElementById("title").innerHTML = "Cart";

            var button = document.getElementById("mainButton");
            button.innerHTML = "Products page";
            button.setAttribute("onclick", "getProducts()");
        };
    };
    xmlHttp.open("GET", url + "/cart", false);
    xmlHttp.send(null);
}

function addToCart(product) {
    console.log(product);
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("POST", url + '/cart', true);
    xmlHttp.setRequestHeader("Content-Type", "application/json");

    xmlHttp.onreadystatechange = () => {
        if (xmlHttp.readyState === XMLHttpRequest.DONE && xmlHttp.status === 200) {
            alert(xmlHttp.responseText);
        }
    }
    xmlHttp.send(JSON.stringify(product));
}

function deleteFromCartById(id) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
            if (this.status === 200 && this.readyState === 4) {
                getCart();
            } else {
                alert(xmlHttp.responseText)
            }
        };
    xmlHttp.open("DELETE", url + "/cart/" + id, false);
    xmlHttp.send(null);
}

function reload(elementName){
    var container = document.getElementById(elementName);
    var content = container.innerHTML;
    container.innerHTML = content;

    console.log("Refreshed");
}

function getButton(func, params, text) {
    var button = "<button class='btn btn-success' onclick='" + func + "(";
    var lastIndex = params.length - 1;
    var index = 0;
    params.forEach(param => {
        button = button + JSON.stringify(param);
        if (lastIndex != index) {
            button = button + ", ";
        };
        index++;
    });

    button = button + ")'>" + text + "</button>";

    console.log(button);

    return button;
}

getProducts()
