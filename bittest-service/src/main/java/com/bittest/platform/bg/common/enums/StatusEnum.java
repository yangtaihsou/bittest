package com.bittest.platform.bg.common.enums;

/**
 * 状态枚举
 */
public class StatusEnum {
    /**
     * 接口请求类型
     */
    public enum ReqType_Status {
        JSF(1, "dubbo"),
        POST(2, "POST"),
        GET(3, "GET"),
        SLEEP(4, "SLEEP"),
        DATABASE(5, "DATABASE"),
        HTTP_REQ_SUCCESS(200, "HTTP_REQ_SUCCESS");
        private final int status;
        private final String desc;

        private ReqType_Status(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        // 根据枚举值，获取描述
        public static String getDesc(int index) {
            for (ReqType_Status c : ReqType_Status.values()) {
                if (c.status == index) {
                    return c.desc();
                }
            }
            return null;
        }

        public Integer status() {
            return status;
        }

        public String desc() {
            return desc;
        }
    }

    /**
     * 检查点检查方法类型
     */
    public enum CheckMethod_Status {
        CHECK_CONSTAINS(1, "包含"),
        CHECK_NOT_CONSTAINS(2, "不包含"),
        CHECK_EQUAL(3, "等于");

        private final int status;
        private final String desc;

        private CheckMethod_Status(int status, String desc) {
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

    /**
     * 检查点检查方法类型
     */
    public enum Result_Status {

        RESULT_PASS(1, "通过"),
        RESULT_FAIL(2, "失败");

        private final int status;
        private final String desc;

        private Result_Status(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        // 根据枚举值，获取描述
        public static String getDesc(int index) {
            for (Result_Status c : Result_Status.values()) {
                if (c.status == index) {
                    return c.desc();
                }
            }
            return null;
        }

        public Integer status() {
            return status;
        }

        public String desc() {
            return desc;
        }
    }

    public enum Finish_Status {
        RUN_ING(1, "执行中"),
        RUN_FINISH(2, "已完成"),
        RUN_EXCEPTION(3, "执行异常");
        private final int status;
        private final String desc;

        private Finish_Status(int status, String desc) {
            this.status = status;
            this.desc = desc;
        }

        // 根据枚举值，获取描述
        public static String getDesc(int index) {
            for (Finish_Status c : Finish_Status.values()) {
                if (c.status == index) {
                    return c.desc();
                }
            }
            return null;
        }

        public Integer status() {
            return status;
        }

        public String desc() {
            return desc;
        }
    }

    /**
     * 检查点检查方法类型
     */
    public enum CheckType_Status {
        CHECK_NOMAL(1, "普通检查点"),
        CHECK_DATABASE(2, "数据库检查点");
        private final int status;
        private final String desc;

        private CheckType_Status(int status, String desc) {
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