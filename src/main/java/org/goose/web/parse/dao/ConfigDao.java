package org.goose.web.parse.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by smart on 15-8-29.
 */
@Component
public class ConfigDao {

    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(OperateLogDao.class);
    private final String proxy = "proxy";
    private final String threshold = "stopwordThreshold";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public void updateProxy(String proxyUrl){
        String sql = "UPDATE CONFIG SET VALUE = ? WHERE KEY = '"+proxy+"'";
        jdbcTemplate.update(sql, proxyUrl);
    }

    public void updateThreshod(String proxyUrl){
        String sql = "UPDATE CONFIG SET VALUE = ? WHERE KEY = '"+threshold+"'";
        jdbcTemplate.update(sql, proxyUrl);
    }


    public String getProxy(){
        return getConfigByKeyWord(proxy);

    }

    public String getStopWordThreshod(){
        return getConfigByKeyWord(threshold);

    }

    private String getConfigByKeyWord(String keyword) {
        String sql = "select value from CONFIG WHERE KEY = ? ";
        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap(sql, new Object[]{keyword});
        String threshod = "";
        for (String s : stringObjectMap.keySet()) {
            threshod= (String)stringObjectMap.get(s);
            break;
        }
        return threshod;
    }


}
