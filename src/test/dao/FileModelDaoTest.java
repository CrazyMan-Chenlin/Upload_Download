package dao;

import entiy.FileModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

/** 
* FileModelDao Tester. 
* 
* @author <Authors name> 
* @since <pre>十月 17, 2018</pre> 
* @version 1.0 
*/ 
public class FileModelDaoTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: insertFileModel(FileModel fileModel) 
* 
*/ 
@Test
public void testInsertFileModel() throws Exception {
    FileModel fileModel = new FileModel();
    fileModel.setContentType("image");
    fileModel.setFile_path("/asdfdsafds");
    fileModel.setFileName("acb");
    fileModel.setInformation("asdfhsadf");
    fileModel.setSize("20k");
    fileModel.setUploadTime("2018-10-11");
    new FileModelDao().insertFileModel(fileModel);
    List<FileModel> fileModels = new FileModelDao().queryFileModel();
    FileModel fileModel1 = fileModels.get(0);
    System.out.println(fileModel1.toString());
} 

/** 
* 
* Method: queryFileModel() 
* 
*/ 
@Test
public void testQueryFileModel() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getFile(int id) 
* 
*/ 
@Test
public void testGetFile() throws Exception { 
//TODO: Test goes here... 
} 


} 
