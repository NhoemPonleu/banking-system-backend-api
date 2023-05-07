package com.example.backendapimbanking.api.accountType;

import org.apache.ibatis.jdbc.SQL;

public class AccountTypeProvider {
    public String selectSql(){
        return new SQL(){{
            SELECT("*");
            FROM("account_types");

        }}.toString();
    }
    public String selectByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM("account_types");
            WHERE("id=#{id}");
        }}.toString();
    }
    public String insertSql(){
        return new SQL(){{
            INSERT_INTO("account_types");
            VALUES("name","#{a.name}");
        }}.toString();
    }
    public String buildUpdatAccountType(){
        return new SQL(){{
            UPDATE("account_types");
            SET("name=#{ac.name}");
            WHERE("id=#{ac.id}");

        }}.toString();
    }

}
