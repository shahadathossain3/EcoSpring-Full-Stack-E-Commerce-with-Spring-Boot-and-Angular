package com.example.springbootecommerce.config;

import com.example.springbootecommerce.filter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .cors().configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(Arrays.asList("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }
            }).and().csrf().disable()
            .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
            .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests((auth) -> auth
                .antMatchers("/category/addCategory").hasRole("ADMIN")
                .antMatchers("/category/updateCategoryById/**").hasRole("ADMIN")
                .antMatchers("/category/deleteCategoryById/**").hasRole("ADMIN")
                .antMatchers("/product/addProduct").hasRole("ADMIN")
                .antMatchers("/product/updateProductById/**").hasRole("ADMIN")
                .antMatchers("/product/deleteProductById/**").hasRole("ADMIN")
                .antMatchers("/order/getAllPendingOrder").hasRole("ADMIN")
                .antMatchers("/order/getAllPendingDeliveryOrder").hasRole("ADMIN")
                .antMatchers("/order/addOrder").hasRole("USER")
                .antMatchers("/order/orderUpdateById/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/order/orderDeleteById/**").hasRole("ADMIN")
                .antMatchers("/role/getAllRole").hasRole("ADMIN")
                .antMatchers("/role/addRole").hasRole("ADMIN")
                .antMatchers("/user/getAllUser").hasRole("ADMIN")
                .antMatchers("/user/user-details/**").hasRole("USER")
                .antMatchers("/user/update-user/**").hasRole("USER")
                .antMatchers("/user/getUserOrderListHistory/**").hasRole("USER")
                .antMatchers("/user/getUserOrderListPending/**").hasRole("USER")
                .antMatchers("/user/login").authenticated()
                .antMatchers("/category/getAllCategory","/category/getCategoryWithProductList/**",
                    "/category/getCategoryById/**","/product/getAllProduct","/product/getProductWithCategoryList/**",
                    "/product/details-products/**","/product/search/**","/user/register","/user/confirm-account/**").permitAll()

            ).httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
