package com.kristin;


import com.kristin.test.LogUtil;
import com.kristin.test.UserDTO;
import com.kristin.test.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kristin
 * @since 2018/7/27 23:09
 **/

public class Test {
    public static void main(String[] args) {
        UserVo userVo = new UserVo.UserVoBuilder().withName("kris").build();
        UserDTO userDTO = UserVo.convert(userVo);
        System.out.println(userDTO);
    }
}