package com.example.json.schema.filter;

import com.example.json.schema.entity.BodyReaderHttpServletRequestWrapper;
import com.example.json.schema.entity.Result;
import com.example.json.schema.enums.EnumResult;
import com.example.json.schema.exception.JsonSchemaFileNotFoundException;
import com.example.json.schema.exception.ParamException;
import com.example.json.schema.jsonSchema.JsonSchemaUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * json schema参数校验过滤器
 *
 * @author: huangwh
 * @mail huangwh@txtws.com
 * @date: 2018-11-22 18:31
 */
public class JsonSchemaFilter extends BaseFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        this.logger.info("======》参数校验过滤器");
        if (servletRequest instanceof HttpServletRequest) {
            BodyReaderHttpServletRequestWrapper bodyReaderHttpServletRequestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
            if (bodyReaderHttpServletRequestWrapper != null) {
                //获取参数
                String rParams = bodyReaderHttpServletRequestWrapper.getBody();
                logger.info(new StringBuffer("[request_param][>>>][").append(bodyReaderHttpServletRequestWrapper.getBody()).append("]").toString());

                HttpServletRequest request = (HttpServletRequest) servletRequest;
                String reqUri = request.getRequestURI();
                this.logger.info(String.format("request uri:%s", reqUri));

                String path = getPathByUri(reqUri);
                this.logger.info(String.format("schema path:%s", path));

                //读取不到路径，跳转到业务处理
                if (StringUtils.isBlank(path) || StringUtils.isBlank(rParams)) {
                    filterChain.doFilter(bodyReaderHttpServletRequestWrapper, servletResponse);
                    return;
                }

                try {
                    //参数校验，异常抛出
                    JsonSchemaUtil.validateJson(rParams, path);
                    filterChain.doFilter(bodyReaderHttpServletRequestWrapper, servletResponse);
                } catch (Exception e) {
                    //按异常类型处理
                    this.logger.info(String.format("%s异常信息：%s",this.getClass().toString(), e.getMessage()));
                    //参数异常，返回异常信息
                    if (e instanceof ParamException) {
                        Result result = new Result();
                        result.setStatus(EnumResult.PARAMS_ERROR.getStatus());
                        result.setMessage(e.getMessage());

                        this.outRespon(servletResponse, Result.toString(result));
                        //json schema文件未找到，即不使用jsonschema插件校验，跳转到业务处理
                    } else if (e instanceof JsonSchemaFileNotFoundException) {
                        filterChain.doFilter(bodyReaderHttpServletRequestWrapper, servletResponse);
                        //其他异常，返回服务器异常
                    } else {
                        Result result = new Result();
                        result.setStatus(EnumResult.SERVER_ERROR.getStatus());
                        result.setMessage(EnumResult.SERVER_ERROR.getMessage());

                        this.outRespon(servletResponse, Result.toString(result));
                    }

                }
            }
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 通过请求uri动态获取校验json文件地址
     * @param uri
     * @return
     */
    private String getPathByUri (String uri) {
        String path = "";
        if (StringUtils.isNotBlank(uri) && uri.contains("/")) {
            String[] array = uri.split("/");
            String packageName = array[1];
            String fileName = "";
            if (array.length > 2) {
                List<String> list = Lists.newArrayList();
                for (int i = 2; i < array.length; i++) {
                    list.add(array[i]);
                }
                fileName = String.join("_", list);
            }
            if (StringUtils.isNotBlank(packageName)) {
                path = String.format("%s/", packageName);
            }
            if (StringUtils.isNotBlank(fileName)) {
                path = String.format("jsonSchema/%s%s_schema.json", path, fileName);
            }
        }
        return path;
    }
}


