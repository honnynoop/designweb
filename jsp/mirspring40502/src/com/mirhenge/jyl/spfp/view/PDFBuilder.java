package com.mirhenge.jyl.spfp.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mirhenge.jyl.calendar.help.CalendarUtil;
import com.mirhenge.jyl.pds.view.AbstractITextPdfView;
import com.mirhenge.jyl.spfp.model.SpfpDiary;

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
		// get data model which is passed by the Spring container
		SpfpDiary diary = (SpfpDiary)model.get("spfpPDF");
		//����
		String fontpath 
		=request.getServletContext().getRealPath("/font");
		String path 
		=request.getServletContext().getRealPath("/upload");
		Image img=null;
		if(!diary.getImg().contains("back")) {
			img=Image.getInstance(path+"/"+diary.getImg());			
		}
		doc.add(new Paragraph(diary.getWdate()+" ����"));
		
		PdfPTable table = new PdfPTable(2);//4ĭ
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] {1.0f, 5.0f});
		table.setSpacingBefore(10);
		//����
		// define font for table header row
		BaseFont fs=BaseFont.createFont(fontpath+"/H2MJRE.TTF", 
				BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		//Font font = FontFactory.getFont(FontFactory.HELVETICA);
		Font font = new Font(fs);
		Font font2 = new Font(fs);
		font.setColor(BaseColor.WHITE);
		font2.setColor(BaseColor.BLACK);
		// define table header cell
		PdfPCell cell = new PdfPCell();
		PdfPCell cell2 = new PdfPCell();
		PdfPCell cell3 = null;
		if(img!=null){
			cell3 = new PdfPCell(img, true);
			//����
			cell3.setBackgroundColor(BaseColor.WHITE);
			cell3.setPadding(4);
		}
		cell.setBackgroundColor(BaseColor.BLUE);
		cell.setPadding(4);
		cell2.setBackgroundColor(BaseColor.WHITE);
		cell2.setPadding(4);
		
		// write table header �ѱۃ���
		cell.setPhrase(new Phrase("�ۼ���", font));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(diary.getId(), font2));
		table.addCell(cell2);
		
		
		cell.setPhrase(new Phrase("�ۼ���", font));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(CalendarUtil.toStrDate(diary.getWdate())+"", font2));
		table.addCell(cell2);
		
		cell.setPhrase(new Phrase("���� ����Ʈ", font));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(diary.getRef(), font2));
		table.addCell(cell2);
		
		cell.setPhrase(new Phrase("�̹���", font));
		table.addCell(cell);
		if(cell3!=null) {
			table.addCell(cell3);
		}else {
			cell2.setPhrase(new Phrase("������ ���׿�...", font2));
			table.addCell(cell2);
		}
		
		cell.setPhrase(new Phrase("����", font));
		table.addCell(cell);
		cell2.setPhrase(new Phrase(diary.getContent(), font2));
		table.addCell(cell2);
		
		doc.add(table);
		
	}

}