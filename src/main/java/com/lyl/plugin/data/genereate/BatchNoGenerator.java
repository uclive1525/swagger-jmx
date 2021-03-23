package com.lyl.plugin.data.genereate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @author zhangtao
 */
public class BatchNoGenerator {

    public static String batchSourceType = null;
    public static String batchMtCode;
    public static String batchParams;
    public static String batchRangeUc;
    public static String UKey;
    //public static String batchUcParmas;

    public static String generateBatchSourceType(){
        List<String> list = new ArrayList<String>();
        Random random = new Random();
        list.add("UC");
        list.add("CT");
        list.add("SS");
        list.add("TB");
        list.add("OS");
        list.add("QB");
        list.add("CC");
        list.add("AC");
        int n = random.nextInt(list.size());
        //int n = 6;
        batchSourceType = list.get(n);
        return batchSourceType;
    }

    public static String generateCurrentDate(){
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return date;
    }


    public static String generateBatchNo(){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        int a = (int)(Math.random()*1000);
        String randnum = String.valueOf(a);

        return batchSourceType + dateName + randnum;
    }

    public static String generateMtCode(){
        // case "SS"
        // 报班首访短信(学员服务调用) 上课提醒(学员服务调用) 考试报名(学员服务调用) 成绩查询短信(学员服务调用)
        // 邮寄计划(学员服务调用)  面授课通知短信(学员服务调用)  打印准考证短信(学员服务调用) 重读短信(学员服务调用)
        // case "CC"
        // 直播通知2小时提醒  (课程中心调用)
        // case "AC"
        // 活动中心拼团活动灵讯通短信通知  (活动中心调用)    活动中心课程开通提醒  (活动中心调用)

        List<String> mtCodelist = new ArrayList<String>();
        Random random = new Random();
        int n;
        String type = batchSourceType;
        switch (type){
            case "UC":
                batchMtCode = "010001";
                break;
            case "CT":
                batchMtCode = "ct-pwd-notify";
                break;
            case "SS":
                mtCodelist.add("202003300935120000000001");
                mtCodelist.add("202003300857470000000001");
                mtCodelist.add("201910211431390000000001");
                mtCodelist.add("202003300939420000000001");
                mtCodelist.add("202003300942370000000001");
                mtCodelist.add("202006081721210000000001");
                mtCodelist.add("202003300927390000000001");
                mtCodelist.add("202003300944420000000001");

                mtCodelist.add("202007031417000000000004");
                mtCodelist.add("202003241804200000000001");
                //mtCodelist.add("202011041659000000000001");//cc
                mtCodelist.add("202011041659000000000002");
                mtCodelist.add("202011041659000000000003");
                //mtCodelist.add("202011041659000000000004");cc

                //n = 11;
                n = random.nextInt(mtCodelist.size());
                batchMtCode = mtCodelist.get(n).toString();
                break;
            case "TB":
                mtCodelist.add("MSG-TB-ALI-EMAIL-VERIFYCODE");
                mtCodelist.add("MSG-TB-ALI-EMAIL-ATTACHFILE");
                //n = 1;
                n = random.nextInt(mtCodelist.size());
                batchMtCode = mtCodelist.get(n);
                break;
            case "OS":
                batchMtCode = "202007212259000000000002";
                break;
            case "QB":
                batchMtCode = "202009161650000000000001";
                break;
            case "CC":
                /*mtCodelist.add("202006240909020000000001");
                mtCodelist.add("202006220955020000000001");*/
                mtCodelist.add("202011041659000000000001");
                mtCodelist.add("202011041659000000000004");
                //n = 1;
                n = random.nextInt(mtCodelist.size());
                batchMtCode = mtCodelist.get(n);
                break;
            default :
                mtCodelist.add("201911081804200000000001");
                mtCodelist.add("201908061829030000000001");
                //n = 0;
                n = random.nextInt(mtCodelist.size());
                batchMtCode = mtCodelist.get(n);
                break;
        }
        return batchMtCode;
    }

