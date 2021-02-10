import {
    removeBodyContent,
    createKeyValueRow
} from "./table.js";
import {addDclListener} from "./load.js";
import {getObject} from "./api.js";
import {getParameter} from './query.js'

let fromCategory;
let returnUrl = '/';
let authorId = null;
let categoryId = null;

addDclListener(() => {
    fromCategory = !!(getParameter('categoryId'));
    if(fromCategory) {
        categoryId = getParameter('categoryId');
        returnUrl = '/category/details.html?id=' + categoryId;
    } else {
        authorId = getParameter('authorId');
        returnUrl = '/author/details.html?id=' + authorId;
    }
    let url;
    if(fromCategory) url = 'categories/' + categoryId;
    else url = 'authors/' + authorId;
    url = url + "/books/" + getParameter('bookId');
    getBook(url);
});

let author = null;
let book = null;
let category = null;

function getBook(url) {
    getObject(url, getAuthor);
}

function getAuthor (obj) {
    book = obj;
    getObject('authors/' + book.author, getCategory);
}

function getCategory (a) {
    author = a;
    getObject('categories/' + book.category, displayBook);
}

function displayBook(c) {
    category = c;
    let tabBody = document.getElementById('tabBody');
    removeBodyContent(tabBody);
    for (let [key, value] of Object.entries(book)) {
        if(key.localeCompare("author") === 0) {
            value = author.name;
        }
        else if(key.localeCompare("category") === 0) {
            value = category.name;
        }
        tabBody.appendChild(createKeyValueRow(key, value));
    }
}
