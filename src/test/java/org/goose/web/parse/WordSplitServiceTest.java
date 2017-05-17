package org.goose.web.parse;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.jsoup.helper.StringUtil;
import org.junit.Test;

import java.util.List;

public class WordSplitServiceTest {

    @Test
    public void testDoParse() throws Exception {

        String content = "Struts 和 JavaServer Faces 等 Web 框架只关注 Web 应用程序中的前进导航。在本文中，Maurizio Albari 介绍了一种改善 Web " +
                "应用程序后退导航的框架，这是通过保存已访问 Web 页面的服务器端导航历史和已访问 Web 页面的有名序列（即 Webflows）来实现的。通过该框架，还可以使用服务器端导航历史自动清理 HTTP" +
                " 会话，从而提高应用程序性能。更好的是，对于前进导航，您仍可以使用自己喜欢的 Web 框架。";
        List<Term> parse = ToAnalysis.parse(content);
        StringBuilder stringBuilder = new StringBuilder();
        for (Term term : parse) {
            String realName = term.getRealName();
            if(!StringUtil.isBlank(realName)) {
                stringBuilder.append(realName).append("/");
            }
        }
        System.out.println(stringBuilder);

    }
}