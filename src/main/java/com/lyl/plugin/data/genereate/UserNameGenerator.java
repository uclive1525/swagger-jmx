package com.lyl.plugin.data.genereate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author niaoshuai
 */
public class UserNameGenerator {

    /**
     * 自动生成名字（中文）
     *
     * @param len
     * @return
     */
    public static String getRandomJianHan(int len) {
        String ret = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int hightPos, lowPos; // 定义高低位
            Random random = new Random();
            // 获取高位值
            hightPos = (176 + Math.abs(random.nextInt(39)));
            // 获取低位值
            lowPos = (161 + Math.abs(random.nextInt(93)));
            byte[] b = new byte[2];
            b[0] = (Integer.valueOf(hightPos).byteValue());
            b[1] = (Integer.valueOf(lowPos).byteValue());
            try {
                // 转成中文
                str = new String(b, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            ret += str;
        }
        return ret;
    }

    /**
    * @Author zgq
    * @Description  截取Cookiex(oldString)中的字符
    * @Date 20-9-25 上午8:33
     * @param oldString
     * @param subType
     * @param subLength
    * @return void
    **/
    public static String subString(String oldString,String subType,int subLength) {
        Map<String, Object> tmp = new HashMap<>();
        String newString ="";
        if (oldString.contains(subType)){
            newString = oldString.substring(subLength, oldString.length() - 1);
        }else {
            newString ="不匹配";
        }
       return newString;
    }

    /**
     * 生成随机用户名，数字和字母组成,
     *
     * @param length
     * @return
     */
    public static String userName(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 写一个百家姓的数组
     */
    private static final String[] FIRST_NAME_ARR = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈",
        "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶",
        "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
        "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费",
        "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于",
        "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧",
        "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴",
        "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席",
        "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐",
        "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢",
        "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪",
        "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀",
        "羊", "于", "惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段",
        "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班",
        "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘",
        "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲",
        "邰", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能",
        "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦",
        "雍", "却", "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "浦", "尚", "农", "温",
        "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦", "艾", "鱼", "容", "向", "古",
        "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文",
        "寇", "广", "禄", "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂",
        "晁", "勾", "敖", "融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养",
        "鞠", "须", "丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红", "游", "郏", "竺", "权", "逯", "盖",
        "益", "桓", "公", "仉", "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴", "归", "海", "晋", "楚",
        "闫", "法", "汝", "鄢", "涂", "钦", "商", "牟", "佘", "佴", "伯", "赏", "墨", "哈", "谯", "篁", "年",
        "爱", "阳", "佟", "言", "福", "南", "火", "铁", "迟", "漆", "官", "冼", "真", "展", "繁", "檀", "祭",
        "密", "敬", "揭", "舜", "楼", "疏", "冒", "浑", "挚", "胶", "随", "高", "皋", "原", "种", "练", "弥",
        "仓", "眭", "蹇", "覃", "阿", "门", "恽", "来", "綦", "召", "仪", "风", "介", "巨", "木", "京", "狐",
        "郇", "虎", "枚", "抗", "达", "杞", "苌", "折", "麦", "庆", "过", "竹", "端", "鲜", "皇", "亓", "老",
        "是", "秘", "畅", "邝", "还", "宾", "闾", "辜", "纵", "侴", "万俟", "司马", "上官", "欧阳", "夏侯",
        "诸葛", "闻人", "东方", "赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "宗正", "濮阳", "淳于", "单于",
        "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空",
        "兀官", "司寇", "南门", "呼延", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "车正", "壤驷", "公良", "拓跋",
        "夹谷", "宰父", "谷梁", "段干", "百里", "东郭", "微生", "梁丘", "左丘", "东门", "西门", "南宫", "第五", "公仪",
        "公乘", "太史", "仲长", "叔孙", "屈突", "尔朱", "东乡", "相里", "胡母", "司城", "张廖", "雍门", "毋丘", "贺兰",
        "綦毋", "屋庐", "独孤", "南郭", "北宫", "王孙" };

    private static String girl="秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    private static String boy="伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";


    /**
     * 随机生成人名
     * @param length {2 - 4}
     * @return
     */
    public static String getRandomNameByChinese(int length) {
        String name = "";
        if(length<=1){
            length = 2;
        }
        if(length >= 5){
            length = 4;
        }
        while (name.length()!=length){
            int index = NumberGenerator.randomInt(FIRST_NAME_ARR.length - 1);
            //获得一个随机的姓氏
            name = FIRST_NAME_ARR[index];
            //可以根据这个数设置产生的男女比例
            int i = NumberGenerator.randomInt(3);
            if (i == 2) {
                int j = NumberGenerator.randomInt(girl.length() - 2);
                if (j % 2 == 0) {
                    name = name + girl.substring(j, j + 2);
                } else {
                    name = name + girl.substring(j, j + 1);
                }
            } else {
                int j = NumberGenerator.randomInt(girl.length() - 2);
                if (j % 2 == 0) {
                    name = name + boy.substring(j, j + 2);
                } else {
                    name = name + boy.substring(j, j + 1);
                }

            }
        }
        return name;
    }


    /**
     * 生成英文名称
     */
    public static String genFirstNameByEnglish() {
        return FIRST_NAMES[new Random().nextInt(FIRST_NAMES.length)];
    }

    public  static String[] FIRST_NAMES = {"Aaron",
        "Abel",
        "Abraham",
        "Adam",
        "Adrian",
        "Aidan",
        "Alva",
        "Alex",
        "Alexander",
        "Alan",
        "Albert",
        "Alfred",
        "Andrew",
        "Andy",
        "Angus",
        "Anthony",
        "Apollo",
        "Arnold",
        "Arthur",
        "August",
        "Austin",
        "Ben",
        "Benjamin",
        "Bert",
        "Benson",
        "Bill",
        "Billy",
        "Blake",
        "Bob",
        "Bobby",
        "Brad",
        "Brandon",
        "Brant",
        "Brent",
        "Brian",
        "Brown",
        "Bruce",
        "Caleb",
        "Cameron",
        "Carl",
        "Carlos",
        "Cary",
        "Caspar",
        "Cecil",
        "Charles",
        "Cheney",
        "Chris",
        "Christian",
        "Christopher",
        "Clark",
        "Cliff",
        "Cody",
        "Cole",
        "Colin",
        "Cosmo",
        "Daniel",
        "Denny",
        "Darwin",
        "David",
        "Dennis",
        "Derek",
        "Dick",
        "Donald",
        "Douglas",
        "Duke",
        "Dylan",
        "Eddie",
        "Edgar",
        "Edison",
        "Edmund",
        "Edward",
        "Edwin",
        "Elijah",
        "Elliott",
        "Elvis",
        "Eric",
        "Ethan",
        "Eugene",
        "Evan",
        "Enterprise",
        "Ford",
        "Francis",
        "Frank",
        "Franklin",
        "Fred",
        "Gabriel",
        "Gaby",
        "Garfield",
        "Gary",
        "Gavin",
        "Geoffrey",
        "George",
        "Gino",
        "Glen",
        "Glendon",
        "Hank",
        "Hardy",
        "Harrison",
        "Harry",
        "Hayden",
        "Henry",
        "Hilton",
        "Hugo",
        "Hunk",
        "Howard",
        "Henry",
        "Ian",
        "Ignativs",
        "Ivan",
        "Isaac",
        "Isaiah",
        "Jack",
        "Jackson",
        "Jacob",
        "James",
        "Jason",
        "Jay",
        "Jeffery",
        "Jerome",
        "Jerry",
        "Jesse",
        "Jim",
        "Jimmy",
        "Joe",
        "John",
        "Johnny",
        "Jonathan",
        "Jordan",
        "Jose",
        "Joshua",
        "Justin",
        "Keith",
        "Ken",
        "Kennedy",
        "Kenneth",
        "Kenny",
        "Kevin",
        "Kyle",
        "Lance",
        "Larry",
        "Laurent",
        "Lawrence",
        "Leander",
        "Lee",
        "Leo",
        "Leonard",
        "Leopold",
        "Leslie",
        "Loren",
        "Lori",
        "Lorin",
        "Louis",
        "Luke",
        "Marcus",
        "Marcy",
        "Mark",
        "Marks",
        "Mars",
        "Marshal",
        "Martin",
        "Marvin",
        "Mason",
        "Matthew",
        "Max",
        "Michael",
        "Mickey",
        "Mike",
        "Nathan",
        "Nathaniel",
        "Neil",
        "Nelson",
        "Nicholas",
        "Nick",
        "Noah",
        "Norman",
        "Oliver",
        "Oscar",
        "Owen",
        "Patrick",
        "Paul",
        "Peter",
        "Philip",
        "Phoebe",
        "Quentin",
        "Randall",
        "Randolph",
        "Randy",
        "Ray",
        "Raymond",
        "Reed",
        "Rex",
        "Richard",
        "Richie",
        "Riley",
        "Robert",
        "Robin",
        "Robinson",
        "Rock",
        "Roger",
        "Ronald",
        "Rowan",
        "Roy",
        "Ryan",
        "Sam",
        "Sammy",
        "Samuel",
        "Scott",
        "Sean",
        "Shawn",
        "Sidney",
        "Simon",
        "Solomon",
        "Spark",
        "Spencer",
        "Spike",
        "Stanley",
        "Steve",
        "Steven",
        "Stewart",
        "Stuart",
        "Terence",
        "Terry",
        "Ted",
        "Thomas",
        "Tim",
        "Timothy",
        "Todd",
        "Tommy",
        "Tom",
        "Thomas",
        "Tony",
        "Tyler",
        "Ultraman",
        "Ulysses",
        "Van",
        "Vern",
        "Vernon",
        "Victor",
        "Vincent",
        "Warner",
        "Warren",
        "Wayne",
        "Wesley",
        "William",
        "Willy",
        "Zack",
        "Zachary",
        "Abigail",
        "Abby",
        "Ada",
        "Adelaide",
        "Adeline",
        "Alexandra",
        "Ailsa",
        "Aimee",
        "Alexis",
        "Alice",
        "Alicia",
        "Alina",
        "Allison",
        "Alyssa",
        "Amanda",
        "Amy",
        "Amber",
        "Anastasia",
        "Andrea",
        "Angel",
        "Angela",
        "Angelia",
        "Angelina",
        "Ann",
        "Anna",
        "Anne",
        "Annie",
        "Anita",
        "Ariel",
        "April",
        "Ashley",
        "Audrey",
        "Aviva",
        "Barbara",
        "Barbie",
        "Beata",
        "Beatrice",
        "Becky",
        "Bella",
        "Bess",
        "Bette",
        "Betty",
        "Blanche",
        "Bonnie",
        "Brenda",
        "Brianna",
        "Britney",
        "Brittany",
        "Camille",
        "Candice",
        "Candy",
        "Carina",
        "Carmen",
        "Carol",
        "Caroline",
        "Carry",
        "Carrie",
        "Cassandra",
        "Cassie",
        "Catherine",
        "Cathy",
        "Chelsea",
        "Charlene",
        "Charlotte",
        "Cherry",
        "Cheryl",
        "Chloe",
        "Chris",
        "Christina",
        "Christine",
        "Christy",
        "Cindy",
        "Claire",
        "Claudia",
        "Clement",
        "Cloris",
        "Connie",
        "Constance",
        "Cora",
        "Corrine",
        "Crystal",
        "Daisy",
        "Daphne",
        "Darcy",
        "Dave",
        "Debbie",
        "Deborah",
        "Debra",
        "Demi",
        "Diana",
        "Dolores",
        "Donna",
        "Dora",
        "Doris",
        "Edith",
        "Editha",
        "Elaine",
        "Eleanor",
        "Elizabeth",
        "Ella",
        "Ellen",
        "Ellie",
        "Emerald",
        "Emily",
        "Emma",
        "Enid",
        "Elsa",
        "Erica",
        "Estelle",
        "Esther",
        "Eudora",
        "Eva",
        "Eve",
        "Evelyn",
        "Fannie",
        "Fay",
        "Fiona",
        "Flora",
        "Florence",
        "Frances",
        "Frederica",
        "Frieda",
        "Flta",
        "Gina",
        "Gillian",
        "Gladys",
        "Gloria",
        "Grace",
        "Grace",
        "Greta",
        "Gwendolyn",
        "Hannah",
        "Haley",
        "Hebe",
        "Helena",
        "Hellen",
        "Henna",
        "Heidi",
        "Hillary",
        "Ingrid",
        "Isabella",
        "Ishara",
        "Irene",
        "Iris",
        "Ivy",
        "Jacqueline",
        "Jade",
        "Jamie",
        "Jane",
        "Janet",
        "Jasmine",
        "Jean",
        "Jenna",
        "Jennifer",
        "Jenny",
        "Jessica",
        "Jessie",
        "Jill",
        "Joan",
        "Joanna",
        "Jocelyn",
        "Joliet",
        "Josephine",
        "Josie",
        "Joy",
        "Joyce",
        "Judith",
        "Judy",
        "Julia",
        "Juliana",
        "Julie",
        "June",
        "Karen",
        "Karida",
        "Katherine",
        "Kate",
        "Kathy",
        "Katie",
        "Katrina",
        "Kay",
        "Kayla",
        "Kelly",
        "Kelsey",
        "Kimberly",
        "Kitty",
        "Lareina",
        "Lassie",
        "Laura",
        "Lauren",
        "Lena",
        "Lydia",
        "Lillian",
        "Lily",
        "Linda",
        "lindsay",
        "Lisa",
        "Liz",
        "Lora",
        "Lorraine",
        "Louisa",
        "Louise",
        "Lucia",
        "Lucy",
        "Lucine",
        "Lulu",
        "Lydia",
        "Lynn",
        "Mabel",
        "Madeline",
        "Maggie",
        "Mamie",
        "Manda",
        "Mandy",
        "Margaret",
        "Mariah",
        "Marilyn",
        "Martha",
        "Mavis",
        "Mary",
        "Matilda",
        "Maureen",
        "Mavis",
        "Maxine",
        "May",
        "Mayme",
        "Megan",
        "Melinda",
        "Melissa",
        "Melody",
        "Mercedes",
        "Meredith",
        "Mia",
        "Michelle",
        "Milly",
        "Miranda",
        "Miriam",
        "Miya",
        "Molly",
        "Monica",
        "Morgan",
        "Nancy",
        "Natalie",
        "Natasha",
        "Nicole",
        "Nikita",
        "Nina",
        "Nora",
        "Norma",
        "Nydia",
        "Octavia",
        "Olina",
        "Olivia",
        "Ophelia",
        "Oprah",
        "Pamela",
        "Patricia",
        "Patty",
        "Paula",
        "Pauline",
        "Pearl",
        "Peggy",
        "Philomena",
        "Phoebe",
        "Phyllis",
        "Polly",
        "Priscilla",
        "Quentina",
        "Rachel",
        "Rebecca",
        "Regina",
        "Rita",
        "Rose",
        "Roxanne",
        "Ruth",
        "Sabrina",
        "Sally",
        "Sandra",
        "Samantha",
        "Sami",
        "Sandra",
        "Sandy",
        "Sarah",
        "Savannah",
        "Scarlett",
        "Selma",
        "Selina",
        "Serena",
        "Sharon",
        "Sheila",
        "Shelley",
        "Sherry",
        "Shirley",
        "Sierra",
        "Silvia",
        "Sonia",
        "Sophia",
        "Stacy",
        "Stella",
        "Stephanie",
        "Sue",
        "Sunny",
        "Susan",
        "Tamara",
        "Tammy",
        "Tanya",
        "Tasha",
        "Teresa",
        "Tess",
        "Tiffany",
        "Tina",
        "Tonya",
        "Tracy",
        "Ursula",
        "Vanessa",
        "Venus",
        "Vera",
        "Vicky",
        "Victoria",
        "Violet",
        "Virginia",
        "Vita",
        "Vivian"
    };

    /**
     * 企业名称
     */
    public static String getRandomCompanyName(){
        String[] areaArry = {"北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江","江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州","云南","陕西","甘肃","青海"};
        String[] formsArry = {"有限责任公司","股份有限公司","有限公司"};
        String[] industryArry = {"企业管理咨询","企业策划","商务咨询","商务信息服务","酒店管理咨询","翻译服务","航空服务","房地产信息咨询","文化咨询","教育信息咨询","二手车鉴定评估","投资控股","国际货运代理","行业名称:建筑设计咨询","工程信息咨询","园林景观设计","爆破科技","广告策划","影视文化传播","计属算机科技以及网络技术","生物科技","影音科技发展","农业科技","医药科技开发","林业科技","生物能源","信息科技"};
        String[] shortNameArry = {"金","新","中","盛","亚","信","华","豪","奥","凯","泰","鑫","创","宝","星","联","晨","百","尔","海","瑞","科","乐","飞","福","皇","嘉","达","佰","美","元","亮","名","欧","特","辰","康","讯","鹏","腾","宏","伟","钧","思","成","翔","隆","东","森","迪","赛","睿","艾","高","德","雅","格","纳","欣","亿","维","锐","菲","佳","沃","晟","博","扬","索","蓝","昂","兴","聚","鸿","略","众","汇","圣","卓","宇","国","普","绿","斯","登","诺","恒","辉","诗","香","鼎","碧","麦","邦","克","凡","利","卡","多","安","尚","川","州","帝","悦","情","明","滋","祥","逸","彩","朗","郎","爱","景","帆","阳","驰","通","骏","力","顺","领","迅","途","益","和","园","波","丰","壹","泽","旭","旺","融","誉","际","巨","骄","为","诚","妙","英","虹","芬","馨","尼","迈","群","拓","建","江","雷","天","策","易","威","玛","日","伦","道","发","唯","一","才","月","丹","文","立","玉","平","同","志","宜","林","奇","政","致","春","帅","盈","泓","品","庭","展","朔","轩","育","航","津","启","振","聆","翌","迎","常","浩","茗","杰","婷","越","岚","超","清","云","淼","业","义","意","资","湘","会","菁","萌","语","荣","赫","宁","铭","齐","毅","霆","晓","衡","儒","静","翰","蔚","忆","双","涛","丽","韵","耀","艺","巍","兰","雪","尧","谊","影","慧","洁","聪","垒","蕾","瀚","骁","永","吉","先","君","依","昌","哲","营","舒","曙","廷","渲","梦","瑜","菏","凤","叶","卫","臻","燕","霖","霏","莲","灿","颜","麒","韬","露","鹤","骄","厅","湾","凡","可","巧","弘","禾","竹","多","帆"};

        StringBuffer companyName = new StringBuffer();
        StringBuffer shortName = new StringBuffer();
        companyName.append(areaArry[NumberGenerator.randomInt(areaArry.length -1)]);

        int lenth = NumberGenerator.randomInt(2,4);
        int arryLength = shortNameArry.length-1;
        while (shortName.length()!=lenth){
            shortName.append(shortNameArry[NumberGenerator.randomInt(arryLength)]);
        }
        companyName.append(shortName)
                .append(industryArry[NumberGenerator.randomInt(NumberGenerator.randomInt(industryArry.length-1))])
                .append(formsArry[NumberGenerator.randomInt(NumberGenerator.randomInt(formsArry.length-1))]);
        return String.valueOf(companyName);
    }

    /**
    * @Author zgq
    * @Description  获取大学专业名称
    * @Date 2020/4/19 下午3:51
    * @Param []
    * @return java.lang.String
    **/
    public static String getCollageProfessional() {
        String[] arr = {"软件工程","汽车工程","计算机科学与技术","网络工程","土木工程"};
        Random r = new Random();
        return arr[r.nextInt(3)];

    }

}
