package org.spring.springboot.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.spring.springboot.common.Constant;
import org.spring.springboot.util.AjaxUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.*;
import java.util.Arrays;

@Controller
public class FileUploadController extends BaseController{

	/** 上传目录名*/  
    private static final String uploadFolderName = "uploadFiles";  
    
    
    /** 允许上传的扩展名*/
    private static String [] extensionPermit = {"txt","jpg","xls","zip","rar"};
	
	@RequestMapping(value="/leader_uploadFile") 
	public String uploadFilePage(String stu_id){
		
		return "leader_uploadFile";
	}
	
	
	
	@RequestMapping(value="/UploadFile",method=RequestMethod.POST) 
	public String uploadFile( MultipartHttpServletRequest multiReq){
	      
	    // 获取上传文件的全文件名
	    String uploadFilePath = multiReq.getFile("file").getOriginalFilename();
	    System.out.println("全文件名:" + uploadFilePath);
	    // 截取上传文件的文件名
	    String uploadFileName = uploadFilePath.substring(
	        uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
	    System.out.println("文件名：" + uploadFileName);
	    // 截取上传文件的后缀
	    String uploadFileSuffix = uploadFilePath.substring(
	        uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
	    System.out.println("后缀名:" + uploadFileSuffix);
	    FileOutputStream fos = null;
	    FileInputStream fis = null;
	    try {
	      fis = (FileInputStream) multiReq.getFile("file").getInputStream();
	      fos = new FileOutputStream(new File(Constant.fileUploadPath + uploadFileName
	          + "."+ uploadFileSuffix)
	          );
	      byte[] buffer = new byte[1024];
	      int length ;
	      while ((length = fis.read(buffer)) != -1){
	        fos.write(buffer,0,length);
	      }
	      fos.flush();
	   
	    } catch (IOException e) {
	    	e.printStackTrace();
	    	return "error";
	    } finally {
	    	CloseStream(fis);
	    	CloseStream(fos);
	    }
	    
		return "success";
	}
	
	@RequestMapping(value = "/uploader", method = RequestMethod.POST)  
    public @ResponseBody String fileUpload(@RequestParam("file") MultipartFile file) throws Exception {  
        //存放在本地工程目录
		String curProjectPath = session.getServletContext().getRealPath("/");  
//        String saveDirectoryPath = curProjectPath + "/" + uploadFolderName;  
//        File saveDirectory = new File(saveDirectoryPath); 
        
        String saveDirectoryPath = Constant.fileUploadPath; 
        File saveDirectory = new File( Constant.fileUploadPath);  
       
  
        // 判断文件是否存在  
        if (!file.isEmpty()) {  
            String fileName = file.getOriginalFilename();  
            String fileExtension = FilenameUtils.getExtension(fileName);   
            if(!Arrays.asList(extensionPermit).contains(fileExtension)){
                AjaxUtil.makeAjaxResponse(response, "不支持上传该文件格式", AjaxUtil.FAIL);
                throw new Exception("No Support extension.");  
            }             
            try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(saveDirectoryPath,file.getOriginalFilename()));
			   AjaxUtil.makeAjaxResponse(response, "上传成功", AjaxUtil.SUCCESS);
            } catch (Exception e) {
				e.printStackTrace();
			}
        }  
        
       return null;
       
    }
	
	
	
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
