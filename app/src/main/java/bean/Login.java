package bean;

/**
 * 作者：李飞 on 2017/4/20 13:17
 * 类的用途：
 */

public class Login {
    /**
     * code : 400
     * datas : {"error":"用户名长度要在6~20个字符"}
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
         * error : 用户名长度要在6~20个字符
         */

        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
