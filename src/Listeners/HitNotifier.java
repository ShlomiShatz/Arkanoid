package Listeners;

/**
 * The HitNotifier interface for all the hit notifiers.
 * @author Shlomo Shatz   */
public interface HitNotifier {

    /**
     * Adds a hit listener to the notifier.
     * @param hl The hit listener being added
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a hit listener from the notifier.
     * @param hl The hit listener being removed
     */
    void removeHitListener(HitListener hl);
}
