package ter.services;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import lombok.Data;
import ter.controllers.VarController;

@Data
public class FileManager {
	private String fileName;
	private String path;
	private final Logger log = Logger.getLogger(VarController.class.getName());

	public FileManager(String path, String fileName) {
		this.fileName = fileName;
		this.path = path;
		
		File file = new File(path + fileName);
		file.delete();
	}

	public void addBreakPage() throws IOException {
		File file = new File(path + fileName);
		FileInputStream fis = new FileInputStream(path + fileName);
		var doc = new XWPFDocument(fis);
		fis.close();
		XWPFParagraph paragraph = doc.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addBreak(BreakType.PAGE);
		FileOutputStream fos = new FileOutputStream(file);
		doc.write(fos);
		fos.close();
		doc.close();
	}
	
	public void WriteRegularText(String text) {
		File file = new File(path + fileName);
		XWPFDocument doc;
		try {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(path + fileName);
				doc = new XWPFDocument(fis);
				fis.close();
			} else {
				doc = new XWPFDocument();
			}
			XWPFParagraph paragraph = doc.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText(text);

			log.info("text was writing: " + text);
			FileOutputStream fos = new FileOutputStream(file);
			doc.write(fos);
			fos.close();
			doc.close();

		} catch (Exception ex) {
			log.info(ex.getMessage());
			log.info(path);
			log.info(fileName);
		}
	}

	public void WriteTable(int[] xArgs, String[] yArgs, String expr) {
		File file = new File(path + fileName);
		XWPFDocument doc;
		try {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(path + fileName);
				doc = new XWPFDocument(fis);
				fis.close();
			} else {
				doc = new XWPFDocument();
			}
			XWPFTable table = doc.createTable(2, xArgs.length + 1);
			XWPFTableRow row = table.getRow(0);
			table.setCellMargins(0, 100, 0, 100);
			row.getCell(0).setText(expr + "\t");

			for (int i = 0; i < xArgs.length; ++i) {
				row.getCell(i + 1).setText(String.valueOf(xArgs[i]));
			}

			row = table.getRow(1);
			row.getCell(0).setText("p \t");
			if(yArgs[0].charAt(0) == 'C') {
				for (int i = 0; i < xArgs.length; ++i) {
					row.getCell(i + 1).setText(String.valueOf(yArgs[i]));
				}
			}
			else {
				for (int i = 0; i < xArgs.length; ++i) {
					row.getCell(i + 1).setText(String.format("%.4f", Double.parseDouble(yArgs[i])));
				}
			}
			

			FileOutputStream fos = new FileOutputStream(file);
			doc.write(fos);
			fos.close();
			doc.close();

		} catch (Exception ex) {
			log.info(ex.getMessage());
			log.info(path);
			log.info(fileName);
		}
	}
}