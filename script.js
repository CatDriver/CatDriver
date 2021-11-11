let loginForm = document.querySelector('.login-form');

document.querySelector('#login-btn').onclick = () =>{
    loginForm.classList.toggle('active');
}

window.onscroll = () =>{
    loginForm.classList.remove('active');
}
