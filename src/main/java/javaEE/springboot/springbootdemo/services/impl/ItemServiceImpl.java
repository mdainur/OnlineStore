package javaEE.springboot.springbootdemo.services.impl;


import javaEE.springboot.springbootdemo.entities.*;
import javaEE.springboot.springbootdemo.repositories.*;
import javaEE.springboot.springbootdemo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PicturesRepository picturesRepository;

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CountryRepository countryRepository;
    @Override
    public ShopItems addItem(ShopItems item) {
        return shopItemRepository.save(item);
    }


//    public ShopItems getItemsDescOrder(double price_for_desc){
//        return (ShopItems) shopItemRepository.findAllByPriceGreaterThanOrderByPriceDesc(price_for_desc);
//    }

//    public ShopItems getItemsAscOrder(double price_for_asc){
//        return (ShopItems) shopItemRepository.findAllByPriceGreaterThanOrderByPricePriceAsc(price_for_asc);
//    }
//
//    public ShopItems getItemsPriceBetween(double price_from, double price_to){
//        return  (ShopItems)shopItemRepository.findAllByPriceBetween(price_from, price_to);
//    }

    @Override
    public List<ShopItems> getAllItems() {
        return shopItemRepository.findAll();
    }

    @Override
    public ShopItems getItem(Long id) {
        return shopItemRepository.findByIdAndPriceGreaterThan(id, 0);
    }

    @Override
    public void deleteItem(ShopItems item) {
        shopItemRepository.delete(item);
    }

    @Override
    public ShopItems saveItem(ShopItems item) {

        return shopItemRepository.save(item);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public Categories saveCategory(Categories category) {
        return categoryRepository.save(category);
    }

    @Override
    public Categories addCategory(Categories category) {

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Categories category) {

        categoryRepository.delete(category);
    }

    @Override
    public List<ShopItems> findAllByCategoriesId(Long category_id) {
        return shopItemRepository.findAllByCategoriesId(category_id);
    }

    @Override
    public List<ShopItems> getTop() {

        return shopItemRepository.findAllByInTopPageIsTrue();
    }

    @Override
    public List<ShopItems> getItemByAscPrice(String name) {
        return shopItemRepository.findAllByNameLikeOrderByPriceAsc(name);
    }

    @Override
    public List<ShopItems> getItemByDescPrice(String name) {
        return shopItemRepository.findAllByNameLikeOrderByPriceDesc(name);
    }

    @Override
    public List<ShopItems> getItemByAscBetweenPrice(String name, double price_from, double price_to) {
        return shopItemRepository.findAllByNameLikeAndPriceBetweenOrderByPriceAsc(name, price_from, price_to);
    }

    @Override
    public List<ShopItems> getItemByDescBetweenPrice(String name, double price_from, double price_to) {
        return shopItemRepository.findAllByNameLikeAndPriceBetweenOrderByPriceDesc(name, price_from, price_to);
    }

    @Override
    public List<ShopItems> getItemsByName(String name) {
        return shopItemRepository.findAllByNameLike(name);
    }

    @Override
    public List<ShopItems> getItemsByBetweenPrice(String name, double price_from, double price_to) {
        return shopItemRepository.findAllByNameLikeAndPriceBetween(name, price_from, price_to);
    }

    @Override
    public List<ShopItems> getItemsByPriceAsc() {
        return shopItemRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<ShopItems> getItemsByPriceDesc() {
        return shopItemRepository.findAllByOrderByPriceDesc();
    }

    @Override
    public List<Brands> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brands addBrand(Brands brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brands saveBrand(Brands brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brands getBrand(Long id) {
        return brandRepository.getOne(id);
    }

    @Override
    public void deleteBrand(Brands brand) {
        brandRepository.delete(brand);
    }

    @Override
    public List<Countries> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Countries addCountry(Countries country) {
        return countryRepository.save(country);
    }

    @Override
    public Countries saveCountry(Countries country) {
        return countryRepository.save(country);
    }

    @Override
    public Countries getCountry(Long id) {
        return countryRepository.getOne(id);
    }

    @Override
    public void deleteCountry(Countries country) {
        countryRepository.delete(country);
    }

    @Override
    public List<ShopItems> findAllByBrandId(Long brand_id) {
        return shopItemRepository.findAllByBrandId(brand_id);
    }

    @Override
    public List<ShopItems> findAllByBrandIdOrderByPriceAsc(Long brand_id) {
        return shopItemRepository.findAllByBrandIdOrderByPriceAsc(brand_id);
    }

    @Override
    public List<ShopItems> findAllByBrandIdOrderByPriceDesc(Long brand_id) {
        return shopItemRepository.findAllByBrandIdOrderByPriceDesc(brand_id);
    }

    @Override
    public List<ShopItems> findAllByBrandIdAndPriceBetween(Long brand_id, double price_from, double price_to) {
        return shopItemRepository.findAllByBrandIdAndPriceBetween(brand_id, price_from, price_to);
    }

    @Override
    public List<ShopItems> findAllByBrandIdAndPriceBetweenOrderByPriceAsc(Long brand_id, double price_from, double price_to) {
        return shopItemRepository.findAllByBrandIdAndPriceBetweenOrderByPriceAsc(brand_id, price_from, price_to);
    }

    @Override
    public List<ShopItems> findAllByBrandIdAndPriceBetweenOrderByPriceDesc(Long brand_id, double price_from, double price_to) {
        return shopItemRepository.findAllByBrandIdAndPriceBetweenOrderByPriceDesc(brand_id, price_from, price_to);
    }

    @Override
    public List<ShopItems> findAllByNameLikeAndBrandId(String name, Long brand_id) {
        return shopItemRepository.findAllByNameLikeAndBrandId(name, brand_id);
    }

    @Override
    public List<ShopItems> findAllByNameLikeAndBrandIdOrderByPriceAsc(String name, Long brand_id) {
        return shopItemRepository.findAllByNameLikeAndBrandIdOrderByPriceAsc(name, brand_id);
    }

    @Override
    public List<ShopItems> findAllByNameLikeAndBrandIdOrderByPriceDesc(String name, Long brand_id) {
        return shopItemRepository.findAllByNameLikeAndBrandIdOrderByPriceDesc(name, brand_id);
    }

    @Override
    public List<ShopItems> findAllByNameLikeAndBrandIdAndPriceBetween(String name, Long brand_id, double price_from, double price_to) {
        return shopItemRepository.findAllByNameLikeAndBrandIdAndPriceBetween(name, brand_id, price_from, price_to);
    }

    @Override
    public List<ShopItems> findAllByNameLikeAndBrandIdAndPriceBetweenOrderByPriceAsc(String name, Long brand_id, double price_from, double price_to) {
        return shopItemRepository.findAllByNameLikeAndBrandIdAndPriceBetweenOrderByPriceAsc(name, brand_id, price_from, price_to);
    }

    @Override
    public List<ShopItems> findAllByNameLikeAndBrandIdAndPriceBetweenOrderByPriceDesc(String name, Long brand_id, double price_from, double price_to) {
        return shopItemRepository.findAllByNameLikeAndBrandIdAndPriceBetweenOrderByPriceDesc(name, brand_id, price_from, price_to);
    }

    @Override
    public void deleteByCategories(Long cat_id) {
        shopItemRepository.deleteByCategories(cat_id);
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItem(Long id) {
        return cartItemRepository.getOne(id);
    }

    @Override
    public CartItem addCartItem(CartItem item) {
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem saveCartItem(CartItem item) {
        return cartItemRepository.save(item);
    }

    @Override
    public void deleteCartItem(CartItem item) {
    cartItemRepository.delete(item);
    }

    @Override
    public List<Pictures> getAllPictures() {
        return picturesRepository.findAll();
    }

    @Override
    public Pictures getPicture(Long id) {
        return picturesRepository.getOne(id);
    }

    @Override
    public Pictures addPicture(Pictures picture) {
        return picturesRepository.save(picture);
    }

    @Override
    public Pictures savePicture(Pictures picture) {
        return picturesRepository.save(picture);
    }

    @Override
    public void deletePicture(Pictures picture) {
        picturesRepository.delete(picture);
    }

    @Override
    public List<Pictures> findByItem(Long id) {
        return picturesRepository.findAllByItemId(id);
    }

    @Override
    public List<Comment> getAllPComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.getOne(id);
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public List<Comment> findAllByItemId(Long id) {
        return commentRepository.findAllByItemId(id);
    }

    @Override
    public List<Comment> findAllByAuthorId(Long id) {
        return commentRepository.findAllByAuthorId(id);
    }

    @Override
    public List<Comment> findAllByItemIdAndAuthorId(Long item_id, Long author_id) {
        return commentRepository.findAllByItemIdAndAuthorId(item_id, author_id);
    }

    @Override
    public List<Comment> findAllByItemIdOrderByAddedDateDesc(Long id) {
        return commentRepository.findAllByItemIdOrderByAddedDateDesc(id);
    }

    @Override
    public List<ShopItems> findAllByBrandName(String brand_name) {
        return shopItemRepository.findAllByBrandName(brand_name);
    }
}
