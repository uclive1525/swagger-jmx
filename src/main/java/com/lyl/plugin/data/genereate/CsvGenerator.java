package com.lyl.plugin.data.genereate;

import org.joda.time.DateTime;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName CsvGenerate
 * @Description TODO
 * @Author zgq
 * @Date 2020/4/20 下午3:09
 */
public class CsvGenerator {
    //文本路径
    static String path = System.getProperty("user.dir") + File.separator + "jsondir" + File.separator;

    /**
    * @Author zgq
    * @Description  TODO
    * @Date 2020/4/20 下午4:48
    * @Param [dataLists, headList, csvName]
    * @return java.io.File
    **/
    public static String createCSVFile(List<Object> dataLists, List<Object> headList, String csvName ) throws IOException {
        File csvFile = null;
        String csvFilePath ="";
        BufferedWriter csvWrite = null;
        DateTime dateTime=new DateTime();
        //去掉时间戳
        String outDate = dateTime.toString("yyyy-MM-dd-HH-mm-ss", Locale.CHINESE);
        try {
            csvFilePath = new StringBuffer(System.getProperty("user.dir"))+"/src/test/resources/"+csvName+".csv";
            File packagePathFile = new File(csvFilePath);
            if(!packagePathFile.exists()) {
                //如果父文件夹不存在
                if (!packagePathFile.getParentFile().exists()) {
                    //新建多层文件夹
                    packagePathFile.getParentFile().mkdirs();
                }
                packagePathFile.createNewFile();
            }

            //创建文件
            csvFile = new File(csvFilePath);
            csvFile.createNewFile();
            csvWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024);

            //写入表头
            write(headList, csvWrite);
            //写入数据
            for ( Object dataList:dataLists){
                write((List<Object>) dataList, csvWrite);
            }
            csvWrite.flush();
        } catch (IOException e) {
            throw  new IOException("文件生成失败");
        } finally {
            try {
                csvWrite.close();
            } catch (IOException e) {
                throw  new IOException("关闭文件流失败");
            }
        }
        return csvFilePath;
    }

   /**
   * @Author zgq
   * @Description  将数据按行写入数据
   * @Date 2020/4/20 下午4:49
   * @Param [dataList, csvWreite]
   * @return void
   **/
    private static void write(List<Object> dataList, BufferedWriter csvWreite) throws IOException {
        for (Object data: dataList) {
            StringBuffer buffer=new StringBuffer();
            String rowStr=buffer.append("\"").append(data).append("\",").toString();
            csvWreite.write(rowStr);
//            csvWreite.newLine();
        }
        csvWreite.newLine();
    }

    /**
    * @Author zgq
    * @Description  根据用户信息特定字段生成Csv文件　便于接口参数化测试
    * @Date 2020/4/20 下午4:13
    * @Param [field1, field2, rowNumS, csvPathName]
    * @return void
    **/
    public static String generateCsvFileByField(String field1, String field2, int rowNumS, String csvPathName) throws IOException {
        String testPath = new StringBuffer(System.getProperty("user.dir"))+"/src/test/resources/"+csvPathName+".csv";
        File packagePathFile = new File(testPath);
        if(!packagePathFile.exists()) {
            //如果父文件夹不存在
            if (!packagePathFile.getParentFile().exists()) {
                //新建多层文件夹
                packagePathFile.getParentFile().mkdirs();
            }
            packagePathFile.createNewFile();
        }
        FileOutputStream out =new FileOutputStream(testPath,true);
        for(int i= 0; i<rowNumS; i++){
            Map dataMap = UserInfoGenerate.getBaseInfo();
            //遍历map写入 todo
            out.write((dataMap.get(field1) +","+
                dataMap.get(field2)
            ).getBytes("UTF-8"));
            out.write("\n".getBytes("UTF-8"));
        }
        out.close();
        return testPath;
    }

    /**
    * @Author zgq
    * @Description  读取文本内容
    * @Date 20-9-28 下午2:51
     * @param fileName
    * @return
    **/
    public static String readTxtFile(String fileName) {
        String lineTxt = null;
        StringBuffer stringBuffer = null;
        try {
            File file = new File(path + fileName);
            //判断文件是否存在
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                stringBuffer = new StringBuffer();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    stringBuffer.append(lineTxt);
                    System.out.println(lineTxt);
                }
                System.out.println("内容"+stringBuffer);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return String.valueOf(stringBuffer);
    }
}

