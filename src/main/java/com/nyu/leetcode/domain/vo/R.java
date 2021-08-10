package com.nyu.leetcode.domain.vo;

import com.nyu.leetcode.base.enums.RespCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * basic response type
 *
 * @author :zixiaotong
 * @date :Created in 2021/8/10 15:49
 */
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {

    /**
     * 响应码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 消息体
     */
    private T data;

    public R(final RespCode respCode, final T data) {
        this.code = respCode.getCode();
        this.message = respCode.getMessage();
        this.data = data;
    }

    public R(final RespCode respCode) {
        this.code = respCode.getCode();
        this.message = respCode.getMessage();
    }

    public R(final int code, final String msg) {
        this.code = code;
        this.message = msg;
    }

    public static <T> R<T> success() {
        return new R<>(RespCode.SUCCESS);
    }

    public static <T> R<T> success(final T data) {
        return new R<>(RespCode.SUCCESS, data);
    }

    public static <T> R<T> error() {
        return new R<>(RespCode.CUSTOM_ERROR);
    }

    public static <T> R<T> error(final String message) {
        return new R<>(RespCode.CUSTOM_ERROR.getCode(), message);
    }

    public static <T> R<T> error(final int code, final String message) {
        return new R<T>().setCode(code).setMessage(message);
    }

    public static <T> R<T> error(final RespCode respCode) {
        return new R<>(respCode);
    }

    public static <T> R<T> error(final RespCode respCode, final String message) {
        return new R<T>().setCode(respCode.getCode()).setMessage(message);
    }
}
