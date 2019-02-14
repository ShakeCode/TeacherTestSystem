package org.spring.springboot.controller;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.spring.springboot.common.Constant;
import org.spring.springboot.domain.Student;
import org.spring.springboot.domain.Teacher;
import org.spring.springboot.service.StudentService;
import org.spring.springboot.service.TeacherService;
import org.spring.springboot.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileAsynscUploadController {

	@Autowired
	private StudentService studentService;
	
	
	@Autowired
	private TeacherService teacherService;
	
	/** 上传目录名*/  
    private static final String uploadFolderName = "uploadFiles";  
    
    
    /** 允许上传的扩展名*/
    private static String [] extensionPermit = {"txt","jpg","xls","zip","rar"};
	
	@RequestMapping(value="/leaderUpload") 
	public String uploadFilePage(String stu_id){
		
		return "leaderUpload";
	}
	
	@ResponseBody
    @RequestMapping(value = "/file_upload" ,method = RequestMethod.POST)
    public Map<String, Object> uploadApkFile(HttpServletRequest request,HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");

        Map<String, Object> json = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        
        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map map =multipartRequest.getFileMap();
         for (Iterator i = map.keySet().iterator(); i.hasNext();) {
                Object obj = i.next();
                multipartFile=(MultipartFile) map.get(obj);

               }
         //存放在本地工程目录
 		String curProjectPath = request.getServletContext().getRealPath("/");  
//         String saveDirectoryPath = curProjectPath + "/" + uploadFolderName;  
//         File saveDirectory = new File(saveDirectoryPath); 
         
         String saveDirectoryPath = Constant.fileUploadPath; 
         File saveDirectory = new File( Constant.fileUploadPath);  
         
        /** 获取文件的后缀* */
        String filename = multipartFile.getOriginalFilename();

        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            File tmpFile = File.createTempFile(filename,
                    filename.substring(filename.lastIndexOf(".")));
            tmpFile.delete();

            FileUtils.copyInputStreamToFile(inputStream, new File(saveDirectoryPath,filename));
            if((filename.substring(filename.indexOf("."), filename.length())).equals(".xls")||
            (filename.substring(filename.indexOf("."), filename.length())).equals(".xlsx")){
            	//解析入库
            	File desfile = new File(saveDirectoryPath+filename);
            	resolveStudentOrTeacher(desfile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	CloseStream(inputStream);
        }
        json.put("message", "文件上传成功");
        json.put("status", 1);
        return json;
    }
	
	/**
	 * 解析学生或者老师信息入库
	 * @param file
	 * @throws Exception 
	 */
	public void resolveStudentOrTeacher(File desfile) throws Exception{
		if(!desfile.exists()){
    		throw new Exception("找不到该文件");
    	}
		 InputStream is = new FileInputStream(desfile);
		 HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		 
		 Student student = null;
		 List<Student> stuList = new ArrayList<Student>();
		 Teacher teacher = null;
		 List<Teacher> teaList = new ArrayList<Teacher>();
		 
		 if("student_sheet".equals(hssfWorkbook.getSheetAt(0).getSheetName())){
			   // 循环工作表Sheet
			 for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			 if (hssfSheet == null) {
			  continue;
			 }
	         // 循环行Row
	             for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	                 HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	                 if (hssfRow != null) {
	                     student = new Student();
	                     HSSFCell name = hssfRow.getCell(0);
	                     HSSFCell stuNo = hssfRow.getCell(1);
	                     HSSFCell passward = hssfRow.getCell(2);
	                     HSSFCell colleName = hssfRow.getCell(3);
	                     HSSFCell enterDate = hssfRow.getCell(4);
	                     HSSFCell major = hssfRow.getCell(5);
	                     HSSFCell classId = hssfRow.getCell(6);
	                     student.setStu_name(getValue(name));
	                     student.setStu_no(getValue(stuNo).substring(0,getValue(stuNo).indexOf(".") ));
	                     student.setPassward(getValue(passward).substring(0, getValue(passward).indexOf(".")));
	                     student.setColle_name(getValue(colleName));
	                     student.setEnterTime(DateUtil.reverseStrToDate(getValue(enterDate)));
	                     student.setMajor(getValue(major));
	                     student.setClass_id(Integer.valueOf(getValue(classId).substring(0,getValue(classId).indexOf("."))));
	                     stuList.add(student);
	                 }
	             }
	             studentService.batchInsertStu(stuList);
			 }
		 }else if("teacher_sheet".equals(hssfWorkbook.getSheetAt(0).getSheetName())){
			   // 循环工作表Sheet
					 for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
					 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
					 if (hssfSheet == null) {
					  continue;
					 }
			         // 循环行Row
			             for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			                 HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			                 if (hssfRow != null) {
			                	 teacher = new Teacher();
			                     HSSFCell name = hssfRow.getCell(0);
			                     HSSFCell teaNo = hssfRow.getCell(1);
			                     HSSFCell passward = hssfRow.getCell(2);
			                     HSSFCell sex = hssfRow.getCell(3);
			                     HSSFCell colleName = hssfRow.getCell(4);
			                     HSSFCell address = hssfRow.getCell(5);
			                     HSSFCell oldColle = hssfRow.getCell(6);
			                     HSSFCell major = hssfRow.getCell(7);
			                     HSSFCell age = hssfRow.getCell(8);
			                     teacher.setTea_name(getValue(name));
			                     teacher.setTea_no(getValue(teaNo).substring(0, getValue(teaNo).indexOf(".")));
			                     teacher.setPassward(getValue(passward).substring(0, getValue(passward).indexOf(".")));
			                     teacher.setSex(getValue(sex));
			                     teacher.setColle_name(getValue(colleName));
			                     teacher.setAddress(getValue(address));
			                     teacher.setOld_colle(getValue(oldColle));
			                     teacher.setMajor(getValue(major));
			                     teacher.setAge(Integer.parseInt(getValue(age).substring(0, getValue(age).indexOf("."))));
			                     teaList.add(teacher);
			                 }
			             }
			             teacherService.batchInsertTea(teaList);
					 }
		 }else{
			 throw new Exception("没有找表头");
		 }
		 
	}
	
    @SuppressWarnings("static-access")
     private String getValue(HSSFCell hssfCell){
    	DecimalFormat df = new DecimalFormat("#");
		if (hssfCell == null){
			return "";
		}
         if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
             // 返回布尔类型的值
             return String.valueOf(hssfCell.getBooleanCellValue());
         } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
     		if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue())).toString();
     		}
				// 返回数值类型的值
             return String.valueOf(hssfCell.getNumericCellValue());
         }else {
             // 返回字符串类型的值
             return String.valueOf(hssfCell.getStringCellValue());
         } 	 
     }
         
	
	/**
	 * 关闭流
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
}
