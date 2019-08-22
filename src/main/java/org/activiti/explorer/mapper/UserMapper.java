package org.activiti.explorer.mapper;

import org.activiti.explorer.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b> UserMapper
 * </b><br><br><i>Description</i> :
 * <br>Date: 2019-07-02     <br>Author : dxl
 */
@Mapper
@Repository
public interface UserMapper{

    User getUserByAccount(String account);

    List<User> getUsersByAccountLike(Map<String, Object> params);
}
