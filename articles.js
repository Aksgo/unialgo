const toggleButton = document.getElementById("toggle-theme")
const theme = document.getElementById("theme-sheet")
let darkMode = true;
toggleButton.addEventListener('click',()=>{
    darkMode = !darkMode
    if(darkMode){
        theme.href="articlestyle.css"
        toggleButton.innerHTML="🌙"
    }
    else{
        theme.href="articlestylelight.css"
        toggleButton.innerHTML="☀️"
    }
})
