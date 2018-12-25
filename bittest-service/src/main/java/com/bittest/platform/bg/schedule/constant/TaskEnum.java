package com.bittest.platform.bg.schedule.constant;


public class TaskEnum {


    public enum TaskStatus {
        init(0, "子任务初始化"),
        lock(1, "子任务锁定"),
        finish(2, "子任务完成"),
        stop(3, "子任务暂停");
        private final Integer status;
        private final String desc;

        private TaskStatus(Integer status, String desc) {
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

    public enum TaskResultStatus {
        init(0, "任务结果初始化"),
        success(1, "任务结果成功"),
        fail(2, "任务结果失败");
        private final Integer status;
        private final String desc;

        private TaskResultStatus(Integer status, String desc) {
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
