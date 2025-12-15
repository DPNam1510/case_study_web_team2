// ================= CHUYỂN FORM ĐĂNG NHẬP/ĐĂNG KÝ =================
const btnSignIn = document.getElementById("btnSignIn");
const btnRegister = document.getElementById("btnRegister");

const formSignIn = document.getElementById("formSignIn");
const formRegister = document.getElementById("formRegister");

btnSignIn.onclick = () => {
    btnSignIn.classList.add("active");
    btnRegister.classList.remove("active");

    formSignIn.classList.add("active");
    formRegister.classList.remove("active");
};

btnRegister.onclick = () => {
    btnRegister.classList.add("active");
    btnSignIn.classList.remove("active");

    formRegister.classList.add("active");
    formSignIn.classList.remove("active");
};

// ================= TOGGLE ADMIN/KHÁCH HÀNG =================
const typeButtons = document.querySelectorAll(".type-btn");
const typeInput = document.getElementById("accountType");

typeButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        typeButtons.forEach(b => b.classList.remove("active"));
        btn.classList.add("active");
        typeInput.value = btn.dataset.type;
    });
});

