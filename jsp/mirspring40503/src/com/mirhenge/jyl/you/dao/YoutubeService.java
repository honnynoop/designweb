package com.mirhenge.jyl.you.dao;

import java.util.List;

import com.mirhenge.jyl.you.model.YoutubeSave;


public interface YoutubeService {
	List<YoutubeSave> getYoutubeList (YoutubeSave you);
	YoutubeSave getYoutube(YoutubeSave you);
	void writeYoutube(YoutubeSave you);
}
