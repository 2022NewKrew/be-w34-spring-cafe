package com.kakao.cafe.repository.user;

public class UserAccountRepositoryQueryAndNameSpace {
    private final String tableName = "user_account";

    public String getTableName(){
        return tableName;
    }
    public String getFindByIdSqlQuery(){
        return String.format("select * from %s where %s = ?", tableName, ColumnName.USER_ID.getColumnName());
    }

    public String getFindAllSqlQuery(){
        return String.format("select * from %s", tableName);
    }
    public enum ColumnName{
        ID("id"),
        USER_ID("user_id"),
        PASSWORD("password"),
        USER_NAME("user_name"),
        EMAIL("email");

        private final String name;

        ColumnName(String name){
            this.name = name;
        }

        public String getColumnName(){
            return this.name;
        }
    }
}
