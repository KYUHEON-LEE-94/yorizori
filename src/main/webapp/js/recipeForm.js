/**
 * recipeForm 추가 버튼을 누르면 추가하는 기능
 */

let tr = document.querySelectorAll('.seq_blank');
let btnAdd = document.querySelector('.btnAdd');

function addTr() {
    let clone = tr[0].cloneNode(true);
    clone.value = '';
    tr[0].parentElement.appendChild(clone);
}

btnAdd.addEventListener('click', () => {
    addTr();
})
