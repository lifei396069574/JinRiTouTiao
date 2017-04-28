package bean;

/**
 * 作者：李飞 on 2017/4/20 13:17
 * 类的用途：
 */

public class Login_suc {
    /**
     * code : 200
     * datas : {"username":"WWWWWWWW","userid":"6","key":"ebaf2ad1fdc16f5da262f8a55cec60e1"}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * username : WWWWWWWW
         * userid : 6
         * key : ebaf2ad1fdc16f5da262f8a55cec60e1
         */

        private String username;
        private String userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
