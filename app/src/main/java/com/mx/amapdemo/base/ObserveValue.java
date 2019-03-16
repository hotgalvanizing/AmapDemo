package com.mx.amapdemo.base;

/**
 * ===NAVI BUSINESS API===
 * 观察value, 方便用来判断是否有修改
 *
 * author PG.Xie
 */
public class ObserveValue<T> {

    private T t;
    private boolean hasUpdated = false;

    /**
     * 这里必须强制要求给予初始化的值，防止空指针问题，自己提醒自己
     *
     * @param t
     */
    public ObserveValue(T t) {
        this.t = t;
    }

    private ObserveValue() {
    }

    /**
     * 检测当前值是否有更新
     *
     * @return
     */
    public boolean hasUpdated() {
        return hasUpdated;
    }

    /**
     * 放入新的值
     *
     * @param data 新的值
     * @return
     */
    public ObserveValue<T> push(T data) {
        if (this.t == null) {
            if (data != null) {
                hasUpdated = true;
            }
        } else if (!hasUpdated) {
            //保证在没有获取新的值的情况下，一直保持是更新状态
            hasUpdated |= !this.t.equals(data);
        }
        this.t = data;
        return this;
    }

    /**
     * 弹出当前存储的值，表示只用了一次，重置更新标识
     *
     * @return 当前存储的值
     */
    public T pop() {
        hasUpdated = false;
        return this.t;
    }

    /**
     * 获取当前存储的值，不重置更新标识
     *
     * @return 当前存储的值
     */
    public T peek() {
        return this.t;
    }

    /**
     * 强制更新当前的设定值
     *
     * @param t 当前需要设定的值
     */
    public void update(T t) {
        this.t = t;
        setUpdated(true);
    }

    /**
     * 更新当前设定值的标识
     *
     * @param isUpdated
     */
    public void setUpdated(boolean isUpdated) {
        this.hasUpdated = isUpdated;
    }
}
