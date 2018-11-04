import entiy.UpLoadFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "MoreUpLoadServlet",urlPatterns = "/MoreUpLoadServlet")
public class MoreUpLoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory(1024*10,new File("D:/temp/"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        //设置多文件上传的总长度
        /*upload.setSizeMax(1024*1024*10);*/
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            List<FileItem> fileItemList = upload.parseRequest(request);
            List<UpLoadFile> upLoadFiles = new ArrayList<>();
            for (FileItem fileItem: fileItemList
                 ) {
                UpLoadFile upLoadFile = new UpLoadFile();
                String filename = fileItem.getName();
                upLoadFile.setFileName(filename);
                long size = fileItem.getSize();
                String strSize;
                if (size>1024 && size<1024*1024){
                    strSize=(size/1024)+"KB";
                }else if (size>=1024*1024 && size<1024*1024*1024){
                    strSize=(size/1024*1024)+"MB";
                }else if (size>=1024*1024*1024){
                    strSize=(size/1024*1024*1024)+"GB";
                }else{
                    strSize=size+"B";
                }
                upLoadFile.setSize(strSize);
                upLoadFile.setContentType(fileItem.getContentType());
                upLoadFile.setUpLoadTime(sdf.format(new Date()));
                InputStream inputStream = fileItem.getInputStream();
                /*使用UUID算法，生成唯一的文件名，避免文件名覆盖*/
                String uuid = UUID.randomUUID().toString();
                filename.substring(filename.lastIndexOf("."));
                filename = uuid+filename;
                try {
                    FileUtils.copyInputStreamToFile(inputStream,new File("D:/File/"+filename));
                    fileItem.delete();
                    upLoadFiles.add(upLoadFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            request.setAttribute("upLoadFiles",upLoadFiles);
            request.getRequestDispatcher("/upLoadList.jsp").forward(request,response);
        }catch(FileUploadBase.SizeLimitExceededException e){
            request.setAttribute("Error","总文件长度超过10M");
            request.getRequestDispatcher(request.getContextPath()+"/MoreUpLoad2.jsp").forward(request,response);
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doGet(request,response);
    }
}
