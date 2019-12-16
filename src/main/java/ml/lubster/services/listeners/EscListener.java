package ml.lubster.services.listeners;

import ml.lubster.services.taskrunners.ITaskRunner;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@inheritDoc}
 * The listener of pressing Esc button.
 */
public class EscListener implements NativeKeyListener {
    /**
     * A List of {@link ITaskRunner}s that are noticed about pressing Esc button.
     */
    List<ITaskRunner> taskManagers;

    /**
     * Creates an instance of the class.
     *
     * @param taskManagers {@link ITaskRunner}s that are noticed about pressing Esc button.
     */
    public EscListener(ITaskRunner... taskManagers) {
        this.taskManagers = new ArrayList<>(Arrays.asList(taskManagers));
    }

    /**
     * {@inheritDoc}
     *
     * @param nativeKeyEvent
     */
    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        // Does nothing
    }

    /**
     * {@inheritDoc}
     * Listens to pressing Esc button.
     *
     * @param nativeKeyEvent
     */
    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            taskManagers.forEach(ITaskRunner::interrupt);
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e) {
                System.err.println("NativeHookException occurred while unregistering");
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param nativeKeyEvent
     */
    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        // Does nothing
    }
}