const saveBtn = document.querySelector("#btn-save");

const savePost = async (event) => {
    event.preventDefault();
    const title = document.querySelector("#save-title").value;
    const post = document.querySelector("#save-post").value;
    const author = 1;
    const status = 0;
    const type = 0;

    const requestBody = {
        title,
        post,
        author,
        status,
        type,
    };

    try {
        const response = await fetch("/api/v1/post/write", {
            method: "POST",
            body: JSON.stringify(requestBody),
            headers: {
                "Content-Type": "application/json",
            },
        });
        if (response.status !== 200) alert("오류 발생: 콘솔을 확인하세요.");
        window.location.href = "/";
    } catch (e) {
        console.log(e);
    }
};

saveBtn.addEventListener("click", savePost);
