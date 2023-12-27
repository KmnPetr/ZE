const modalWindow = document.getElementById('modal');
const apply_button = document.getElementById('apply_button');
const close_button = document.getElementById('close_button');
const title_modal = document.getElementById('title_modal');
const form_modal = document.getElementById('form_modal');
const idWord = document.createElement('h3');
function openUpdatingModal(idValue){
    modalWindow.classList.add('open');

    const idValueConst = idValue; /*чтобы это значение денамически не изменялось*/
    fillModalWindow(idValueConst);
}

function fillModalWindow(idValueConst){
    fetchWordById(idValueConst);
}
function fetchWordById(id){
    fetch('/words/'+id)
        .then(response => response.json())
        .then(word => {
            fillModalBox(word);
        })
        .catch(error => {
            console.error('Произошла ошибка:', error);
        });
}
function fillModalBox(word){
    title_modal.innerText = 'Updating the word!';

    form_modal.innerHTML = "";

    idWord.id = 'id';
    idWord.innerText = 'Id: '+word.id;
    form_modal.appendChild(idWord);

    let i = 1;
    for (; i < keys.length; i++) {
        const input = document.createElement('textarea');
        input.type = 'text';
        input.placeholder = keys[i];
        input.id = keys[i]+'Word';
        if (word[keys[i]]!=null){
            input.value = word[keys[i]];
        }
        const defaultValue = word[keys[i]];
        input.addEventListener('input', function() {
            if (input.value !== defaultValue) {
                input.style.color = "blue";
            } else {
                input.style.color = "black";
            }
        });
        form_modal.appendChild(input);
    }
}

apply_button.addEventListener('click',function (){
    console.log('Touch Apply Button');
})
close_button.addEventListener('click',function (){
    form_modal.innerHTML = "";
    modalWindow.classList.remove('open');
})