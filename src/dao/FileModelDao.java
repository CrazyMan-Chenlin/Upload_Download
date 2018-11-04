package dao;

import Util.JdbcUtil;
import entiy.FileModel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.List;


public class FileModelDao {
    public void insertFileModel(FileModel fileModel){
        QueryRunner qr=new QueryRunner(JdbcUtil.getDataSource());
        String sql="insert into file_list (fileName,Size,ContentType,upLoadTime,file_path,information) value(?,?,?,?,?,?)";
        try {
            qr.update(sql,new Object[]{
                    fileModel.getFileName(),
                    fileModel.getSize(),
                    fileModel.getContentType(),
                    fileModel.getUploadTime(),
                    fileModel.getFile_path(),
                    fileModel.getInformation()
            });
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
    public List<FileModel> queryFileModel() {
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select * from file_list";
        try {
            List<FileModel> query = (List<FileModel>) qr.query(sql, new BeanListHandler(FileModel.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    public FileModel getFile(int id){
        QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
        String sql = "select * from file_list where id=?";
        try {
            FileModel fileModel = (FileModel) qr.query(sql, new BeanHandler(FileModel.class),new Object[]{id});
            return fileModel;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
