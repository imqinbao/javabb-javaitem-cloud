package cn.javabb.generator.config.keywords;

import cn.javabb.generator.config.IKeyWordsHandler;

import java.util.List;
import java.util.Locale;

/**
 * @desc:   mysql5.7关键词处理
 *       这里选取了mysql5.7文档中的关键字和保留字（含移除）https://dev.mysql.com/doc/refman/5.7/en/keywords.html
 * @author: javabb (javabob(a)163.com)
 * @create: 2020/07/15 21:16
 */
public abstract class BaseKeyWordsHandler implements IKeyWordsHandler {

    public List<String> keyWords;

    public BaseKeyWordsHandler(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public List<String> getKeyWords() {
        return keyWords;
    }

    public boolean isKeyWords(String columnName) {
        return getKeyWords().contains(columnName.toUpperCase(Locale.ENGLISH));
    }

}
