const toggleButton = document.getElementById("toggle-theme");
const theme = document.getElementById("theme-sheet");
let darkMode = localStorage.getItem('darkMode')==='true';
const setTheme = ()=>{
    if(darkMode){
        theme.href="articlestyle.css";
        toggleButton.innerHTML="🌙";
    }
    else{
        theme.href="articlestylelight.css";
        toggleButton.innerHTML="☀️";
    }
}
setTheme();
toggleButton.addEventListener('click',()=>{
    darkMode = !darkMode;
    //storing theme in local storage to prevent disappearing after reload
    localStorage.setItem('darkMode', darkMode)
    setTheme();
})