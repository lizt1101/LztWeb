package com.lzt.dao.impl;

import com.lzt.Bo.PermissionBo;
import com.lzt.dao.PermissionDao;
import com.lzt.dao.RoleDao;
import com.lzt.entity.Permission;
import com.lzt.entity.Role;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("permissionDao")
public class PermissionDaoImpl extends AllDaoImpl<Permission> implements PermissionDao {


    public PermissionDaoImpl() {
        super(PermissionDao.class);
    }

    @Override
    public List<PermissionBo> getPers(Set<Integer> roleIds) {
        String sql = "SELECT p.`per_name` perName FROM `lzt_permission` p WHERE p.`role_id` IN (:ids)";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .addScalar("perName", StringType.INSTANCE).setParameterList("ids",roleIds)
                .setResultTransformer(Transformers.aliasToBean(PermissionBo.class));
        return query.list();
    }
}












