package cn.javabb.common.model;

import cn.javabb.common.enums.ResponseCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/10/28 18:29
 */
@Data
@ApiModel
public class ResponseModel<T> implements Serializable {

    private static final long serialVersionUID = -494064635182763697L;

    @ApiModelProperty(value = "结果码",example = "0")
    private Integer code;

    @ApiModelProperty(value = "返回信息",example = "成功")
    private String msg;

    @ApiModelProperty(value = "结果对象",example = "")
    private T data;


    public ResponseModel() {
        this.code = ResponseCodeEnum.SUCCESS.getCode();
        this.msg = ResponseCodeEnum.SUCCESS.getMsg();
    }

    public ResponseModel(Integer code) {
        this.code = code;
    }

    public ResponseModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseModel(ResponseCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public static <T> ResponseModel success(){
        return new ResponseModel();
    }

    public static <T> ResponseModel success(T data){
        ResponseModel responseModel = new ResponseModel(ResponseCodeEnum.SUCCESS);
        responseModel.setData(data);
        return responseModel;
    }

    public static <T> ResponseModel error(){
        return new ResponseModel(ResponseCodeEnum.FAIL);
    }

    public static <T> ResponseModel error(Integer code){
        return new ResponseModel(ResponseCodeEnum.FAIL);
    }

    public static <T> ResponseModel error(String msg){
        return new ResponseModel(ResponseCodeEnum.FAIL.getCode(),msg);
    }

    public static <T> ResponseModel error(ResponseCodeEnum codeEnum){
        return new ResponseModel(codeEnum);
    }

}
