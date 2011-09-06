package com.hboam.am.util;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public final class ClassLoaderUtil {  
    private static Method addURL = initAddMethod();  
      
    private static final Method initAddMethod() {  
        try {  
            Method add = URLClassLoader.class  
                .getDeclaredMethod("addURL", new Class[] { URL.class });  
            add.setAccessible(true);  
            return add;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    private static URLClassLoader system = (URLClassLoader) ClassLoader.getSystemClassLoader();  
  
    /** 
     * ѭ������Ŀ¼���ҳ����е�JAR�� 
     */  
    private static final void loopFiles(File file, List<File> files) {  
        if (file.isDirectory()) {  
            File[] tmps = file.listFiles();  
            for (File tmp : tmps) {  
                loopFiles(tmp, files);  
            }  
        } else {  
            if (file.getAbsolutePath().endsWith(".jar") || file.getAbsolutePath().endsWith(".zip")) {  
                files.add(file);  
            }  
        }  
    }  
  
    /** 
     * <pre> 
     * ����JAR�ļ� 
     * </pre> 
     * 
     * @param file 
     */  
    public static final void loadJarFile(File file) {  
        try {  
            addURL.invoke(system, new Object[] { file.toURI().toURL() });  
            System.out.println("����JAR����" + file.getAbsolutePath());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * <pre> 
     * ��һ��Ŀ¼��������JAR�ļ� 
     * </pre> 
     * 
     * @param path 
     */  
    public static final void loadJarPath(String path) {  
        List<File> files = new ArrayList<File>();  
        File lib = new File(path);  
        loopFiles(lib, files);  
        for (File file : files) {  
            loadJarFile(file);  
        }  
    }  
}  
