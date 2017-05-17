package org.goose.web.parse.service;

import com.google.common.base.CharMatcher;
import org.goose.web.parse.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by smart on 15-8-29.
 */
@Controller
@RequestMapping("/config")
public class ConfigService {
    @Autowired
    private ConfigDao configDao;


    /**
     * 执行正文解析
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "updateConfig")
    @ResponseBody
    public void doParse( String proxyValue,String stopWord) throws IOException {
        configDao.updateProxy(proxyValue);

        try {
            int i = Integer.parseInt(stopWord);
            configDao.updateThreshod(stopWord);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }

}
