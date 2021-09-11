package com.soap.tobyofspringone.chapter3.sub5.templatecallbackfunction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.BiFunction;

public class Calculator {

    //두번째 방법(공통부분 한번더 템플릿으로 이동)
    public <T> T lineReadTemplate(String filePath, BiFunction<String, T, T> callback, T initVal)
        throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            T res = initVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                res = callback.apply(line, res);
            }
            return res;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) { //BR 오브젝트가 생성되기전에 예외가 발생할수도 있으므로 반드시 null 체크
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public Integer calcSumV3(String filePath) throws IOException {
        return lineReadTemplate(filePath,
            (line, value) -> value + Integer.valueOf(line),
            0);
    }

    public Integer calcMultiplyV3(String filePath) throws IOException {
        return lineReadTemplate(filePath,
            (line, value) -> value * Integer.valueOf(line),
            1);
    }

    public String concatenateV3(String filePath) throws IOException {
        return lineReadTemplate(filePath,
            (line, value) -> value + line,
            "");
    }

}