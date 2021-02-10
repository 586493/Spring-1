import {
    removeBodyContent,
    createKeyValueRow,
    createTextCell,
    createBtnCellWithOnClick,
    createBtnCellWithListener
} from "./table.js";
import {addDclListener} from "./load.js";
import {getObject} from "./api.js";
import {getParameter} from './query.js'
import {getGatewayUrl} from "./config.js";

addDclListener(() => getAuthor());


function getAuthor() {
    getObject('authors/' + getParameter('id'), displayAuthor);
}

function displayAuthor(obj) {
    let newBookBtn = document.getElementById('newBookBtn');
    newBookBtn.setAttribute('onclick',
        "window.location.href = '/book/add.html?authorId=" + getParameter('id') + "'")
    let tabBody = document.getElementById('tabBody');
    removeBodyContent(tabBody);
    for (const [key, value] of Object.entries(obj)) {
        tabBody.appendChild(createKeyValueRow(key, value));
    }
    getObject('authors/' + getParameter('id') + '/books', displayBooks)
}

function displayBooks(list) {
    let tabBody2 = document.getElementById('tabBody2');
    removeBodyContent(tabBody2);
    list.books.forEach(b => {
        tabBody2.appendChild(createBookRow(b));
    })
}

function createBookRow(book) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(book.title));
    tr.appendChild(createBtnCellWithOnClick('details',
        "window.location.href = '/book/details.html?authorId="
        + getParameter('id') + "&bookId=" + book.id + "'"));
    tr.appendChild(createBtnCellWithOnClick('edit',
        "window.location.href = '/book/edit.html?authorId="
        + getParameter('id') + "&bookId=" + book.id + "'"));
    tr.appendChild(createBtnCellWithListener("\u00D7",
        () => removeBook(book.id)));
    return tr;
}

function removeBook(id) {
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            getAuthor();
        }
    };
    request.open("DELETE", getGatewayUrl() + '/api/books/' + id, true);
    request.send();
}
