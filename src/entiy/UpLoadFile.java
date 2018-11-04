package entiy;

public class UpLoadFile {
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    private String fileName;
    private String size;
    private String  contentType;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUpLoadTime() {
        return upLoadTime;
    }

    public void setUpLoadTime(String upLoadTime) {
        this.upLoadTime = upLoadTime;
    }

    private String  upLoadTime;

}
