import {removeOptions, createOption} from "./select.js";
import {addDclListener} from '/js/load.js';
import {getGatewayUrl} from '/js/config.js';
import {getParameter} from "./query.js";
import {getObject} from "./api.js";

let fromCategory;
let returnUrl = '/';
let authorSelectElem;
let categorySelectElem;
let fromAuthorId = null;
let fromCategoryId = null;

addDclListener(() => {
    authorSelectElem = document.getElementById('authorId');
    categorySelectElem = document.getElementById('categoryId');
    fromCategory = !!(getParameter('categoryId'));
    if(fromCategory) {
        fromCategoryId = parseInt(getParameter('categoryId'));
        returnUrl = '/category/details.html?id=' + fromCategoryId;
    } else {
        fromAuthorId = parseInt(getParameter('authorId'));
        returnUrl = '/author/details.html?id=' + fromAuthorId;
    }
    authorSelectElem.disabled = !fromCategory;
    categorySelectElem.disabled = fromCategory;
    document
        .getElementById("form")
        .addEventListener('submit', event => createBook(event))
    getObject('authors', authorSelect);
    getObject('categories', categorySelect);
});

function authorSelect(list) {
    removeOptions(authorSelectElem);
    list.authors.forEach(a => {
        authorSelectElem.appendChild(createOption(a.name, a.id, fromAuthorId));
    })
}

function categorySelect(list) {
    removeOptions(categorySelectElem);
    list.categories.forEach(b => {
        categorySelectElem.appendChild(createOption(b.name, b.id, fromCategoryId));
    })
}

export function createBook(event) {
    event.preventDefault();

    const json = {
        'title': document.getElementById('title').value,
        'authorId': document.getElementById('authorId').value,
        'categoryId': document.getElementById('categoryId').value,
        'price': document.getElementById('price').value,
        'description': document.getElementById('description').value,
        'isbn': document.getElementById('isbn').value,
        'publicationYear': document.getElementById('publicationYear').value,
        'pages': document.getElementById('pages').value
    };

    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            if(this.status !== 200 && this.responseText) {
                alert(this.responseText);
            } else {
                window.location.replace(returnUrl);
            }
        }
    };
    request.open("POST", getGatewayUrl() + '/api/books/', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.send(JSON.stringify(json));
}


