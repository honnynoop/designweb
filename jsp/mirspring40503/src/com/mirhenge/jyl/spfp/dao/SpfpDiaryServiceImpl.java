package com.mirhenge.jyl.spfp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mirhenge.jyl.spfp.model.SpfpDiary;

@Service
public class SpfpDiaryServiceImpl implements SpfpDiaryService {
	
	@Autowired
	private SpfpDiaryDao spfpDiaryDao;

	@Override
	@Transactional
	public void writeSpfpDiary(SpfpDiary diary) {
		spfpDiaryDao.writeSpfpDiary(diary);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SpfpDiary> getSpfpDiaryList() {
		return spfpDiaryDao.getSpfpDiaryList();
	}

	@Override
	@Transactional(readOnly=true)
	public SpfpDiary getSpfpDiary(SpfpDiary diary) {
		return spfpDiaryDao.getSpfpDiary(diary);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<SpfpDiary> getSpfpDay(String yyyymmdd) {
		return spfpDiaryDao.getSpfpDay(yyyymmdd);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<SpfpDiary> getSpfpMonth(String yyyymm) {
		return spfpDiaryDao.getSpfpMonth(yyyymm);
	}

	@Override
	@Transactional
	public void updateSpfpDiary(SpfpDiary diary) {
		spfpDiaryDao.updateSpfpDiary(diary);
	}

	@Override
	@Transactional
	public void deleteSpfpDiary(SpfpDiary diary) {
		spfpDiaryDao.deleteSpfpDiary(diary);
	}

	@Override
	@Transactional
	public void updateTeam(SpfpDiary diary) {
		spfpDiaryDao.updateTeam(diary);
	}

}
