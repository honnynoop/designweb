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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mirhenge.jyl.calendar.help.CalendarUtil;
import com.mirhenge.jyl.calendar.help.JYLCal;
import com.mirhenge.jyl.member.dao.JYLMemberService;
import com.mirhenge.jyl.pds.help.FUpUtil;
import com.mirhenge.jyl.spfp.dao.SpfpDiaryService;
import com.mirhenge.jyl.spfp.model.SpfpDiary;
import com.mirhenge.jyl.spfp.model.SpfpDiaryTool;

@Controller
public class SpfpController {
	private static final Logger logger = 
			LoggerFactory.getLogger(SpfpController.class);
	@Autowired
	private SpfpDiaryService spfpDiaryService;
	@Autowired
	private JYLMemberService jYLMemberService;
	
	@RequestMapping(value = "spfpcal.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String spfpcal(HttpServletRequest request,
			JYLCal jcal,Model model) throws Exception {
		logger.info("Welcome SpfpController calendar! "+ new Date());
		jcal.calculate();
		String yyyyMm=CalendarUtil.yyyymm(
				jcal.getYear(), jcal.getMonth());
		List<SpfpDiary> flist= spfpDiaryService.getSpfpMonth(yyyyMm);
		model.addAttribute("flist", flist);
		model.addAttribute("doc_title", "CALENDAR");
		model.addAttribute("jcal", jcal);
		return "spfpcal.tiles";
	}//
	
	@RequestMapping(value = "spfplist.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String spfplist(String yyyymmdd, Model model) {
		logger.info("Welcome SpfpController spfplist! " + new Date());
		model.addAttribute("doc_title", "일지 기록실");
		model.addAttribute("spfplist", spfpDiaryService.getSpfpDay(yyyymmdd));
		return "spfplist.tiles";
	}//
	
