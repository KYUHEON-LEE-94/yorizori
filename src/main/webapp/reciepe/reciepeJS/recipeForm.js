/**
 * recipeForm 추가 버튼을 누르면 추가하는 기능
 */

let tr = document.querySelectorAll('.seq_blank');
let btn = document.querySelector('.btn');



btn.addEventListener('click', function () {
        let clone = tr[0].cloneNode(true);
        tr[0].parentElement.appendChild(clone);
})