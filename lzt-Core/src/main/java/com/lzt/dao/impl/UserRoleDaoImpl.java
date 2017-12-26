package com.lzt.dao.impl;

import com.lzt.Bo.RoleBo;
import com.lzt.dao.UserRoleDao;
import com.lzt.entity.UserRole;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends AllDaoImpl<UserRole> implements UserRoleDao{

    public UserRoleDaoImpl() {
        super(UserRole.class);
    }

    @Override
    public List<RoleBo> getRoles(Integer id) {
        String sql = "select o.`role_name` roleName,o.id id FROM `lzt_user_role` r,`lzt_role` o WHERE o.`id` = r.`role_id` AND r.`user_id`=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .addScalar("roleName", StringType.INSTANCE).addScalar("id", IntegerType.INSTANCE).setParameter("id",id)
                .setResultTransformer(Transformers.aliasToBean(RoleBo.class));
        return query.list();
    }
}













