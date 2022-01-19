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
        return String.format("select * from %s where %s = ? and (%s = 1 or %s is null)", tableName,
                ColumnName.PARENT.getColumnName(), ColumnName.FLAG.getColumnName(), ColumnName.FLAG.getColumnName());
    }

    public String getFindAllSqlQuery(){
        return String.format("select * from %s where (%s = 1 or %s is null) and %s = -1", tableName,
                ColumnName.FLAG.getColumnName(), ColumnName.FLAG.getColumnName(), ColumnName.PARENT);
    }

    public String getDeleteSqlQuery(){
        return String.format("update %s set %s=0 where %s = ?", tableName,
                ColumnName.FLAG.getColumnName(), ColumnName.ID.getColumnName());
    }

    public String getContentsUpdateSqlQuery(){
        return String.format("update %s set (%s, %s) = (?, ?) where %s=?", tableName, ColumnName.TITLE.getColumnName(),
                ColumnName.CONTENTS.getColumnName(), ColumnName.ID.getColumnName());
    }

    public enum ColumnName{
        ID("id"),
        WRITER("writer"),
        TITLE("title"),
        CONTENTS("contents"),
        DATE("date"),
        PARENT("parent"),
        FLAG("flag");

        private final String name;

        ColumnName(String name){
            this.name = name;
        }

        public String getColumnName(){
            return this.name;
        }
    }
}
