package com.github.xiaogegechen.library.impl;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.github.xiaogegechen.library.AttrsHandler;
import com.github.xiaogegechen.library.model.Attr;

import java.util.List;

/** 支持替换View的属性如下
 *  * @attr ref android.R.styleable#View_accessibilityHeading
 *  * @attr ref android.R.styleable#View_alpha
 *  * @attr ref android.R.styleable#View_background
 *  * @attr ref android.R.styleable#View_clickable
 *  * @attr ref android.R.styleable#View_contentDescription
 *  * @attr ref android.R.styleable#View_duplicateParentState
 *  * @attr ref android.R.styleable#View_fadingEdgeLength
 *  * @attr ref android.R.styleable#View_filterTouchesWhenObscured
 *  * @attr ref android.R.styleable#View_fitsSystemWindows
 *  * @attr ref android.R.styleable#View_isScrollContainer
 *  * @attr ref android.R.styleable#View_focusable
 *  * @attr ref android.R.styleable#View_focusableInTouchMode
 *  * @attr ref android.R.styleable#View_focusedByDefault
 *  * @attr ref android.R.styleable#View_hapticFeedbackEnabled
 *  * @attr ref android.R.styleable#View_keepScreenOn
 *  * @attr ref android.R.styleable#View_keyboardNavigationCluster
 *  * @attr ref android.R.styleable#View_longClickable
 *  * @attr ref android.R.styleable#View_minHeight
 *  * @attr ref android.R.styleable#View_minWidth
 *  * @attr ref android.R.styleable#View_outlineSpotShadowColor
 *  * @attr ref android.R.styleable#View_outlineAmbientShadowColor
 *  * @attr ref android.R.styleable#View_padding
 *  * @attr ref android.R.styleable#View_paddingHorizontal
 *  * @attr ref android.R.styleable#View_paddingVertical
 *  * @attr ref android.R.styleable#View_paddingBottom
 *  * @attr ref android.R.styleable#View_paddingLeft
 *  * @attr ref android.R.styleable#View_paddingRight
 *  * @attr ref android.R.styleable#View_paddingTop
 *  * @attr ref android.R.styleable#View_paddingStart
 *  * @attr ref android.R.styleable#View_paddingEnd
 *  * @attr ref android.R.styleable#View_saveEnabled
 *  * @attr ref android.R.styleable#View_rotation
 *  * @attr ref android.R.styleable#View_rotationX
 *  * @attr ref android.R.styleable#View_rotationY
 *  * @attr ref android.R.styleable#View_scaleX
 *  * @attr ref android.R.styleable#View_scaleY
 *  * @attr ref android.R.styleable#View_scrollX
 *  * @attr ref android.R.styleable#View_scrollY
 *  * @attr ref android.R.styleable#View_scrollbarSize
 *  * @attr ref android.R.styleable#View_scrollbars
 *  * @attr ref android.R.styleable#View_scrollbarDefaultDelayBeforeFade
 *  * @attr ref android.R.styleable#View_scrollbarFadeDuration
 *  * @attr ref android.R.styleable#View_stateListAnimator
 *  * @attr ref android.R.styleable#View_soundEffectsEnabled
 *  * @attr ref android.R.styleable#View_translationX
 *  * @attr ref android.R.styleable#View_translationY
 *  * @attr ref android.R.styleable#View_translationZ
 */
public class ViewHandler extends AttrsHandler {
    @Override
    public void handleAttrs(View view, List<Attr> attrList) {
        for (Attr attr : attrList) {
            // 属性名
            String attrName = attr.getAttrName();
            // 属性值，已经去掉了"@"
            int resId = getResIdFromAttr (attr);
            switch (attrName){

                case "accessibilityHeading":
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                        if(attr.getType() == Attr.TYPE_BOOL){
                            boolean newBool = getBoolean(resId);
                            view.setAccessibilityHeading(newBool);
                        }
                    }
                    break;

                case "alpha":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        float newFloat = getFloat(resId);
                        view.setAlpha(newFloat);
                    }
                    break;

                case "background":
                    // 颜色
                    if(attr.getType() == Attr.TYPE_COLOR){
                        int newColor = getColor (resId);
                        view.setBackgroundColor(newColor);
                    }else if(attr.getType() == Attr.TYPE_DRAWABLE || attr.getType() == Attr.TYPE_MIPMAP){
                        // drawable or mipmap
                        Drawable newDrawable = getDrawable(resId);
                        view.setBackground(newDrawable);
                    }
                    break;

                case "clickable":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setClickable(newBool);
                    }
                    break;

