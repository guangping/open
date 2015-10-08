/**
 * 日    期：12-2-13
 */
package com.varela.open;

/**
 * <pre>
 *   ROP的异常。
 * </pre>
 *
h5
 */
public class RopException extends RuntimeException {
    public RopException() {
    }

    public RopException(String message) {
        super(message);
    }

    public RopException(String message, Throwable cause) {
        super(message, cause);
    }

    public RopException(Throwable cause) {
        super(cause);
    }
}

