package com.example.backendapimbanking.api.user;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserMapper {
    @InsertProvider(type = UserProvider.class,method = "buildInsertSql")
    @Options(useGeneratedKeys = true , keyColumn = "id", keyProperty = "id")
    void insert(@Param("u") User user);
    @SelectProvider(type =UserProvider.class,method = "buildSelectIdSql")
    @Results(id="resultMap",value = {
            @Result(column = "student_card_id",property = "studentCardId"),
            @Result(column = "is_student",property = "isStudent")
    })
    Optional<User>selectById(@Param("id") Integer id);
    @SelectProvider(type = UserProvider.class,method = "buildSelectSql")
    @ResultMap("resultMap")
    List<User> select(@Param("name") String name);
    @Select("SELECT EXISTS(SELECT * FROM users WHERE id=#{id})")
    boolean existById(@Param("id") Integer id);
    @DeleteProvider(type = UserProvider.class,method = "deleteSql")
    void deleteById(@Param("id") Integer id);
    @UpdateProvider(type = UserProvider.class,method = "buildUpdatedIsDeleted")
    void updateIsDeletedById(@Param("id") Integer id, @Param("status") boolean status);
    @SelectProvider(type = UserProvider.class ,method = "buildSelectByStudentCardId")
    @ResultMap("resultMap")
    Optional<User> selectByStudentCardId(@Param("studentCardId") String studentCardId);
}
