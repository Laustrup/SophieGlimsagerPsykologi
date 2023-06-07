function logout() {

}

function login() {

}

async function isLoggedIn() {
    return localStorage.getItem("password") !== undefined;
}