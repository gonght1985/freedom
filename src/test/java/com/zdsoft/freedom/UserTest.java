package com.zdsoft.freedom;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.zdsoft.freedom.test.model.Organization;
import com.zdsoft.freedom.test.model.User;

import junit.framework.TestCase;

/**
 * 测试示例
 * 
 * @author gonght
 *
 */
public class UserTest extends TestCase {
	
	private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
	
    public void init() {  
        // 但在5.1.0版本汇总，hibernate则采用如下新方式获取：
		// 1. 配置类型安全的准服务注册类，这是当前应用的单例对象，不作修改，所以声明为final
		// 在configure("cfg/hibernate.cfg.xml")方法中，如果不指定资源路径，默认在类路径下寻找名为hibernate.cfg.xml的文件
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		// 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
		SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		// 会话对象
        session = sessionFactory.openSession();
        // 开启事务
        transaction = session.beginTransaction();
    }
	
    public void testSaveOrg() {
    	init();
		Organization orgRoot = new Organization();
		orgRoot.setOrgNm("重庆正大集团公司");
		session.save(orgRoot);
		destory();
	}
    
    @SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public void testSaveUserAndAccount() {	
    	
    	init();
    	
		/*User user = new User();
		user.setUserName("龚海淘");
		session.save(user);
		Account account = new Account();
		account.setAccountNo("gonght");
		account.setAccountPwd("123456");
		account.setUser(user);
		session.save(account);
		user.getAccounts().add(account);
		session.update(user);*/
		
		User user = new User();
		user.setUserName("龚治宇");
		user.addAccount("gongzy", "123456");
		//关联机构
		Query query = session.createQuery("from Organization org where org.orgNm=?")
				.setParameter(0, "重庆正大集团公司");//设置问号，从0开始。
        List<Organization> organizations = query.list();  
        if(null!=organizations && !organizations.isEmpty()){
        	System.out.println(organizations.get(0).getOrgNm());
        	user.setOrg(organizations.get(0));
        }		
		session.save(user);
		
		destory();
	}
    
    @SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public void testQuery() {	
    	
    	init();

		Query query = session.createQuery("from Organization");
        List<Organization> organizations = query.list();  
        if(null!=organizations && !organizations.isEmpty()){
        	System.out.println(organizations.get(0).getOrgNm());
        }

        Query query2 = session.createQuery("from User user where user.org.orgNm = :orgNm")
				.setParameter("orgNm", "重庆正大集团公司");
        List<User> users = query2.list();  
        if(null!=users && !users.isEmpty()){
        	for(User user:users) {
        		System.out.println(user.getUserName());
        	}
        }
        
		destory();
	}
	
    public void destory() {
        // 先提交事务
        transaction.commit();
        // 关闭会话
        session.close();
        // 关闭会话工厂
        sessionFactory.close();
    }	
}