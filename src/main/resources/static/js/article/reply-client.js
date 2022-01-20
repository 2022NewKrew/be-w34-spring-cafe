'use strict'

let replyClient = {
    url: "/api/v1/articles",
    create: function (reply) {
        const requestUrl = `${this.url}/${reply.articleId}/reply`;
        return fetch(requestUrl, {
            method: "POST",
            body: JSON.stringify(reply),
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            }
        }).then(async (response) => {
            return {
                location: response.headers.get("location"),
                replyId: await response.json()
            }
        });
    },
    get: function (articleId, replyId) {
        const requestUrl = `${this.url}/${articleId}/reply/${replyId}`;
        return fetch(requestUrl).then(async (response) => await response.json());
    },
    delete: function (articleId, replyId) {
        const requestUrl = `${this.url}/${articleId}/reply/${replyId}`;
        return fetch(requestUrl, {method: "DELETE"}).then(async (response) => await response.json());
    }
}
