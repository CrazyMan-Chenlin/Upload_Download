import Exception.FileTypeErrorException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.naming.Name;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "UpLoadServlet",urlPatterns = "/UpLoadServlet")
public class UpLoadServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // 参数一表示缓冲区的大小，
        // 参数二表示缓冲文件的临时目录
        DiskFileItemFactory factory = new DiskFileItemFactory(1024*10,new File("D:/temp/"));
        ServletFileUpload  upload = new ServletFileUpload(factory);
        //设置文件名编码
        upload.setHeaderEncoding("UTF-8");
        //设置单个文件最大容量
       upload.setFileSizeMax(1024*1024*5);
        try {
            //解析request数据，把每一个文件都装到FileItem中，
            List<FileItem> fileItemList = upload.parseRequest(request);
            for (FileItem fileItem: fileItemList
                 ) {
                //得到文件名
                String name = fileItem.getName();
                //得到文件大小
                long size = fileItem.getSize();
                //得到内容类型
                String contentType = fileItem.getContentType();
                //限制文件类型
                if(!contentType.toLowerCase().matches("image/[a-z]*")){
                    throw new FileTypeErrorException("文件类型不符合!");
                }
                //得到文件内容的输入流
                InputStream inputStream = fileItem.getInputStream();
                //使用FileUtils工具拷贝文件
                FileUtils.copyInputStreamToFile(inputStream,new File("D:/Files/"+name));
                //删除临时文件
                fileItem.delete();
                System.out.println(name);
                System.out.println(size);
                System.out.println(contentType);
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            request.setAttribute("Error", "文件超过5M！");
            request.getRequestDispatcher("upload.jsp").forward(request,response);
        } catch(FileUploadException e) {
            e.printStackTrace();
        } catch (FileTypeErrorException e) {
            request.setAttribute("Error",e.getMessage());
            request.getRequestDispatcher("upload.jsp").forward(request,response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
       doPost(request,response);
    }
}
