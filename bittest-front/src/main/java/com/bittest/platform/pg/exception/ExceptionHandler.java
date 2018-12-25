package com.bittest.platform.pg.exception;

import com.bittest.platform.pg.util.ErpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("ExceptionHandler捕获到了异常", ex);

        if (ex instanceof AuthException) {
            logger.info("已经被捕捉！");
            return new ModelAndView(new RedirectView("/auth/goAuth", true, false, false));//没有登录
        } else if (ex instanceof ErpException) {
            return new ModelAndView(new RedirectView("/error/catchError?type=1", true, false, false));//没有权限

        } else if (ex instanceof MaxUploadSizeExceededException) {

            logger.error("上传附件不能超过2M");
            return new ModelAndView(new RedirectView("/error/catchError?type=3", true, false, false));//附件过大

        } else {
            return new ModelAndView(new RedirectView("/error/catchError?type=2", true, false, false));//服务器内部错误

        }
    }
}
