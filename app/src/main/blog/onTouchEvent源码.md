ACTION_DOWN:
###View#onTouchEvent:ACTION_DOWN

```
                case MotionEvent.ACTION_DOWN:
                    mHasPerformedLongPress = false;

                    if (performButtonActionOnTouchDown(event)) {
                        break;
                    }

                    // Walk up the hierarchy to determine if we're inside a scrolling container.
                    boolean isInScrollingContainer = isInScrollingContainer();

                    // For views inside a scrolling container, delay the pressed feedback for
                    // a short period in case this is a scroll.
                    if (isInScrollingContainer) {
                        mPrivateFlags |= PFLAG_PREPRESSED;
                        if (mPendingCheckForTap == null) {
                            mPendingCheckForTap = new CheckForTap();
                        }
                        mPendingCheckForTap.x = event.getX();
                        mPendingCheckForTap.y = event.getY();
                        postDelayed(mPendingCheckForTap, ViewConfiguration.getTapTimeout());
                    } else {
                        // Not inside a scrolling container, so show the feedback right away
                        setPressed(true, x, y);
                        checkForLongClick(0, x, y);
                    }
                    break;
```
1、首先，将mHasPerformedLongPress的值置为false，表示长按事件还未触发；
2、接着， boolean isInScrollingContainer = isInScrollingContainer()，判断当前view是否处于滑动状态；
3、如果处于滑动状态，就先给mPrivateFlags设置一个PREPRESSED的标识，然后发送一个延迟为ViewConfiguration.getTapTimeout()的延迟消息，到达延时时间后会执行CheckForTap()里面的run方法；
note:
①这里的ViewConfiguration.getTapTimeout()的值为100ms,
```
private static final int TAP_TIMEOUT = 100;
```
②View#checkForTap

```
private final class CheckForTap implements Runnable {
        public float x;
        public float y;

        @Override
        public void run() {
            mPrivateFlags &= ~PFLAG_PREPRESSED;
            setPressed(true, x, y);
            checkForLongClick(ViewConfiguration.getTapTimeout(), x, y);
        }
    }
    
   
private void setPressed(boolean pressed, float x, float y) {
        if (pressed) {
            drawableHotspotChanged(x, y);
        }

        setPressed(pressed);
    }
    
public void setPressed(boolean pressed) {
        final boolean needsRefresh = pressed != ((mPrivateFlags & PFLAG_PRESSED) == PFLAG_PRESSED);

        if (pressed) {
            mPrivateFlags |= PFLAG_PRESSED;
        } else {
            mPrivateFlags &= ~PFLAG_PRESSED;
        }

        if (needsRefresh) {
            refreshDrawableState();
        }
        dispatchSetPressed(pressed);
    }
    
```
在CheckForTap的run方法里面，首先给mPrivateFlags清除掉PREPRESSED的标识；然后调用setPressed(true)方法，给mPrivateFlags添加PFLAG_PRESSED标记；接着调用checkForLongClick(ViewConfiguration.getTapTimeout(), x, y)方法。
③View#checkForLongClick

```
private void checkForLongClick(int delayOffset, float x, float y) {
        if ((mViewFlags & LONG_CLICKABLE) == LONG_CLICKABLE) {
            mHasPerformedLongPress = false;

            if (mPendingCheckForLongPress == null) {
                mPendingCheckForLongPress = new CheckForLongPress();
            }
            mPendingCheckForLongPress.setAnchor(x, y);
            mPendingCheckForLongPress.rememberWindowAttachCount();
            postDelayed(mPendingCheckForLongPress,
                    ViewConfiguration.getLongPressTimeout() - delayOffset);
        }
    }
```
&emsp;&emsp;在checkForLongClick方法中，如果当前view是可长按的（默认都为true），先将mHasPerformedLongPress置为false，最后发送一个延时为ViewConfiguration.getLongPressTimeout() - delayOffset的消息，执行CheckForLongPress里面的run方法。这里的ViewConfiguration.getLongPressTimeout()的值为500ms.

④View#CheckForLongPress

```
private final class CheckForLongPress implements Runnable {
        private int mOriginalWindowAttachCount;
        private float mX;
        private float mY;

        @Override
        public void run() {
            if (isPressed() && (mParent != null)
                    && mOriginalWindowAttachCount == mWindowAttachCount) {
                if (performLongClick(mX, mY)) {
                    mHasPerformedLongPress = true;
                }
            }
        }

        public void setAnchor(float x, float y) {
            mX = x;
            mY = y;
        }

        public void rememberWindowAttachCount() {
            mOriginalWindowAttachCount = mWindowAttachCount;
        }
    }
    
    public boolean isPressed() {
        return (mPrivateFlags & PFLAG_PRESSED) == PFLAG_PRESSED;
    }
```
&emsp;&emsp;在CheckForLongPress的run方法中，会先判断mPrivateFlags是否有PFLAG_PRESSED标记，如果有，就执行performLongClick方法，也就是我们通过view.setOnLongClickListener方法设置的长按事件的监听，执行的实质是调用我们设置的mOnLongClickListener.onLongClick方法。如果这个onLongClick方法的返回值为true，就将mHasPerformedLongPress的值设为true。