    public static String generateBatchParams(){
        String code = batchMtCode;
        switch (code){
            case "010001":
                batchParams = "\n    number: '01'";
                break;
            case "202003300857470000000001":
                batchParams = "\n    teacherName : '张三'\n    teacherPhone: '12345678901'";
                break;
            case "201910211431390000000001":
                batchParams = "\n    scheduleModulsubject : '量子力学'\n    scheduleCourseaddr: '世玺中心2804'\n    lessontimeStartdate: '20:05'";
                break;
            case "202003300939420000000001":
            case "202003300942370000000001":
            case "202006081721210000000001":
            case "202003300935120000000001":
            case "202003300944420000000001":
                batchParams = "\n    projectName : '注册会计师'";
                break;
            case "202003300927390000000001":
                batchParams = "\n    projectName : '注册会计师'\n    lessontimeStartdate: '2020-11-26 18:00:00'\n    scheduleCourseaddr: '世玺中心2804'";
                break;
            case "202007031417000000000004":
                batchParams = "\n    openDate : '11月01号'";
                break;
            case "202003241804200000000001":
                batchParams = "\n    msgContext : '自定义的消息，不要过长'";
                break;
            case "202011041659000000000001":
                batchParams = "\n    first : '您预订的课程信息'\n    courseName : 'Introduce'\n    classTime : '2015-07-15 星期三 19:00-20:00'\n    classAddress : '深圳罗湖中心'\n    remark : '请提前做好课程预习，提前15分钟到达上课中心。'\n    pagepathParams : 'courseId=123'";
                break;
            case "202011041659000000000002":
                batchParams = "\n    first : '您好，初一数学春季尖子班的作业内容有变化了~'\n    homeworkName : '2014年9月24日作业'\n    homeworkInfo : '请大家将数学课本68页面，课后作业3，6，7，8题作业完成，明早上交。'\n    remark : '感谢您的查阅，请及时更新您的作业信息。'\n    pagepathParams : 'paperId=123&projectId=123'";
                break;
            case "202011041659000000000003":
                batchParams = "\n    first : '学员张强，你已完成了当日学习计划'\n    studyContent : '第一课第5章《KPI的指定》'\n    completeTime : '2016年6月15日 18:36'\n    remark : '恭喜您，按时完成今天的学习任务，一枚学霸诞生！！'\n    pagepathParams : 'planId=123'";
                break;
            case "202011041659000000000004":
                batchParams = "\n    first : '你报名参加的课程更新了'\n    courseName : '如何熟练地和外国人聊天'\n    courseType : '系列课'\n    courseTeacher : 'nasa爸爸英语课堂'\n    courseTime : '2017-02-03 20:00'\n    remark : '你主动报名参加的课程即将老师，请准时参与哦'\n    pagepathParams : 'courseId=123&sourceId=123'";
                break;

            case "MSG-TB-ALI-EMAIL-VERIFYCODE":
                batchParams = "\n    verifyCode : '1223'\n    minutes: '15'\n    emailAddrees: '17721030192@163.com'\n    tips: 'sasasd'";
                break;
            case "MSG-TB-ALI-EMAIL-ATTACHFILE":
                //batchParams = "\n    title : '优路教育优秀人才给您发了封简历'\n    minutes: '15'\n    content: |- \n    <body>Document 111 </body>  \n    attachFiles: \n    - fileName: '15991902027310647.txt'\n    - filePath: 'http://192.168.11.207:8093/fileservice/api/file/download?path=2020/09/04/15991902027310647.txt'\n    - fileName: '15992729933970965.gif'\n    - filePath: 'http://192.168.11.207:8093/fileservice/api/file/download?path=2020/09/05/15992729933970965.gif'";
                batchParams = "\n    title : '优路教育优秀人才给您发了封简历'\n    minutes: '15'\n    content: Document 111\n    attachFiles: \n    - fileName: '15991902027310647.txt'\n    - filePath: 'http://192.168.11.207:8093/fileservice/api/file/download?path=2020/09/04/15991902027310647.txt'\n    - fileName: '15992729933970965.gif'\n    - filePath: 'http://192.168.11.207:8093/fileservice/api/file/download?path=2020/09/05/15992729933970965.gif'";
                //batchParams = "\n    title : '优路教育优秀人才给您发了封简历'\n    minutes: '15'\n    content: |- \n      <!DOCTYPE html>\n      <html lang=\"en\">\n      <head>\n        <meta charset=\"UTF-8\">\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n        <title>Document</title>\n      </head>\n      <body  style=\"background: #f3f4f6; padding-top:40px;\">\n        111111<div style=\"width:440px; margin:0 auto; border-radius: 4px; box-shadow: 0px 2px 4px 2px #dcdee3; padding:30px; background:#fff; box-shadow: 0px 2px 4px 2px #dcdee3;\">\n          <img src=\"./images/title.png\" alt=\"\" style=\"height:72px;\">\n          <div style=\"height: 1px; background: #c8cfda; margin: 30px 0;\"></div>\n          <div style=\"font-size: 20px; font-weight: 500;  color: #e73928; margin-bottom: 30px;\">优路教育优秀人才给您发了封简历</div>\n          <div style=\"background: #f3f4f6;border-radius: 4px; padding:30px; display: flex; align-items: center;\">\n            <img src=\"./images/defaultAvatar.png\" style=\"height: 64px; width: 64px; margin-right: 11px;\">\n            <div style=\"display: flex; flex-direction: column; justify-content:space-between; height: 60px; margin-left: 8px; font-size: 14px; color: #333;\">\n              <div style=\"font-weight: bold;\"><span style=\"margin-right: 10px; \">徐小兰</span><span>男</span></div>\n              <div><span style=\"margin-right: 10px;\">32岁</span><span style=\"margin-right: 10px;\">硕士</span><span>10-15</span></div>\n              <div>北京字节跳动科技有限公司</div>\n            </div>\n          </div>\n          <p style=\"font-size: 14px; font-weight: 400; color: #999999;line-height: 20px; margin-top:7px;\">感谢您对环球优路的信任，我们经为您推荐更多更优秀的人才。祝公司蓬勃发展、生意兴隆。</p>\n        </div>\n      </body>\n      </html>\n  attachFiles: \n    - fileName: '15991902027310647.txt'\n    - filePath: 'http://192.168.11.207:8093/fileservice/api/file/download?path=2020/09/04/15991902027310647.txt'\n    - fileName: '15992729933970965.gif'\n    - filePath: 'http://192.168.11.207:8093/fileservice/api/file/download?path=2020/09/05/15992729933970965.gif'";
                break;
            case "202007212259000000000002":
                batchParams = "\n    courseName : '课程名称120'\n    teacherName: 'xxx老师'\n    startTime: '2020-10-23 16:18:00'\n    tips: 'sasasd'";
                break;
            case "202009161650000000000001":
                batchParams = "\n    paperName : '九建量子力学'\n    beginTime: '下午14：30'\n    extClientparams: \n      type: '1'";
                break;
            case "202006240909020000000001":
                batchParams = "\n    playDate : '10月22号晚8点'\n    playName: '测试9建直播课'\n    extClientparams: \n    - type: '3'\n    content: \n    - remindType: '2'\n    - classId: 'classId01'\n    - coursewareId: 'courseware.getCoursewareId()'\n    - coursewareName: 'courseware.getCoursewareName()'\n    - coursewareBeginstate: '20:05'\n    - coursewareVideoprividers: 'courseware.getCoursewareVideoprividers()'";
                break;
            case "202006220955020000000001":
                batchParams = "\n    playDate : '10月22号晚8点'\n    playName: '测试9建直播课'\n    extClientparams: \n    - type: '3'\n    content: \n    - remindType: '1'\n    - classId: 'classId01'\n    - coursewareId: 'courseware.getCoursewareId()'\n    - coursewareName: 'courseware.getCoursewareName()'\n    - coursewareBeginstate: '20:05'\n    - coursewareVideoprividers: 'courseware.getCoursewareVideoprividers()'";
                break;
            case "201908061829030000000001":
                batchParams = "\n    activityName : '活动名称'\n    goodsName: '资料名称'\n    link: 'http://192.168.11.206:8082/apiservice/api/mc/batch/send'";
                break;
            case "201911081804200000000001":
                batchParams = "\n    link : 'http://192.168.11.206:8082/apiservice/api/mc/batch/send'\n    telephone: '客服电话'\n    classDate: '下午 2:10'";
                break;
            default:
                return "";
        }
        return batchParams;
    }

