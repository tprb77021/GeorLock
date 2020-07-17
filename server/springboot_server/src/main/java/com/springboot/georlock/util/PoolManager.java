/*
 * DB 연동때 마다 매번 mybatis 설정파일을 로드하는 과정을 거치면 유지보수성이 떨어지므로
 * mybatis 연동을 위한 재사용목적의 객체를 정의하자.
 */

package com.springboot.georlock.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class PoolManager {
	/*private static PoolManager instance;
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;

	private PoolManager() {
		String resource = "mybatis-config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static PoolManager getInstance() {
		if (instance == null) {//instance가 null일떄만 객체 생성
			instance = new PoolManager();
		}
		return instance;
	}

	// mybatis는 default commit 옵션이 false 이므로,
	// 쿼리문 수행 후 commit 해야한다.
	//매번 생성
	public SqlSession getSession() {
		SqlSession session = null;
		session = sqlSessionFactory.openSession();
		return session;
	}*/
}