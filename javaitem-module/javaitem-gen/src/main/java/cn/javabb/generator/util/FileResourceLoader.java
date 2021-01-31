package cn.javabb.generator.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/29 16:09
 */
@Slf4j
public class FileResourceLoader extends ResourceLoader {
    Map<String, Long> fileLastModified = new HashMap<>();
    @Override
    public void init(ExtendedProperties extendedProperties) {

    }

    @Override
    public InputStream getResourceStream(String s) throws ResourceNotFoundException {
        File file = null;
        try {
            file = getResourceFile(s);
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.info("FileNotFoundException:" + s + "  " + file.getPath());
            return this.getClass().getResourceAsStream(s);
        } finally {
            if (file != null)
                fileLastModified.put(s, file.lastModified());
        }
    }

    @Override
    public boolean isSourceModified(Resource resource) {
        long lastModified = resource.getLastModified();
        File file = getResourceFile(resource.getName());
        return lastModified != file.lastModified();
    }

    @Override
    public long getLastModified(Resource resource) {
        return fileLastModified.get(resource.getName());
    }


    private File getResourceFile(String name) {
        return new File(String.format("%s/%s", name));
    }
}
