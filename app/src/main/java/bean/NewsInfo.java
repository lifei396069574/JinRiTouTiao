package bean;

/**
 * 作者：李飞 on 2017/4/11 11:25
 * 类的用途：
 */

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * onCreated = "sql"：增加唯一约束
 */
@Table(name = "news_info",onCreated = "CREATE UNIQUE INDEX realative_unique ON news_info(id, uri,title)")
public class NewsInfo {
    /**
     * name = "id"：数据库表中的一个字段
     * isId = true：是否是主键
     * autoGen = true：是否自动增长
     * property = "NOT NULL"：添加约束
     */
    public NewsInfo() {
    }

    public NewsInfo(String uri, String title, String zhuangt) {
        this.uri = uri;
        this.title = title;
        this.zhuangt = zhuangt;
    }

    @Column(name = "_id", isId = true, autoGen = true)
    public int _id;

    @Column(name = "id")
    public int id;// id

    @Column(name = "uri")
    public String uri;// uri

    @Column(name = "title")
    public String title;// 标题

    @Column(name = "zhuangt")
    public String zhuangt;// 状态


    @Override
    public String toString() {
        return "person [_id=" + _id +", id= "+ id + ", uri=" + uri + ", title=" + title
                + "]";
    }
}
