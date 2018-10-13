package tech.tengshe789.miaoliao.exception;


import lombok.Getter;
import tech.tengshe789.miaoliao.result.CodeMsg;

@Getter
public class GlobleException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobleException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }
}
