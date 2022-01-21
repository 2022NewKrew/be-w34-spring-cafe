String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".submit-write button[type=submit]").click(addAnswer);

function addAnswer(e) {
  e.preventDefault(); //submit 이 자동으로 동작하는 것을 막는다.

  var queryString = $(".submit-write").serialize(); //form data들을 자동으로 묶어준다.
  console.log("query : "+ queryString);

  $.ajax({
    type : 'post',
    url : '/api/articles/1/replies',
    data : queryString,
    dataType : 'json',
    error : function () {
      console.log('failure');
    },
    success : function (data, status) {
      // 댓글 수 1증가
      var count = $("#qna-comment-count strong").html();
      $("#qna-comment-count strong").text(parseInt(count) + 1);

      var articleTag = $('<article>').addClass('article');
      articleTag.append(generateArticleHeader(data));
      articleTag.append(generateArticleDoc(data));
      articleTag.append(generateArticleUtil(data));

      $(".qna-comment-slipp-articles").prepend(articleTag);
      $("textarea[name=comment]").val("");
    },
  });
}

function generateArticleHeader(data) {
  var articleHeader = $('<div>').addClass('article-header');
  var articleHeaderThumb = $('<div>').addClass('article-header-thumb');
  articleHeaderThumb.append('<img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">');

  var articleHeaderText = $('<div>').addClass('article-header-text');
  articleHeaderText.append('<a href="/users/1/자바지기" class="article-author-name">' + data.writerName + '</a>');
  articleHeaderText.append('<a href="#answer-1434" class="article-header-time" title="퍼머링크">' + data.createdTime + '</a>');

  articleHeader.append(articleHeaderThumb);
  articleHeader.append(articleHeaderText);
  return articleHeader;
}

function generateArticleDoc(data) {
  var comment = $('<div>').addClass('article-doc comment-doc');
  comment.append('<p>' + data.comment + '</p>');
  return comment;
}

function generateArticleUtil(data) {
  var articleUtil = $('<div>').addClass('article-util');
  var utilList = $('<ul>').addClass('article-util-list');
  articleUtil.append(utilList);
  if(data.canUpdate) {
    var modifyItem = $('<li>');
    modifyItem.append('<a class="link-modify-article" href="/questions/413/answers/1405/form">수정</a>');
    utilList.append(modifyItem);

    var deleteItem = $('<li>');
    var deleteForm = $('<form>').addClass('delete-answer-form');
    deleteForm.attr('action', '/articles/' + data.articleId + '/replies/' + data.replyId);
    deleteForm.attr('method', 'post');
    var hiddenDeleteInput = $('<input>');
    hiddenDeleteInput.attr('type', 'hidden');
    hiddenDeleteInput.attr('name', '_method');
    hiddenDeleteInput.attr('value', 'delete');
    deleteForm.append(hiddenDeleteInput);

    var deleteButton = $('<button>').addClass('delete-answer-button');
    deleteButton.attr('type', 'submit');
    deleteButton.text('삭제');
    deleteForm.append(deleteButton);
    deleteItem.append(deleteForm);
    utilList.append(deleteItem);
  }
  return articleUtil;
}
