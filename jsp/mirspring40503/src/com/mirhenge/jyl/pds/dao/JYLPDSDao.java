package com.mirhenge.jyl.pds.dao;
import java.util.List;
import com.mirhenge.jyl.pds.model.JYLPds;
public interface JYLPDSDao {
	void uploadPDS(JYLPds dto);
	List<JYLPds> getPDSList ();
	void pdsReadCount(int pdsid);
	void downloadCount(int pdsid);
	JYLPds getPDS(int pdsid);
	void updatePDS(JYLPds pdsdto);
	void delPDS(int seq);
}