⑤View中长按事件的具体触发：

```
public boolean performLongClick(float x, float y) {
        mLongClickX = x;
        mLongClickY = y;
        final boolean handled = performLongClick();
        mLongClickX = Float.NaN;
        mLongClickY = Float.NaN;
        return handled;
    }
    
    public boolean performLongClick() {
        return performLongClickInternal(mLongClickX, mLongClickY);
    }
    
    private boolean performLongClickInternal(float x, float y) {
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_LONG_CLICKED);

        boolean handled = false;
        final ListenerInfo li = mListenerInfo;
        if (li != null && li.mOnLongClickListener != null) {
            handled = li.mOnLongClickListener.onLongClick(View.this);
        }
        if (!handled) {
            final boolean isAnchored = !Float.isNaN(x) && !Float.isNaN(y);
            handled = isAnchored ? showContextMenu(x, y) : showContextMenu();
        }
        if (handled) {
            performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
        }
        return handled;
    }

```
&emsp;&emsp;具体的调用时在performLongClickInternal中的handled = li.mOnLongClickListener.onLongClick(View.this)这儿执行的。
4、如果当前View不是滑动状态，那么直接会先调用setPressed(true, x, y)方法，给mPrivateFlags设置PFLAG_PRESSED标记，再调用checkForLongClick(0, x, y)方法，立即发送一个500ms的延时消息，延时消息的内容跟滑动状态下发送的一致，都是会先判断mPrivateFlags是否有PFLAG_PRESSED标记，如果有，就执行performLongClick方法。

好了，ACTION_DOWN事件就分析完了，稍微总结下，
①先设置mHasPerformedLongPress的值为false。
②判断当前View是否处于滚动状态，如果是滚动状态，就先给mPrivateFlags设置PFLAG_PREPRESSED标记，然后发送一个延时为100ms的CheckForTap消息，在CheckForTap的run方法中，先清除掉mPrivateFlags的PFLAG_PREPRESSED标记，再给mPrivateFlags设置上PFLAG_PRESSED标记，接着发送一个(500-100)ms的延时消息CheckForLongPress，CheckForLongPress的run方法中会调用mOnLongClickListener.onLongClick(View.this)方法，如果mOnLongClickListener.onLongClick的返回值为true，则给mHasPerformedLongPress赋值为true。
③如果不是滚动状态，就直接发一个延时500ms的CheckForLongPress消息，调用过程同上方一致。
④总体来说，在ACTION_DOWN中会发送一个500ms的延时消息CheckForLongPress，用来检测长按事件；在CheckForLongPress的run方法中，最后会执行mOnLongClickListener.onLongClick(View.this)方法，即我们设置的长按监听。

###View#onTouchEvent:ACTION_MOVE
&emsp;&emsp;接下来分析下ACTION_MOVE的源码：

```
				case MotionEvent.ACTION_MOVE:
                    drawableHotspotChanged(x, y);

                    // Be lenient about moving outside of buttons
                    if (!pointInView(x, y, mTouchSlop)) {
                        // Outside button
                        removeTapCallback();
                        if ((mPrivateFlags & PFLAG_PRESSED) != 0) {
                            // Remove any future long press/tap checks
                            removeLongPressCallback();

                            setPressed(false);
                        }
                    }
                    break;
```
在move事件中，会先判断是否移出了当前View的范围，如果没有移出，就什么也不做；如果移出了，先去掉mPrivateFlags的PFLAG_PREPRESSED标签，并移除掉down事件中设置的CheckForTap消息，也就是在滚动状态下发出的延时100ms的消息；接着判断当前view的mPrivateFlags是否包含PFLAG_PRESSED标签，如果有，就移除掉down中发送的延时消息CheckForLongPress，接着给 mPrivateFlags去掉PFLAG_PRESSED标记。
总体来说，如果View在move的过程中移出了当前View的范围，那么就移除掉它的长按事件的消息，就不会执行它的长按事件了。

