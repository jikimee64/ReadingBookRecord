package com.soap.tobyofspringone.chapter3.sub5.templatecallbackgeneric;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filePath));
            T res = initVal;
            String line = null;
            while( (line = br.readLine()) != null){
                res = callback.doSomethingWithLine(line, res);
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

    public Integer calcSumV2(String filePath) throws IOException {
        LineCallback<Integer> sumCallback =
            (line, value) -> value + Integer.valueOf(line);
        return lineReadTemplate(filePath, sumCallback, 0);
    }

    public Integer calcMultiplyV2(String filePath) throws IOException {
        LineCallback<Integer> multiplyCallback =
            (line, value) -> value * Integer.valueOf(line);
        return lineReadTemplate(filePath, multiplyCallback, 1);
    }

    public String concatenate(String filePath) throws IOException {
        LineCallback<String> concatenateCallback =
            (line, value) -> value + line;
        return lineReadTemplate(filePath, concatenateCallback, "");
    }

}