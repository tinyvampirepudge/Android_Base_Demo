#Android事件分发解析（Activity，fragment，View，ViewGroup）
&emsp;&emsp;事件分发是每一个Android开发者必学必会的知识点，常见的控件的点击、长按事件要用到它，解决滑动冲突要用到它，较复杂的自定义控件里面它也很常见。另外，大家常说的事件分发机制，具体是如何由Activity传递到view和viewgroup上的，也想在这里说明白。这篇文章的目的是记录下自己了解的事件分发的相关知识。

##事件分发的相关概念
1. 事件分发的对象
&emsp;&emsp;事件分发的对象就是MotionEvent。
&emsp;&emsp;Touch事件相关细节（发生触摸的位置、时间、历史记录、手势动作等）被封装成MotionEvent对象。

###MotionEvent源码中的解释：
```
/** Object used to report movement (mouse, pen, finger, trackball) events.
 * Motion events may hold either absolute or relative movements and other data,
 * depending on the type of device.
 */

/** Motion events describe movements in terms of an action code and a set of axis values.
 * The action code specifies the state change that occurred such as a pointer going
 * down or up.  The axis values describe the position and other movement properties.
 */
```
&emsp;&emsp;在手指接触屏幕后产生的一系列事件中，典型的事件类型有以下几种：
. MotionEvent.ACTION_DOWN——手指刚接触屏幕；
. MotionEvent.ACTION_MOVE——手指在屏幕上移动；
. MotionEvent.ACTION_CANCEL——非人为原因结束本次事件；
. MotionEvent.ACTION_UP——手指从屏幕上松开的一瞬间。
&emsp;&emsp;正常情况下，一次手指触摸屏幕的行为会触发一系列点击事件，从手指接触屏幕至手指离开屏幕，这个过程产生的一系列事件，我们称之为事件序列。
&emsp;&emsp;同一个事件序列是指从手指接触屏幕的那一刻起，到手指离开屏幕的那一刻结束，在这个过程中所产生的一系列事件，这个事件序列以down事件开始，中间含有数量不定的move事件，最终以up事件结束。如下图所示：
![Markdown](http://i4.fuimg.com/588910/30e8a902fd3dfd42.png)

2. 事件分发的本质.
&emsp;&emsp;所谓事件分发，其实就是对MotionEvent事件的分发过程，即当一个MotionEvent产生了以后，系统需要把这个事件传递给一个具体的View，这个传递的过程就是分发过程。

3. 事件分发涉及了哪些主要的类？
> 一个点击事件产生后，传递顺序是：Activity（Window） -> ViewGroup -> View.
如下图：
![Markdown](http://i4.fuimg.com/588910/37a3931392414fb2.png)

4. 事件分发过程主要涉及哪些方法？
&emsp;&emsp;点击事件的分发过程由三个很重要的方法来共同完成：dispatchTouchEvent、onInterceptTouchEvent、onTouchEvent方法。
View#dispatchTouchEvent:
```
public boolean dispatchTouchEvent(MotionEvent event) {
	...
}
```
&emsp;&emsp;用来进行事件的分发。如果事件能够传递给当前View，那么此方法一定会被调用，返回结果受当前View的onTouchEvent和下级dispatchTouchEvent方法的影响，表示是否消耗当前时间。

ViewGroup#onInterceptTouchEvent
```
public boolean onInterceptTouchEvent(MotionEvent ev) {
	...
}
```
&emsp;&emsp;在dispatchTouchEvent方法里面调用，用来判断是否拦截某个事件，如果当前View拦截了某个事件，那么在同一个事件序列当中，此方法不会被再次调用，返回结果表示是否拦截当前事件。

View#onTouchEvent
```
public boolean onTouchEvent(MotionEvent event) {
	...
}
```
&emsp;&emsp;在dispatchTouchEvent方法中调用，用来处理点击事件，返回结果表示是否消耗当前事件，如果不消耗，则在同一个事件序列中，当前View无法再次接受到事件。

上述三个方法之间的关系可以用下面的伪代码表示：
```
public boolean dispatchTouchEvent(MotionEvent ev){
    boolean consume = false;
    if(onInterceptTouchEvent(ev)){
        consume = onTouchEvent(ev);
    }else{
        consume = child.dispatchTouchEvent(ev);
    }
    return consume;
}
```
&emsp;&emsp;通过上面的伪代码，我们大致可以了解点击事件的额传递规则：对于一个根ViewGroup来说，点击事件产生后，首先会传递给它，这时它的ViewGroup#DispatchTouchEvent(ev)方法就会被调用，接下来会执行ViewGroup#onInterceptTouchEvent(ev)方法，如果onInterceptTouchEvent(ev)方法返回true，接着这个事件就会交给当前ViewGroup的onTouchEvent(ev)方法处理；如果为false，就表示它不拦截当前事件，这时当前事件就会继续传递给它的子元素，接着子元素的dispatchTouchEvent方法就会被调用，如此反复直到事件被最终处理。

##相关结论、注意事项和小技巧。
&emsp;&emsp;关于事件传递的机制，这里给出一些结论，根据这些结论可以更好的理解整个传递机制，如下所示。接下来会在源码分析的过程中对这些结论一一进行验证。
（1）正常情况下，一个事件序列只能被一个View拦截且消耗。这一条的原因可以参考(2),因为一旦一个元素拦截了此事件，那么同一个时间序列内的所有时间都会直接交给它处理，因此同一个时间序列中的事件不能分别由两个View同时处理，但是通过特殊手段可以做到，比如一个View将本该自己处理的事件通过onTouchEvent强行传递给其他View处理。
（2）某个View一旦决定拦截，那么这一个事件序列都只能由它来处理（如果事件序列能够传递给它的话），并且它的onInterceptTouchEvent不会再被调用。这条也很好理解，就是说当一个View决定拦截一个事件后，那么系统会把同一个事件序列内的其他方法都直接交给它来处理，因此就不会再调用这个View的onTouchEvent去询问它是否要拦截了。
（3）某个View一旦开始处理事件，如果它不消耗ACTION_DOWN事件（onTouchEvent返回了false），那么同一事件序列中的其他事件都不会在交给它来处理，并且事件将重新交由它的父元素去处理，即父元素的onTouchEvent会被调用。意思就是事件一旦交给一个View处理，那么它就必须消耗掉，否则同一事件序列中剩下的事件就不再交给它来处理了，这就好比上级交给程序员一件事，如果这件事没有处理好，短期内上级就不敢再把事情交给这个程序员做了，二者是类似的道理。
（4）如果View不消耗除了ACTION_DOWN以外的其他事件，那么这个点击事件会消失，此时父元素的onTouchEvent并不会被调用，并且当前View可以持续收到后续的事件，最终这些消失的点击事件会传递给Activity处理。
（5）ViewGroup默认不拦截任何事件。Android源码中ViewGroup的onInterceptTouchEvent方法默认返回false。
（6）View没有onInterceptTouchEvent方法，一旦有点击事件传递给它，那么它的onTouchEvent方法就会被调用。
（7）View的onTouchEvent默认都会消耗事件（返回true），除非它是不可点击的(clickable和longClickable同时为false)。View的longClickable属性默认都为false，clickable属性要分情况，比如Button的cliclable属性默认为true，而TextView的属性默认为false。
（8）View的enable属性不影响onTouchEvent的默认返回值。哪怕一个View是disable状态的，只要它的clickable或者longClickable有一个为true，那么它的onTouchEvent就返回true。
（9）onCLick会发生的前提是当前View是可点击的，并且它收到了down和up事件。
（10）事件传递过程是由外向内的，即事件总是先传递给父元素，然后再由父元素分发给子View，通过requestDisallowInterceptTouchEvent方法可以再子元素中干预父元素的时间分发过程，但是ACTION_DOWN事件除外。

##事件分发的源码分析
&emsp;&emsp;为了便于理解，源码分析的步骤由简到难，次序分别是View、ViewGroup、Activity的事件分发。
View的事件分发：
View#dispatchTouchEvent(ev):
```
/**
     * Pass the touch screen motion event down to the target view, or this
     * view if it is the target.
     *
     * @param event The motion event to be dispatched.
     * @return True if the event was handled by the view, false otherwise.
     */
    public boolean dispatchTouchEvent(MotionEvent event) {
        ...

        boolean result = false;

       	...

        if (onFilterTouchEventForSecurity(event)) {
            ...
            //noinspection SimplifiableIfStatement
            ListenerInfo li = mListenerInfo;
            if (li != null && li.mOnTouchListener != null
                    && (mViewFlags & ENABLED_MASK) == ENABLED
                    && li.mOnTouchListener.onTouch(this, event)) {
                result = true;
            }

            if (!result && onTouchEvent(event)) {
                result = true;
            }
        }

       	...
        return result;
    }
```
&emsp;&emsp;从这段代码可以看出View对点击事件的处理过程，首先会判定有没有设置onTouchEventListener，如果onTouchListener不为空并且onTouchListener.onTouch(ev)方法的返回值为true，那么onTouchEvent(ev)方法就不会被调用。可见onTouchListener的优先级高于onTouchEnent方法。
&emsp;&emsp;接下来在分析onTouchEvent的实现·1 ：
View#onTouchEvent:
&emsp;&emsp;首先看下View处于不可用状态下的的处理过程，如果View是可点击的或者可长按的，此时点击事件会被消耗掉。这段代码验证了上边的（8）。
```
if ((viewFlags & ENABLED_MASK) == DISABLED) {
    if (action == MotionEvent.ACTION_UP && (mPrivateFlags & PFLAG_PRESSED) != 0) {
        setPressed(false);
    }
    // A disabled view that is clickable still consumes the touch
    // events, it just doesn't respond to them.
    return (((viewFlags & CLICKABLE) == CLICKABLE
            || (viewFlags & LONG_CLICKABLE) == LONG_CLICKABLE)
            || (viewFlags & CONTEXT_CLICKABLE) == CONTEXT_CLICKABLE);
}
```
接着，如果View设有代理，那么还会执行TouchDelegate的onTouchEvent方法，它的用法跟OnTouchListener类似，这里不做深究。
```
  if (mTouchDelegate != null) {
            if (mTouchDelegate.onTouchEvent(event)) {
                return true;
            }
        }
```
&emsp;&emsp;如果我们的View可以点击或者可以长按，注意if的范围，最终一定return true;这证实了（7）。
```
if (((viewFlags & CLICKABLE) == CLICKABLE ||
                (viewFlags & LONG_CLICKABLE) == LONG_CLICKABLE) ||
                (viewFlags & CONTEXT_CLICKABLE) == CONTEXT_CLICKABLE) {
            switch (action) {
                case MotionEvent.ACTION_UP:
                    ...
                    break;

                case MotionEvent.ACTION_DOWN:
                    ...
                    break;

                case MotionEvent.ACTION_CANCEL:
                    ...
                    break;

                case MotionEvent.ACTION_MOVE:
                    ...
                    break;
            }

            return true;
        }
```
&emsp;&emsp;接下来看下onTouchEvent中对点击事件的具体处理，如下所示：我们从ACTION_DOWN, ACTION_MOVE, ACTION_UP的顺序看起。

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

ViewGroup的事件分发：
Activity的事件分发：




> 参考文档和书籍：
> http://blog.csdn.net/carson_ho/article/details/54136311
> 《Android开发艺术探索》 任玉刚
> http://blog.csdn.net/lmj623565791/article/details/39102591
> http://blog.csdn.net/lmj623565791/article/details/38960443