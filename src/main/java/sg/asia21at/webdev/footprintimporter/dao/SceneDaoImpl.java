package sg.asia21at.webdev.footprintimporter.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sg.asia21at.webdev.footprintimporter.entity.Scene;
import sg.asia21at.webdev.footprintimporter.mybatis.mapper.SceneMapper;

@Repository
public class SceneDaoImpl implements ISceneDao {
	@Autowired
	private SceneMapper mapper;
	
	@Override
	public int deleteById(int id) {
		return mapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(Scene record) {
		if (record != null) {
			return mapper.insert(record);
		}
		return 0;
	}
	@Override
	public Scene selectById(int id) {
		return mapper.selectByPrimaryKey(id);
	}

//	@Override
//	public List<T> selectByStripId(Integer satellite, String stripid) {
//		return mapper.selectByStripId(satellite, stripid);
//	}
//	@Override
//	public List<T> selectByStripIdDate(Integer satellite, String stripid, LocalDate startDate, LocalDate endDate) {
//		return mapper.selectByStripIdDate(satellite, stripid, startDate, endDate);
//	}
	@Override
	public int insertList(List<Scene> entityList) {
		if (entityList.size() > 0) {
			return mapper.insertList(entityList);
		}
		return 0;
	}

}