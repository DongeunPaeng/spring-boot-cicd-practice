const saveBtn = document.querySelector("#btn-save");
const editBtn = document.querySelector("#btn-edit");
const deleteBtn = document.querySelector("#btn-delete");

const deletePost = async (event) => {
    event.preventDefault();
    const id = document.location.href.split("/").slice(-1).pop();

    const requestBody = {
        id,
    };

    try {
        const response = await fetch("/api/v1/post/delete", {
            method: "DELETE",
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

const editPost = async (event) => {
    event.preventDefault();
    const id = document.location.href.split("/").slice(-1).pop();
    const title = document.querySelector("#save-title").value;
    const post = document.querySelector("#save-post").value;
    const author = 1;
    const status = 0;
    const type = 0;

    const requestBody = {
        id,
        title,
        post,
        author,
        status,
        type,
    };

    try {
        const response = await fetch("/api/v1/post/edit", {
            method: "PUT",
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
        if (response.status !== 200) alert("error!");
        window.location.href = "/";
    } catch (e) {
        console.log(e);
    }
};

if (saveBtn) {
    saveBtn.addEventListener("click", savePost);
}
if (editBtn) {
    editBtn.addEventListener("click", editPost);
}
if (deleteBtn) {
    deleteBtn.addEventListener("click", deletePost);
}
