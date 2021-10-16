package javaEE.springboot.springbootdemo.controllers;
//
//import javaEE.springboot.springbootdemo.beans.FirstBean;
//import javaEE.springboot.springbootdemo.beans.TestBean;
//import javaEE.springboot.springbootdemo.beans.ThirdBean;
//import javaEE.springboot.springbootdemo.db.DBManager;
//import javaEE.springboot.springbootdemo.db.ShopItem;
//import javaEE.springboot.springbootdemo.db.dDBManager1;
//import javaEE.springboot.springbootdemo.db.Tasks;
import antlr.StringUtils;
import javaEE.springboot.springbootdemo.entities.*;
import javaEE.springboot.springbootdemo.services.ItemService;
//import javaEE.springboot.springbootdemo.services.TestService;
import javaEE.springboot.springbootdemo.services.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import javax.jws.WebParam;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.sql.Date;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import static java.nio.charset.StandardCharsets.UTF_8;

@Controller
public class HomeController {
    public static int count = 0;

    private static Random rand = new Random((new Date()).getTime());
    //
//    @Autowired
//    private TestBean testBean;
//
//    @Autowired
//    private FirstBean firstBean;
//
//    @Autowired
//    private FirstBean secondBean;
//
//    @Autowired
//    private ThirdBean thirdBean;
//
//    @Autowired
//    private TestService testService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Value("${file.avatar.viewPath}")
    private String viewPath;

    @Value("${file.avatar.uploadPath}")
    private String uploadPath;

    @Value("${file.avatar.defaultPicture}")
    private String defaultPicture;


    @Value("static/items/")
    private String viewPathItem;

    @Value("target/classes/static/items/")
    private String uploadPathItem;

    @GetMapping(value = "/")
    @PreAuthorize("isAnonymous() || isAuthenticated()")
    public String getIndex(Model model, HttpSession session) {
        return "index";
    }

    @GetMapping(value = "/labs")
    @PreAuthorize("isAnonymous() || isAuthenticated()")
    public String getTopo(Model model, HttpSession session) {
//        model.addAttribute("currentUser", getUserData());
//
//        List<Categories> categories = itemService.getAllCategories();
//        model.addAttribute("categories", categories);
//
//        List<ShopItems> itemsTop = itemService.getTop();
//        model.addAttribute("items", itemsTop);
//        List<Brands> brands = itemService.getAllBrands();
//        model.addAttribute("brands", brands);
//
//        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
//        double total = 0;
//        if(cartItems != null) {
//            for (CartItem i : cartItems) {
//                total += i.getItem().getPrice() * i.getAmount();
//            }
//        }
//        model.addAttribute("total", total);
//        model.addAttribute("cart", cartItems);
//        model.addAttribute("amount", getAmount(session));

        model.addAttribute("p_result", 1);
        model.addAttribute("q_result", 1);
        model.addAttribute("clicked", false);

        return "labs";
    }


//    //////////////////////////////////////////////////////////
    @GetMapping(value = "/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String adminLogin(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        List<ShopItems> itemsTop = itemService.getAllItems();
        model.addAttribute("items", itemsTop);

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));
        return "admin";
    }

    @GetMapping(value = "/countries")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String country(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());
        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        List<ShopItems> itemsTop = itemService.getAllItems();
        model.addAttribute("items", itemsTop);

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));

        return "countries";
    }

    @GetMapping(value = "/categories")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String category(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());
        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        List<ShopItems> itemsTop = itemService.getAllItems();
        model.addAttribute("items", itemsTop);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));
        return "categories";
    }


    @GetMapping(value = "/brands")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String brand(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());
        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        List<ShopItems> itemsTop = itemService.getAllItems();
        model.addAttribute("items", itemsTop);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));
        return "brands";
    }

    @GetMapping(value = "/list_items")
    public String listOfItems(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<ShopItems> items = itemService.getAllItems();
        model.addAttribute("all_items", items);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));
        return "list_items";
    }


    @PostMapping(value = "/addCountry")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCountry(@RequestParam(name = "name", defaultValue = "No Name") String name,
                             @RequestParam(name = "code", defaultValue = "No code") String code) {

        Countries country = new Countries(null, name, code);
        itemService.addCountry(country);

        return "redirect:/countries";
    }

    @PostMapping(value = "/addBrand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCountry(@RequestParam(name = "name", defaultValue = "No Name") String name,
                             @RequestParam(name = "country_id", defaultValue = "0") Long country_id) {

        Countries country = itemService.getCountry(country_id);
        if (country != null) {
            Brands brand = new Brands(null, name, country);
            itemService.addBrand(brand);
        }

        return "redirect:/brands";
    }

    @PostMapping(value = "/addCategory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCategory(@RequestParam(name = "name", defaultValue = "No Name") String name,
                              @RequestParam(name = "logo_URL", defaultValue = "") String logo_URL) {

        itemService.addCategory(new Categories(null, name, logo_URL));
        return "redirect:/categories";
    }


