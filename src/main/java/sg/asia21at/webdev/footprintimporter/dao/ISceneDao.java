package sg.asia21at.webdev.footprintimporter.dao;

import java.util.List;

import sg.asia21at.webdev.footprintimporter.entity.Scene;

public interface ISceneDao {
	
	Scene selectById(int id);
	int insert(Scene record);
	int insertList(List<Scene> entityList);
	
	int deleteById(int id);
	
}
