import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
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
import java.util.List;

@WebServlet(name = "UpLoadServlet2",urlPatterns = "/UpLoadServlet2")
public class UpLoadServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiskFileItemFactory factory=new DiskFileItemFactory(1024,new File("D:/temp/"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        //注册监听器，提供上传速度
        upload.setProgressListener(new MyListener());
        try {
            List<FileItem> fileItemList = upload.parseRequest(request);
            if (fileItemList!=null){
                for (FileItem item: fileItemList
                     ) {
                    if (item.isFormField()){
                        /*
                         * 由于使用了multipart/form-data这个
                         * 所以是无法 getParameter()
                         * 在上传表单中，除了file文件外，还有text，等类型
                         *  被封装在FileItem中
                         *  所以在FileItem中 有的是其他类型的数据，有的是file类型的数据，需要加以判断
                         * */
                        if(item.getFieldName().equals("text1")){
                            String text1=item.getString("UTF-8");
                            System.out.println("text1"+text1);
                        }
                    }else{
                        String fileName = item.getName();
                        String contentType = item.getContentType();
                        long size = item.getSize();
                        InputStream inputStream = item.getInputStream();
                        FileUtils.copyInputStreamToFile(inputStream,new File("D:/file/"+fileName));
                        item.delete();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
    }
}
  class MyListener implements ProgressListener{
    /*
    * l  上传字节数
    * l1 总字节数
    * i  文件数
    * */
      @Override
      public void update(long l, long l1, int i) {
          System.out.println("文件已经上传了"+l+"总字节数是"+l1+"正在传第"+i+"个文件");
      }
  }
