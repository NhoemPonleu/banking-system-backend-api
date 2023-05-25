package com.example.backendapimbanking.api.user;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private String table = "users";

    public String buildInsertSql() {
        return new SQL() {{
            INSERT_INTO(table);
            VALUES("name", "#{u.name}");
            VALUES("gender", "#{u.gender}");
            VALUES("one_signal_id", "#{u.oneSignalId}");
            VALUES("student_card_id", "#{u.studentCardId}");
            VALUES("is_student", "#{u.isStudent}");
            VALUES("is_deleted", "FALSE");


        }}.toString();
    }

    public String buildSelectIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(table);
            WHERE("id=#{id}", "is_deleted=FALSE");
        }}.toString();
    }

    public String deleteSql() {
        return new SQL() {{
            DELETE_FROM(table);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildUpdatedIsDeleted() {
        return new SQL() {{
            UPDATE(table);
            SET("is_deleted=#{status}");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildSelectSql() {
        return new SQL() {{
            SELECT("*");
            FROM(table);
            WHERE("name ILIKE '%' ||#{name}||'%'", "is_deleted=FALSE");
            ORDER_BY("id DESC");

        }}.toString();
    }

    public String buildSelectByStudentCardId() {
        return new SQL() {{
            SELECT("*");
            FROM(table);
            WHERE("student_card_id=#{studentCardId}", "is_deleted=FALSE");
        }}.toString();
    }

}