//    @GetMapping(value = "/details/{item_id}")
//    public String detail(Model model,
//                         @PathVariable(name = "item_id") Long id){
//        ShopItems item = itemService.getItem(id);
//        model.addAttribute("item", item);
//        return "details";
//    }

    @GetMapping(value = "/details/{id}")
    @PreAuthorize("isAuthenticated() || isAnonymous()")
    public String details(Model model, @PathVariable(name = "id") Long id, HttpSession session) {
        model.addAttribute("currentUser", getUserData());
        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        ShopItems item = itemService.getItem(id);
        model.addAttribute("item", item);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        List<Pictures> pictures = itemService.findByItem(id);
        model.addAttribute("pictures", pictures);
        model.addAttribute("amount", getAmount(session));
        List<Comment> comments = itemService.findAllByItemIdOrderByAddedDateDesc(id);
        model.addAttribute("comments", comments);
        return "details";
    }


    @GetMapping(value = "/itemDetails/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String itemDetails(Model model, @PathVariable(name = "id") Long id, HttpSession session) {
        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        ShopItems item = itemService.getItem(id);
        model.addAttribute("item", item);

        List<Categories> itemCategories = item.getCategories();
        model.addAttribute("itemCategories", itemCategories);
//
        List<Categories> unUsedCat = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            if (!itemCategories.contains(categories.get(i))) {
                unUsedCat.add(categories.get(i));
            }
        }
        for (int i = 0; i < unUsedCat.size(); i++) {
            System.out.println(unUsedCat.size() + " " + unUsedCat.get(i).getName());
        }

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);

        model.addAttribute("unUsed", unUsedCat);


        List<ShopItems> items = itemService.getAllItems();
        model.addAttribute("items", items);
        model.addAttribute("currentUser", getUserData());

        List<Pictures> pictures = itemService.findByItem(id);
        model.addAttribute("pictures", pictures);

        model.addAttribute("amount", getAmount(session));
        return "itemDetails";
    }

    @PostMapping(value = "/addItem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String addItem(@RequestParam(name = "name", defaultValue = "No Name") String name,
                          @RequestParam(name = "description", defaultValue = "No Description") String description,
                          @RequestParam(name = "price", defaultValue = "0") double price,
                          @RequestParam(name = "brand_id", defaultValue = "0") Long brand_id,
                          @RequestParam(name = "stars", defaultValue = "0") int stars,
                          @RequestParam(name = "small_picture_url", defaultValue = "https://theenglishpost.com/wp-content/uploads/2018/09/Electronics-Item.jpg") String small_picture_url,
                          @RequestParam(name = "large_picture_url", defaultValue = "https://theenglishpost.com/wp-content/uploads/2018/09/Electronics-Item.jpg") String large_picture_url,
                          @RequestParam(name = "added_date", defaultValue = "2020-11-03") Date added_date,
                          @RequestParam(name = "in_top_page", defaultValue = "true") boolean in_top_page) {
        List<Categories> categories = new ArrayList<>();
        Brands brand = itemService.getBrand(brand_id);

        if (brand != null) {
            ShopItems item = new ShopItems(null, name, description, price, stars, small_picture_url, large_picture_url, (java.sql.Date) added_date, in_top_page, brand, categories);
            itemService.addItem(item);
        }
        return "redirect:/admin";
    }


    @PostMapping(value = "/saveItem")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String editItem(Model model, @RequestParam(name = "id", defaultValue = "0") Long id,
                           @RequestParam(name = "name", defaultValue = "No Name") String name,
                           @RequestParam(name = "description", defaultValue = "No Description") String description,
                           @RequestParam(name = "brand_id", defaultValue = "0") Long brand_id,
                           @RequestParam(name = "price", defaultValue = "0") double price,
                           @RequestParam(name = "stars", defaultValue = "0") int stars,
                           @RequestParam(name = "small_picture_url", defaultValue = "https://theenglishpost.com/wp-content/uploads/2018/09/Electronics-Item.jpg") String small_picture_url,
                           @RequestParam(name = "large_picture_url", defaultValue = "https://theenglishpost.com/wp-content/uploads/2018/09/Electronics-Item.jpg") String large_picture_url,
                           @RequestParam(name = "added_date", defaultValue = "2020-11-03") Date added_date,
                           @RequestParam(name = "in_top_page", defaultValue = "true") boolean in_top_page) {
        model.addAttribute("currentUser", getUserData());
        ShopItems item = itemService.getItem(id);
        model.addAttribute("item", item);
        List<Categories> categories = item.getCategories();
        Brands brand = itemService.getBrand(brand_id);
        itemService.saveItem(new ShopItems(id, name, description, price, stars, small_picture_url, large_picture_url, (java.sql.Date) added_date, in_top_page, brand, categories));
        return "redirect:/itemDetails/" + id;
    }


    @PostMapping(value = "/saveCountry")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCountry(Model model, @RequestParam(name = "id", defaultValue = "0") Long id,
                              @RequestParam(name = "name", defaultValue = "No Name") String name,
                              @RequestParam(name = "code", defaultValue = "No code") String code) {
        Countries country = itemService.getCountry(id);
        model.addAttribute("country", country);
        itemService.saveCountry(new Countries(id, name, code));
        model.addAttribute("currentUser", getUserData());
        return "redirect:/countries";
    }

    @PostMapping(value = "/saveCategory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCategory(Model model, @RequestParam(name = "id", defaultValue = "0") Long id,
                               @RequestParam(name = "name", defaultValue = "No Name") String name,
                               @RequestParam(name = "logo_URL", defaultValue = "") String logo_URL) {
        Categories category = itemService.getCategory(id);
        model.addAttribute("category", category);
        itemService.saveCategory(new Categories(id, name, logo_URL));
        model.addAttribute("currentUser", getUserData());
        return "redirect:/categories";
    }

    @PostMapping(value = "/saveBrand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editItem(Model model, @RequestParam(name = "id", defaultValue = "0") Long id,
                           @RequestParam(name = "name", defaultValue = "No Name") String name,
                           @RequestParam(name = "country_id", defaultValue = "0") Long country_id) {
        Countries country = itemService.getCountry(country_id);
        model.addAttribute("country", country);
        Brands brand = itemService.getBrand(id);
        model.addAttribute("brand", brand);
        if (country != null) {
            itemService.saveBrand(new Brands(id, name, country));
        }
        model.addAttribute("currentUser", getUserData());
        return "redirect:/brands";
    }

    @GetMapping(value = "/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String delete(@RequestParam(name = "id", defaultValue = "0") Long id) {
        ShopItems item = itemService.getItem(id);
        itemService.deleteItem(item);
        return "redirect:/admin";
    }

    @GetMapping(value = "/deleteCountry")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCountry(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Countries country = itemService.getCountry(id);
        itemService.deleteCountry(country);
        return "redirect:/countries";
    }

    @GetMapping(value = "/deleteCategory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCategory(@RequestParam(name = "id", defaultValue = "0") Long id) {

        Categories category = itemService.getCategory(id);
        itemService.deleteCategory(category);
        return "redirect:/categories";
    }

    @GetMapping(value = "/deleteBrand")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteBrand(@RequestParam(name = "id", defaultValue = "0") Long id, Model model) {
        model.addAttribute("currentUser", getUserData());
        Brands brand = itemService.getBrand(id);
        itemService.deleteBrand(brand);
        return "redirect:/brands";
    }

    @GetMapping(value = "/search")
    public String searchItem(Model model, HttpSession session,
                             @RequestParam(name = "name", defaultValue = "No Name") String name,
                             @RequestParam(name = "brand_id", defaultValue = "0") Long brand_id,
                             @RequestParam(name = "search_type", defaultValue = "search") String search_type,
                             @RequestParam(name = "price_from", defaultValue = "0") double price_from,
                             @RequestParam(name = "price_to", defaultValue = "0") double price_to) {

        model.addAttribute("currentUser", getUserData());
        List<ShopItems> items = itemService.getItemsByName(name);
        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);
        if (brand_id != 0) {
            items = itemService.findAllByBrandId(brand_id);
        }
        if (price_from == 0 && price_to == 0) {
            if (name.equals("No Name") && brand_id == 0 && search_type.equals("ascending")) {
                items = itemService.getItemsByPriceAsc();
            }
            if (name.equals("No Name") && brand_id == 0 && search_type.equals("descending")) {
                items = itemService.getItemsByPriceDesc();
            }
            if (name.equals("No Name") && brand_id != 0 && search_type.equals("descending")) {
                items = itemService.findAllByBrandIdOrderByPriceDesc(brand_id);
            }
            if (name.equals("No Name") && brand_id != 0 && search_type.equals("ascending")) {
                items = itemService.findAllByBrandIdOrderByPriceAsc(brand_id);
            }
            if (name.equals("No Name") && brand_id != 0 && search_type.equals("between")) {
                items = itemService.findAllByBrandId(brand_id);
            }
            if (!name.equals("No Name") && brand_id == 0 && search_type.equals("ascending")) {
                items = itemService.getItemByAscPrice(name);
            }
            if (!name.equals("No Name") && brand_id == 0 && search_type.equals("descending")) {
                items = itemService.getItemByDescPrice(name);
            }
            if (!name.equals("No Name") && brand_id != 0 && search_type.equals("between")) {
                items = itemService.findAllByNameLikeAndBrandId(name, brand_id);
            }
            if (!name.equals("No Name") && brand_id != 0 && search_type.equals("descending")) {
                items = itemService.findAllByNameLikeAndBrandIdOrderByPriceDesc(name, brand_id);
            }
            if (!name.equals("No Name") && brand_id != 0 && search_type.equals("ascending")) {
                items = itemService.findAllByNameLikeAndBrandIdOrderByPriceAsc(name, brand_id);
            }
        } else if (brand_id == 0 && !name.equals("No Name") && price_from != 0 && price_to != 0) {
            if (search_type.equals("between")) {
                items = itemService.getItemsByBetweenPrice(name, price_from, price_to);
            }
            if (search_type.equals("ascending")) {
                items = itemService.getItemByAscBetweenPrice(name, price_from, price_to);
            }
            if (search_type.equals("descending")) {
                items = itemService.getItemByDescBetweenPrice(name, price_from, price_to);
            }
        } else if (name.equals("No Name") && brand_id != 0 && price_from != 0 && price_to != 0) {
            if (search_type.equals("between")) {
                items = itemService.findAllByBrandIdAndPriceBetween(brand_id, price_from, price_to);
            }
            if (search_type.equals("ascending")) {
                items = itemService.findAllByBrandIdAndPriceBetweenOrderByPriceAsc(brand_id, price_from, price_to);
            }
            if (search_type.equals("descending")) {
                items = itemService.findAllByBrandIdAndPriceBetweenOrderByPriceDesc(brand_id, price_from, price_to);
            }
        } else if (!name.equals("No Name") && brand_id != 0 && price_from != 0 && price_to != 0) {
            if (search_type.equals("between")) {
                items = itemService.findAllByNameLikeAndBrandIdAndPriceBetween(name, brand_id, price_from, price_to);
            }
            if (search_type.equals("ascending")) {
                items = itemService.findAllByNameLikeAndBrandIdAndPriceBetweenOrderByPriceAsc(name, brand_id, price_from, price_to);
            }
            if (search_type.equals("descending")) {
                items = itemService.findAllByNameLikeAndBrandIdAndPriceBetweenOrderByPriceDesc(name, brand_id, price_from, price_to);
            }
        }

        model.addAttribute("search_items", items);
        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));
        return "search";
    }
