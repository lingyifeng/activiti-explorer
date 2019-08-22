package org.activiti.explorer.mapper;
import org.activiti.explorer.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b> RoleMapper
 * </b><br><br><i>Description</i> :
 * <br>Date: 2019-07-02     <br>Author : dxl
 */
@Repository
public interface RoleMapper {


    List<Role> getGroupByAccount(String account);
}
