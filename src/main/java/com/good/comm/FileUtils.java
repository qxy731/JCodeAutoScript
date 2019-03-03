package com.good.comm;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class FileUtils {

    static Calendar calendar = Calendar.getInstance();
    static SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd.HHmmss");
    /**
     * 创建文件目录
     * 
     * @param filename
     *            文件全路径
     */
    public static void createFilePath(String filename) {
        if (!StringUtils.isEmpty(filename)) {
            filename = filename.trim();
            if (filename.endsWith("/") || filename.endsWith("\\")) {
                filename = filename.substring(0, filename.length() - 1);
            }
            int idx1 = filename.lastIndexOf('/');
            int idx2 = filename.lastIndexOf('\\');
            int idx = idx1 > idx2 ? idx1 : idx2;
            if (idx > 0) {
                File dir = new File(filename.substring(0, idx));
                if (!dir.exists()) {
                    dir.mkdirs();
                }
            }
        }
    }



    public static String getCurrentDate() {
        return format.format(new Date());
    }

    public static boolean rename(File file, String newFileName) {
        File nFile = new File(newFileName);
        return file.renameTo(nFile);
    }

    /**
     * 移动文件到指定目录，必须是同一文件系统
     * @param srcFile
     * @param newPath 指定目录
     * @return 如为空表示成功，否则是错误信息
     */
    public static String moveFile(File srcFile, String newPath,boolean replace) {
        if ( srcFile == null || !srcFile.exists()){
            return "源文件[" +(srcFile==null?"":srcFile.getAbsolutePath())+ "]不存在";
        }
        if (!srcFile.canWrite()){
            return "对文件[" + srcFile.getAbsolutePath() + "]没有写权限";
        }
        File targetPath = new File(newPath);
        if (!targetPath.exists()){
            targetPath.mkdirs();
        }
        File target = new File(newPath,srcFile.getName());
        if (target.exists()){
            if (replace){
                if (!target.delete()){
                    return "目标文件[" +target.getAbsolutePath()+ "]无法被覆盖";
                }
                else{
                    target = new File(newPath,srcFile.getName());
                }
            }
            else{
                return "目标文件[" +target.getAbsolutePath()+ "]已经存在";
            }
        }
        if (!srcFile.renameTo(target)) {
            return "移动文件失败[" + srcFile.getAbsolutePath() + "] to [" +newPath+ "]";
        }
        return null;
    }

    public static void quietClose(Closeable cl) {
        try {
            try {
            }
            finally {
                if (cl != null) {
                    cl.close();
                }
            }
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }
    

    /**
     * 复制文件到另外的目录，如果要复制的是目录，那么，目录的所有内容都会被复制
     * 
     * @param filePath
     *            要复制的文件或目录的绝对路径
     * @param dirPath
     *            文件要复制到的目录
     * @throws IOException
     *             如果复制出现错误，如：文件不存在，目录不存在，读写错误等
     */
    public static void copyFileToDir(String filePath, String dirPath)
            throws IOException {
        copyFileToDir(new File(filePath), new File(dirPath),null);
    }
    
    public static void copyFileToDir(String filePath, String dirPath,ICopyFilter filter)
        throws IOException {
        copyFileToDir(new File(filePath), new File(dirPath), filter);
    }

    /**
     * 复制文件到指定目录
     * 
     * @param file
     *              源文件
     * @param dir
     *              目标目录
     * 
     * @throws IOException
     */
    public static void copyFileToDir(File file, File dir,ICopyFilter filter) throws IOException {
        if (!file.exists()) {
            throw new IOException("复制文件失败，因为要复制的文件不存在："
                    + file.getAbsolutePath());
        }
        if (!dir.isDirectory()) {
            throw new IOException("复制文件失败，因为目标不是目录：" + dir.getAbsolutePath());
        }
        copyFile(file, new File(dir, file.getName()),filter);
    }

    /**
     * 复制文件或目录到新的文件或目录
     * 
     * @param source
     *            要复制的文件或者目录
     * @param target
     *            新的文件或者目录
     * @throws IOException
     *             如果复制中出现错误
     */
    public static void copyFile(File source, File target,ICopyFilter filter) throws IOException {
        if (source.isFile()) {
            if (filter != null) {
                if (!filter.accept(source, target)){
                    return;
                }
            }
            InputStream in = null;
            OutputStream out = null;
            try {
                FileUtils.createFilePath(target.getCanonicalPath());
                int bufferSize = (int) source.length();
                // 如果小于1M,那么,每次为100K
                if (bufferSize < 1024 * 1024) {
                    bufferSize = 1024 * 100;
                } else {
                    // 如果大于1M,那么,每次为1M
                    bufferSize = 1024 * 1024;
                }

                in = new FileInputStream(source);
                out = new FileOutputStream(target);
                byte[] buffer = new byte[bufferSize];
                int ch = 0;
                while ((ch = in.read(buffer)) != -1) {
                    out.write(buffer, 0, ch);
                    out.flush();
                }
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                    }
                }
            }
        } else {
            target.mkdirs();
            File[] files = source.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    copyFile(file, new File(target, file.getName()),filter);
                }
            }
        }
    }
    
    /**
     * 递归删除文件
     * @param file
     * @param fileOnly  只删除文件，不删除目录
     */
    public static void deleteFile(File file,boolean fileOnly) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for(File f : files) {
                    deleteFile(f,fileOnly);
                }
                if (!fileOnly) {
                    file.delete();
                }
            }
            else {
                file.delete();
            }
        }
    }
    
    /**
     * 一次性获得文件内容
     * 
     * @param file
     * @throws IOException 
     */
    public static byte[] getFileContent(File file) throws IOException {
        FileInputStream fis = null;
        try {
            fis =new FileInputStream(file);
            byte[] bs = new byte[fis.available()];
            fis.read(bs);
            return bs;
        } finally {
            FileUtils.quietClose(fis);
        }
    }

    /**
     * 保存文件内容
     * 
     * @param filepath
     * @param content
     * @param append
     * @throws IOException
     */
    public static void saveFileContent(String filepath, byte[] content, boolean append) throws IOException {
        createFilePath(filepath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filepath,append);
            fos.write(content);
        } finally {
            FileUtils.quietClose(fos);
        }
    }

    /**
     * 解析路径和文件名
     * c:/temp  com/Util.java
     * ==>
     * c:/temp/com  Util.java
     * 
     * @param path 
     *              路径
     * @param fn 
     *              带路径文件名
     * @return 
     *          String[0]--路径 
     *          String[1]--文件名
     */
    public static String[] translatePath(String path,String fn)
    {
        String[] ret = new String[2];
        path = path.replace('\'','/');
        if ( path.endsWith("/") )
           path = path.substring(0,path.length()-1);

        fn = fn.replace('\'','/');
        if ( fn.startsWith("/") )
           fn = fn.substring(1);

        int idx = fn.lastIndexOf('/');
        if (idx != -1) {
            path = (path.length() ==0 ? "":(path + '/')) + fn.substring(0,idx);
            fn = fn.substring(idx +1);
        }
        ret[0] = path;
        ret[1] = fn;
        return ret;
    }
    /**
     * 创建文件目录
     * 
     * @param pathname
     *              文件目录
     * @return
     *              如果目录已经存在，也返回true
     * @throws IOException
     */
    public static boolean mkdirs(String pathname) {
        File dir = new File(pathname);
        if (!dir.exists()) {
            return dir.mkdirs();
        }
        return true;
    }
    
    /**
     * 文件或目录重命名（move的功能）
     * 源路径和目标路径必须在同一磁盘设备（比如都在 C 盘）
     * 
     * @param oldFileName
     *                  源路径
     * @param newFileName
     *                  目标路径
     */
    public static boolean rename(String oldFileName,String newFileName)
    {
        File oldFile = new File(oldFileName);
        File newFile = new File(newFileName);
        if ( !oldFile.exists()){
            throw new RuntimeException("源文件[" +oldFileName+"]不存在");
        }
        if ( newFile.exists()){
            throw new RuntimeException("目标文件名[" +newFileName+"]已经存在");
        }
        
        return oldFile.renameTo(newFile);
    }
}
