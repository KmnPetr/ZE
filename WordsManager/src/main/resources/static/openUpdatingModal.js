const modalWindow = document.getElementById('modal');
const apply_button = document.getElementById('apply_button');
const close_button = document.getElementById('close_button');
const title_modal = document.getElementById('title_modal');
const form_modal = document.getElementById('form_modal');
const idWord = document.createElement('h3');
let idWord_This = null;

function openUpdatingModal(idValue){
    modalWindow.classList.add('open');

    const idValueConst = idValue; /*чтобы это значение денамически не изменялось*/
    idWord_This = idValueConst;
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

    idWord.id = 'idWord';
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
    applyChanges();
    closeModal();
})
close_button.addEventListener('click',function (){
    closeModal();
});

/**
 * эти два метода скрывают модальное окно если клик был на серой области
 */
document.querySelector('#modal .modal_box').addEventListener('click',event => {
    event._isClickWithInModal = true;
});
document.getElementById('modal').addEventListener('click',event=>{
    if(event._isClickWithInModal) return;
    closeModal();
});

function closeModal(){
    form_modal.innerHTML = "";
    idWord_This = null;
    modalWindow.classList.remove('open');
}

function applyChanges(){
    const wordMap = new Map();
    wordMap.set('id',idWord_This.toString());
    let i = 0;
    for (; i < keys.length; i++) {
        var textarea = document.getElementById(keys[i]+'Word');
        var value = textarea.value;
        wordMap.set(keys[i],value);
    }


// Выводим все ключи Map
    wordMap.forEach((value, key) => {
        console.log(key+': '+value);
    });


    closeModal();
}