package com.data.datawaytest;

import com.data.datawaytest.entity.ImageVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

@SpringBootTest
class DatawayTestApplicationTests {

    @Test
    void contextLoads() {
        //String url="https://www.nbcolt.com/oitFile/20191227/549a905f-e43f-4080-850b-b0366a7ca15d.jpg";
        //String substring = url.substring(url.lastIndexOf("/"), url.lastIndexOf("."));

    }

    public String downloadImages(ImageVO imageVO) {
        URL url = null;
        String filepath="";
        try {
            url = new URL(imageVO.getUrl());
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            String newpaht =filepath+ LocalDateTime.now().toString();
            try{
                File file = new File(newpaht);
                if(!file.exists()){
                    file.mkdirs();
                }
            }catch(Exception e){
            }
            filepath=imageVO.getUrl().substring(imageVO.getUrl().lastIndexOf("/")+1, imageVO.getUrl().lastIndexOf("."))+".jpg";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filepath));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filepath;
    }
}
