package utils;

import org.xutils.DbManager;
import org.xutils.x;

import java.io.File;

/**
 * 作者：李飞 on 2017/4/12 11:26
 * 类的用途： 获得数据库
 */

public class DbUtils {
    private static DbManager.DaoConfig daoConfig;

    public static DbManager getDaoConfig(){

        if(daoConfig==null){
            daoConfig = new DbManager.DaoConfig()
                    .setAllowTransaction(true)//设置允许开启事务
                    .setDbName("mynews.db")//创建数据库的名称
                    // 不设置dbDir时, 默认存储在app的私有目录.
                    .setDbDir(new File("/sdcard")) // "sdcard"的写法并非最佳实践, 这里为了简单, 先这样写了.
                    .setDbVersion(1)//数据库版本号
//                    .setDbOpenListener(new DbManager.DbOpenListener() {
//                        @Override
//                        public void onDbOpened(DbManager db) {
//                            // 开启WAL, 对写入加速提升巨大
//                            db.getDatabase().enableWriteAheadLogging();
//                        }
//                    })
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                            // TODO: ...
                            //db.addColumn(...);
                            // db.dropTable(...);
                            // ...
                            // or
                            // db.dropDb();
                        }
                    });
        }


        DbManager db = x.getDb(daoConfig);
        return db;
    }
}
