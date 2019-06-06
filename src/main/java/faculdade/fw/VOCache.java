package faculdade.fw;

public class VOCache {

    private long expire;
    private Object value;

    public VOCache(long expire, Object value) {
        this.expire = expire;
        this.value = value;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
