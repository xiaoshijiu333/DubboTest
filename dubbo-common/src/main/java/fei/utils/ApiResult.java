package fei.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author xiaoshijiu
 * @function 返回给前端的json数据
 * @date 2019/4/16
 */
@Setter
@Getter
public class ApiResult<T> {
    //响应码
    private Integer resultCode;
    //响应信息
    private String resultMes;
    //响应数据
    private List<T> modelList;

    /**
     * @function: 返回成功信息
     */
    public static <T> ApiResult succ(List<T> model) {
        ApiResult<T> result = new ApiResult<>();
        result.setResultCode(200);
        result.setResultMes("请求成功");
        result.setModelList(model);
        return result;
    }
    /**
     * @function: 返回失败数据
     */
    public static <T> ApiResult fail() {
        ApiResult<T> result = new ApiResult<>();
        result.setResultCode(500);
        result.setResultMes("请求失败");
        return result;
    }
}
