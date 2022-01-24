function deleteCommentEvent(currentElement) {
    let rowElement = currentElement.parentElement.parentElement;
    let splited = rowElement.id.split('-');
    let articleId = splited[1];
    let commentId = splited[2];

    axios.delete(`/board/articles/${articleId}/comments/${commentId}`)
        .then(() => {
            rowElement.remove();
        })
        .catch((error) => {
            alert(error.response.data);
        });
}

window.onload = function () {
    document.getElementById("commentWriteButton").onclick = function () {
        let articleId = Number(document.getElementById("commentWriteArticleId").value);
        let writerId = document.getElementById("commentWriteUserId").value;
        let content = document.getElementById("commentWriteContent").value;

        let data = {
            articleId,
            writerId,
            content,
        };

        axios.post(`/board/articles/${articleId}/comments`, data)
            .then(({data}) => {
                let newComment = document.createElement("tr");
                newComment.setAttribute("id", `comment-${articleId}-${data.commentId}`);
                newComment.innerHTML = `
                    <th class="text-end" style="width: 130px">
                        ${writerId}
                    </th>
                    <td>
                        ${content}
                    </td>
                    <td style="width: 250px">
                        ${data.formattedCreatedDate}
                    </td>
                    <td style="width: 70px">
                        <button class="btn btn-dark commentDeleteButton" type="button" onclick="deleteCommentEvent(this)">삭제</button>
                    </td>
                `
                document.getElementById("commentsTableBody").appendChild(newComment);
            });
    };

    let deleteButtons = document.getElementsByClassName("commentDeleteButton");

    for (let i = 0, size = deleteButtons.length; i < size; i++) {
        deleteButtons[i].onclick = function () {
            let rowElement = this.parentElement.parentElement;
            let splited = rowElement.id.split('-');
            let articleId = splited[1];
            let commentId = splited[2];

            axios.delete(`/board/articles/${articleId}/comments/${commentId}`)
                .then(() => {
                    rowElement.remove();
                })
                .catch((error) => {
                    alert(error.response.data);
                });
        };
    }
}