package com.xr;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Check extends HttpServlet {

    @Override
    public void init(){
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();


        try {
            JSONObject json = new JSONObject();

            String name = "";
            String cookieId = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie cookie : cookies){
                if("name".equals(cookie.getName())){
                    name = cookie.getValue();
                }else if("cookieId".equals(cookie.getName())){
                    cookieId = cookie.getValue();
                }
            }

            MySession mySession = new MySession();
            HashMap<String,String> mySessionMsg = mySession.getMsg(name);
            if (cookieId.equals(mySessionMsg.get("id"))) {
                json.put("key", 0);
                json.put("name", name);
                json.put("goods", mySessionMsg.get("goods"));
            }

            out.print(json);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        doGet(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}