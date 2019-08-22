package org.activiti.explorer.conf;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.activiti.explorer.mapper.RoleMapper;
import org.activiti.explorer.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lingzong on 2019/8/21.
 */

@Component
public class CustomGroupEntityManager extends GroupEntityManager {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Group> findGroupsByUser(final String userCode) {
        if (userCode == null)
            return null;

        List<Role> bGroupList = roleMapper.getGroupByAccount(userCode);

        List<Group> gs = new ArrayList<Group>();
        GroupEntity g;
        for (Role bGroup : bGroupList) {
            g = new GroupEntity();
            g.setRevision(1);
            g.setType("assignment");
            g.setId( bGroup.getRoleName());
            g.setName(bGroup.getRoleName());
            gs.add(g);
        }
        return gs;
    }

    @Override
    public GroupQuery createNewGroupQuery() {
        return super.createNewGroupQuery();
    }

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        String account = query.getUserId();
        List<Group> gs = new ArrayList<Group>();
        List<Role> roleList = roleMapper.getGroupByAccount(account);
        for (Role role : roleList) {
            GroupEntity g= new GroupEntity();
            if(role.getRoleName().equals("Administrator")){
                g.setRevision(1);
                g.setType("security-role");
                g.setId( "admin");
                g.setName("Admin");
            }else {
                g.setRevision(1);
                g.setType("assignment");
                g.setId(role.getRoleName());
                g.setName(role.getRoleName());
            }
            gs.add(g);
        }

        return gs;
    }
}
