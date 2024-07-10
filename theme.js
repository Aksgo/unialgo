const toggleButton = document.getElementById("toggle-theme");
const lightTheme = document.getElementById("light-theme")
const darkTheme = document.getElementById("dark-theme")
let darkMode = localStorage.getItem('darkMode')==='true';
const setTheme = ()=>{
    if(darkMode){
        darkTheme.removeAttribute('disabled')
        lightTheme.setAttribute('disabled','true')
        toggleButton.innerHTML="☀️";
    }
    else{
        lightTheme.removeAttribute('disabled')
        darkTheme.setAttribute('disabled','true')
        toggleButton.innerHTML="🌙";
    }
}
setTheme();
toggleButton.addEventListener('click',()=>{
    darkMode = !darkMode;
    //storing theme in local storage to prevent disappearing after reload
    localStorage.setItem('darkMode', darkMode)
    setTheme();
})