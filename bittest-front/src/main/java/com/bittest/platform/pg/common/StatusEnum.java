package com.bittest.platform.pg.common;

/**
 * 状态枚举
 * Created by baijunlong on 2015/6/25.
 */
public class StatusEnum {
    public enum ReqType_Status {
        JSF(1, "dubbo"),
        POST(2, "POST"),
        GET(3, "GET"),
        SLEEP(4, "SLEEP"),
        DATABASE(5, "数据库操作"),
        HTTP_REQ_SUCCESS(200, "HTTP_REQ_SUCCESS");

        private final int status;
        private final String desc;

        private ReqType_Status(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public Integer status() {
            return status;
        }

        public String desc() {
            return desc;
        }
    }

    public enum Relation_Status {
        ENABLE(1, "启用"),
        DIS_ENABLE(2, "禁用");

        private final int status;
        private final String desc;

        private Relation_Status(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        public Integer status() {
            return status;
        }

        public String desc() {
            return desc;
        }
    }
}