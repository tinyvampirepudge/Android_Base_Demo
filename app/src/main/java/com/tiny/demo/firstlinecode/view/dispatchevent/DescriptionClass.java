package com.tiny.demo.firstlinecode.view.dispatchevent;

/**
 * Created by 87959 on 2017/6/21.
 */

public class DescriptionClass {
    /**
     * 这个包下的是关于view事件分发的过程。
     * ViewGroup的事件分发。
     */

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (mInputEventConsistencyVerifier != null) {
//            mInputEventConsistencyVerifier.onTouchEvent(ev, 1);
//        }
//
//        // If the event targets the accessibility focused view and this is it, start
//        // normal event dispatch. Maybe a descendant is what will handle the click.
//        if (ev.isTargetAccessibilityFocus() && isAccessibilityFocusedViewOrHost()) {
//            ev.setTargetAccessibilityFocus(false);
//        }
//
//        //初始化handled的值。
//        boolean handled = false;
//        if (onFilterTouchEventForSecurity(ev)) {
//            final int action = ev.getAction();
//            final int actionMasked = action & MotionEvent.ACTION_MASK;
//
//            // Handle an initial down.
//            if (actionMasked == MotionEvent.ACTION_DOWN) {
//                // Throw away all previous state when starting a new touch gesture.
//                // The framework may have dropped the up or cancel event for the previous gesture
//                // due to an app switch, ANR, or some other state change.
//                cancelAndClearTouchTargets(ev);
//                resetTouchState();
//            }
//
//            // Check for interception.
//            final boolean intercepted;//是否拦截
//            //①如果是down操作或者mFirstTouchTarget不为空，就根据(mGroupFlags & FLAG_DISALLOW_INTERCEPT)
//            // 的状态给intercepted赋值。
//            //②intercepted = true;表示拦截
//            if (actionMasked == MotionEvent.ACTION_DOWN
//                    || mFirstTouchTarget != null) {
//                //是否不允许拦截，如果mGroupFlags有FLAG_DISALLOW_INTERCEPT的标签，
//                //那么disallowIntercept的值就为true。
//                //如果disallowIntercept的值为true,那么直接就给intercepted赋值为false.
//                //若为false,
//                final boolean disallowIntercept = (mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0;
//                if (!disallowIntercept) {//允许拦截，调用onInterceptTouchEvent(ev)方法，返回值赋给intercepted；
//                    intercepted = onInterceptTouchEvent(ev);
//                    ev.setAction(action); // restore action in case it was changed
//                } else {//不允许拦截，intercepted = false
//                    intercepted = false;
//                }
//            } else {
//                // There are no touch targets and this action is not an initial down
//                // so this view group continues to intercept touches.
//                intercepted = true;
//            }
//
//            // If intercepted, start normal event dispatch. Also if there is already
//            // a view that is handling the gesture, do normal event dispatch.
//            if (intercepted || mFirstTouchTarget != null) {
//                ev.setTargetAccessibilityFocus(false);
//            }
//
//            // Check for cancelation.
//            final boolean canceled = resetCancelNextUpFlag(this)
//                    || actionMasked == MotionEvent.ACTION_CANCEL;
//
//            // Update list of touch targets for pointer down, if needed.
//            final boolean split = (mGroupFlags & FLAG_SPLIT_MOTION_EVENTS) != 0;
//            TouchTarget newTouchTarget = null;
//            boolean alreadyDispatchedToNewTouchTarget = false;
//            //如果未取消也未拦截。
//            if (!canceled && !intercepted) {
//
//                // If the event is targeting accessiiblity focus we give it to the
//                // view that has accessibility focus and if it does not handle it
//                // we clear the flag and dispatch the event to all children as usual.
//                // We are looking up the accessibility focused host to avoid keeping
//                // state since these events are very rare.
//                View childWithAccessibilityFocus = ev.isTargetAccessibilityFocus()
//                        ? findChildWithAccessibilityFocus() : null;
//
//                if (actionMasked == MotionEvent.ACTION_DOWN
//                        || (split && actionMasked == MotionEvent.ACTION_POINTER_DOWN)
//                        || actionMasked == MotionEvent.ACTION_HOVER_MOVE) {
//                    final int actionIndex = ev.getActionIndex(); // always 0 for down
//                    final int idBitsToAssign = split ? 1 << ev.getPointerId(actionIndex)
//                            : TouchTarget.ALL_POINTER_IDS;
//
//                    // Clean up earlier touch targets for this pointer id in case they
//                    // have become out of sync.
//                    removePointersFromTouchTargets(idBitsToAssign);
//
//                    final int childrenCount = mChildrenCount;
//                    if (newTouchTarget == null && childrenCount != 0) {
//                        final float x = ev.getX(actionIndex);
//                        final float y = ev.getY(actionIndex);
//                        // Find a child that can receive the event.
//                        // Scan children from front to back.
//                        final ArrayList<View> preorderedList = buildTouchDispatchChildList();
//                        final boolean customOrder = preorderedList == null
//                                && isChildrenDrawingOrderEnabled();
//                        final View[] children = mChildren;
//                        for (int i = childrenCount - 1; i >= 0; i--) {
//                            final int childIndex = getAndVerifyPreorderedIndex(
//                                    childrenCount, i, customOrder);
//                            final View child = getAndVerifyPreorderedView(
//                                    preorderedList, children, childIndex);
//
//                            // If there is a view that has accessibility focus we want it
//                            // to get the event first and if not handled we will perform a
//                            // normal dispatch. We may do a double iteration but this is
//                            // safer given the timeframe.
//                            if (childWithAccessibilityFocus != null) {
//                                if (childWithAccessibilityFocus != child) {
//                                    continue;
//                                }
//                                childWithAccessibilityFocus = null;
//                                i = childrenCount - 1;
//                            }
//
//                            if (!canViewReceivePointerEvents(child)
//                                    || !isTransformedTouchPointInView(x, y, child, null)) {
//                                ev.setTargetAccessibilityFocus(false);
//                                continue;
//                            }
//
//                            newTouchTarget = getTouchTarget(child);
//                            if (newTouchTarget != null) {
//                                // Child is already receiving touch within its bounds.
//                                // Give it the new pointer in addition to the ones it is handling.
//                                newTouchTarget.pointerIdBits |= idBitsToAssign;
//                                break;
//                            }
//
//                            resetCancelNextUpFlag(child);
//                            if (dispatchTransformedTouchEvent(ev, false, child, idBitsToAssign)) {
//                                // Child wants to receive touch within its bounds.
//                                mLastTouchDownTime = ev.getDownTime();
//                                if (preorderedList != null) {
//                                    // childIndex points into presorted list, find original index
//                                    for (int j = 0; j < childrenCount; j++) {
//                                        if (children[childIndex] == mChildren[j]) {
//                                            mLastTouchDownIndex = j;
//                                            break;
//                                        }
//                                    }
//                                } else {
//                                    mLastTouchDownIndex = childIndex;
//                                }
//                                mLastTouchDownX = ev.getX();
//                                mLastTouchDownY = ev.getY();
//                                newTouchTarget = addTouchTarget(child, idBitsToAssign);
//                                alreadyDispatchedToNewTouchTarget = true;
//                                break;
//                            }
//
//                            // The accessibility focus didn't handle the event, so clear
//                            // the flag and do a normal dispatch to all children.
//                            ev.setTargetAccessibilityFocus(false);
//                        }
//                        if (preorderedList != null) preorderedList.clear();
//                    }
//
//                    if (newTouchTarget == null && mFirstTouchTarget != null) {
//                        // Did not find a child to receive the event.
//                        // Assign the pointer to the least recently added target.
//                        newTouchTarget = mFirstTouchTarget;
//                        while (newTouchTarget.next != null) {
//                            newTouchTarget = newTouchTarget.next;
//                        }
//                        newTouchTarget.pointerIdBits |= idBitsToAssign;
//                    }
//                }
//            }
//
//            // Dispatch to touch targets.
//            if (mFirstTouchTarget == null) {
//                // No touch targets so treat this as an ordinary view.
//                handled = dispatchTransformedTouchEvent(ev, canceled, null,
//                        TouchTarget.ALL_POINTER_IDS);
//            } else {
//                // Dispatch to touch targets, excluding the new touch target if we already
//                // dispatched to it.  Cancel touch targets if necessary.
//                TouchTarget predecessor = null;
//                TouchTarget target = mFirstTouchTarget;
//                while (target != null) {
//                    final TouchTarget next = target.next;
//                    if (alreadyDispatchedToNewTouchTarget && target == newTouchTarget) {
//                        handled = true;
//                    } else {
//                        final boolean cancelChild = resetCancelNextUpFlag(target.child)
//                                || intercepted;
//                        if (dispatchTransformedTouchEvent(ev, cancelChild,
//                                target.child, target.pointerIdBits)) {
//                            handled = true;
//                        }
//                        if (cancelChild) {
//                            if (predecessor == null) {
//                                mFirstTouchTarget = next;
//                            } else {
//                                predecessor.next = next;
//                            }
//                            target.recycle();
//                            target = next;
//                            continue;
//                        }
//                    }
//                    predecessor = target;
//                    target = next;
//                }
//            }
//
//            // Update list of touch targets for pointer up or cancel, if needed.
//            if (canceled
//                    || actionMasked == MotionEvent.ACTION_UP
//                    || actionMasked == MotionEvent.ACTION_HOVER_MOVE) {
//                resetTouchState();
//            } else if (split && actionMasked == MotionEvent.ACTION_POINTER_UP) {
//                final int actionIndex = ev.getActionIndex();
//                final int idBitsToRemove = 1 << ev.getPointerId(actionIndex);
//                removePointersFromTouchTargets(idBitsToRemove);
//            }
//        }
//
//        if (!handled && mInputEventConsistencyVerifier != null) {
//            mInputEventConsistencyVerifier.onUnhandledEvent(ev, 1);
//        }
//        return handled;
//    }
//
//    @Override
//    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//        if (disallowIntercept == ((mGroupFlags & FLAG_DISALLOW_INTERCEPT) != 0)) {
//            // We're already in this state, assume our ancestors are too
//            return;
//        }
//
//        if (disallowIntercept) {
//            mGroupFlags |= FLAG_DISALLOW_INTERCEPT;
//        } else {
//            mGroupFlags &= ~FLAG_DISALLOW_INTERCEPT;
//        }
//
//        // Pass it up to our parent
//        if (mParent != null) {
//            mParent.requestDisallowInterceptTouchEvent(disallowIntercept);
//        }
//    }
//
//    /**
//     * Implement this method to intercept all touch screen motion events.  This
//     * allows you to watch events as they are dispatched to your children, and
//     * take ownership of the current gesture at any point.
//     * <p>
//     * 实现这个方法可以拦截所有的屏幕触摸事件。也就是说你可以查看所有分发给子view的事件，
//     * 和任何位置的当前手势操作。
//     * <p>
//     * <p>Using this function takes some care, as it has a fairly complicated
//     * interaction with {@link View#onTouchEvent(MotionEvent)
//     * View.onTouchEvent(MotionEvent)}, and using it requires implementing
//     * that method as well as this one in the correct way.  Events will be
//     * received in the following order:
//     * <p>
//     * 使用这个方法需要格外留意，因为它与view.onTouchEvent(ev)的交互相当复杂。
//     * 使用这个方法时不仅要保证它的正确实现，还需要实现view.onTouchEvent(ev)方法.
//     * 事件接收的步骤如下：
//     * ①你将会在这里接收到down事件。
//     * ②down可能被两个地方处理，一个是子view，另一个是你自身的onTouchEvent()方法。
//     * 这意味着你应该实现onTouchEvent()方法并且返回true，这样做的话你就可以继续查看
//     * 其余的手势，而不是让父view去处理这个事件。
//     * 实现onTouchEvent()方法并且返回true,在onInterceptTouchEvent()方法中你将不会接
//     * 收到任何的后续事件，并且所有的touch事件（像往常一样）一定会传递给onTouchEvent()方法。
//     * ③如果这个方法返回值为false，所有接下来的事件都会首先传递到这里，然后再传递给
//     * 目标的onTouchEvent()方法。
//     * ④如果这里返回值为true，你将不会受到任何后续事件。目标view将会接收到相同的事件和额外的cancel事件，
//     * 所有的更多的事件将会传递给你的onTouchEvent()方法，这里将不在出现。
//     * <p>
//     * <p>
//     * <ol>
//     * <li> You will receive the down event here.
//     * <li> The down event will be handled either by a child of this view
//     * group, or given to your own onTouchEvent() method to handle; this means
//     * you should implement onTouchEvent() to return true, so you will
//     * continue to see the rest of the gesture (instead of looking for
//     * a parent view to handle it).  Also, by returning true from
//     * onTouchEvent(), you will not receive any following
//     * events in onInterceptTouchEvent() and all touch processing must
//     * happen in onTouchEvent() like normal.
//     * <li> For as long as you return false from this function, each following
//     * event (up to and including the final up) will be delivered first here
//     * and then to the target's onTouchEvent().
//     * <li> If you return true from here, you will not receive any
//     * following events: the target view will receive the same event but
//     * with the action {@link MotionEvent#ACTION_CANCEL}, and all further
//     * events will be delivered to your onTouchEvent() method and no longer
//     * appear here.
//     * </ol>
//     *
//     * @param ev The motion event being dispatched down the hierarchy.
//     * @return Return true to steal motion events from the children and have
//     * them dispatched to this ViewGroup through onTouchEvent().
//     * The current target will receive an ACTION_CANCEL event, and no further
//     * messages will be delivered here.
//     * 返回true的话就会从子view中偷取事件，并将它们分发给viewGroup的onTouchEvent()方法。
//     * 当前方法将会受到一个cancel事件，接下来不会受到任何后续消息了。
//     *
//     */
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (ev.isFromSource(InputDevice.SOURCE_MOUSE)
//                && ev.getAction() == MotionEvent.ACTION_DOWN
//                && ev.isButtonPressed(MotionEvent.BUTTON_PRIMARY)
//                && isOnScrollbarThumb(ev.getX(), ev.getY())) {
//            return true;
//        }
//        return false;
//    }

}
