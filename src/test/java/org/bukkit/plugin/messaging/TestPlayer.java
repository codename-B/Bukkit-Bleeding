package org.bukkit.plugin.messaging;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class TestPlayer implements InvocationHandler {
    private static interface MethodHandler {
        Object handle(TestPlayer testPlayer, Object[] args);
    }
    private static final Constructor<? extends Player> constructor;
    private static final HashMap<Method, MethodHandler> methods = new HashMap<Method, MethodHandler>();
    static {
        try {
            methods.put(Player.class.getMethod("getType"),
                new MethodHandler() {
                    public Object handle(TestPlayer server, Object[] args) {
                        return EntityType.PLAYER;
                    }
                });
            constructor = Proxy.getProxyClass(Player.class.getClassLoader(), Player.class).asSubclass(Player.class).getConstructor(InvocationHandler.class);
        } catch (Throwable t) {
            throw new Error(t);
        }
    }

    public static Player getInstance() {
        try {
            return constructor.newInstance(new TestPlayer());
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private TestPlayer() {}

    public Object invoke(Object proxy, Method method, Object[] args) {
        MethodHandler handler = methods.get(method);
        if (handler != null) {
            return handler.handle(this, args);
        }
        throw new UnsupportedOperationException(String.valueOf(method));
    }
}
