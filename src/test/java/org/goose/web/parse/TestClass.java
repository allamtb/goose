package org.goose.web.parse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2016
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2016/3/29 15:14
 */
@Component
public class TestClass implements InitializingBean {

    public void sayYes(){
        System.out.println("yes");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化了");
    }
}
