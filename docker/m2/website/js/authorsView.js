import {
    removeBodyContent,
    createTextCell,
    createBtnCellWithListener,
    createBtnCellWithOnClick
} from '/js/table.js';
import {getGatewayUrl} from '/js/config.js';
import {addDclListener} from '/js/load.js';
import {getObject} from '/js/api.js';

addDclListener(() => getAuthors());

function getAuthors() {
    getObject('authors', displayAuthors);
}

function displayAuthors(list) {
    let tabBody = document.getElementById('tabBody');
    removeBodyContent(tabBody);
    list.authors.forEach(a => {
        tabBody.appendChild(createAuthorRow(a));
    })
}

function createAuthorRow(author) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(author.name));
    tr.appendChild(createBtnCellWithOnClick('details',
        "window.location.href = '/author/details.html?id=" + author.id + "'"));
    tr.appendChild(createBtnCellWithOnClick('edit',
        "window.location.href = '/author/edit.html?id=" + author.id + "'"));
    tr.appendChild(createBtnCellWithListener("\u00D7",
        () => removeAuthor(author.id)));
    return tr;
}

function removeAuthor(id) {
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            getAuthors();
        }
    };
    request.open("DELETE", getGatewayUrl() + '/api/authors/' + id, true);
    request.send();
}




