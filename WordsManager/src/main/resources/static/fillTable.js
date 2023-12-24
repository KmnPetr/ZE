function fillTable(listWords){

    const keys = Object.keys(listWords[0]);
    console.log(keys);

    const tableListWords = document.getElementById("tableListWords");
    tableListWords.innerHTML = "";

    for (let i = 0; i < listWords.length; i++) {
        const newRow = tableListWords.insertRow(i);

        //добавляем слушатель на строку
        const idValue = listWords[i].id;
        newRow.onclick = function() {
            window.location.href = 'editWord.html?idWord=' + idValue;
        };
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