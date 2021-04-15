
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@SpringBootTest
public class ProductTest {

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Test
    public void test (){
//        if(esTemplate==null){
//            System.out.println("null");
//            return;
//        }
        System.out.println(">>>>>>>>>>>>> "+esTemplate);
    }
}
