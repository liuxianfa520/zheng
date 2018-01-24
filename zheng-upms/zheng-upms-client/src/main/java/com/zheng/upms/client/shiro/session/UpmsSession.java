package com.zheng.upms.client.shiro.session;

import lombok.Data;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;

/**
 * 自定义session
 *
 * @author shuzheng
 * @date 2017/2/27
 * @see Session
 */
@Data
public class UpmsSession extends SimpleSession {

    /**
     * 用户浏览器类型
     */
    private String userAgent;

    /**
     * 在线状态
     */
    private OnlineStatus status = OnlineStatus.off_line;

    /**
     * 在线状态
     */
    public enum OnlineStatus {
        /**
         * 在线
         */
        on_line("在线"),

        /**
         * 离线
         */
        off_line("离线"),

        /**
         * 强制退出
         */
        force_logout("强制退出");

        private final String info;

        OnlineStatus(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

}
