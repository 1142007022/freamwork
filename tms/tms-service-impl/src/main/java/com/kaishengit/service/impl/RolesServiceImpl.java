package com.kaishengit.service.impl;

import com.kaishengit.entitys.Power;
import com.kaishengit.entitys.Roles;
import com.kaishengit.entitys.RolesPowerKey;
import com.kaishengit.mapper.RolesMapper;
import com.kaishengit.mapper.RolesPowerMapper;
import com.kaishengit.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private RolesPowerMapper rolesPowerMapper;

    @Override
    public void addRoles(Roles roles) {

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addRolesAndPowers(Roles roles, Integer[] powerIds) {
        rolesMapper.insert(roles);
        for(Integer powerId:powerIds){
            RolesPowerKey rolesPowerKey = new RolesPowerKey();
            rolesPowerKey.setPowerId(powerId);
            rolesPowerKey.setRolesId(roles.getId());
            rolesPowerMapper.insert(rolesPowerKey);
        }

    }

    @Override
    public List<Roles> findAllRoles() {
        return rolesMapper.findAllRoles();
    }
}
