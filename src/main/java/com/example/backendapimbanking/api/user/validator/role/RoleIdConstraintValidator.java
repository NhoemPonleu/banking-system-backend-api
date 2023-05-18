package com.example.backendapimbanking.api.user.validator.role;

import com.example.backendapimbanking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleIdConstraintValidator implements ConstraintValidator<RoleIdConstraint,List<Integer>> {
    private final UserMapper userMapper;
    @Override
    public boolean isValid(List<Integer> roleIds, ConstraintValidatorContext context) {
        for(Integer roleId:roleIds){
            if (!userMapper.existRoleById(roleId)){
                return false;
            }
        }
        return true;
    }
}
