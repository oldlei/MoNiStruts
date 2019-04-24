package com.dy.java17;


import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyFilter implements Filter {
    HashMap<String,Ban_sj> qz = new HashMap<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Wjjx wj = new Wjjx("struts.xml");
        qz = wj.qz();
    }

    @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String servletPath = request.getServletPath();
            if(servletPath.endsWith(".jsp")){
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                String reqUrl = servletPath.substring(1);
                System.out.println(reqUrl+"|reqUrl");
                Ban_sj ban_sj = qz.get(reqUrl);

                try{
                    Class<?> aClass = Class.forName(ban_sj.getClassName());
                    Object o = aClass.newInstance();
                    Map<String, String[]> parameterMap = servletRequest.getParameterMap();
                    Set<String> strings = parameterMap.keySet();
                    for (String str:strings) {
                        String methonName = "set"+str.substring(0,1).toUpperCase()+str.substring(1);

                        Method method = aClass.getMethod(methonName, new Class[]{String.class});
                        method.invoke(o,new Object[]{request.getParameter(str)});
                    }
                    Method execute = aClass.getMethod("execute", new Class[]{});
                    String fangFaName =(String) execute.invoke(o, new Object[]{});
                    System.out.println(fangFaName+"|ffn");
                    String s = ban_sj.getJresult_sj().get(fangFaName);
                    //s=
                    response.sendRedirect(s);
                    System.out.println(s);

                }catch (Exception e){
                    System.out.println("异常");
                }

            }

        }

    @Override
    public void destroy() {

    }
}
