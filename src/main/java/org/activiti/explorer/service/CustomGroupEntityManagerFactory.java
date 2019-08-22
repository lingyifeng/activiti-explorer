package org.activiti.explorer.service;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.activiti.explorer.conf.CustomGroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lingzong on 2019/8/21.
 */
@Service
public class CustomGroupEntityManagerFactory implements SessionFactory {

    @Resource
    private CustomGroupEntityManager customGroupEntityManager;

    public Class<?> getSessionType() {
        // 返回原始的GroupManager类型
        return GroupIdentityManager.class;
    }

    public Session openSession() {
        // 返回自定义的GroupManager实例
        return customGroupEntityManager;
    }

    @Autowired
    public void setCustomGroupEntityManager(CustomGroupEntityManager customGroupEntityManager) {
        this.customGroupEntityManager = customGroupEntityManager;
    }

}
