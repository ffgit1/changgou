package com.changgou.file.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.file.pojo.FastFSFile;
import com.changgou.file.util.FastDFSClient;
import jdk.net.SocketFlow;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file){
        try {
            //判断文件是否存在
            if(file == null){
                throw new RuntimeException("文件不存在");
            }
            //获取文件的完整路径
            String originalFilename = file.getOriginalFilename();
            if(StringUtils.isEmpty(originalFilename)){
                //如果路径为空
                throw new RuntimeException("文件不存在");
            }
            //获取文件的扩展名
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //获取文件的内容
            byte[] content = file.getBytes();
            //创建文件上传的封装实体类
            FastFSFile fastFSFile = new FastFSFile(originalFilename, content, extName);
            //基于工具类进行文件上传，并接受返回参数 String[]
            String[] uploadResult = FastDFSClient.upload(fastFSFile);
            //封装返回结果
            String url = FastDFSClient.getTrackerUrl() + uploadResult[0] + "/" + uploadResult[1];
            return new Result(true, StatusCode.OK,"文件上传成功",url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,StatusCode.ERROR,"文件上传失败");
    }

    /**
     * 删除文件
     * @param url
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(String url){
        if(url!=null&&url.length()>35){
            //解析url，分成group组名和文件存储完整名
            String name = url.substring(28);
            String gorupname = name.substring(0, 6);
            String remoteFileName = name.substring(7);
            //删除图片
            int i = FastDFSClient.deleteFile(gorupname, remoteFileName);
            //如果i=0 代表删除成功
            if(i==0){
                return new Result(true,StatusCode.OK,"删除成功");
            }
            return new Result(false,StatusCode.ERROR,"删除失败");
        }

        return new Result(false,StatusCode.ERROR,"删除失败");
    }
}
