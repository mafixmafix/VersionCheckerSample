package tk.pallas.versionchecker;

/**
 * Created by Mafix <ahangarani.amir@gmail.com>
 */

public interface ICondition<R> {
    void whatHappen(IVersion<R> version);
}
