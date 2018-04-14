package com.kaishengit.mapper;

import com.kaishengit.entitys.RolesPowerExample;
import com.kaishengit.entitys.RolesPowerKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesPowerMapper {
    long countByExample(RolesPowerExample example);

    int deleteByExample(RolesPowerExample example);

    int deleteByPrimaryKey(RolesPowerKey key);

    int insert(RolesPowerKey record);

    int insertSelective(RolesPowerKey record);

    List<RolesPowerKey> selectByExample(RolesPowerExample example);

    int updateByExampleSelective(@Param("record") RolesPowerKey record, @Param("example") RolesPowerExample example);

    int updateByExample(@Param("record") RolesPowerKey record, @Param("example") RolesPowerExample example);
}