//
    @PostMapping(value = "/assignCategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String assignCategory(@RequestParam(name = "item_id") Long item_id,
                                 @RequestParam(name = "category_id") Long category_id) {
        Categories category = itemService.getCategory(category_id);
        if (category != null) {
            ShopItems item = itemService.getItem(item_id);
            if (item != null) {
                List<Categories> categories = item.getCategories();
                if (categories == null) {
                    categories = new ArrayList<>();
                }
                categories.add(category);
                itemService.saveItem(item);

                return "redirect:/itemDetails/" + item_id;
            }
        }
        return "admin";
    }

    @PostMapping(value = "/cleanCategory")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String cleanCategory(@RequestParam(name = "item_id") Long item_id,
                                @RequestParam(name = "category_id") Long category_id) {
        Categories category = itemService.getCategory(category_id);
        if (category != null) {
            ShopItems item = itemService.getItem(item_id);
            if (item != null) {
                List<Categories> categories = item.getCategories();
                categories.remove(category);
                itemService.saveItem(item);
                return "redirect:/itemDetails/" + item_id;
            }
        }
        return "admin";
    }

    @GetMapping(value = "/403")
    public String accessdDenied(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "403";
    }

    @GetMapping(value = "/login")
    public String login(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<ShopItems> itemsTop = itemService.getTop();
        model.addAttribute("items", itemsTop);
        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("amount", getAmount(session));
        model.addAttribute("cart", cartItems);
        return "login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<ShopItems> itemsTop = itemService.getTop();
        model.addAttribute("items", itemsTop);
        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));
        return "profile";
    }

    private Users getUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User secUser = (User) authentication.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }

    @GetMapping(value = "/register")
    public String register(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<ShopItems> itemsTop = itemService.getTop();
        model.addAttribute("items", itemsTop);
        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));
        return "register";
    }
