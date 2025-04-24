package collision.listeners.hit_notifiers;


import collision.listeners.hit_listeners.infrastructure.HitListener;

/**
 * An interface for objects that can notify listeners when a hit event occurs.
 *
 * Classes implementing this interface can register and remove listeners
 * that respond to hit events.
 */
public interface HitNotifier {

   /**
    * Adds the given listener to the list of listeners to be notified of hit events.
    *
    * @param hl the hit listener to add
    */
   void addHitListener(HitListener hl);

   /**
    * Removes the given listener from the list of listeners to be notified of hit events.
    *
    * @param hl the hit listener to remove
    */
   void removeHitListener(HitListener hl);
}
