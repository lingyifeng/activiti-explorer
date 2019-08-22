package org.activiti.explorer.utils;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.explorer.model.Role;
import org.activiti.explorer.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lingzong on 2019/8/21.
 */
public class ActivitiUserUtils {

    public static UserEntity toActivitiUser(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getAccount());
        userEntity.setFirstName(user.getAccount());
        userEntity.setLastName(user.getAccount());
        userEntity.setPassword("123456");
        userEntity.setEmail(user.getEmail());
        userEntity.setRevision(1);
        return userEntity;
    }

    public static GroupEntity toActivitiGroup(Role sysRole) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setRevision(1);
        groupEntity.setType("assignment");
        groupEntity.setId(sysRole.getRoleName());
        groupEntity.setName(sysRole.getRoleName());
        return groupEntity;

    }

    public static List<Group> toActivitiGroups(List<Role> sysRoles) {
        List<Group> groups = new ArrayList<Group>();
        for (Role sysRole : sysRoles) {
            GroupEntity groupEntity = toActivitiGroup(sysRole);
            groups.add(groupEntity);
        }
        return groups;

    }
}
