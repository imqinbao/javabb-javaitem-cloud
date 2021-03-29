package cn.javabb.generator.controller;

import cn.javabb.generator.config.GenConfig;
import cn.javabb.generator.model.TableInfo;
import cn.javabb.generator.util.GenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目生成器
 */
@Api(tags = "代码生成模块")
@RequestMapping("/api/generator")
@RestController()
@RefreshScope
public class GeneratorController {
    @Value("${gen.cache:1}")
    private Integer cache;
    @Value("${gen.local:false}")
    private boolean local;
    @Value("${gen.debugModel:false}")
    private boolean debugModel;
    /**
     * 生成项目
     */
    @ApiOperation("代码生成")
    @PostMapping()
    public Map<String, Object> generator(@RequestBody @ApiParam(name = "生成配置",value = "传入JSON格式") GenConfig genConfig) {
        try {
            String path = new GenUtil(cache).gen(genConfig);
            if (path == null) return error("生成失败，请检查配置");
            return ok("项目生成成功", path);
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
    }

    /**
     * 获取模板列表
     */
    @ApiOperation(value = "获取模板列表",notes = "返回系统中的所有模板")
    @GetMapping("/templates")
    public Map<String, Object> listTpl() {
        return ok("查询成功", new GenUtil(cache).listTpl());
    }
    /**
     * 获取数据库表信息
     */
    @GetMapping("/tables")
    @ApiOperation(value = "获取数据库表信息",notes = "根据配置的数据库信息获取表信息")
    public Map<String, Object> listTable(GenConfig genConfig) {
        List<TableInfo> tableList = GenUtil.getTableList(genConfig.getDataSourceConfig());
        return ok("查询成功",tableList);
    }

    /**
     * 上传模板
     */
    @PostMapping("/upload")
    @ApiOperation(value = "模板上传",notes = "只支持ZIP压缩文件")
    public Map<String, Object> upload(MultipartFile file) {
        if (new GenUtil(cache).upload(file)) {
            return ok("上传成功", file.getOriginalFilename());
        }
        return error("上传失败");
    }

    /**
     * 历史生成记录
     */
    @GetMapping("/history")
    @ApiOperation(value = "历史生成记录")
    public Map<String, Object> history() {
        return ok("查询成功", new GenUtil(cache).history());
    }

    /**
     * 下载生成后的压缩包
     */
    @GetMapping("/download")
    @ApiOperation(value = "项目下载",notes = "只支持ZIP压缩文件")
    public void download(String file, HttpServletResponse response) {
        File outFile = new GenUtil(cache).getOutputFile(file);
        if (!outFile.exists()) return;
        // 设置下载文件header
        response.setContentType("application/force-download");
        String fileName = outFile.getName();
        try {
            fileName = URLEncoder.encode(fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        // 输出文件流
        OutputStream os = null;
        BufferedInputStream is = null;
        try {
            os = response.getOutputStream();
            is = new BufferedInputStream(new FileInputStream(outFile));
            byte[] bytes = new byte[1024 * 256];
            int len;
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 返回结果信息
     */
    private Map<String, Object> ok(String msg, Object data) {
        return result(0, msg, data);
    }

    private Map<String, Object> error(String msg) {
        return result(1, msg, null);
    }

    private Map<String, Object> result(Integer code, String msg, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }

}
