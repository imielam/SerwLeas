/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

/**
 *
 * @author Macimi
 */
public class DbException extends Exception {
    private String OfficialMessage = "<h2>Wystąpił błąd po stronie serwera.</h2><br/><h3>Proszę spróbuj ponownie później</h3>";
    private String msg = "";

    public String getMsg() {
        return msg;
    }
    /**
     * Creates a new instance of
     * <code>DbException</code> without detail message.
     */
    public DbException() {
    }

    /**
     * Constructs an instance of
     * <code>DbException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DbException(String msg) {
        super(msg);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return OfficialMessage;
    }
    
}
