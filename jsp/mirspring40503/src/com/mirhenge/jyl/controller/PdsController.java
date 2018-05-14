package com.mirhenge.jyl.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mirhenge.jyl.pds.dao.JYLPDSService;
import com.mirhenge.jyl.pds.help.FUpUtil;
import com.mirhenge.jyl.pds.model.JYLPds;

@Controller
public class PdsController {
	private static final Logger logger = 
			LoggerFactory.getLogger(PdsController.class);
	
	@Autowired
	private JYLPDSService jYLPDSService;
	
	@RequestMapping(value = "pdslist.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String pdslist(Model model) {
		logger.info("Welcome PdsController pdslist! "+ new Date());
		model.addAttribute("doc_title", "PDS �ڷ��");
		model.addAttribute("pdslist", jYLPDSService.getPDSList() );
		return "pdslist.tiles";
	}//
	@RequestMapping(value = "pdswrite.do", 
			method = RequestMethod.GET)
	public String pdswrite(Model model) {
		logger.info("Welcome PdsController pdswritebefor! "+ new Date());
		model.addAttribute("doc_title", "PDS �ڷᾲ��");
		return "pdswrite.tiles";
	}//
	@RequestMapping(value = "pdsupload.do", 
			method = RequestMethod.POST)
	public String pdsupload(
			JYLPds pdsdto,
			HttpServletRequest request,
			@RequestParam(value="fileload", required=false)MultipartFile fileload,
			Model model) {
		model.addAttribute("doc_title", "PDS �ڷ�ø���");
		logger.info("Welcome PDSController! "+ pdsdto);
		pdsdto.setFilename(fileload.getOriginalFilename());//�̸��� ���Ծȵ�
		
		logger.info("Welcome PDSController pdsupload! "+ new Date());
		String fupload =request.getServletContext().getRealPath("/upload");
		//String fupload = "c:\\upload";//��Ȳ�� ���� �����ϵ��� ������.
		logger.info(": "+ fupload);
		String f=pdsdto.getFilename();

		String newFile=FUpUtil.getNewFile(f);//�̸�+long+.���̻�
		
		logger.info(fupload+"/"+newFile);
		pdsdto.setFilename(newFile);//�̸� ����
		try {
			File file=new File(fupload+"/"+newFile);
			//logger.info(fupload+"\\"+newFile);
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			//db�� ���� ���ϸ� �ڸ�Ʈ
			jYLPDSService.uploadPDS(pdsdto);
			logger.info("Welcome pdsupload success! ");
		} catch (IOException e) {
			logger.info("Welcome pdsupload fail! ");
		}
		return "redirect:/pdslist.do";
	}//
	
	
	@RequestMapping(value = "fileDownload.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String download(int seq,HttpServletRequest request,
			String filename, Model model) throws Exception {
		logger.info("Welcome PDSController download! "+ new Date());
		//String fupload = "c:\\upload\\";
		String fupload =request.getServletContext().getRealPath("/upload");
		File downloadFile = new File(fupload +"/"+filename);

		model.addAttribute("downloadFile", downloadFile );
		model.addAttribute("seq", seq );
		return "downloadView";
	}
	@RequestMapping(value = "pdsdetail.do", method = RequestMethod.GET)
	public String pdsdetail(
			int seq, Model model) {
		logger.info("Welcome PDSController pdsdetail! "+ new Date());
		jYLPDSService.pdsReadCount(seq);
		JYLPds pdsdto=jYLPDSService.getPDS(seq);
		model.addAttribute("pds", pdsdto );
		model.addAttribute("doc_title", "PDS �󼼳���");
		return "pdsdetail.tiles";
	}//
	
	@RequestMapping(value = "pdsdel.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdsdel(int seq, Model model) {
		logger.info("Welcome PDSController pdsdel! "+ new Date());
		jYLPDSService.delPDS(seq);
		return "redirect:/pdslist.do";
	}//
	@RequestMapping(value = "pdsupdateAf.do", 
			method = RequestMethod.POST)
	public String pdsupdateAf(
			JYLPds pdsdto,String namefile,
			HttpServletRequest request,
			@RequestParam(value="fileload", required=false)MultipartFile fileload,
			Model model) {
		model.addAttribute("doc_title", "PDS �����ϱ�");
		//logger.info("Welcome PDSController pdsupdateAf! ----------------------"+ pdsdto);
		
		pdsdto.setFilename(fileload.getOriginalFilename());//�̸��� ���Ծȵ�
		String fupload =request.getServletContext().getRealPath("/upload");
		//logger.info(": ------------->"+ fupload);
		if(pdsdto.getFilename()!=null && !pdsdto.getFilename().equals("")){
			logger.info("Welcome PDSController pdsupdateAf! "+ new Date());
			
			//String fupload = "c:\\upload";//��Ȳ�� ���� �����ϵ��� ������.
			String f=pdsdto.getFilename();
			String newFile=FUpUtil.getNewFile(f);//�̸�+long+.���̻�
			//logger.info("Welcome PDSController pdsupdateAf!     "+fupload+"/"+newFile);
			pdsdto.setFilename(newFile);//�̸� ����
			try {
				File file=new File(fupload+"/"+newFile);
				//logger.info(fupload+"\\"+newFile);
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				jYLPDSService.updatePDS(pdsdto);
				//logger.info("Welcome pdsupdateAf if success! =====================");
			} catch (IOException e) {
				logger.info("Welcome pdsupdateAf if fail! =========================");
			}
		}else {
			if((namefile!=null && !namefile.equals("")) ){
				pdsdto.setFilename(namefile);//�̸� ����
				jYLPDSService.updatePDS(pdsdto);
				//logger.info("Welcome pdsupdateAf else success! =======================");
			}else{
				logger.info("Welcome pdsupdateAf no update ! =======================");
			}
		}
		//logger.info("Welcome PDSController pdsupdateAf!  end ------------------- "+ new Date());
		
		return "redirect:/pdslist.do";
	}//
	@RequestMapping(value = "pdsupdate.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdsupdate(int seq, Model model) {
		logger.info("Welcome PDSController pdsupdate! ======================="+ new Date());
		JYLPds pdsdto=jYLPDSService.getPDS(seq);
		model.addAttribute("pds", pdsdto );
		model.addAttribute("doc_title", "PDS �����ϱ�");
		return "pdsupdate.tiles";
	}//

}
