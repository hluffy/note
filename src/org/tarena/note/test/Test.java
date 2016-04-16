package org.tarena.note.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.User;

public class Test {
	public static void main(String[] args) throws SQLException {
		String conf="applicationContext.xml";
		ApplicationContext ac=new ClassPathXmlApplicationContext(conf);
		DataSource ds=ac.getBean("dbcp",DataSource.class);
		System.out.println(ds.getConnection());
		SqlSessionFactory factory=ac.getBean("ssf",SqlSessionFactory.class);
		System.out.println(factory.openSession());
		UserMapperDao dao=ac.getBean("userMapperDao",UserMapperDao.class);
		User user=dao.findByName("zhoujia");
		System.out.println(user.getCn_user_id()+":"+user.getCn_user_name());
	}

}