###View#onTouchEvent:ACTION_UP
```
				case MotionEvent.ACTION_UP:
                    boolean prepressed = (mPrivateFlags & PFLAG_PREPRESSED) != 0;
                    if ((mPrivateFlags & PFLAG_PRESSED) != 0 || prepressed) {
                        // take focus if we don't have it already and we should in
                        // touch mode.
                        boolean focusTaken = false;
                        if (isFocusable() && isFocusableInTouchMode() && !isFocused()) {
                            focusTaken = requestFocus();
                        }

                        if (prepressed) {
                            // The button is being released before we actually
                            // showed it as pressed.  Make it show the pressed
                            // state now (before scheduling the click) to ensure
                            // the user sees it.
                            setPressed(true, x, y);
                       }

                        if (!mHasPerformedLongPress && !mIgnoreNextUpEvent) {
                            // This is a tap, so remove the longpress check
                            removeLongPressCallback();

                            // Only perform take click actions if we were in the pressed state
                            if (!focusTaken) {
                                // Use a Runnable and post this rather than calling
                                // performClick directly. This lets other visual state
                                // of the view update before click actions start.
                                if (mPerformClick == null) {
                                    mPerformClick = new PerformClick();
                                }
                                if (!post(mPerformClick)) {
                                    performClick();
                                }
                            }
                        }

                        if (mUnsetPressedState == null) {
                            mUnsetPressedState = new UnsetPressedState();
                        }

                        if (prepressed) {
                            postDelayed(mUnsetPressedState,
                                    ViewConfiguration.getPressedStateDuration());
                        } else if (!post(mUnsetPressedState)) {
                            // If the post failed, unpress right now
                            mUnsetPressedState.run();
                        }

                        removeTapCallback();
                    }
                    mIgnoreNextUpEvent = false;
                    break;
```
接着看下up的源码:
①先判断mPrivateFlags是否包含PFLAG_PREPRESSED标记，这个如果此时还有这个标记，就说明down事件触发的时候是在滚动状态下，并且距离触发时间没有超过100ms.
②接下来，判断mPrivateFlags是否包含PFLAG_PRESSED或者PFLAG_PREPRESSED标签。需要注意的是，PFLAG_PREPRESSED标签是在down的滚动状态下设置的，在它的100ms毫秒的延时消息执行的时候这个标签会去除掉，重新添加上PFLAG_PRESSED标签；另外，如果是非滚动状态下，会直接设置PFLAG_PRESSED标签的。在move中如果没有移出View范围外，这两个标签就不会移除掉，所以正常状态下这两个标记至少有一个存在。当然，如果在move过程中已经移出了当前View的范围，这两个标记就都不存在了，这里我们先考虑没有移出当前View范围的情况。
③如果mPrivateFlags包含PFLAG_PRESSED标签，就给mPrivateFlags设置PFLAG_PRESSED标签。
④判断的mHasPerformedLongPress的值是否为false。在一次事件序列中，mHasPerformedLongPress的只有在mOnLongClickListener.onLongClick(view)的返回值为true的情况下才为true，在ACTION_DOWN中还会重新赋值为false。mHasPerformedLongPress的值表示mOnLongClickListener存在，并且mOnLongClickListener.onLongClick的返回值为true。
⑤如果mHasPerformedLongPress为false，先移出掉CheckForLongPress，也就是不执行长按事件的回调。接下来执行的就是我们的重点，执行点击事件，PerformClick方法。
⑥View#performClick

```
public boolean performClick() {
        final boolean result;
        final ListenerInfo li = mListenerInfo;
        if (li != null && li.mOnClickListener != null) {
            playSoundEffect(SoundEffectConstants.CLICK);
            li.mOnClickListener.onClick(this);
            result = true;
        } else {
            result = false;
        }

        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_CLICKED);
        return result;
    }
```
如果mOnClickListener不为空，就执行它的onClick方法。
⑦最后，去掉mPrivateFlags的PFLAG_PREPRESSED标签，并移除掉down事件中设置的CheckForTap消息。

至此，View#onTouch方法中的事件分发分析完了，总结下：
①整个View的事件转发流程是：View.dispatchEvent->View.setOnTouchListener->View.onTouchEvent。
②在dispatchTouchEvent中会进行OnTouchListener的判断，如果OnTouchListener不为null且返回true，则表示事件被消费，onTouchEvent不会被执行；否则执行onTouchEvent。
③在onTouchEvent方法中，会先判断mTouchDelegate是否为空，如果mTouchDelegate不为空并且它的onTouchEvent方法的返回值为true，就不会执行后边的down，move，up等逻辑了。TouchDelegate的用法主要是当前view的点击区域，使用很方便，具体用法可另行Google。
④长按事件是在down中触发的，从触发到执行的延时为500ms。触发了长按事件以后，如果在500ms内，手指移动出了view的区域或者触发了up事件，则长按监听会被移除掉，不会被触发。
⑤如果mOnLongClickListener不为空并且它的onLongClick方法的返回值为true，则在up事件中就不会触发onClick操作，点击事件也就不会触发。
⑥View常见的几个监听器的执行顺序是：
onTouchListener --> TouchDelegate --> onLongClickListener --> onClickListener.
除了最后的onClickListener，前面三个监听器的执行逻辑都是一样的，如果不为空并且监听器的执行体的返回值为true，就表示事件已经被消费，不再向下执行，后边的监听器就不会调用了。