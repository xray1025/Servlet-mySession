package com.xr;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Login extends HttpServlet {

    List<User> users = new ArrayList<>();

    @Override
    public void init(){
        users.add(new User("zhangsan","123","pc"));
        users.add(new User("lisi","456","phone"));
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            JSONObject json = JSONObject.parseObject(responseStrBuilder.toString());
            String name = json.getString("name");
            String password = json.getString("password");
            json.put("key",1);
            json.put("msg","输入有误");


            for (User user : users) {
                if (name.equals(user.getName()) && password.equals(user.getPassword())){
                    json.put("key",0);

                    String id = UUID.randomUUID().toString().replace("-", "");
                    MySession mySession = new MySession();
                    mySession.setMsg(id, name, user.getGoods());

                    response.addCookie(new Cookie("name", name));
                    response.addCookie(new Cookie("cookieId", id));

                }
            }
            out.print(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}





