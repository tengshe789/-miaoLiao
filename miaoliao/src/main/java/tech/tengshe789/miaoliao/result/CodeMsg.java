package tech.tengshe789.miaoliao.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeMsg {

    private int code;
    private String msg;

    //通用的错误码1001XX
    public static CodeMsg SUCCESS = new CodeMsg(100100, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(100101, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(100102, "参数校验异常：%s");
    public static final CodeMsg REQUEST_ILLEGAL = new CodeMsg(100103, "非法请求");


    //登录模块 1002XX
    public static CodeMsg NULL_USERNAME_PASSWORD = new CodeMsg(100200, "用户名密码不能为空~");
    public static CodeMsg ERROR_PWD= new CodeMsg(100201, "密码错误");



    private CodeMsg( ) {
    }

    private CodeMsg( int code,String msg ) {
        this.code = code;
        this.msg = msg;
    }

    //可以返回带参数的校验码
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }


}
