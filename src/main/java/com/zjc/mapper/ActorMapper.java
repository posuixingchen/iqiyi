package com.zjc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjc.pojo.Actor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActorMapper extends BaseMapper<Actor> {
    public List<Actor> findActor();
}
