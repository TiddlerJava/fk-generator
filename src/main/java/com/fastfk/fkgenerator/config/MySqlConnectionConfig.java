package com.fastfk.fkgenerator.config;

/**
 * @info: MySql连接配置
 * @author: yuzhiqiang
 * @mail: yuzhiqiang@tieserv.com
 * @date: 2019/11/28 15:26
 * @vesion: 0.0.1
 */

public class MySqlConnectionConfig {
    public static final String CON_URL="jdbc:mysql://localhost:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true&&serverTimezone=UTC";
    public static final String DRIVER="com.mysql.cj.jdbc.Driver";
    public static final String USER_NAME="root";
    public static final String PASSWORD="123456";
}
