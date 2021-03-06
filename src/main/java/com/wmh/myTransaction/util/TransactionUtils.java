package com.wmh.myTransaction.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Component
@Scope("prototype")
public class TransactionUtils {
    // 全局接受事务状态
    private TransactionStatus transactionStatus;
    // 获取事务源
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    // 开启事务
    public TransactionStatus begin() {
        System.out.println("开启事务");
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    // 提交事务
    public void commit() {
        if (transactionStatus != null) {
            System.out.println("提交事务");
            dataSourceTransactionManager.commit(transactionStatus);
        }
    }

    // 回滚事务
    public void rollback() {
        if (transactionStatus != null) {
            System.out.println("回滚事务");
            dataSourceTransactionManager.rollback(transactionStatus);
        }
    }
}
