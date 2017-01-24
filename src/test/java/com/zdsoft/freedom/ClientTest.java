package com.zdsoft.freedom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.zdsoft.freedom.test.model.Client;
import com.zdsoft.freedom.test.model.ClientOwner;
import com.zdsoft.freedom.test.model.Organization;

import junit.framework.TestCase;

public class ClientTest extends TestCase {

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

    public void testSave() {
        init();
        
        //1、添加客户，添加ght
//        Client client = new Client();
//        client.setClientNm("重庆正大集团公司");
//        ClientOwner clientOwner = new ClientOwner();
//        clientOwner.setClient(client);
//        clientOwner.setGrantorCd("ght");
//        clientOwner.setGrantorNm("龚海淘");
//        client.getCrmClientOwners().add(clientOwner);
//        session.save(client);
        
        //2、遍历客户和权限人
//        Query query = session.createQuery("from Client c where c.clientNm=?").setParameter(0, "重庆正大集团公司");//设置问号，从0开始。
//        List<Client> clients = query.list();  
//        if(null!=clients && !clients.isEmpty()){
//            for(Client client2:clients){
//                for(ClientOwner clientOwner2:client2.getCrmClientOwners()){
//                    System.out.println("客户："+client2.getClientNm()+",的权限是人："+clientOwner2.getGrantorNm());
//                }
//            }
//        }
        
        //3、添加dyy
//        Query query = session.createQuery("from Client c where c.clientNm=?").setParameter(0, "重庆正大集团公司");//设置问号，从0开始。
//        List<Client> clients = query.list();  
//        if(null!=clients && !clients.isEmpty()){
//            Client client = clients.get(0);
//            ClientOwner clientOwner = new ClientOwner();
//            clientOwner.setClient(client);
//            clientOwner.setGrantorCd("zfj");
//            clientOwner.setGrantorNm("周峰杰");
//            client.getCrmClientOwners().add(clientOwner);
//            session.saveOrUpdate(client);
//        }
        
        //4、移除dyy
        Query query = session.createQuery("from Client c where c.clientNm=?").setParameter(0, "重庆正大集团公司");//设置问号，从0开始。
        List<Client> clients = query.list();  
        
        if(null!=clients && !clients.isEmpty()){
            Client client = clients.get(0);
            
            Set<ClientOwner> x = client.getCrmClientOwners();
            Collection<ClientOwner> xx = new ArrayList<ClientOwner>();
            for (ClientOwner clientOwner : x) {
                System.out.println("客户：" + client.getClientNm() + ",的权限是人：" + clientOwner.getGrantorNm());
                
                if(clientOwner.getGrantorCd().equals("dyy")){
                    clientOwner.setClient(null);
                    //client.getCrmClientOwners().remove(clientOwner);
                    xx.add(clientOwner);
                }
                
                if(clientOwner.getGrantorCd().equals("zfj")){
                    clientOwner.setClient(null);
                    //client.getCrmClientOwners().remove(clientOwner);
                    xx.add(clientOwner);
                }
            }
            
            client.getCrmClientOwners().removeAll(xx);
            session.saveOrUpdate(client);
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