                case "contentDescription":
                    if(attr.getType() == Attr.TYPE_STRING){
                        String newString = getString(resId);
                        view.setContentDescription(newString);
                    }
                    break;

                case "duplicateParentState":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setDuplicateParentStateEnabled(newBool);
                    }
                    break;

                case "fadingEdgeLength":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setFadingEdgeLength(newDimen);
                    }
                    break;

                case "filterTouchesWhenObscured":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setFilterTouchesWhenObscured(newBool);
                    }
                    break;

                case "fitsSystemWindows":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setFitsSystemWindows(newBool);
                    }
                    break;

                case "isScrollContainer":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setScrollContainer(newBool);
                    }
                    break;

                case "focusable":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setFocusable(newBool);
                    }
                    break;

                case "focusableInTouchMode":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setFocusableInTouchMode(newBool);
                    }
                    break;

                case "focusedByDefault":
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        if(attr.getType() == Attr.TYPE_BOOL){
                            boolean newBool = getBoolean(resId);
                            view.setFocusedByDefault(newBool);
                        }
                    }
                    break;

                case "hapticFeedbackEnabled":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setHapticFeedbackEnabled(newBool);
                    }
                    break;

                case "keepScreenOn":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setKeepScreenOn(newBool);
                    }
                    break;

                case "keyboardNavigationCluster":
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        if(attr.getType() == Attr.TYPE_BOOL){
                            boolean newBool = getBoolean(resId);
                            view.setKeyboardNavigationCluster(newBool);
                        }
                    }
                    break;

                case "longClickable":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setLongClickable(newBool);
                    }
                    break;

                case "minHeight":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setMinimumHeight(newDimen);
                    }
                    break;

                case "minWidth":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setMinimumWidth(newDimen);
                    }
                    break;

                case "outlineSpotShadowColor":
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                        if(attr.getType() == Attr.TYPE_COLOR){
                            int newColor = getColor (resId);
                            view.setOutlineSpotShadowColor(newColor);
                        }
                    }
                    break;

                case "outlineAmbientShadowColor":
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                        if(attr.getType() == Attr.TYPE_COLOR){
                            int newColor = getColor (resId);
                            view.setOutlineAmbientShadowColor(newColor);
                        }
                    }
                    break;

                case "padding":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setPadding(newDimen, newDimen, newDimen, newDimen);
                    }
                    break;

                case "paddingHorizontal":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setPadding(newDimen, view.getPaddingTop(), newDimen, view.getPaddingBottom());
                    }
                    break;

                case "paddingVertical":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setPadding(view.getPaddingLeft(), newDimen, view.getPaddingRight(), newDimen);
                    }
                    break;

                case "paddingBottom":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), newDimen);
                    }
                    break;

                case "paddingTop":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setPadding(view.getPaddingLeft(), newDimen, view.getPaddingRight(), view.getPaddingBottom());
                    }
                    break;

                case "paddingLeft":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setPadding(newDimen, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                    }
                    break;

                case "paddingRight":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), newDimen, view.getPaddingBottom());
                    }
                    break;

                case "paddingStart":
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                        if(attr.getType() == Attr.TYPE_DIMEN){
                            int newDimen = getDimensionPixelSize(resId);
                            view.setPaddingRelative(newDimen, view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
                        }
                    }
                    break;

                case "paddingEnd":
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
                        if(attr.getType() == Attr.TYPE_DIMEN){
                            int newDimen = getDimensionPixelSize(resId);
                            view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), newDimen, view.getPaddingBottom());
                        }
                    }
                    break;

                case "saveEnabled":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setSaveEnabled(newBool);
                    }
                    break;

                case "rotation":
                    // 整数
                    if(attr.getType() == Attr.TYPE_INTEGER){
                        int newInteger = getInteger(resId);
                        view.setRotation(newInteger);
                    }else if(attr.getType() == Attr.TYPE_DIMEN){
                        // 浮点数
                        float newFloat = getFloat(resId);
                        view.setRotation(newFloat);
                    }
                    break;

                case "rotationX":
                    // 整数
                    if(attr.getType() == Attr.TYPE_INTEGER){
                        int newInteger = getInteger(resId);
                        view.setRotationX(newInteger);
                    }else if(attr.getType() == Attr.TYPE_DIMEN){
                        // 浮点数
                        float newFloat = getFloat(resId);
                        view.setRotationX(newFloat);
                    }
                    break;

                case "rotationY":
                    // 整数
                    if(attr.getType() == Attr.TYPE_INTEGER){
                        int newInteger = getInteger(resId);
                        view.setRotationY(newInteger);
                    }else if(attr.getType() == Attr.TYPE_DIMEN){
                        // 浮点数
                        float newFloat = getFloat(resId);
                        view.setRotationY(newFloat);
                    }
                    break;

                case "scaleX":
                    // 整数
                    if(attr.getType() == Attr.TYPE_INTEGER){
                        int newInteger = getInteger(resId);
                        view.setScaleX(newInteger);
                    }else if(attr.getType() == Attr.TYPE_DIMEN){
                        // 浮点数
                        float newFloat = getFloat(resId);
                        view.setScaleX(newFloat);
                    }
                    break;

                case "scaleY":
                    // 整数
                    if(attr.getType() == Attr.TYPE_INTEGER){
                        int newInteger = getInteger(resId);
                        view.setScaleY(newInteger);
                    }else if(attr.getType() == Attr.TYPE_DIMEN){
                        // 浮点数
                        float newFloat = getFloat(resId);
                        view.setScaleY(newFloat);
                    }
                    break;

                case "scrollX":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setScrollX(newDimen);
                    }
                    break;

                case "scrollY":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setScrollY(newDimen);
                    }
                    break;

                case "scrollbarSize":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setScrollBarSize(newDimen);
                    }
                    break;

                case "scrollbarDefaultDelayBeforeFade":
                    if(attr.getType() == Attr.TYPE_INTEGER){
                        int newInteger = getInteger(resId);
                        view.setScrollBarDefaultDelayBeforeFade(newInteger);
                    }
                    break;

                case "scrollbarFadeDuration":
                    if(attr.getType() == Attr.TYPE_INTEGER){
                        int newInteger = getInteger(resId);
                        view.setScrollBarFadeDuration(newInteger);
                    }
                    break;

                case "stateListAnimator":
                    // 一般都是drawable
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        if(attr.getType() == Attr.TYPE_DRAWABLE){
                            StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(sApplicationContext, getId(resId));
                            view.setStateListAnimator(stateListAnimator);
                        }
                    }
                    break;

                case "soundEffectsEnabled":
                    if(attr.getType() == Attr.TYPE_BOOL){
                        boolean newBool = getBoolean(resId);
                        view.setSoundEffectsEnabled(newBool);
                    }
                    break;

                case "translationX":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setTranslationX(newDimen);
                    }
                    break;

                case "translationY":
                    if(attr.getType() == Attr.TYPE_DIMEN){
                        int newDimen = getDimensionPixelSize(resId);
                        view.setTranslationY(newDimen);
                    }
                    break;
                case "translationZ":
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        if(attr.getType() == Attr.TYPE_DIMEN){
                            int newDimen = getDimensionPixelSize(resId);
                            view.setTranslationZ(newDimen);
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