///////////////////// **********Message
    @PostMapping(value = "/register")
    public String registerPost(Model model,
                               @RequestParam(name = "userFullname", defaultValue = "No user") String userFullname,
                               @RequestParam(name = "userEmail", defaultValue = "No user") String email,
                               @RequestParam(name = "userPassword", defaultValue = "No user") String password,
                               @RequestParam(name = "userRePassword", defaultValue = "No user") String rePassword) {
        String message1 = "Please, try again!";
        String message2 = "Successful registration!";
        if (rePassword.equals(password)) {
            Users newUser = new Users();
            newUser.setFullname(userFullname);
            newUser.setPassword(password);
            newUser.setEmail(email);

            if (userService.userCreate(newUser) != null) {
                model.addAttribute("success", message2);
                return "register";
            }
        }
        model.addAttribute("error", message1);

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<ShopItems> itemsTop = itemService.getTop();
        model.addAttribute("items", itemsTop);
        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        return "register";
    }
///////////******Message
    @PostMapping(value = "/updateProfile")
    public String updateProfile(Model model, @RequestParam(name = "userFullname", defaultValue = "User") String userFullname) {
        model.addAttribute("currentUser", getUserData());
        Users myUser = getUserData();
        myUser.setFullname(userFullname);
        userService.saveUser(myUser);
        String message;
        message = "successProfileUpdate";
        model.addAttribute("successProfileUpdate", message);
        return "redirect:/profile";
    }



    ///////////////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/userDetails")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String userDetails(Model model, @RequestParam(name = "id") Long id, HttpSession session) {
        model.addAttribute("currentUser", getUserData());

        List<Roles> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        Users user = userService.getUser(id);
        model.addAttribute("user", user);

        List<Roles> userRoles = user.getRoles();
        model.addAttribute("userRoles", userRoles);
