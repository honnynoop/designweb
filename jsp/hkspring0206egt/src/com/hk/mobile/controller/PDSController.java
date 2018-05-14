package com.hk.mobile.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
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

import com.hk.mobile.dao.HKPDSService;
import com.hk.mobile.help.FUpUtil;
import com.hk.mobile.member.model.HKPds;

@Controller
public class PDSController {

	private static final Logger logger = 
			LoggerFactory.getLogger(PDSController.class);

	@Resource(name = "hKPDSService")
	private HKPDSService hKPDSService;
	
	@RequestMapping(value = "pdslist.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String pdslist(Model model) {
		logger.info("Welcome PDSController pdslist! "+ new Date());
		List<HKPds> pdslist=hKPDSService.getPDSList();
		model.addAttribute("doc_title", "PDS �ڷ��");
		model.addAttribute("pdslist", pdslist );
		return "pdslist.tiles";
	}//
	@RequestMapping(value = "pdswrite.do", 
			method = RequestMethod.GET)
	public String pdswrite(Model model) {
		logger.info("Welcome PDSController pdswritebefor! "+ new Date());
		model.addAttribute("doc_title", "PDS �ڷᾲ��");
		return "pdswrite.tiles";
	}//
	
	@RequestMapping(value = "pdsupdateAf.do", 
			method = RequestMethod.POST)
	public String pdsupdateAf(
			HKPds pdsdto,String namefile,
			HttpServletRequest request,
			@RequestParam(value="fileload", required=false)MultipartFile fileload,
			Model model) {
		model.addAttribute("doc_title", "PDS �����ϱ�");
		//logger.info("Welcome PDSController pdsupdateAf! ----------------------"+ pdsdto);
		
		pdsdto.setFilename(fileload.getOriginalFilename());//�̸��� ���Ծȵ�
		String fupload =
				request.getServletContext().getRealPath("/upload");
		//logger.info(": ------------->"+ fupload);
		if(pdsdto.getFilename()!=null && !pdsdto.getFilename().equals("")){
			logger.info("Welcome PDSController pdsupdateAf! "+ new Date());
			
			//String fupload = "c:\\upload";//��Ȳ�� ��� �����ϵ��� ������.
			String f=pdsdto.getFilename();
			String newFile=FUpUtil.getNewFile(f);//�̸�+long+.���̻�
			//logger.info("Welcome PDSController pdsupdateAf!     "+fupload+"/"+newFile);
			pdsdto.setFilename(newFile);//�̸� ����
			try {
				File file=new File(fupload+"/"+newFile);
				//logger.info(fupload+"\\"+newFile);
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				hKPDSService.updatePDS(pdsdto);
				//logger.info("Welcome pdsupdateAf if success! =====================");
			} catch (IOException e) {
				logger.info("Welcome pdsupdateAf if fail! =========================");
			}
		}else {
			if((namefile!=null && !namefile.equals("")) ){
				pdsdto.setFilename(namefile);//�̸� ����
				hKPDSService.updatePDS(pdsdto);
				//logger.info("Welcome pdsupdateAf else success! =======================");
			}else{
				logger.info("Welcome pdsupdateAf no update ! =======================");
			}
		}
		//logger.info("Welcome PDSController pdsupdateAf!  end ------------------- "+ new Date());
		
		return "redirect:/pdslist.do";
	}//
	@RequestMapping(value = "pdsupload.do", 
			method = RequestMethod.POST)
	public String pdsupload(
			HKPds pdsdto,
			HttpServletRequest request,
			@RequestParam(value="fileload", required=false)MultipartFile fileload,
			Model model) {
		model.addAttribute("doc_title", "PDS �ڷ�ø���");
		logger.info("Welcome PDSController! "+ pdsdto);
		pdsdto.setFilename(fileload.getOriginalFilename());//�̸��� ���Ծȵ�
		
		logger.info("Welcome PDSController pdsupload! "+ new Date());
		String fupload =
		request.getServletContext().getRealPath("/upload");
		logger.info(": "+ fupload);
		//String fupload = "c:\\upload";//��Ȳ�� ��� �����ϵ��� ������.
		String f=pdsdto.getFilename();

		String newFile=FUpUtil.getNewFile(f);//�̸�+long+.���̻�
		
		logger.info(fupload+"/"+newFile);
		pdsdto.setFilename(newFile);//�̸� ����
		try {
			File file=new File(fupload+"/"+newFile);
			//logger.info(fupload+"\\"+newFile);
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			hKPDSService.uploadPDS(pdsdto);
			logger.info("Welcome pdsupload success! ");
		} catch (IOException e) {
			logger.info("Welcome pdsupload fail! ");
		}
		return "redirect:/pdslist.do";
	}//
	
	
	
	@RequestMapping(value = "pdsdetail.do", method = RequestMethod.GET)
	public String pdsdetail(
			int seq, Model model) {
		hKPDSService.pdsReadCount(seq);
		HKPds pdsdto=hKPDSService.getPDS(seq);
		model.addAttribute("pds", pdsdto );
		model.addAttribute("doc_title", "PDS �󼼳���");
		logger.info("Welcome CustUserController pdsdetail! "+ new Date());
		return "pdsdetail.tiles";
	}//
	
	@RequestMapping(value = "pdsupdate.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdsupdate(int seq, Model model) {
		logger.info("Welcome CustUserController pdsupdate! ======================="+ new Date());
		HKPds pdsdto=hKPDSService.getPDS(seq);
		model.addAttribute("pds", pdsdto );
		model.addAttribute("doc_title", "PDS �����ϱ�");
		return "pdsupdate.tiles";
	}//
	@RequestMapping(value = "pdsdel.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String pdsdel(int seq, Model model) {
		logger.info("Welcome CustUserController pdsdel! "+ new Date());
		hKPDSService.delPDS(seq);
		return "redirect:/pdslist.do";
	}//
	
	
	@RequestMapping(value = "fileDownload.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String download(int seq,HttpServletRequest request,
			String filename, Model model) throws Exception {
		//String fupload = "c:\\upload\\";
		String fupload =
				request.getServletContext().getRealPath("/upload");
		File downloadFile = new File(fupload +"/"+filename);
 
		model.addAttribute("downloadFile", downloadFile );
		model.addAttribute("seq", seq );
		return "downloadView";
	}
}
