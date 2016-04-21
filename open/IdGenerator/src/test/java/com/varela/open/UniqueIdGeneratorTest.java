package com.varela.open;


import com.varela.open.id.UniqueElement;
import com.varela.open.id.UniqueidService;
import com.varela.open.id.impl.DpopUniqueidService;
import org.testng.annotations.Test;

/**
 * UniqueIdGenerator Test
 *
 * @author cgd
 * @date 2015-11-11 下午03:59:44
 */
public class UniqueIdGeneratorTest {

    @Test
    public void run() {
        UniqueidService service = new DpopUniqueidService();
        long id = service.generateId();
        System.out.println(id);
        System.out.println("---------------------");
        UniqueElement item = service.explainId(id);
        System.out.println(item.getTimestamp());
        System.out.println(item.getBizId());
        System.out.println(item.getMachineId());
        System.out.println(item.getStateId());
        System.out.println(item.getSequence());

        System.out.println("========================================");

        long id2 = service.generateId();
        System.out.println(id2);
        System.out.println("---------------------");
        UniqueElement item2 = service.explainId(id2);
        System.out.println(item2.getTimestamp());
        System.out.println(item2.getBizId());
        System.out.println(item2.getMachineId());
        System.out.println(item2.getStateId());
        System.out.println(item2.getSequence());
    }

}
