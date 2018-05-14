package com.hk.mobile.help;

import java.io.*;
import java.net.URLEncoder;
 
import java.util.Map;
 




import javax.servlet.ServletContext;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
 
import org.springframework.web.servlet.view.AbstractView;

import com.hk.mobile.dao.HKPDSService;

public class DownloadView extends AbstractView {
 
	@Autowired
	private HKPDSService hKPDSService;

	public DownloadView() {
		setContentType("application/download; charset=utf-8");
	}
 
	@Override
	public void renderMergedOutputModel(Map<String, Object> model,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ÁØºñÁß
		//ServletContext application= getServletContext();

		File file = (File)model.get("downloadFile");
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		String fileName = null;
		if(ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"), "iso-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Length", "" + file.length());
        response.setHeader("Pragma", "no-cache;");
        response.setHeader("Expires", "-1;");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fi = null;
		try {
			fi = new FileInputStream(file);
			FileCopyUtils.copy(fi, out);
			int seq=(Integer)model.get("seq");
	    	hKPDSService.downloadCount(seq);
		} finally {
			if(fi != null) {
				try {
					fi.close();
				} catch(IOException ioe) {}
			}
		}
		out.flush();
	}
}