package sg.asia21at.webdev.footprintimporter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.asia21at.webdev.footprintimporter.dao.ISceneDao;
import sg.asia21at.webdev.footprintimporter.entity.Scene;
import sg.asia21at.webdev.footprintimporter.exception.FailAddException;
import sg.asia21at.webdev.footprintimporter.exception.InnerException;
import sg.asia21at.webdev.footprintimporter.exception.RepeatLogException;
import sg.asia21at.webdev.footprintimporter.service.ISceneService;

@Service
public class SceneServiceImpl implements ISceneService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ISceneDao dao;
	
	@Override
	public int deleteById(int id) {
		return dao.deleteById(id);
	}

	@Override
	public int insert(Scene record) {
		return dao.insert(record);
	}

	@Override
	public Scene selectById(int id) {
		return dao.selectById(id);
	}

	@Override
	@Transactional
	public int insertList(List<Scene> entityList) {
		int result = 0;
		int inter = 100;
		try {
			for (int i = 0; i <= entityList.size(); i += inter) {
				int endIndex = (i + inter < entityList.size()) ? i + inter : entityList.size();
				result += dao.insertList(entityList.subList(i, endIndex));
			}			
			if (result <= 0) {
				throw new RepeatLogException("Error: No entry imported.");
			} else {
				return result;
			}
		} catch (DataIntegrityViolationException e1) {
			throw new FailAddException("Failed adding records. Check parameters or repeat entry.");
		} catch (RepeatLogException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new InnerException("Inner error:" + e.getMessage());
		}
	}
	
}
