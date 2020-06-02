package red.mohist.common.async;

public class AsyncProperties {

    private final Boolean canAsync;
    private final Type asyncType;



    @Async
    public AsyncProperties(boolean canAsync, Type asyncType) {
        this.canAsync = canAsync;
        this.asyncType = asyncType;

    }

    public Boolean canAsync() {
        return canAsync;
    }

    public Type getAsyncType() {
        return asyncType;
    }

    public enum Type {
        VANILLA, MODDED
    }
}
