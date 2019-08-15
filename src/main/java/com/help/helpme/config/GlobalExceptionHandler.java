package com.help.helpme.config;

import com.help.helpme.utils.Result;
import com.help.helpme.utils.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 校验异常拦截
 * @author: zhangshuai
 * @create: 2019-06-28 11:52
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = Logger.getLogger(getClass());


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result jsonErrorHandler(HttpServletRequest req, Exception e) {

        // 全局统一校验
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException  ex = (MethodArgumentNotValidException ) e;

            BindingResult result = ex.getBindingResult();
            StringBuffer sb = new StringBuffer();

            for (FieldError error : result.getFieldErrors()) {
//                String field = error.getField();
                String msg = error.getDefaultMessage()+",";
//                String message = String.format("%s:%s ", field, msg);
                sb.append(msg);
            }


            return ResultUtil.validateError(sb.toString().substring(0, sb.toString().length() -1));
        }
        // 未知异常
        else {
            logger.error(e.getMessage(), e);
            return ResultUtil.validateError(e.toString());
        }
    }

}
