package org.lucidant.springboot.events;

@FunctionalInterface
public interface Callback {

	void onComplete(boolean result);
}
