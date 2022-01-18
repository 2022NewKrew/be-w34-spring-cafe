<<<<<<< HEAD:src/main/java/com/kakao/cafe/article/application/port/in/ArticleInventoryInfo.java
package com.kakao.cafe.article.application.port.in;
=======
package com.kakao.cafe.web.article.form;
>>>>>>> JehPark:src/main/java/com/kakao/cafe/web/article/form/ArticleInventoryInfo.java

public class ArticleInventoryInfo {
    private final long articleId;
    private final String writerName;
    private final String title;
    private final int numberOfReply;
    private final String createdTime;

    public ArticleInventoryInfo(long articleId, String createdTime, String writerName, String title, int numberOfReply) {
        this.articleId = articleId;
        this.writerName = writerName;
        this.title = title;
        this.numberOfReply = numberOfReply;
        this.createdTime = createdTime;
    }
}
