package org.goose.web.parse.dao;

import org.goose.web.parse.vo.OptLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by smart on 15-8-29.
 */
@Component
public class OperateLogDao {


    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(OperateLogDao.class);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void doLog(int modeId, String request, String response) {
        logger.debug("计入操作日志--------");
        String sql = "insert into opt_log(MOUDLUE,REQUST,RESPONSE) values (?,?,?)";
        jdbcTemplate.update(sql, modeId, request,response);
        logger.debug("操作日志成功--------");
    }

    public List<OptLog> LogList() {
        String sql = "SELECT m.MOUDAL_NAME,t.REQUST,t.RESPONSE from OPT_LOG t LEFT JOIN  MOUDAL m on t.MOUDLUE = m.MOUDAL_ID";


        List<OptLog> optLogList = jdbcTemplate.query(sql, new RowMapper<OptLog>() {
            public OptLog mapRow(ResultSet rs, int rowNum) throws SQLException {
                OptLog optLog = new OptLog();
                optLog.setMoudal(rs.getString(1));
                optLog.setRequest(rs.getString(2));
                optLog.setReponse(rs.getString(3));
                return optLog;
            }
        });
        return optLogList;
    }




}
