package bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 作者：李飞 on 2017/4/16 20:38
 * 类的用途：
 */
@Table(name = "PersonInfo",onCreated = "CREATE UNIQUE INDEX realative_unique ON PersonInfo(name,pwd)")
public class PersonInfo {

    @Column(name = "_id", isId = true, autoGen = true)
    public int _id;

    @Column(name = "name")
    public String name;// uri

    @Column(name = "pwd")
    public String pwd;// 标题


    @Override
    public String toString() {
        return "person [_id=" + _id  + ", name=" + name + ", pwd=" + pwd
                + "]";
    }
}
