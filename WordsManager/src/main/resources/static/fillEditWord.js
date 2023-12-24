async function fillEditWord() {
    const idWord = getIdWord()

    const word = await fetchData(idWord);

    showWord(word);
}

/**
 * Функция получит параметр 'idWord' из url
 */
function getIdWord(){
    let url = window.location.href;
    let params = new URLSearchParams(url.split('?')[1]);
    let param = params.get('idWord');

    return param;
}
async function fetchData(id) {
    try {
        const response = await fetch('/words/' + id);
        const word = await response.json();
        console.log(word);
        return word;
    } catch (error) {
        console.error('Произошла ошибка:', error);
    }
}

function showWord(word){
    const keys = Object.keys(word);

    const editableWord = document.getElementById("editableWord");
    editableWord.innerHTML = ""; // очищаем содержимое элемента

    for (let i = 0; i < keys.length; i++) {
        const h2 = document.createElement("h2");
        h2.textContent = keys[i]+': '+ word[keys[i]];
        editableWord.appendChild(h2);
    }
}