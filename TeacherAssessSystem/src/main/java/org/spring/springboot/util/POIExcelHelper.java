package org.spring.springboot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Table.Cell;

public class POIExcelHelper {
	
  private static final String EXCEL_XLS = "xls";  
  private static final String EXCEL_XLSX = "xlsx";  
  
    /**
     * 设置表头样式 
     * @param workbook
     * @return
     */
    public static HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLUE.index);
        font.setFontHeight((short) 200);
        font.setFontName("楷体_GB2312");
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);
        style.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return style;
    }
    
    /**
     * 设置表格特别数据样式
     * @param workbook
     * @return
     */
    public static HSSFCellStyle getDataStyle2(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeight((short) 200);
        font.setFontName("楷体_GB2312");

        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setFont(font);
        style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return style;
    }

    /**
     * 创建单元格内容
     * @param row
     * @param id
     * @param value
     * @param style
     */
    @SuppressWarnings("deprecation")
    public static void createCell(HSSFRow row, int id, String value, HSSFCellStyle style) {
        HSSFCell cell = row.createCell((short) id);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(value);
        if (style != null) {
            cell.setCellStyle(style);
        }
    }

    /**
     * 创建报表文件
     * @param workbook
     * @param dir
     * @param filename
     * @throws IOException
     */
    public static void createFile(HSSFWorkbook workbook, String dir, String filename)
            throws IOException {
        dir = dir == null ? "" : dir.trim();
        if( !"".equals(dir) ){
            if( !dir.endsWith(File.separator) ){
                dir += File.separator ;
            }
        }
        File outdir = new File(dir);
        if (!outdir.exists()) {
            outdir.mkdirs();
        }
        FileOutputStream fOut = new FileOutputStream(dir + filename);
        workbook.write(fOut);
        fOut.flush();
        fOut.close();
    }
    
	
    /** 
     * 判断Excel的版本,获取Workbook 
     * @param in 
     * @param filename 
     * @return 
     * @throws IOException 
     */  
    public static Workbook getWorkbok(File file) throws IOException{  
        Workbook wb = null;  
        FileInputStream in = new FileInputStream(file);  
        if(file.getName().endsWith(EXCEL_XLS)){  //Excel 2003  
            wb = new HSSFWorkbook(in);  
        }else if(file.getName().endsWith(EXCEL_XLSX)){  // Excel 2007/2010  
            wb = new XSSFWorkbook(in);  
        }  
        return wb;  
    }  
}
