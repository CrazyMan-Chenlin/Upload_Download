package servlet;

import entiy.FileModel;
import service.FileModelService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet(name = "DownLoadServlet1",urlPatterns = "/DownLoadServlet1")
public class DownLoadServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        FileModelService fileModelService=new FileModelService();
        FileModel fileModel = fileModelService.getFileModel(Integer.parseInt(id));
        InputStream is=new FileInputStream(new File( this.getServletContext().getRealPath("/upload")+fileModel.getFile_path()));
        ServletOutputStream os = response.getOutputStream();
        String filename=fileModel.getFileName();
        filename= URLEncoder.encode(filename,"utf-8");
        response.setHeader("content-disposition","attachment;filename="+filename);
        byte[] buf = new byte[1024];
        int len;
        while ((len=is.read(buf))!=-1){
            os.write(buf,0,len);
        }
        is.close();
        os.close();
        request.getRequestDispatcher("/success.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);
    }
}
