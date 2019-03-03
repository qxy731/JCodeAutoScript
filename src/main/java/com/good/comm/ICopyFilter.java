package com.good.comm;

import java.io.File;

/**
 * �ļ����ƹ���
 * @author wei.wu<wwu01@longtop.com>
 *
 */
public interface ICopyFilter {

    boolean accept(File source,File target);
}
