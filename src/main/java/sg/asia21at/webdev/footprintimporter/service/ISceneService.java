package sg.asia21at.webdev.footprintimporter.service;

import java.util.List;

import sg.asia21at.webdev.footprintimporter.entity.Scene;

public interface ISceneService {
	Scene selectById(int id);
	int insert(Scene record);
	int insertList(List<Scene> entityList);
	
	int deleteById(int id);
}
