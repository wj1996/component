package com.wj.controller;

import com.wj.vo.Product;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consumer2")
public class ConsumerProductController {
    public static final String PRODUCT_GET_URL = "http://CLOUD-PROVIDER2/product/get/";
    public static final String PRODUCT_LIST_URL="http://CLOUD-PROVIDER2/product/list/";
    public static final String PRODUCT_ADD_URL = "http://CLOUD-PROVIDER2/product/add/";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private HttpHeaders httpHeaders;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/product/get")
    public Object getProduct(long id) {

        /*
        *
        * 基本调用方式
        * restTemplate.getForObject(PRODUCT_GET_URL + id,Product.class);
        * */

        Product product = restTemplate.exchange(PRODUCT_GET_URL + id, HttpMethod.GET,new HttpEntity<Object>(httpHeaders), Product.class).getBody();
        return  product;
    }

    @RequestMapping("/product/list")
    public  Object listProduct() throws Exception{
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("CLOUD-PROVIDER2") ;
        String uri = String.format("http://%s:%s/product/list",serviceInstance.getHost(), serviceInstance.getPort());
        System.out.println(
                "【*** ServiceInstance ***】host = " + serviceInstance.getHost()
                        + "、port = " + serviceInstance.getPort()
                        + "、serviceId = " + serviceInstance.getServiceId());
        /*
        * 使用认证的 使用exchange方法
        * */
        System.out.println(uri);
        URI u = new URI(uri);
        List<Product> list = restTemplate.exchange(u,HttpMethod.GET,
                new HttpEntity<Object>(httpHeaders), List.class).getBody();
        return  list;
    }

    @RequestMapping("/product/add")
    public Object addPorduct(Product product) {
        Boolean result = restTemplate.exchange(PRODUCT_ADD_URL, HttpMethod.POST,
                new HttpEntity<Object>(product,httpHeaders), Boolean.class).getBody();
        return  result;
    }
}