    public static String generateBatchRangeUc(){
       // case "UC" "CT"  "SS"  "AC"   MOBILE
        String type = batchSourceType;
        switch (type){
            case "TB":
                batchRangeUc = "EMAIL";
                break;
            case "OS":
            case "QB":
            case "CC":
                batchRangeUc = "ID";
                break;
            default :
                batchRangeUc = "MOBILE";
                break;
        }
        return batchRangeUc;
    }


    public static String generateUcKey(){
        // case "UC" "CT"  "SS"  "AC"   18539288035  18539288036
        String type = batchSourceType;
        switch (type){
            case "TB":
                UKey = "\n  - '17721030192@163.com'";
                break;
            case "OS":
            case "QB":
            case "CC":
                UKey = "\n  - 'USER20201021590000000002'\n  - 'USER20190925010000000563'";
                break;
            default :
                UKey = "\n  - '18539288035'";
                break;
        }
        return UKey;
    }


    /*public static String generateUcParams(){
        batchUcParmas = "\n  - userId: 'USER20190925010000000563'\n  - loginName: '张三'";
        return batchUcParmas;
    }*/


     public static void main(String[] args) {
         for(int i=0; i<2;i++){
             System.out.println(generateBatchSourceType());
             System.out.println(generateCurrentDate());
             System.out.println(generateBatchNo());
             System.out.println(generateMtCode());
             System.out.println(generateBatchParams());
             System.out.println(generateBatchRangeUc());
             System.out.println(generateUcKey());
             System.out.println("--------------------------");
         }
    }
}
