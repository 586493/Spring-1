import {addDclListener} from '/js/load.js';
import {getGatewayUrl} from '/js/config.js';

addDclListener(() =>
    document
        .getElementById("form")
        .addEventListener('submit', event => createAuthor(event))
);

export function createAuthor(event) {
    event.preventDefault();

    const json = {
        'name': document.getElementById('authorName').value,
        'birthDate': document.getElementById('authorBirthDate').value,
        'nationality': document.getElementById('authorNationality').value
    };

    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState === 4) {
            if(this.status !== 200 && this.responseText) {
                alert(this.responseText);
            } else {
                window.location.replace('/author/list.html');
            }
        }
    };
    request.open("POST", getGatewayUrl() + '/api/authors/', true);
    request.setRequestHeader('Content-Type', 'application/json');
    request.send(JSON.stringify(json));
}