	@RequestMapping(value = "spfpwrite.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String spfpwrite(Model model) {
		logger.info("Welcome SpfpController spfpwrite! " + new Date());
		model.addAttribute("doc_title", "일지 쓰기");
		//model.addAttribute("spfpwrite", spfpDiaryService.getSpfpDiaryList());
		return "spfpwrite.tiles";
	}//
	@RequestMapping(value = "spfpwriteAf.do", method = RequestMethod.POST)
	public String spfpwriteAf(SpfpDiary diary, HttpServletRequest request,
			@RequestParam(value = "fileload", required = false) MultipartFile fileload, Model model) {
		model.addAttribute("doc_title", "일지 업로드");
		logger.info("Welcome SpfpController! " + diary);
		diary.setImg(fileload.getOriginalFilename());//
		logger.info("Welcome SpfpController spfpwriteAf! " + new Date());
		String fupload =request.getServletContext().getRealPath("/upload");
		//String fupload = "c:\\upload";//상황에 따라 변경하도록 만들자.
		logger.info(": "+ fupload);
		String f=diary.getImg();

		String newFile = FUpUtil.getNewFile(f);

		logger.info(fupload + "/" + newFile);
		diary.setImg(newFile);
		try {
			File file = new File(fupload + "/" + newFile);
			logger.info(fupload+"\\"+newFile);
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());

			// db에 저장 안하면 코멘트.
			spfpDiaryService.writeSpfpDiary(diary);
			logger.info("Welcome spfpwriteAf success! ");
		} catch (IOException e) {
			logger.info("Welcome spfpwriteAf fail! ");
		}
		return "redirect:/spfpcal.do";
	}//
	
	@RequestMapping(value = "spfpdetail.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String spfpdetail(SpfpDiary diary,HttpServletRequest request,Model model) {
		logger.info("Welcome SpfpController spfpdetail! " + new Date());
		logger.info("Welcome SpfpController spfpdetail! " + diary);
		model.addAttribute("doc_title", "일지 보기");
		SpfpDiary dto = spfpDiaryService.getSpfpDiary(diary);
		logger.info("Welcome SpfpController spfpdetail! " + dto);
		model.addAttribute("spfpDiary", dto);
		model.addAttribute("path", request.getContextPath()+"/upload/");
		//model.addAttribute("spfpwrite", spfpDiaryService.getSpfpDiaryList());
		return "spfpdetail.tiles";
	}//
	
	@RequestMapping(value = "spfpupdate.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pdsupdate(SpfpDiary diary, HttpServletRequest request, Model model) {
		logger.info("Welcome CustUserController spfpupdate! =======================" + new Date());
		SpfpDiary dto = spfpDiaryService.getSpfpDiary(diary);
		model.addAttribute("spfpDiary", dto);
		logger.info("Welcome SpfpController spfpupdate! ");
		return "spfpupdate.tiles";
	}//
	
	@RequestMapping(value = "spfpupdateAf.do", method = RequestMethod.POST)
	public String spfpupdateAf(SpfpDiary diary, String namefile, HttpServletRequest request,
			@RequestParam(value = "fileload", required = false) MultipartFile fileload, Model model) {
		model.addAttribute("doc_title", "일지 수정");
		// logger.info("Welcome PDSController pdsupdateAf!
		// ----------------------"+ pdsdto);

		diary.setImg(fileload.getOriginalFilename());// �̸��� ���Ծȵ�
		//String fupload = request.getServletContext().getRealPath("/upload");
		// logger.info(": ------------->"+ fupload);
		if (diary.getImg() != null && !diary.getImg().equals("")) {
			logger.info("Welcome SpfpController spfpupdateAf! " + new Date());
			String fupload =request.getServletContext().getRealPath("/upload");
			//String fupload = "c:\\upload";//상황에 따라 변경하도록 만들자.
			String f = diary.getImg();
			String newFile = FUpUtil.getNewFile(f);// 
			// logger.info("Welcome PDSController pdsupdateAf!
			// "+fupload+"/"+newFile);
			diary.setImg(newFile);// 
			try {
				File file = new File(fupload + "/" + newFile);
				logger.info(fupload+"\\"+newFile);
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				spfpDiaryService.updateSpfpDiary(diary);
				// logger.info("Welcome pdsupdateAf if success!
				// =====================");
			} catch (IOException e) {
				logger.info("Welcome spfpupdateAf if fail! =========================");
			}
		} else {
			if ((namefile != null && !namefile.equals(""))) {
				diary.setImg(namefile);// �
				spfpDiaryService.updateSpfpDiary(diary);
				// logger.info("Welcome pdsupdateAf else success!
				// =======================");
			} else {
				logger.info("Welcome spfpupdateAf no update ! =======================");
			}
		}
		// logger.info("Welcome PDSController pdsupdateAf! end
		// ------------------- "+ new Date());

		return "redirect:/spfpcal.do";
	}//
	
	@RequestMapping(value = "spfpdelete.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String spfpdelete(SpfpDiary diary, Model model) {
		logger.info("Welcome SpfpController pdsdel! " + new Date());
		spfpDiaryService.deleteSpfpDiary(diary);
		return "redirect:/spfpcal.do";
	}//
	
	@RequestMapping(value = "spfpPDF.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String spfpPDF(SpfpDiary diary,Model model) throws Exception {
		logger.info("Welcome SpfpController spfpPDF! "+ new Date());
		    model.addAttribute("spfpPDF", spfpDiaryService.getSpfpDiary(diary));
		   // model.addAttribute("path", "C:\\Users\\Jermy\\workspace\\mirspring40428\\WebContent\\upload\\");
		return "diaryView";
	}
	
	@RequestMapping(value = "spfpprev.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody SpfpDiaryTool spfpprev(String seq, HttpServletRequest request, Model model) {
		logger.info("Welcome SpfpController spfpprev! " + new Date());
		SpfpDiary diary=new SpfpDiary();
		diary.setSeq(Integer.parseInt(seq));
		//String fupload =request.getServletContext().getRealPath("/upload");
		//SpfpDiaryTool tool=new SpfpDiaryTool(spfpDiaryService.getSpfpDiary(diary), fupload+"/");
		SpfpDiaryTool tool=new SpfpDiaryTool(spfpDiaryService.getSpfpDiary(diary), "./upload/");
		//SpfpDiaryTool tool=new SpfpDiaryTool(spfpDiaryService.getSpfpDiary(diary), request.getContextPath()+"/upload/");
		return tool;
	}//
	
	@RequestMapping(value = "spfpteam.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String spfpteam(String seq, HttpServletRequest request, Model model) {
		logger.info("Welcome SpfpController spfpteam! " + new Date());
		model.addAttribute("members", jYLMemberService.getIDList());
		return "spfpteam.tiles";
	}//
	
	@RequestMapping(value = "spfpteamAf.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String spfpteamAf(SpfpDiary diary, HttpServletRequest request, Model model) {
		logger.info("Welcome SpfpController spfpteamAf! " + new Date());
		spfpDiaryService.updateTeam(diary);
		return "redirect:/spfpteam.do";
	}//
}
