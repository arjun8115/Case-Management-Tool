package com.lawtendo.cmtool.application.helper;






import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.tool.xml.XMLWorkerHelper;

@Service
public class HTMLToPDFHelper {

	private static final Logger logger = LoggerFactory.getLogger(HTMLToPDFHelper.class);
	
	public void HtmlToPDFHelper() {
		logger.info("pdf helper");
		try {
			String k = "<html><body> This is my Project </body></html>";
			logger.info("html");
			// OutputStream file = new FileOutputStream(new File("C:\\xyz.pdf"));
			OutputStream file = new FileOutputStream(new File("hello.pdf"));
			 logger.info("in test.pdf");
			 Document document = new Document();
			 PdfWriter writer = PdfWriter.getInstance(document, file);
			 document.open();
			 InputStream is = new ByteArrayInputStream(k.getBytes());
			 XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
			 document.close();
			 file.close();
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
