package com.ins.collect.client;

import com.ins.model.collect.CollectionMoment;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


//@SpringBootTest
//@RunWith(SpringRunner.class)
public class MomentClientTest {
    //    @Autowired
//    MomentClient momentClient;
//
//    @Test
//    public void testMq() {
//        CommonResult commentsByUserId = momentClient.getMomentByIds("(1,2)");
//        System.out.println(commentsByUserId);
//    }
    @Test
    public void testMq() {
        List<CollectionMoment> collectionMoments = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            collectionMoments.add(new CollectionMoment().setCollectionId(Integer.toString(i)));
        }
        StringBuilder sb = new StringBuilder();

        collectionMoments.stream()
                .map(CollectionMoment::getCollectionId)
                .forEach(x-> sb.append(x).append(","));

        String ids= sb.substring(0,sb.length()-1);


        System.out.println("s = " + ids);
    }


}
