import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(name = "DownLoadServlet",urlPatterns = "/DownLoadServlet")
public class DownLoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* String path = DownLoadServlet.class.getResource("/download/头像.jpg").getPath();
        System.out.println(path);
        FileInputStream fis=new FileInputStream(new File(path));*/
        String filename="头像.jpg";
        InputStream is = DownLoadServlet.class.getResourceAsStream("/download/" + filename);
        ServletOutputStream os = response.getOutputStream();
        /*
        * 告诉浏览器以下载的方式下载资源
        * 响应头与请求头不能存在中文
        * */
        filename= URLEncoder.encode(filename,"utf-8");
        /*Ie&google*/
        response.setHeader("content-disposition","attachment;filename="+filename);
        /*firefox*/
        /*response.setHeader("content-disposition","attachment;filename*="+filename);*/
        /*可以使用user-agent 来判断浏览器*/
        byte[] buf=new byte[1024];
        int len=0;
        while ((len=is.read(buf))!=-1){
            os.write(buf,0,len);
        }
        os.close();
        is.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
