package service;

import dao.FileModelDao;
import entiy.FileModel;

import java.util.List;

public class FileModelService {
    public void insertFileModel(FileModel fileModel){
        FileModelDao fileModelDao=new FileModelDao();
        fileModelDao.insertFileModel(fileModel);
    }
    public List<FileModel> queryFileModel(){
        FileModelDao fileModelDao=new FileModelDao();
        List<FileModel> fileModels = fileModelDao.queryFileModel();
        return fileModels;
    }
    public FileModel getFileModel(int id){
        FileModelDao fileModelDao=new FileModelDao();
        FileModel fileModel = fileModelDao.getFile(id);
        return fileModel;
    }
}
