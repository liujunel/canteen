package com.canteen.sys.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.canteen.sys.common.ResultObj;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 文件上传
 *
 * @author:junle
 * @create:2020/2/20-22:08
 */
@RestController
@RequestMapping("file")
@Slf4j
public class FileUploadController {

    // 构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
    // 默认值
    public static String REAL_PATH = "canteen/src/main/resources/static/uploadFile/img/";

    static {
        // 读取配置文件的存储地址
        InputStream stream = FileUploadController.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try {
            if (null != stream) {
                properties.load(stream);
                String filepath = properties.getProperty("filepath");
                if (null != filepath) {
                    REAL_PATH = filepath;
                }
            }
        } catch (IOException e) {
            log.error("无法加载file.properties文件" + e.getMessage(), e);
        }
    }


    @PostMapping("upload")
    public ResultObj upload(@RequestParam("fileName") MultipartFile file) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return new ResultObj(-1, "文件为空，上传失败");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //存放上传文件的文件夹
        String time = sdf.format(new Date());
        File dir = new File(REAL_PATH + time);
        if (!dir.isDirectory()) {
            //递归生成文件夹
            dir.mkdirs();
        }
        // 获取文件名
        String oldFileName = file.getOriginalFilename();
        // 构造新的文件名
        String newFileName = IdUtil.simpleUUID() + oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());
        // 获取文件的大小
        long size = file.getSize();
        log.info("上传文件的文件名为：" + newFileName + " 文件大小为：" + size);
        // 构建真实的文件路径
        File newFile = new File(dir.getAbsolutePath() + File.separator + newFileName + "_temp");
        try {
            file.transferTo(newFile); //保存文件
            log.info("上传文件成功 文件名为：" + newFileName + " 文件大小为：" + size + " 文件路径为：" + time + "/" + newFileName + "_temp");
            return new ResultObj(200, time + "/" + newFileName + "_temp");
        } catch (Exception e) {
            log.error("上传文件失败 文件名为：" + oldFileName + " 文件大小为：" + size + e.getMessage());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 显示图片(防止暴露图片地址等等)
     *
     * @param path
     * @return
     */
    @RequestMapping("showImageByPath")
    public ResponseEntity<Object> showImageByPath(String path) {
        //1,构造文件对象
        File file = new File(REAL_PATH, path);
        if (file.exists()) {
            //将下载的文件，封装byte[]
            byte[] bytes = null;
            try {
                if (StringUtils.isNotBlank(path)) {
                    bytes = FileUtil.readBytes(file);
                }
            } catch (Exception e) {
                log.error("图片不存在" + e.getMessage());
            }
            //创建封装响应头信息的对象
            HttpHeaders header = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            //header.setContentDispositionFormData("attachment", "123.jpg");
            //创建ResponseEntity对象
            ResponseEntity<Object> entity = new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
            return entity;
        }
        return null;
    }

    /**
     * 更改图片名字
     *
     * @param facultyImgPath
     */
    public static String renameFile(String facultyImgPath) {
        File file = new File(REAL_PATH, facultyImgPath);
        String imgPath = facultyImgPath.replace("_temp", "");
        if (file.exists()) {
            file.renameTo(new File(REAL_PATH, imgPath));
        }
        return imgPath;
    }

    /**
     * 删除原来的图片
     *
     * @param oldImgPathName
     */
    public static void removeFileByPath(String oldImgPathName) {
        if (!oldImgPathName.equals("default.jpg")) {
            File file = new File(REAL_PATH, oldImgPathName);
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
