package org.goose.tool.common;

import java.io.*;

/**
 * 功能描述：
 * Copyright: Copyright (c) 2015
 * Company: 电子科技大学
 * Author: MaoKai
 * Version: 1.0
 * Time:2015/4/19 10:59
 */
public class ObjectUtil {

        /**
         * 对象深copy
         * @param src obj
         * @return obj
         * @throws Throwable
         */
        public static Object deepClone(Object src) throws IOException, ClassNotFoundException {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(src);
                oos.close();

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Object o = ois.readObject();
                ois.close();
                return o;
        }
}
