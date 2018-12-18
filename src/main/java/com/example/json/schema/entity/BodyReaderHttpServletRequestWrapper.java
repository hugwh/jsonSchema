package com.example.json.schema.entity;

import jodd.io.StreamUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private byte[] body;  
    
    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {  
        super(request);  
        body = StreamUtil.readBytes(request.getReader());  
    }  
  
    @Override  
    public BufferedReader getReader() throws IOException {  
        return new BufferedReader(new InputStreamReader(getInputStream()));  
    }  
  
    @Override  
    public ServletInputStream getInputStream() throws IOException {  
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);  
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {  
                return bais.read();  
            }  
        };  
    }
    
    public String getBody() throws UnsupportedEncodingException {
    	return new String(body, "UTF-8");
    }
    
    public void setBody(String content) throws UnsupportedEncodingException {
    	body = content.getBytes("UTF-8");
    }

}
