package com.kakao.cafe.repository.article;

public class ArticleRepositoryQueryAndNameSpace {
    private final String tableName = "article";

    public String getTableName(){
        return tableName;
    }
    public String getFindByIdSqlQuery(){
        return String.format("select * from %s where %s = ?", tableName, ColumnName.ID.getColumnName());
    }

    public String getFindCommentsSqlQuery(){
        return String.format("select * from %s where %s = ?", tableName, ColumnName.PARENT.getColumnName());
    }

    public String getFindAllSqlQuery(){
        return String.format("select * from %s where flag = 1 or flag is null", tableName);
    }

    public String getDeleteSqlQuery(){
        return String.format("update %s set flag=0 where id = ?", tableName);
    }

    public enum ColumnName{
        ID("id"),
        WRITER("writer"),
        TITLE("title"),
        CONTENTS("contents"),
        DATE("date"),
        COMMENT_SIZE("comment_size"),
        PARENT("parent");

        private final String name;

        ColumnName(String name){
            this.name = name;
        }

        public String getColumnName(){
            return this.name;
        }
    }
}
