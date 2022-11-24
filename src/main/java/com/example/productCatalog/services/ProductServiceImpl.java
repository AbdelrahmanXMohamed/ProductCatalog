package com.example.productCatalog.services;

import com.example.productCatalog.dto.ProductWithCategory;
import com.example.productCatalog.dto.ProductWithIdDto;
import com.example.productCatalog.entities.Categories;
import com.example.productCatalog.entities.Products;
import com.example.productCatalog.mappers.CategoryMapper;
import com.example.productCatalog.mappers.ProductMapper;
import com.example.productCatalog.repositories.CategoriesRepository;
import com.example.productCatalog.repositories.ProductsRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    private CategoryMapper categoryMapper=Mappers.getMapper(CategoryMapper.class);
    @Override
    public List<ProductWithIdDto> retrievingAllProducts() {
        List <Products> products=productsRepository.findAll() ;

        List<ProductWithIdDto> productWithIdDtoList=products
                .stream()
                .map(product-> productMapper.productsTProductWithIdDto(product)).collect(Collectors.toList());

        for (int i=0;i<products.size();i++)
           productWithIdDtoList.get(i).setCategory( categoryMapper.categoriesToCategoryDto(products.get(i).getCategory()));
        return productWithIdDtoList;
    }
    @Override
    public Products addProduct(ProductWithCategory productDto){
        Categories categories =
                categoriesRepository
                        .getReferenceById(productDto.getCategoryId());
        Products product=productsRepository
                .save(productMapper.productDtoToProducts(productDto));
        categories
                .getProducts()
                .add(product);
        product
                .setCategory(categories);
        return productsRepository
                .save(product);
    }
    @Override
    public Products updateProducts(Long id,ProductWithCategory productDto){
        Products product= productsRepository.findById(id).get();
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setNameAr(productDto.getNameAr());
        product.setNameEn(productDto.getNameEn());

        if (categoriesRepository.findById(productDto.getCategoryId()).isPresent() )
            product.setCategory(categoriesRepository.findById(productDto.getCategoryId()).get());
        return productsRepository.save(product);
    }
    @Override
    public void deleteProduct(Long id) {
        productsRepository.delete(productsRepository.getReferenceById(id));
    }
    @Override
    public List<ProductWithIdDto> retrievingAllProductsOrderedByPopularity() {
        List<Products> products=productsRepository.findAll();
        products = products.stream().sorted(
                (products1,products2) ->
                        products2.getProductsOrders().size()-products1.getProductsOrders().size())
                .collect(Collectors.toList());
        return products.stream().map(product->productMapper.productsTProductWithIdDto(product)).collect(Collectors.toList());
    }
}
