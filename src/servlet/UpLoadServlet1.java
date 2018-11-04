package servlet;

import entiy.FileModel;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import service.FileModelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UpLoadServlet1",urlPatterns = "/UpLoadServlet1")
public class UpLoadServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory(1024,new File("D:/temp/"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        FileModelService fms=new FileModelService();
        upload.setFileSizeMax(1024*1024*5);
        upload.setHeaderEncoding("UTF-8");
        try {
            List<FileItem> fileItemList = upload.parseRequest(request);
            FileModel fileModel=null;
            for (FileItem item:fileItemList
                 ) {
                if (item.isFormField()){
                    String information = item.getString("utf-8");
                    System.out.println(information);
                   fileModel.setInformation(information);
                    fms.insertFileModel(fileModel);
                }else{
                    fileModel=new FileModel();
                    fileModel.setContentType(item.getContentType());
                    String fileName = item.getName();
                    long size = item.getSize();
                    String str;
                    if (size>=1024&&size<1024*1024){
                        str=(size/1024)+"KB";
                    }else if (size>=1024*1024&&size>1024*1024*1024){
                        str=(size/1024)+"MB";
                    }else if (size>=1024*1024*1024){
                        str=(size/1024)+"GB";
                    }else{
                        str=size+"B";
                    }
                    fileModel.setSize(str);
                    fileModel.setUploadTime(sdf.format(new Date()));
                    String uuid = UUID.randomUUID().toString();
                    fileName =uuid+fileName.substring(fileName.lastIndexOf("."));
                    fileModel.setFileName(fileName);
                    String directory = makeDirectory(fileName);
                    InputStream is = item.getInputStream();
                    FileUtils.copyInputStreamToFile(is,new File(this.getServletContext().getRealPath("/upload")+"/"+directory+fileName));
                    System.out.println(this.getServletContext().getRealPath("/upload"));
                    fileModel.setFile_path("/"+directory+fileName);
                    item.delete();
                }
            }
            List<FileModel> fileModels = fms.queryFileModel();
            request.setAttribute("fileModels",fileModels);
            request.getRequestDispatcher("/success.jsp").forward(request,response);
        }catch (FileUploadBase.FileSizeLimitExceededException e){
            request.setAttribute("message","单个文件超过5M");
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
    }
    /**
     * 返回新的目录结构： e.g  11（第一级目录）/2(第二级目录)
     */
    private String makeDirectory(String fileName){
        //1.得到文件名的hashCode值
        int code = fileName.hashCode();

        //2.算出第一层目录的名称
        int first = code & 0xF;

        //3.算出第二层目录的名称
        int second = code & (0xF>>1);
        return first+"/"+second+"/";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
