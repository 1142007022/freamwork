package Product;

import com.kaishengit.enitys.Product;
import com.kaishengit.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class ProductTest {

    @Autowired
    private ProductService productService;

    @Test
    public void findById(){
        Product product = productService.findById(2177);
        System.out.println(product);
    }

    @Test
    public void findAll(){
        List<Product> lists = productService.findAll();
        for(Product product:lists) {
            System.out.println(product);
        }
    }

    @Test
    public void deleteById(){
        productService.deleteById(2177);
    }

    @Test

    public void update(){
        Product product = productService.findById(2178);
        product.setProductName("机械键盘");
        productService.update(product);
    }


}
