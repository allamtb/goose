package org.goose.web.parse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2016
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2016/3/29 15:08
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:*Context.xml")

public class TestInit {
    @Autowired
    private TestClass myClass;

    @Test
    public void testAuto() throws Exception {

        myClass.sayYes();
    }

}
