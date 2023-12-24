function requestingListWords(){
    fetch('/words')
        .then(response => response.json())
        .then(listWords => {
            fillTable(listWords);
        })
        .catch(error => {
            console.error('Произошла ошибка:', error);
        });
}