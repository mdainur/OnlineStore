package javaEE.springboot.springbootdemo.services;

//import com.sun.corba.se.impl.encoding.CDROutputObject;
import javaEE.springboot.springbootdemo.entities.*;

import java.util.Calendar;
import java.util.List;

public interface ItemService {

    ShopItems addItem(ShopItems item);
    List<ShopItems> getAllItems();
    ShopItems getItem(Long id);
    void deleteItem(ShopItems item);
    ShopItems saveItem(ShopItems item);

    List<Brands> getAllBrands();
    Brands addBrand(Brands brand);
    Brands saveBrand(Brands brand);
    Brands getBrand(Long id);
    void deleteBrand(Brands brand);

    List<Countries> getAllCountries();
    Countries addCountry(Countries country);
    Countries saveCountry(Countries country);
    Countries getCountry(Long id);
    void deleteCountry(Countries country);

    List<Categories> getAllCategories();
    Categories getCategory(Long id);
    Categories saveCategory(Categories category);
    Categories addCategory(Categories category);
    void deleteCategory(Categories category);
    List<ShopItems> findAllByCategoriesId(Long category_id);

    List<ShopItems> getTop();


    List<ShopItems> findAllByBrandName(String brand_name);

    List<ShopItems> getItemByAscPrice(String name);
    List<ShopItems> getItemByDescPrice(String name);
    List<ShopItems> getItemByAscBetweenPrice(String name, double price_from, double price_to);
    List<ShopItems> getItemByDescBetweenPrice(String name, double price_from, double price_to);
    List<ShopItems> getItemsByName(String nae);
    List<ShopItems> getItemsByBetweenPrice(String name, double price_from, double price_to);

    List<ShopItems> getItemsByPriceAsc();
    List<ShopItems> getItemsByPriceDesc();

    List<ShopItems> findAllByBrandId(Long brand_id);
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

    void deleteByCategories(Long cat_id);

    List<CartItem> getAllCartItems();
    CartItem getCartItem(Long id);
    CartItem addCartItem(CartItem item);
    CartItem saveCartItem(CartItem item);
    void deleteCartItem(CartItem item);

    List<Pictures> getAllPictures();
    Pictures getPicture(Long id);
    Pictures addPicture(Pictures picture);
    Pictures savePicture(Pictures picture);
    void deletePicture(Pictures picture);
    List<Pictures> findByItem(Long id);


    List<Comment> getAllPComments();
    Comment getComment(Long id);
    Comment addComment(Comment comment);
    Comment saveComment(Comment comment);
    void deleteComment(Comment comment);
    List<Comment> findAllByItemId(Long id);
    List<Comment> findAllByAuthorId(Long id);
    List<Comment> findAllByItemIdAndAuthorId(Long item_id, Long author_id);
    List<Comment> findAllByItemIdOrderByAddedDateDesc(Long id);
}
