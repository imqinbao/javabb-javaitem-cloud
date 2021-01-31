package cn.javabb.generator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/12 15:38
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Template implements Cloneable{

    private String filePath;
    private String fileContent;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
