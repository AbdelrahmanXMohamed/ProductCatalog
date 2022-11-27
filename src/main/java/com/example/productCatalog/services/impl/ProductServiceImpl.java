package com.example.productCatalog.services.impl;

import com.example.productCatalog.dtos.ProductResponseDto;
import com.example.productCatalog.dtos.ProductWithCategory;
import com.example.productCatalog.entities.Categories;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.excaptions.ProductNotFoundException;
import com.example.productCatalog.mappers.CategoryMapper;
import com.example.productCatalog.mappers.ProductMapper;
import com.example.productCatalog.repositories.CategoriesRepository;
import com.example.productCatalog.repositories.ProductsRepository;
import com.example.productCatalog.services.ProductService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<ProductResponseDto> retrievingAllProducts() {
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
        Categories categories =
                categoriesRepository
                        .getReferenceById(productDto.getCategoryId());
        Products product=productsRepository
                .save(productMapper.productDtoToProducts(productDto));
        categories
                .getProductItems()
                .add(product);
        product
                .setCategory(categories);
        return productsRepository
                .save(product);
    }
    @Override
    public Products updateProducts(Long id,ProductWithCategory productDto){

        Products product= productsRepository.getReferenceById(id);
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setNameAr(productDto.getNameAr());
        product.setNameEn(productDto.getNameEn());
        Optional<Categories>  categoriesOptional =
                categoriesRepository.findById(productDto.getCategoryId());
        categoriesOptional.ifPresent(product::setCategory);
        return productsRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id) {
        Optional<Products> products = productsRepository.findById(id);
        if(!products.isPresent()||!products.get().getStatus())
            throw new ProductNotFoundException(id);
        products.get().setStatus(Boolean.FALSE);
        productsRepository.save(products.get());
    }
    @Override
    public List<ProductResponseDto> retrievingAllProductsOrderedByPopularity() {
        List<Products> products=productsRepository.findAll();
        products = products.stream().sorted(
                (products1,products2) ->
                        products2.getOrderedProducts().size() - products1.getOrderedProducts().size())
                .collect(Collectors.toList());
        return products.stream().map(productMapper::productsToProductWithIdDto).collect(Collectors.toList());
    }
}
