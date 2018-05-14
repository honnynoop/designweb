package com.mirhenge.jyl.spfp.dao;

import java.util.List;

import com.mirhenge.jyl.spfp.model.SpfpDiary;

public interface SpfpDiaryService {
	void writeSpfpDiary(SpfpDiary diary);
	List<SpfpDiary> getSpfpDiaryList();
	SpfpDiary getSpfpDiary(SpfpDiary diary);
	List<SpfpDiary> getSpfpDay(String yyyymmdd);
	List<SpfpDiary> getSpfpMonth(String yyyymm);
	void updateSpfpDiary(SpfpDiary diary);
	void deleteSpfpDiary(SpfpDiary diary);
	void updateTeam(SpfpDiary diary);
	
}
