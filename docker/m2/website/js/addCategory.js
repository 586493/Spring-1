import {addDclListener} from '/js/load.js';
import {getGatewayUrl} from '/js/config.js';

addDclListener(() =>
    document
        .getElementById("form")
        .addEventListener('submit', event => createCategory(event))
);

export function createCategory(event) {
    event.preventDefault();

    const json = {
        'name': document.getElementById('categoryName').value,
        'description': document.getElementById('categoryDescription').value,
    };

    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            if(this.status !== 200 && this.responseText) {
                alert(this.responseText);
            } else {
                window.location.replace('/category/list.html');
            }
        }
    };
    request.open("POST", getGatewayUrl() + '/api/categories/', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.send(JSON.stringify(json));
}


