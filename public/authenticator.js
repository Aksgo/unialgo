
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-app.js";
import { 
  GoogleAuthProvider , 
  getAuth, signInWithPopup, 
  signOut, setPersistence,
   browserSessionPersistence,
   onAuthStateChanged
} from "https://www.gstatic.com/firebasejs/10.12.2/firebase-auth.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-analytics.js";
const firebaseConfig = {
    apiKey: "AIzaSyCmLnHZu1jLruXAky2fS-veHG9yugr2j8g",
    authDomain: "unialgo.firebaseapp.com",
    projectId: "unialgo",
    storageBucket: "unialgo.appspot.com",
    messagingSenderId: "103581567567",
    appId: "1:103581567567:web:6c932615dfd50f7473b83a",
    measurementId: "G-FK3XM27458"
  };
//intializing app
const app = initializeApp(firebaseConfig);
//initializing analytics
const analytics = getAnalytics(app);
//performing sign in
const auth = getAuth(app);

setPersistence(auth, browserSessionPersistence)
  .then(() => {
    // Persistence set successfully
  })
  .catch((error) => {
    // Error setting persistence
    console.error("Error setting persistence:", error);
  });

const provider = new GoogleAuthProvider( );
let loginbtn = document.getElementById("signin-btn");
onAuthStateChanged(auth, (user)=>{
  if(user){
    loginbtn.innerHTML="Logout "+user.displayName;
  }
  else{
    loginbtn.innerHTML="Sign In";
  }
})
loginbtn.addEventListener('click', changeSignInState);
function changeSignInState(){
  if(!auth.currentUser){
    signInWithPopup(auth,provider)
    .then((result)=>{
      const credential = GoogleAuthProvider.credentialFromResult(result);
          const token = credential.accessToken;
          // The signed-in user info.
          const user = result.user;
          const curid = user.uid;
          loginbtn.innerHTML="Logout "+user.displayName;
    }).catch((error) => {
      // Handle Errors here.
      const errorCode = error.code;
      const errorMessage = error.message;
      // The email of the user's account used.
      const email = error.customData.email;
      // The AuthCredential type that was used.
      const credential = GoogleAuthProvider.credentialFromError(error);
      // ...

  });
  }
  else{
    signOut(auth).then(()=>{
      loginbtn.innerHTML="Sign In";
      
    }).catch((error) => {
      // An error happened.
      console.error("Error signing out:", error);
    });
  }
}
