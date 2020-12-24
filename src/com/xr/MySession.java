package com.xr;

import java.io.*;
import java.util.*;

public class MySession {

    public void setMsg(String id, String name, String goods){

        try {
            File file = new File("D:\\Java\\workspace\\login\\web\\WEB-INF\\" + name +".txt");
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write("id:" + id + "\n");
            output.write("name:" + name + "\n");
            output.write("goods:" + goods + "\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public HashMap<String, String> getMsg(String name){
        HashMap<String, String> map = new HashMap<>();
        try {
            File file = new File("D:\\Java\\workspace\\login\\web\\WEB-INF\\" +  name + ".txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String[] line = scanner.nextLine().split(":");
                map.put(line[0],line[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void delMsg(String name) {
        File file = new File("D:\\Java\\workspace\\login\\web\\WEB-INF\\" +  name + ".txt");
        file.delete();
    }
}