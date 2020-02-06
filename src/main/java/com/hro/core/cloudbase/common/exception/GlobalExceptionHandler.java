package com.hro.core.cloudbase.common.exception;

import com.hro.core.cloudbase.enums.ResultCodeEnum;
import com.hro.core.cloudbase.response.CommonWrapper;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

//    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public CommonWrapper defaultErrorHandler(HttpServletResponse response, Exception e) {
        CommonWrapper wrapper = new CommonWrapper();
        wrapper.setResultCode(ResultCodeEnum.FAILURE.getCode());
        wrapper.setResultMsg("服务器内部错误！详细信息：" + e.getMessage());
        return wrapper;
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public CommonWrapper authenticationExceptionHandler() {
        CommonWrapper wrapper = new CommonWrapper();
        wrapper.setResultCode(ResultCodeEnum.FAILURE.getCode());
        wrapper.setResultMsg("认证失败，账号信息认证错误！");
        return wrapper;
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public CommonWrapper httpRequestMethodNotSupportedExceptionHandler(HttpServletResponse response) {
        CommonWrapper wrapper = new CommonWrapper();
        wrapper.setResultCode(ResultCodeEnum.FAILURE.getCode());
        wrapper.setResultMsg("method方法不允许！");
        return wrapper;
    }

    /**
     * 自定义http异常处理
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
            factory.addErrorPages(errorPage404, errorPage403);
        });
    }

    @RequestMapping(value = "/404")
    public CommonWrapper noFoundPage404() {
        CommonWrapper wrapper = new CommonWrapper();
        wrapper.setResultCode(ResultCodeEnum.RESOURCE_NOT_FOUND.getCode());
        wrapper.setResultMsg("没有找到访问资源！");
        return wrapper;
    }

    @RequestMapping(value = "/403")
    public CommonWrapper forbiden403() {
        CommonWrapper wrapper = new CommonWrapper();
        wrapper.setResultCode(ResultCodeEnum.FORBIDDEN.getCode());
        wrapper.setResultMsg("没有权限访问！");
        return wrapper;
    }
}
