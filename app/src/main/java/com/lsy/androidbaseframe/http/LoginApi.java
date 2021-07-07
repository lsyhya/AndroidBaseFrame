package com.lsy.androidbaseframe.http;

import com.hjq.http.config.IRequestApi;

public class LoginApi implements IRequestApi {
    @Override
    public String getApi() {
        return "store/storeLogin";
    }

    private String uuid;

    public LoginApi setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public final static class LoginStoreBean {


        /**
         * code : 200
         * msg : success
         * data : {"storeId":"store1"}
         */

        private int code;
        private String msg;
        private DataBean data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * storeId : store1
             */

            private String storeId;

            private String id;

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

}
