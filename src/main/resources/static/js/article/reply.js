'use strict'

window.onload = function () {
    document.getElementsByClassName("submit-write")[0].addEventListener('click', makeReply, false);
    document.getElementsByClassName("qna-comment-slipp-articles")[0].addEventListener('click', deleteReply, false);
}

class Reply {
    constructor(articleId, userId, comment) {
        this.articleId = articleId;
        this.userId = userId;
        this.comment = comment;
    }
}

async function makeReply(event) {
    if (!(event.target.classList.contains('create-reply'))) {
        return;
    }
    const reply = new Reply($("#articleId").val(), $("#userId").val(), $("#comment").val());
    const response = await replyClient.create(reply);
    const replyJson = await replyClient.get(reply.articleId, response.replyId);
    const answerTemplate = makeAnswerTemplate(replyJson.author, replyJson.createdAt, replyJson.comment, replyJson.articleId, replyJson.replyId)
    $(".qna-comment-slipp-articles").prepend(answerTemplate);
    document.getElementById('comment').value = '';
}

async function deleteReply(event) {
    if (!(event.target.classList.contains('delete-answer-button'))){
        return;
    }
    const button = event.target;
    const articleId = button.getAttribute("data-articleId");
    const replyId = button.getAttribute("data-replyId");
    let response = await replyClient.delete(articleId, replyId);
    button.closest("article").remove();
}

function makeAnswerTemplate(author, createdAt, comment, articleId, replyId) {
    let answerTemplate = $("#answerTemplate").html();
    return answerTemplate.format(author, createdAt, comment, articleId, replyId);
}
