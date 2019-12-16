package ml.lubster.services.listeners;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class that keeps listeners and manages them.
 */
public class KeyListener {
    /**
     * List of registered {@link NativeKeyListener}
     */
    private final List<NativeKeyListener> listeners;

    /**
     * Creates an instance of the class. Disables logging.
     *
     * @param listeners {@link NativeKeyListener}s.
     */
    public KeyListener(NativeKeyListener... listeners) {
        Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);
        this.listeners = new ArrayList<>(Arrays.asList(listeners));
    }

    /**
     * Registers hook. Starts working {@link KeyListener#listeners}
     */
    public void start() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println("Exception was occurred while registering the native hook.");
            System.err.println(e.getMessage());
        }
        listeners.forEach(GlobalScreen::addNativeKeyListener);
    }

    /**
     * Unregisters hook. Stops working {@link KeyListener#listeners}
     */
    public void stop() {
        try {
            GlobalScreen.unregisterNativeHook();
            listeners.forEach(GlobalScreen::removeNativeKeyListener);
        } catch (NativeHookException e) {
            System.err.println("NativeHookException occurred while unregistering");
        }
    }
}