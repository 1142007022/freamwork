package com.kaishengit.service.impl;

import com.kaishengit.entitys.Power;
import com.kaishengit.entitys.PowerExample;
import com.kaishengit.entitys.RolesPowerExample;
import com.kaishengit.entitys.RolesPowerKey;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.mapper.PowerMapper;
import com.kaishengit.mapper.RolesPowerMapper;
import com.kaishengit.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerMapper powerMapper;
    @Autowired
    private RolesPowerMapper rolesPowerMapper;

    public void addPower(Power power) {
        powerMapper.insert(power);
    }

    public List<Power> findAllPower() {
        return powerMapper.selectByExample(null);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void delPowerById(Integer id){
        RolesPowerExample rolesPowerExample = new RolesPowerExample();
        rolesPowerExample.createCriteria().andPowerIdEqualTo(id);
        List<RolesPowerKey> rolesPowerKeyList = rolesPowerMapper.selectByExample(rolesPowerExample);
        if (rolesPowerKeyList != null && rolesPowerKeyList.size() != 0) {
            throw new ServiceException("��Ȩ���н�ɫ����ʹ��ɾ��ʧ�ܣ�");
        } else {
            PowerExample powerExample = new PowerExample();
            powerExample.createCriteria().andParentIdEqualTo(id);
            List<Power> powerList = powerMapper.selectByExample(powerExample);

            for (Power power : powerList) {
                delPowerById(power.getId());
            }
        }
    }


}
