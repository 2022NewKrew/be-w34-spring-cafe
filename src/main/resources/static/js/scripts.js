String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};

//TODO: js템플릿에서 함수가 있는데 첫번째 진입시 충돌남
function clickLoginModal() {
    let con = document.getElementById("loginModal");
    console.log(con.style.display);
    if (con.style.display !== 'none') {
        con.style.display = 'none';
    }else {
        con.style.display = 'block';
    }
};

const addAnswer = async (questionId) => {
    const answer = document.getElementById('answer');
    console.log(answer);
    const formData = new FormData(answer);
    
    let obj = {};
    formData.forEach((value,key) => {
        obj[key] = value;
    });

    const data = JSON.stringify(obj);

    const ret = await api.postReply(questionId, data);

    await appendAnswer(ret, questionId);
};

const appendAnswer = async (replyComment, questionId) => {
    
    const commnetBody = document.getElementById('replyComment');

    const list = replyComment.map((reply) => `
    <li style="margin: 15px 0; padding: 10px; background-color: #fff; border-radius: 8px; position:relative;">
        <div>
            <span style="font-size: 15px;font-weight: bold;">${reply.writer}</span><span style="margin-left: 10px; font-size: 14px;">${reply.createTime}</span>
        </div>
        <p style="margin-top: 10px; font-size: 16px;">
            ${reply.comment}
        </p>
        <form action="/questions/${questionId}/answers/${reply.id}" method="post" style="position: absolute; top: 10px; right: 10px; display: ${reply.thisIsMine ? 'display' : 'none'}">
            <input type="hidden" name="_method" value="DELETE">
            <button type="submit" style="border-radius: 50%; border: 0;">x</button>
        </form>
    </li>
    `).join("");

    commnetBody.innerHTML = list;
};

const api = {
    postReply: async (questionId, data) => {
        try{
            const response = await fetch(`/api/questions/${questionId}/answer`,{
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: data
            })
            .then(response => response.json());

            return  await response;
            

        }catch(e) {
            console.error(e);
        }
    } 
};