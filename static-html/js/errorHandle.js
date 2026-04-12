const nameInput = document.querySelector('input[name="name"]');
const messageInput = document.querySelector('textarea');
const errorDiv = document.getElementById("error-message");

function validateName() {
    const value = nameInput.value;

    if (!value || value.length < 2) {
        nameInput.style.border = "3px solid red";
        return ("名前は2文字以上で入力してください");
    }
    nameInput.style.border = "";
    return null;
}

function validateMessage() {
    const value = messageInput.value;

    if (!value || value.length < 10) {
        messageInput.style.border = "3px solid red";
        return ("メッセージは10文字以上で入力してください");
    }
    messageInput.style.border = "";
    return null;
}

//入力時_個別チェック
nameInput.addEventListener("input", function () {
    const error = validateName();
    errorDiv.textContent = error ?? "";
});

messageInput.addEventListener("input", function () {
    const error = validateMessage();
    errorDiv.textContent = error ?? "";
});

//送信時チェック
function handleSubmit() {
    let errors = [];

    const nameError = validateName();
    const messageError = validateMessage();

    if (nameError) errors.push(nameError);
    if (messageError) errors.push(messageError);

    if (errors.length > 0) {
        errorDiv.innerHTML = errors.join("<br>");
        return;
    }

    errorDiv.textContent = "";
}