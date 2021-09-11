package com.soap.tobyofspringone.chapter3.sub5.templatecallback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer fileReadTemplate(String filePath, BufferedReaderCallback callback)
        throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            //콜백 오브젝트 호출. 템플릿에서 만든 컨텍스트 정보인 BR을 전달해주고
            //콜백의 작업 결과를 받아둔다.
            int ret = callback.doSomethingWithReader(br);

            return ret;
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

    public Integer calcSum(String filePath) throws IOException {
        BufferedReaderCallback sumCallback =
            br -> {
                Integer sum = 0;
                String line = null;
                while ((line = br.readLine()) != null) {
                    sum += Integer.valueOf(line);
                }
                return sum;
            };
        return fileReadTemplate(filePath, sumCallback);
    }

    public Integer calcMultiply(String filePath) throws IOException {
        BufferedReaderCallback multiplyCallback =
            br -> {
                Integer multiply = 1;
                String line = null;
                while ((line = br.readLine()) != null) {
                    multiply *= Integer.valueOf(line);
                }
                return multiply;
            };
        return fileReadTemplate(filePath, multiplyCallback);
    }

    //두번째 방법(공통부분 한번더 템플릿으로 이동)
    public Integer lineReadTemplate(String filePath, LineCallback callback, int initVal) throws IOException {
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filePath));
            Integer res = initVal;
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
        LineCallback sumCallback =
            (line, value) -> value + Integer.valueOf(line);
        return lineReadTemplate(filePath, sumCallback, 0);
    }

    public Integer calcMultiplyV2(String filePath) throws IOException {
        LineCallback multiplyCallback =
            (line, value) -> value * Integer.valueOf(line);
        return lineReadTemplate(filePath, multiplyCallback, 1);
    }


}
