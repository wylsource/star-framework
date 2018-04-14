package org.star4j.framework;

/**
 * @Author: WuYL
 * @Description: 提供相关配置项常量
 * @Date: Created on 2017/6/16
 * @Modified By:
 */
public interface ConfigConstant {


    /**
     * 核心配置文件名称
     */
    String CONFIG_FILE = "star.properties";

    /**
     * 数据库常量配置
     */
    String JDBC_DRIVER = "star.framework.jdbc.driver";
    String JDBC_URL = "star.framework.jdbc.url";
    String JDBC_USERNAME = "star.framework.jdbc.username";
    String JDBC_PASSWORD = "star.framework.jdbc.password";

    /**
     * 加载 类、JSP、资源文件 常量配置
     */
    String APP_BASE_PACKAGE = "star.framework.app.base_package";
    String APP_JSP_PATH = "star.framework.app.jsp_path";
    String APP_ASSET_PATH = "star.framework.app.asset_path";
    String APP_UPLOAD_LIMIT = "star.framework.app.upload_limit";

}
