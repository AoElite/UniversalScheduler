package com.github.Anon8281.universalScheduler;

import com.github.Anon8281.universalScheduler.scheduling.schedulers.TaskScheduler;
import com.github.Anon8281.universalScheduler.scheduling.tasks.MyScheduledTask;
import org.bukkit.plugin.Plugin;

public abstract class UniversalRunnable implements Runnable {
    MyScheduledTask task;

    public synchronized void cancel() throws IllegalStateException {
        task.cancel();
    }

    /**
     * Returns true if this task has been cancelled.
     *
     * @return true if the task has been cancelled
     * @throws IllegalStateException if task was not scheduled yet
     */
    public synchronized boolean isCancelled() throws IllegalStateException {
        checkScheduled();
        return task.isCancelled();
    }

    /**
     * Schedules this in the Bukkit scheduler to run on next tick.
     *
     * @param plugin the reference to the plugin scheduling task
     * @return {@link MyScheduledTask}
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException    if this was already scheduled
     * @see TaskScheduler#runTask(Runnable)
     */

    public synchronized MyScheduledTask runTask(Plugin plugin) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(UniversalScheduler.getScheduler(plugin).runTask(this));
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules this in the Bukkit scheduler to run asynchronously.
     *
     * @param plugin the reference to the plugin scheduling task
     * @return {@link MyScheduledTask}
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException    if this was already scheduled
     * @see TaskScheduler#runTaskAsynchronously(Runnable)
     */

    public synchronized MyScheduledTask runTaskAsynchronously(Plugin plugin) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(UniversalScheduler.getScheduler(plugin).runTaskAsynchronously(this));
    }

    /**
     * Schedules this to run after the specified number of server ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param delay  the ticks to wait before running the task
     * @return {@link MyScheduledTask}
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException    if this was already scheduled
     * @see TaskScheduler#runTaskLater(Runnable, long)
     */

    public synchronized MyScheduledTask runTaskLater(Plugin plugin, long delay) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(UniversalScheduler.getScheduler(plugin).runTaskLater(this, delay));
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules this to run asynchronously after the specified number of
     * server ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param delay  the ticks to wait before running the task
     * @return {@link MyScheduledTask}
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException    if this was already scheduled
     * @see TaskScheduler#runTaskLaterAsynchronously(Runnable, long)
     */

    public synchronized MyScheduledTask runTaskLaterAsynchronously(Plugin plugin, long delay) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(UniversalScheduler.getScheduler(plugin).runTaskLaterAsynchronously(this, delay));
    }

    /**
     * Schedules this to repeatedly run until cancelled, starting after the
     * specified number of server ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param delay  the ticks to wait before running the task
     * @param period the ticks to wait between runs
     * @return {@link MyScheduledTask}
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException    if this was already scheduled
     * @see TaskScheduler#runTaskTimer(Runnable, long, long)
     */

    public synchronized MyScheduledTask runTaskTimer(Plugin plugin, long delay, long period) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(UniversalScheduler.getScheduler(plugin).runTaskTimer(this, delay, period));
    }

    /**
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules this to repeatedly run asynchronously until cancelled,
     * starting after the specified number of server ticks.
     *
     * @param plugin the reference to the plugin scheduling task
     * @param delay  the ticks to wait before running the task for the first
     *               time
     * @param period the ticks to wait between runs
     * @return {@link MyScheduledTask}
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException    if this was already scheduled
     * @see TaskScheduler#runTaskTimerAsynchronously(Runnable, long, long)
     */

    public synchronized MyScheduledTask runTaskTimerAsynchronously(Plugin plugin, long delay, long period) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(UniversalScheduler.getScheduler(plugin).runTaskTimerAsynchronously(this, delay, period));
    }

    private void checkScheduled() {
        if (task == null) {
            throw new IllegalStateException("Not scheduled yet");
        }
    }

    private void checkNotYetScheduled() {
        if (task != null) {
            throw new IllegalStateException("Already scheduled");
        }
    }


    private MyScheduledTask setupTask(final MyScheduledTask task) {
        this.task = task;
        return task;
    }


}
