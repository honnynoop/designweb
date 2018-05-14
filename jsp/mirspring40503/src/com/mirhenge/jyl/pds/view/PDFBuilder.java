package com.mirhenge.jyl.pds.view;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.format.CellFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mirhenge.jyl.controller.PdsController;
import com.mirhenge.jyl.mboard.model.JYLMBoard;

import javafx.scene.control.Cell;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PDFBuilder extends AbstractITextPdfView {
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String fontpath 
		=request.getServletContext().getRealPath("/font");
		
		// get data model which is passed by the Spring container
		List<JYLMBoard> bbss = (List<JYLMBoard>) model.get("bbslist");
		
		doc.add(new Paragraph("JYLBoard for BBS 2017 pdf"));
		
		PdfPTable table = new PdfPTable(4);//4칸
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {1.0f, 1.0f, 6.0f, 2.0f});//4칸 비율
		table.setSpacingBefore(10);
		BaseFont fs=BaseFont.createFont(fontpath+"/H2MJRE.TTF", 
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		// define font for table header row
		//Font font = FontFactory.getFont(FontFactory.HELVETICA);
		Font font=new Font(fs);
		font.setColor(BaseColor.WHITE);
		
		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(4);
	
		//write table header 한글 깨지는 현상 어떻게 
		cell.setPhrase(new Phrase("번호", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("아이디", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("제목", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("작성일", font));
		table.addCell(cell);
		
		Font font2=new Font(fs);
		font2.setColor(BaseColor.BLACK);
		//font2.setFamily("");
		//font2.setStyle("");
		font2.setSize(9);
		
		PdfPCell cell2 = new PdfPCell();
		cell2.setPadding(4);
		// write table row data
		for (JYLMBoard bbs : bbss) {
			cell2.setPhrase(new Phrase(bbs.getSeq()+"", font2));
			table.addCell(cell2);
			
			cell2.setPhrase(new Phrase(bbs.getId()+"", font2));
			table.addCell(cell2);
			
			cell2.setPhrase(new Phrase(bbs.getTitle()+"", font2));
			table.addCell(cell2);
			
			cell2.setPhrase(new Phrase(bbs.getWdate()+"", font2));
			table.addCell(cell2);
			
		}
		
		doc.add(table);
		
	}

}