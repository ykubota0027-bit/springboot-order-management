function handleSubmit() {

    // ① 値を取得
    const name = document.querySelector('input[name="name"]').value;
    const gender = document.querySelector('input[name="gender"]:checked')?.value;
    const message = document.querySelector('textarea').value;

    // 各Input項目
    const nameInput = document.querySelector('input[name="name"]');
    const genderInput = document.querySelector('input[name="gender"]');
    const messageInput = document.querySelector('textarea');

    // エラー状態項目
    let isNameError = false;
    let isMessageError = false;
    let isGenderError = false;

    // 表示エリア取得
    const errorDiv = document.getElementById("error-message");
    const resultDiv = document.getElementById("resultArea");

    // 初期化（前回の表示を消す）
    errorDiv.textContent = "";
    resultDiv.textContent = "";

    // ② チェック
    let errors = [];

    if (!name) {
        errors.push("名前を入力してください");
        isNameError = true;
    }
    if (name.length < 2) {
        errors.push("名前は2文字以上で入力してください");
        isNameError = true;
    }

    if (!gender) {
        errors.push("性別を選択してください");
        isGenderError = true;
    }

    if (!message) {
        errors.push("メッセージを入力してください");
        isMessageError = true;
    }
    if (message.length < 10) {
        errors.push("メッセージは10文字以上で入力してください");
        isMessageError = true;
    }

    if (isNameError) {
        nameInput.style.border = "3px solid red";
    }
    if (isGenderError) {
        genderInput.style.border = "3px solid brue";
    }
    if (isMessageError) {
        messageInput.style.border = "3px solid red";
    }
    // ③ 分岐
    if (errors.length > 0) {
        // エラー表示
        errorDiv.innerHTML = errors.join("<br>");
        return;
    }

    // ④ 正常処理（画面に表示）
    resultDiv.innerHTML = `
      <h3>送信完了</h3>
      <p>名前：${name}</p>
      <p>性別：${gender}</p>
      <p>メッセージ：${message}</p>
    `;
}