//
        List<Roles> unUsedRole = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            if (!userRoles.contains(roles.get(i))) {
                unUsedRole.add(roles.get(i));
            }
        }
        for (int i = 0; i < unUsedRole.size(); i++) {
            System.out.println(unUsedRole.size() + " " + unUsedRole.get(i).getName());
        }
        model.addAttribute("unUsed", unUsedRole);


        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));
        return "userDetails";
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String adminUsers(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        List<ShopItems> itemsTop = itemService.getAllItems();
        model.addAttribute("items", itemsTop);

        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);

        List<Roles> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("cart", cartItems);
        model.addAttribute("amount", getAmount(session));
        return "users";
    }

    @PostMapping(value = "/addUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(Model model,
                          @RequestParam(name = "userFullname", defaultValue = "User") String userFullname,
                          @RequestParam(name = "userEmail", defaultValue = "examle@gmail") String email,
                          @RequestParam(name = "userPassword", defaultValue = "pass") String password) {

//        String picName = DigestUtils.sha1Hex("avatar_"+currentUser.getId()+"_!Picture");
//
//        byte[] bytes = file.getBytes();
//        Path path = Paths.get(uploadPath + picName + ".jpg");
//        Files.write(path, bytes);

//        userService.userCreate(new Users(null, email, userFullname, password,null, null));
        Users user = new Users();
        user.setEmail(email);
        user.setFullname(userFullname);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping(value = "/editUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editUser(Model model,
                           @RequestParam(name = "id", defaultValue = "0") Long id,
                           @RequestParam(name = "userFullname", defaultValue = "User") String userFullname,
                           @RequestParam(name = "userEmail", defaultValue = "examle@gmail") String email,
                           @RequestParam(name = "userPassword", defaultValue = "pass") String password) {

        model.addAttribute("currentUser", getUserData());

        Users user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setFullname(userFullname);
        userService.saveUser(user);
        return "redirect:/users";
    }

    /////////////*************************MESSAGEEEEEEEEE**************//////////////
    @PostMapping(value = "/updatePassword")
    public String updatePassword(Model model, @RequestParam(name = "oldPassword", defaultValue = "pass") String oldPassword,
                                 @RequestParam(name = "newPassword", defaultValue = "pass") String newPassword,
                                 @RequestParam(name = "rePassword", defaultValue = "pass") String rePassword,
                                 @RequestParam(name = "email", defaultValue = "example@.com") String email
    ) {
        String message;
        model.addAttribute("currentUser", getUserData());
        Users user = userService.getUserByEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if ( bCryptPasswordEncoder.matches(oldPassword,user.getPassword())) {
            if (newPassword.equals(rePassword)) {
                user.setPassword(bCryptPasswordEncoder.encode(newPassword));
                userService.saveUser(user);
                message = "successPasswordChange";
                model.addAttribute("successPasswordChange", message);
            }
            else {
                return "/403";
            }
        }
        else {
            return "/403";
        }
        return "redirect:/profile?id=" + user.getId();
    }

    @PostMapping(value = "/assignRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String assignRole(@RequestParam(name = "user_id", defaultValue = "0") Long user_id,
                                 @RequestParam(name = "role_id", defaultValue = "0") Long role_id) {
        Roles role = userService.getRole(role_id);
        if (role != null) {
            Users user = userService.getUser(user_id);
            if (user != null) {
                List<Roles> roles = user.getRoles();
                if (roles == null) {
                    roles = new ArrayList<>();
                }
                roles.add(role);
                userService.saveUser(user);
            }
        }

        return "redirect:/userDetails?id=" + user_id;
    }

    @PostMapping(value = "/cleanRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String cleanRole(@RequestParam(name = "user_id", defaultValue = "0") Long user_id,
                            @RequestParam(name = "role_id", defaultValue = "0") Long role_id) {
        Roles role = userService.getRole(role_id);
        if (role != null) {
            Users user = userService.getUser(user_id);
            if (user != null) {
                List<Roles> roles = user.getRoles();
                roles.remove(role);
                userService.saveUser(user);
            }
        }

        return "redirect:/userDetails?id=" + user_id;
    }

    @PostMapping(value = "/uploadAvatarAdmin")
    public String uploadAvatar(Model model,@RequestParam(name = "userAva", defaultValue = "static")MultipartFile file,
                               @RequestParam(name = "user_id")Long user_id) {


        Users currentUser = userService.getUser(user_id);
        model.addAttribute("user", currentUser);
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") ) {

            try {

//                Users currentUser = userService.getUser(user_id);
                String picName = DigestUtils.sha1Hex("avatar_"+currentUser.getId()+"_!Picture");

                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + picName + ".jpg");
                Files.write(path, bytes);
                currentUser.setPictureURL(picName);
                userService.saveUser(currentUser);

                return "redirect:/profile?success";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/userDetails?id=" + user_id;
    }

    @GetMapping(value = "/roles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String role(Model model, HttpSession session) {
        model.addAttribute("currentUser", getUserData());
        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<Countries> countries = itemService.getAllCountries();
        model.addAttribute("countries", countries);

        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);

        List<Users> users = userService.getAllUsers();
        model.addAttribute("users", users);

        List<Roles> roles = userService.getAllRoles();
        model.addAttribute("roles", roles);

        List<ShopItems> itemsTop = itemService.getAllItems();
        model.addAttribute("items", itemsTop);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("amount", getAmount(session));
        model.addAttribute("cart", cartItems);
        return "roles";
    }

    @PostMapping(value = "/addRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addRole(@RequestParam(name = "name", defaultValue = "No Name") String name,
                              @RequestParam(name = "roleDescription", defaultValue = "") String description) {
        Roles role = new Roles();
        role.setRole(name);
        role.setName(name);
        role.setDescription(description);
        userService.addRole(role);
        return "redirect:/roles";
    }

    @PostMapping(value = "/editRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editRole(Model model, @RequestParam(name = "id", defaultValue = "0") Long id,
                               @RequestParam(name = "name", defaultValue = "No Name") String name,
                               @RequestParam(name = "description", defaultValue = "") String description) {
        Roles role = userService.getRole(id);
        model.addAttribute("role", role);
        role.setName(name);
        role.setRole(name);
        role.setDescription(description);
        userService.saveRole(role);
        model.addAttribute("currentUser", getUserData());
        return "redirect:/roles";
    }

    @GetMapping(value = "/deleteUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Users user = userService.getUser(id);
        userService.deleteUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/deleteRole")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteRole(@RequestParam(name = "id", defaultValue = "0") Long id) {
        Roles role = userService.getRole(id);
        userService.deleteRole(role);
        return "redirect:/roles";
    }

    ///////////////////////////////////////////////////////////////

    @PostMapping(value = "/uploadAvatar")
    public String uploadAvatar(@RequestParam(name = "userAva", defaultValue = "static")MultipartFile file) {

        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") ) {

            try {

                Users currentUser = getUserData();
                String picName = DigestUtils.sha1Hex("avatar_"+currentUser.getId()+"_!Picture");

                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPath + picName + ".jpg");
                Files.write(path, bytes);
                currentUser.setPictureURL(picName);
                userService.saveUser(currentUser);

                return "redirect:/profile?success";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/";
    }

    @GetMapping(value = "/viewAvatar/{url}", produces = (MediaType.IMAGE_JPEG_VALUE))
    @PreAuthorize("isAuthenticated() || isAnonymous()")
    public @ResponseBody byte[] viewProfilePhoto(@PathVariable(name = "url")String url) throws IOException{
        String pictureUrRL = viewPath+defaultPicture;
        if(url!=null && !url.equals("null")){
            pictureUrRL = viewPath+url+".jpg";
        }
        InputStream in;
        try{
            ClassPathResource resource = new ClassPathResource(pictureUrRL);
            in = resource.getInputStream();
        }catch (Exception e){
            ClassPathResource resource = new ClassPathResource(viewPath+defaultPicture);
            in = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(in);
    }

    @PostMapping(value = "/uploadItemPicture")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String uploadPicture(@RequestParam(name = "item_picture")MultipartFile file,
                                @RequestParam(name = "item_id")Long item_id){
        if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png") ) {

            try {
                ShopItems item = itemService.getItem(item_id);
                String picName = DigestUtils.sha1Hex("picture_"+ System.currentTimeMillis()+"_!Picture");

                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadPathItem + picName + ".jpg");
                Files.write(path, bytes);
                Pictures picture = new Pictures();
                picture.setUrl(picName);
                picture.setItem(item);
                Date addedDate = new Date(System.currentTimeMillis());
                picture.setAddedDate((java.sql.Date) addedDate);
                itemService.addPicture(picture);

                return "redirect:/itemDetails/" + item_id;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/viewItemPicture/{url}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @PreAuthorize("isAuthenticated() || isAnonymous()")
    public @ResponseBody byte[] viewItemPhoto(@PathVariable(name = "url")String url) throws IOException{
        String pictureUrRL = viewPathItem+defaultPicture;
        if(url!=null && !url.equals("null")){
            pictureUrRL = viewPathItem+url+".jpg";
        }
        InputStream in;
        try{
            ClassPathResource resource = new ClassPathResource(pictureUrRL);
            in = resource.getInputStream();
        }catch (Exception e){
            ClassPathResource resource = new ClassPathResource(viewPathItem+defaultPicture);
            in = resource.getInputStream();
            e.printStackTrace();
        }
        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "/deleteItemPicture")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String deleteItemPicture(@RequestParam(name = "picture_id")Long piture_id){
        Pictures picture = itemService.getPicture(piture_id);
        Long item_id = picture.getItem().getId();
        if(picture != null){
            itemService.deletePicture(picture);
            return "redirect:/itemDetails/" + item_id;
        }
        return "redirect:/";
    }

///////////////////////////////////////////////////////////////////////////////////


    @GetMapping(value = "/addToCart/{id}")
    @PreAuthorize("isAnonymous() || isAuthenticated()")
    public String addToCart(@PathVariable(name = "id") Long id,
                            Model model, HttpSession session){
        ShopItems item = itemService.getItem(id);
        if(session.getAttribute("cart") == null){
            List<CartItem> cartItems = new ArrayList<>();

            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setAmount(1);
            cartItems.add(cartItem);

            session.setAttribute("cart", cartItems);
        }else{
            List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
            int index = isExists(id, cartItems);
            if(index == -1){
                CartItem cartItem = new CartItem();
                cartItem.setItem(item);
                cartItem.setAmount(1);
                cartItems.add(cartItem);
            }else {
                int amount = cartItems.get(index).getAmount() +1;
                cartItems.get(index).setAmount(amount);
            }
            session.setAttribute("cart", cartItems);

        }

        return "redirect:/cart";
    }

    private int isExists(Long id, List<CartItem> cartItems){
        for(int i = 0; i < cartItems.size(); i++){
            if(cartItems.get(i).getItem().getId() == id){
                return i;
            }
        }
        return -1;
    }

    @GetMapping(value = "/deleteFromCart")
    @PreAuthorize("isAnonymous() || isAuthenticated()")
    public String deleteBasket(HttpSession session, Model model,
                               @RequestParam(name = "id")Long id){
        ShopItems item = itemService.getItem(id);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        int index = isExists(id, cartItems);
        if(index != -1){
            CartItem cartItem = cartItems.get(index);
            if(cartItem.getAmount() == 1){
                cartItems.remove(cartItem);
            }
            else{
                cartItem.setAmount(cartItem.getAmount() - 1);
            }
        }
        session.setAttribute("cart", cartItems);

        return "redirect:/cart";
    }

    @GetMapping(value = "/clearAllCart")
    @PreAuthorize("isAnonymous() || isAuthenticated()")
    public String clearAll(Model model, HttpSession session){
        session.removeAttribute("cart");
        return "redirect:/cart";
    }

    @GetMapping(value = "/cart")
    @PreAuthorize("isAnonymous() || isAuthenticated()")
    public String cart(Model model, HttpSession session, HttpServletRequest request){
        //model which sends list of items in cart
        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<ShopItems> itemsTop = itemService.getTop();
        model.addAttribute("items", itemsTop);
        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        if(request.getParameter("success")!=null){
            String success = "Successfully Payment";
            model.addAttribute("success", success);
        }
        if(request.getParameter("error")!=null){
            String error = "Please, Try again!";
            model.addAttribute("error", error);
        }
            model.addAttribute("total", total);
        model.addAttribute("amount", getAmount(session));
        model.addAttribute("cart", cartItems);
        return "cart";
    }

    @PostMapping(value = "/checkIn")
    @PreAuthorize("isAnonymous() || isAuthenticated()")
    public String checkIn(@RequestParam(name = "fullname") String fullname,
                          Model model, HttpSession session){
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");

        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<ShopItems> itemsTop = itemService.getTop();
        model.addAttribute("items", itemsTop);
        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);
        if(cartItems != null){
            for(CartItem i : cartItems){
                i.setOwner(fullname);
                i.setBuyDate((java.sql.Date) new Date(System.currentTimeMillis()));
                itemService.addCartItem(i);
            }
            session.removeAttribute("cart");
//            String success = "Successfully Payment";
////            HttpServletRequest request = success;
//            model.addAttribute("success", success);

            return "redirect:/cart?success";
        }else{
//            String error = "Please, Try again!";
//            model.addAttribute("error", error);

            return "redirect:/cart?error";
        }
    }

    @GetMapping(value = "/soldItem")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String soldItem(Model model, HttpSession session){

        model.addAttribute("currentUser", getUserData());

        List<Categories> categories = itemService.getAllCategories();
        model.addAttribute("categories", categories);

        List<ShopItems> itemsTop = itemService.getTop();
        model.addAttribute("items", itemsTop);
        List<Brands> brands = itemService.getAllBrands();
        model.addAttribute("brands", brands);
        List<CartItem> sold = itemService.getAllCartItems();
        model.addAttribute("sold", sold);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        double total = 0;
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                total += i.getItem().getPrice() * i.getAmount();
            }
        }
        model.addAttribute("total", total);
        model.addAttribute("amount", getAmount(session));
        model.addAttribute("cart", cartItems);
        return "soldItem";
    }

    private int getAmount(HttpSession session){
        int amount = 0;
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        if(cartItems != null) {
            for (CartItem i : cartItems) {
                amount += i.getAmount();
            }
        }
        return amount;
    }

    /////////////////////////////////////////////////////////////////////



    @PostMapping(value = "/addComment")
    @PreAuthorize("isAuthenticated()")
    public String addComment(Model model, HttpSession session,
                             @RequestParam(name = "comment") String comment,
                             @RequestParam(name = "item_id")Long item_id){
        model.addAttribute("currentUser", getUserData());
        Users author = getUserData();
        ShopItems item = itemService.getItem(item_id);
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm");
        java.util.Date date = new Date(System.currentTimeMillis());
        if(item != null){
            Comment comment1 = new Comment();
            if(comment != null && comment != ""){
                comment1.setComment(comment);
                comment1.setAddedDate(date);
                comment1.setAuthor(author);
                comment1.setItem(item);
                itemService.addComment(comment1);
            }
        }
        return "redirect:/details/" + item_id;
    }

    @GetMapping(value = "/deleteComment")
    @PreAuthorize("isAuthenticated()")
    public String deleteComment(@RequestParam(name = "comment_id") Long comment_id) {
        Comment comment = itemService.getComment(comment_id);
        itemService.deleteComment(comment);
        Long item_id = comment.getItem().getId();
        return "redirect:/details/" + item_id;
    }

//    @GetMapping(value = "/deleteCommentAdmin")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
//    public String deleteCommentAdmin(@RequestParam(name = "item_id")Long item_id,
//                                @RequestParam(name = "comment_id") Long comment_id) {
//        Comment comment = itemService.getComment(comment_id);
//        List<Comment> comments = itemService.getAllPComments();
//        itemService.deleteComment(comment);
//        return "redirect:/details/" + item_id;
//    }

    @PostMapping(value = "/editComment")
    @PreAuthorize("isAuthenticated()")
    public String editComment(@RequestParam(name = "comment_id") Long comment_id,
                              @RequestParam(name = "comment") String comment){
        Comment comment1 = itemService.getComment(comment_id);
        Long item_id = comment1.getItem().getId();
        if(comment1 != null){
            if(comment1.getAuthor() == getUserData()){
                comment1.setComment(comment);
                itemService.saveComment(comment1);
            }
        }
        return "redirect:/details/" + item_id;
    }

    @GetMapping(value = "/clearAllComment")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    public String clearAllComment(Model model, @RequestParam(name = "id") Long id){
        List<Comment> comments = itemService.getAllPComments();
        for(Comment c : comments){
            itemService.deleteComment(c);
        }
        return "redirect:/details/" + id;
    }
}
