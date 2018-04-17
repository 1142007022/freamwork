package com.kaishengit.mapper;

import com.kaishengit.entitys.Power;
import com.kaishengit.entitys.PowerExample;
import java.util.List;

import com.kaishengit.entitys.Roles;
import org.apache.ibatis.annotations.Param;

public interface PowerMapper {
    long countByExample(PowerExample example);

    int deleteByExample(PowerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Power record);

    int insertSelective(Power record);

    List<Power> selectByExample(PowerExample example);

    Power selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Power record, @Param("example") PowerExample example);

    int updateByExample(@Param("record") Power record, @Param("example") PowerExample example);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);


    List<Power> findMyPowerById(Integer id);
}