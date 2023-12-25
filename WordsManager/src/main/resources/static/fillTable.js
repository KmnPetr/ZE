const closePopUp = document.getElementById('pop_up_close');
const popUp = document.getElementById('pop_up');
const pop_up_body = document.getElementById('pop_up_body');

closePopUp.addEventListener('click',()=>{
    popUp.classList.remove('active');
})

requestingListWords();

/*
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////*/
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
function fillTable(listWords){

    const keys = Object.keys(listWords[0]);
    console.log(keys);

    const tableListWords = document.getElementById("tableListWords");
    tableListWords.innerHTML = "";

    for (let i = 0; i < listWords.length; i++) {
        const newRow = tableListWords.insertRow(i);

        //добавляем слушатель на строку
        addLisenerOnRow(newRow,listWords[i].id);
        //заполняем строку
        fillRow(newRow,keys,listWords[i])
    }

}
function fillRow(row,keys,word){
    for (let i = 0; i < keys.length; i++) {
        var newCell = row.insertCell(i);
        newCell.innerHTML = word[keys[i]];
    }

}
function addLisenerOnRow(newRow,idValue){
    const idValueConst = idValue;
    newRow.onclick = function() {
        // window.location.href = 'editWord.html?idWord=' + idValueConst; //наверное больше не нужна

        fillPopUp(idValueConst);
        popUp.classList.add('active');

    };

}
function fillPopUp(idValueConst){
    fetchWordById(idValueConst);
}
function fetchWordById(id){
    fetch('/words/'+id)
        .then(response => response.json())
        .then(word => {

            const paragraph = document.createElement('p');
            paragraph.textContent = word.id+word.foreignWord;
            pop_up_body.appendChild(paragraph);

        })
        .catch(error => {
            console.error('Произошла ошибка:', error);
        });
}


