package demo.utils;

public class ResultUtil {

    //返回对象
    public static Result toJson(Object data) {
        Result result1;
        if (data == null) {
            result1 = ResultUtil.error();
        } else {
            result1 = ResultUtil.success(data);
        }
        return result1;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.setStatus(0);
        result.setInfo("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error() {
        Result result = new Result();
        result.setStatus(1);
        result.setInfo("失败");
        result.setData(null);
        return result;
    }
}
