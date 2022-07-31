package com.liteiot.common.msg;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Table结果响应体
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TableResultResponse<T> extends BaseResponse {

    TableData<T> data;

    /**
     * 弃用
     * @see com.liteiot.common.msg.TableResultResponse#build(long,List<T>)
     */
    @Deprecated
    public TableResultResponse(long total, List<T> rows) {
        this.data = new TableData<T>(total, rows);
    }

    /**
     * 返回分页数据
     * @param total 数据总数
     * @param rows 返回的数据集合
     */
    public static <T> TableResultResponse<T> build(long total, List<T> rows){
        return new TableResultResponse(total, rows);
    }

    public TableResultResponse() {
        this.data = new TableData<T>();
    }

    TableResultResponse<T> total(int total) {
        this.data.setTotal(total);
        return this;
    }

    TableResultResponse<T> total(List<T> rows) {
        this.data.setRows(rows);
        return this;
    }

    public TableData<T> getData() {
        return data;
    }

    public void setData(TableData<T> data) {
        this.data = data;
    }

    public class TableData<T> {
        long total;
        List<T> rows;

        public TableData(long total, List<T> rows) {
            this.total = total;
            this.rows = rows;
        }

        private TableData() {
        }

        public long getTotal() {
            return total;
        }

        private void setTotal(long total) {
            this.total = total;
        }

        public List<T> getRows() {
            return rows;
        }

        private void setRows(List<T> rows) {
            this.rows = rows;
        }
    }
}
