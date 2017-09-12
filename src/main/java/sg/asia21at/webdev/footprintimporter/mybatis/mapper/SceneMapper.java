package sg.asia21at.webdev.footprintimporter.mybatis.mapper;

import java.util.List;

import sg.asia21at.webdev.footprintimporter.entity.Scene;

public interface SceneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Scene record);

    int insertSelective(Scene record);

    Scene selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Scene record);

    int updateByPrimaryKey(Scene record);
    
    int insertList(List<Scene> entityList);
}