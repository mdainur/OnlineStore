package javaEE.springboot.springbootdemo.repositories;


//import com.sun.xml.internal.ws.api.ha.StickyFeature;
import javaEE.springboot.springbootdemo.entities.Brands;
import javaEE.springboot.springbootdemo.entities.Categories;
import javaEE.springboot.springbootdemo.entities.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ShopItemRepository extends JpaRepository<ShopItems, Long> {
    List<ShopItems> findAllByBrandName(String brand_name); // To search only with brand name

    List<ShopItems> findAllByNameLike(String name); // To search only with item name

    List<ShopItems> findAllByNameLikeOrderByPriceAsc(String name); // To find items with name in Ascending order
    List<ShopItems> findAllByNameLikeOrderByPriceDesc(String name);   // To find items with name in Descending order
    ShopItems findByIdAndPriceGreaterThan(Long id, double price); // To find items between price
    //  To find items with name between price
    List<ShopItems> findAllByNameLikeAndPriceBetween(String name, double price_from, double price_to);
    //    To find items with name between price in ascending order
    List<ShopItems> findAllByNameLikeAndPriceBetweenOrderByPriceAsc(String name, double price_from, double price_to);
    //    To find items with name between price in Descending order
    List<ShopItems> findAllByNameLikeAndPriceBetweenOrderByPriceDesc(String name, double price_from, double price_to);

    List<ShopItems> findAllByOrderByPriceAsc(); // To filter items in Ascending order
    List<ShopItems> findAllByOrderByPriceDesc(); // To filter items in Descending order

    List<ShopItems> findAllByBrandIdOrderByPriceAsc(Long brand_id);
    List<ShopItems> findAllByBrandIdOrderByPriceDesc(Long brand_id);

    List<ShopItems> findAllByBrandIdAndPriceBetween(Long brand, double price_from, double price_to);
    List<ShopItems> findAllByBrandIdAndPriceBetweenOrderByPriceAsc(Long brand, double price_from, double price_to);
    List<ShopItems> findAllByBrandIdAndPriceBetweenOrderByPriceDesc(Long brand, double price_from, double price_to);

    List<ShopItems> findAllByNameLikeAndBrandId(String name, Long brand_id);
    List<ShopItems> findAllByNameLikeAndBrandIdOrderByPriceAsc(String name, Long brand_id);
    List<ShopItems> findAllByNameLikeAndBrandIdOrderByPriceDesc(String name, Long brand_id);

    List<ShopItems> findAllByNameLikeAndBrandIdAndPriceBetween(String name, Long brand_id, double price_from, double price_to);
    List<ShopItems> findAllByNameLikeAndBrandIdAndPriceBetweenOrderByPriceAsc(String name, Long brand_id, double price_from, double price_to);
    List<ShopItems> findAllByNameLikeAndBrandIdAndPriceBetweenOrderByPriceDesc(String name, Long brand_id, double price_from, double price_to);


    List<ShopItems> findAllByInTopPageIsTrue();

    List<ShopItems> findAllByBrandId(Long brand_id);

    List<ShopItems> findAllByCategoriesId(Long category_id);
    void deleteByCategories(Long cat_id);
}
