package cn.javabb.common.web.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc:
 * @author: javabb (javabob(a)163.com)
 * @create: 2021/01/20 19:42
 */
public class BaseController {


    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            ints.add(i);
        }
        Long start = System.currentTimeMillis();
        for (int i = 0; i < ints.size(); i++) {
            if (i == 999) {
                System.out.println(System.currentTimeMillis()-start);
            }
        }
    }
}
