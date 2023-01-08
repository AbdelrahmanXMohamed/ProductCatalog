package com.example.product_catalog.service.impl;

import com.example.product_catalog.dto.ProductResponseDto;
import com.example.product_catalog.dto.ProductWithCategory;
import com.example.product_catalog.entity.Categories;
import com.example.product_catalog.entity.Products;
import com.example.product_catalog.exception.CategoryNotFoundException;
import com.example.product_catalog.exception.ProductNotFoundException;
import com.example.product_catalog.mapper.CategoryMapper;
import com.example.product_catalog.mapper.ProductMapper;
import com.example.product_catalog.repository.CategoriesRepository;
import com.example.product_catalog.repository.ProductsRepository;
import com.example.product_catalog.service.ProductService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    private final CategoryMapper categoryMapper=Mappers.getMapper(CategoryMapper.class);
    @Override
    public List<ProductResponseDto> getProducts() {
        List <Products> products=productsRepository.findAll() ;

        List<ProductResponseDto> productResponseDtoList =products
                .stream()
                .map(productMapper::productsToProductWithIdDto).collect(Collectors.toList());

        for (int i=0;i<products.size();i++)
           productResponseDtoList.get(i).setCategory( categoryMapper.categoriesToCategoryDto(products.get(i).getCategory()));
        return productResponseDtoList;
    }
    @Override
    public Products addProduct(ProductWithCategory productDto){

        Categories categories = categoriesRepository.getReferenceById(productDto.getCategoryId());
        try {
            productDto.setImage(imageBase64ToURL(productDto.getImage()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Products product=productsRepository.save(productMapper.productDtoToProducts(productDto));
        categories.getProductItems().add(product);
        product.setCategory(categories);
        return productsRepository.save(product);
    }
    @Override
    public Products updateProducts(Long id,ProductWithCategory productDto) throws IOException {
        Optional<Products> productOptional =
                productsRepository.findById(id);
        if (!productOptional.isPresent()) {
            throw new ProductNotFoundException(productDto.getCategoryId());
        }
        Optional<Categories>  categoryOptional =
                categoriesRepository.findById(productDto.getCategoryId());
        if (!categoryOptional.isPresent()) {
            throw new CategoryNotFoundException(productDto.getCategoryId());
        }
        if (productDto.getImage()==null || productDto.getImage().isEmpty())
        {
            productDto.setImage(productOptional.get().getImage());
        }
        else {
            productDto.setImage(imageBase64ToURL(productDto.getImage()));

        }
        Products product = productMapper.productDtoToProducts(productDto);
        product.setId(id);
        categoryOptional.ifPresent(product::setCategory);
        return productsRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id) {
        Optional<Products> products = productsRepository.findById(id);
        if(!products.isPresent()||Boolean.FALSE.equals(products.get().getStatus()))
            throw new ProductNotFoundException(id);
        products.get().setStatus(Boolean.FALSE);
        productsRepository.save(products.get());
    }
    @Override
    public List<ProductResponseDto> getPopularProducts() {
        return productsRepository.findByOrderByOrderedQuantityDesc().stream()
                        .filter(Products::getStatus)
                        .map(productMapper::productsToProductWithIdDto)
                        .collect(Collectors.toList());}
    @Override
    public ProductResponseDto getProductsById(Long id) {
        return productMapper.productsToProductResponseDto(productsRepository.findById(id).orElseThrow(()-> new RuntimeException()));
    }

    private String imageBase64ToURL(String imageBase64) throws IOException {

        String delims="[,]";
        String[] parts = imageBase64.split(delims);
        String imageString = parts[1];
        BufferedImage image = null;

        byte[] imageByteArray = Base64.decode(imageString.getBytes());
        InputStream is = new ByteArrayInputStream(imageByteArray);
        image = ImageIO.read(is);
        is.close();

        String fileExtension = null;
        String delimiter="[/]";
        String[] tokens = extractMimeType(imageBase64).split(delimiter);
        fileExtension = tokens[1];

        Date currentDate= new Date();
        String imageName= String.valueOf(currentDate.getTime())+"."+fileExtension;
        File imageFile= new File("src/main/resources/static/images/"+imageName);
        ImageIO.write(image, fileExtension, imageFile);

        return "http://localhost:8080/images/"+imageName;
    }
    private static String extractMimeType(final String encoded) {
        final Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
        final Matcher matcher = mime.matcher(encoded);
        if (!matcher.find())
            return "";
        return matcher.group(1).toLowerCase();
    }
}
