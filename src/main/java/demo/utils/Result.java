package demo.utils;

public class Result<D> {

    /**
     * 错误码
     */
    private int status;
    /**
     * 提示信息
     */
    private String info;
    /**
     * 具体的内容
     */
    private D data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
