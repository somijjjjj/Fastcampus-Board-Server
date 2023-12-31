package com.fastcampus.boardserver.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.fastcampus.boardserver.mapper") 
public class MysqlConfig {
	
	public SqlSessionFactory sqlSeesionFactory(DataSource dataSource) throws Exception{
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean(); //주입이 성공하면 수정 못하도록 final 객체 생성
		sessionFactory.setDataSource(dataSource); //sessionFactory 객체에 dataSource 설정
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/*.xml")); //특정 경로의 mybatis 리소스를 읽어들인다는 것을 설정
		
		Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"); //config정보 가져오기
		sessionFactory.setConfigLocation(myBatisConfig); //sessionFactory의 myBatisConfig 설정
		
		return sessionFactory.getObject(); //sessionFactory 객체 반환하여 myBatis 연동
	}

}
