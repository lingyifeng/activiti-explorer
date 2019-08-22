package org.activiti.explorer.conf;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.activiti.explorer.ExplorerApp;
import org.activiti.explorer.mapper.RoleMapper;
import org.activiti.explorer.mapper.UserMapper;
import org.activiti.explorer.model.Role;
import org.activiti.explorer.model.User;
import org.activiti.explorer.utils.ActivitiUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lingzong on 2019/8/21.
 */

@Component
public class CustomUserEntityManager extends UserEntityManager {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserEntity findUserById(String userId) {
        UserEntity userEntity = new UserEntity();
        User cue = userMapper.getUserByAccount(userId);//这是我们的dao方法查询回来的方法，是自己定义的user
        userEntity = ActivitiUserUtils.toActivitiUser(cue);//将自定义的user转化为activiti的类
        return userEntity;//返回的是activiti的实体类
    }


    @Override
    public UserQuery createNewUserQuery() {
        return super.createNewUserQuery();
    }


    @Override
    public List<org.activiti.engine.identity.User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("account", query.getId());
        map.put("fullnamelike", query.getFullNameLike());
        if (ExplorerApp.get().getLoggedInUser() != null) {
            List<String> groups = new ArrayList<String>();
            List<Group> list = ExplorerApp.get().getLoggedInUser().getGroups();
            for (Group group : list) {
                groups.add(group.getId());
            }
            if(groups.size()>0) {
                map.put("groups", groups);
            }
        }
        List<User> user = userMapper.getUsersByAccountLike(map);
        ArrayList<org.activiti.engine.identity.User> list = new ArrayList<org.activiti.engine.identity.User>();
        for (User user1 : user) {
            UserEntity entity = new UserEntity();
            entity.setId(user1.getAccount());
            entity.setFirstName(user1.getAccount());
            entity.setLastName("");
            list.add(entity);
        }

        return list;
    }

    @Override
    public List<Group> findGroupsByUser(final String userCode) {
        if (userCode == null)
            return null;

        List<Role> groupIds = roleMapper.getGroupByAccount(userCode);

        List<Group> gs = null;
        gs = ActivitiUserUtils.toActivitiGroups(groupIds);
        return gs;


    }

}
