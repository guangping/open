package com.varela;

import com.varela.pojo.AMapAddress;
import com.varela.utils.AMapUtils;
import org.testng.annotations.Test;

/**
 * Created by 51offer on 2016/3/4.
 */
public class AMapTest {

    @Test
    public void run(){
        AMapAddress aMapAddress= AMapUtils.getAddress("湖南省岳阳楼区","岳阳楼区");
        System.out.println(aMapAddress);
    }
}
