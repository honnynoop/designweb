package com.mirhenge.jyl.you.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mirhenge.jyl.you.model.YoutubeSave;


@Service
public class YoutubeServiceImpl implements YoutubeService {
	
	@Autowired
	private YoutubeDao youtubeDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<YoutubeSave> getYoutubeList(YoutubeSave you) {
		return youtubeDao.getYoutubeList(you);
	}

	@Override
	@Transactional(readOnly=true)
	public YoutubeSave getYoutube(YoutubeSave you) {
		return youtubeDao.getYoutube(you);
	}

	@Override
	@Transactional
	public void writeYoutube(YoutubeSave you) {
		youtubeDao.writeYoutube(you);
	}

}
