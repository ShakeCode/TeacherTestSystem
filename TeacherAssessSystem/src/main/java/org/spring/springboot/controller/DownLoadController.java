package org.spring.springboot.controller;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.spring.springboot.domain.AssessTeacherResult;
import org.spring.springboot.service.AssessTeacherResultService;
import org.spring.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DownLoadController extends BaseController{

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AssessTeacherResultService assessTeacherResultService;
	
	/** 上传目录名*/  
    private static final String downloadFolderName = "downloadFiles";  
    
    private static final String EXCEL_XLS = "xls";  
    private static final String EXCEL_XLSX = "xlsx";  
    
	@RequestMapping(value="/student_download",method=RequestMethod.GET) 
	public String studentDownLoad() throws IOException{
		//写入学生属性进入表格第一行
		writeStudent();
		//存放在本地工程目录
		String curProjectPath = request.getServletContext().getRealPath("/");  
	    String saveDirectoryPath = curProjectPath + "/" + downloadFolderName;  
	    File saveDirectory = new File(saveDirectoryPath); 
	    
	    File studentFile = new File(saveDirectoryPath+"\\student_temple.xls");
	    if(!studentFile.exists()){
	    	studentFile.createNewFile();
	    }
	    String fileName = "student_temple.xls";
	    response.setHeader("content-type", "application/force-download");
	    response.setContentType("application/force-download");
	    response.setHeader("Content-Disposition", "attachment;filename=" +fileName);
	    BufferedInputStream bf = null;
	    OutputStream fos = null;
	    try {
			byte[] buffer = new byte[1024*64];
			int length = 0;
			bf = new BufferedInputStream(new FileInputStream(studentFile));
			fos = response.getOutputStream();
			while((length = bf.read(buffer))!= -1){
				fos.write(buffer, 0, length);
				fos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseStream(fos);
			CloseStream(bf);
		}
	    return null;
	}
	
	@RequestMapping(value="/Assess/Teacher/Result/Download",method=RequestMethod.GET)
	public String AssessTeacherResultDownLoad() throws IOException{
		writeAssessForTeacher();
		//存放在本地工程目录
				String curProjectPath = request.getServletContext().getRealPath("/");  
			    String saveDirectoryPath = curProjectPath + "/" + downloadFolderName;  
			    File saveDirectory = new File(saveDirectoryPath); 
			    
			    File assessFile = new File(saveDirectoryPath+"\\老师总评结果.xls");
			    if(!assessFile.exists()){
			    	assessFile.createNewFile();
			    }
			    String fileName = "老师总评结果.xls";
			    response.setHeader("content-type", "application/force-download");
			    response.setContentType("application/force-download");
			    response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(fileName));
			    BufferedInputStream bf = null;
			    OutputStream fos = null;
			    try {
					byte[] buffer = new byte[1024*64];
					int length = 0;
					bf = new BufferedInputStream(new FileInputStream(assessFile));
					fos = response.getOutputStream();
					while((length = bf.read(buffer))!= -1){
						fos.write(buffer, 0, length);
						fos.flush();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					CloseStream(fos);
					CloseStream(bf);
				}
			    return null;
	}
	
	/**
	 * 写入评价老师的结果进入excel
	 * @throws FileNotFoundException
	 */
	public void writeAssessForTeacher()throws FileNotFoundException{
		List<AssessTeacherResult> assessList= new ArrayList<AssessTeacherResult>();
		try {
			assessList = assessTeacherResultService.selectAllAssessResult();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//存放在本地工程目录
		String curProjectPath = request.getServletContext().getRealPath("/");  
	    String saveDirectoryPath = curProjectPath + "/" + downloadFolderName;  
	    File saveDirectory = new File(saveDirectoryPath); 
	    HSSFWorkbook wb = new HSSFWorkbook();
	    HSSFSheet sheet = wb.createSheet("assessTeacher");
	    HSSFRow row=sheet.createRow(0); 
        HSSFCellStyle cellStyle=wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
        HSSFFont  fontStyle=wb.createFont();  
        fontStyle.setFontName("黑体");    
        //设置字体高度  
        fontStyle.setFontHeightInPoints((short)20);    
        //设置字体颜色  
        fontStyle.setColor(HSSFColor.BLUE.index);  
        //设置粗体  
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellStyle.setFont(fontStyle);   //设置字体颜色
        row.setRowStyle(cellStyle);
        //创建单元格并设置单元格内容  
        row.createCell(0).setCellValue("工号");  
        row.createCell(1).setCellValue("姓名");      
        row.createCell(2).setCellValue("学院");  
        row.createCell(3).setCellValue("评价类别"); 
        row.createCell(4).setCellValue("平均分"); 
        row.createCell(5).setCellValue("评价人数"); 
        row.createCell(6).setCellValue("总分"); 
        row.createCell(7).setCellValue("等级"); 
        
        //写入数据
        for(int i =0; i<assessList.size();++i){
        	AssessTeacherResult temp = assessList.get(i);
        	HSSFRow rowv = sheet.createRow(i + 1);
        	HSSFCell cell = rowv.createCell(0);
        	cell.setCellValue(temp.getTeaNo());
        	 cell = rowv.createCell(1);
        	 cell.setCellValue(temp.getTeaName());
        	 cell = rowv.createCell(2);
        	 cell.setCellValue(temp.getColleaName());
        	 cell = rowv.createCell(3);
        	 cell.setCellValue(temp.getAssessType());
        	 cell = rowv.createCell(4);
        	 cell.setCellValue(temp.getAvgScore());
        	 cell = rowv.createCell(5);
        	 cell.setCellValue(temp.getTotallAccessCount());
        	 cell = rowv.createCell(6);
        	 cell.setCellValue(temp.getTotallScore());
        	 cell = rowv.createCell(7);
        	 cell.setCellValue(temp.getTotallLevel());
        }
        FileOutputStream out = null;
        out = new FileOutputStream(saveDirectoryPath+"\\老师总评结果.xls");
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			CloseStream(out);
		}
	}
	
	@RequestMapping(value="/teacher_download",method=RequestMethod.GET) 
	public String teacherDownLoad() throws IOException{
		//写入老师基本属性如excel模板表
		writeTeacher();
		 //存放在本地工程目录
		String curProjectPath = request.getServletContext().getRealPath("/");  
	    String saveDirectoryPath = curProjectPath + "/" + downloadFolderName;  
	    File saveDirectory = new File(saveDirectoryPath); 
	    File teacherFile = new File(saveDirectoryPath+"\\teacher_temple.xls");
	    if(!teacherFile.exists()){
	    	teacherFile.createNewFile();
	    }
	    String fileName = "teacher_temple.xls";
	    response.setHeader("content-type", "application/force-download");
	    response.setContentType("application/force-download");
	    response.setHeader("Content-Disposition", "attachment;filename=" +fileName);
	    BufferedInputStream bf = null;
	    OutputStream fos = null;
	    try {
			byte[] buffer = new byte[1024*64];
			int length = 0;
			bf = new BufferedInputStream(new FileInputStream(teacherFile));
			fos = response.getOutputStream();
			while((length = bf.read(buffer))!= -1){
				fos.write(buffer, 0, length);
				fos.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CloseStream(fos);
			CloseStream(bf);
		}
		return null;
	}
	
	/**
	 * @param cin
	 */
	public void CloseStream(Closeable cin) {
		if(cin != null){
			try {
				cin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				throw new Exception("没有正确的流");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void writeStudent() throws FileNotFoundException{
		//存放在本地工程目录
		String curProjectPath = request.getServletContext().getRealPath("/");  
	    String saveDirectoryPath = curProjectPath + "/" + downloadFolderName;  
	    File saveDirectory = new File(saveDirectoryPath); 
	    HSSFWorkbook wb = new HSSFWorkbook();
        HSSFRow row= ctreatHSSFRow(wb,"1");
        //创建单元格并设置单元格内容  
        row.createCell(0).setCellValue("姓名");  
        row.createCell(1).setCellValue("学号");      
        row.createCell(2).setCellValue("密码");  
        row.createCell(3).setCellValue("学院名称"); 
        row.createCell(4).setCellValue("入学时间"); 
        row.createCell(5).setCellValue("专业"); 
        row.createCell(6).setCellValue("班级号"); 
        FileOutputStream out = null;
        out = new FileOutputStream(saveDirectoryPath+"\\student_temple.xls");
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			CloseStream(out);
		}
	}
	
	public void writeTeacher() throws FileNotFoundException{
		//存放在本地工程目录
		String curProjectPath = request.getServletContext().getRealPath("/");  
	    String saveDirectoryPath = curProjectPath + "/" + downloadFolderName;  
	    File saveDirectory = new File(saveDirectoryPath); 
	    HSSFWorkbook wb = new HSSFWorkbook();
        HSSFRow row= ctreatHSSFRow(wb,"2");
        //创建单元格并设置单元格内容  
        row.createCell(0).setCellValue("姓名");  
        row.createCell(1).setCellValue("工号");      
        row.createCell(2).setCellValue("密码");  
        row.createCell(3).setCellValue("性别"); 
        row.createCell(4).setCellValue("学院"); 
        row.createCell(5).setCellValue("住址"	); 
        row.createCell(6).setCellValue("毕业学校"); 
        row.createCell(7).setCellValue("专业"); 
        row.createCell(8).setCellValue("年龄"); 
        FileOutputStream out = null;
        out = new FileOutputStream(saveDirectoryPath+"\\teacher_temple.xls");
		try {
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			CloseStream(out);
		}
	}
	
	public HSSFRow ctreatHSSFRow( HSSFWorkbook  wb,String flag){
		HSSFSheet sheet=null;
		if("1".equals(flag)){
			 sheet =wb.createSheet("student_sheet");//创建学生sheet页
		}else if("2".equals(flag)){
			 sheet=wb.createSheet("teacher_sheet");//创建老师sheet页
		}else{
			 sheet=wb.createSheet("teacherAssess_sheet");//创建老师评价sheet页
		}
//		sheet.setColumnWidth(0, 3766);   //设置列宽
     HSSFRow row=sheet.createRow(0); 
     HSSFCellStyle cellStyle=wb.createCellStyle();
     cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中  
     HSSFFont  fontStyle=wb.createFont();  
     fontStyle.setFontName("黑体");    
     //设置字体高度  
     fontStyle.setFontHeightInPoints((short)20);    
     //设置字体颜色  
     fontStyle.setColor(HSSFColor.BLUE.index);  
     //设置粗体  
     fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
     cellStyle.setFont(fontStyle);   //设置字体颜色
     row.setRowStyle(cellStyle);
     return row;
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
