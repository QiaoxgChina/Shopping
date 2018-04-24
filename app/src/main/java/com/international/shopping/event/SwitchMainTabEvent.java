package com.international.shopping.event;

/**
 * Created by Administrator on 2018/4/24.
 */

public class SwitchMainTabEvent {

    private int targetFragment;
    private int beforeFragment;

    public SwitchMainTabEvent(int before,int target){
        this.beforeFragment = before;
        this.targetFragment = target;
    }

    public int getTargetFragment() {
        return targetFragment;
    }

    public void setTargetFragment(int targetFragment) {
        this.targetFragment = targetFragment;
    }

    public int getBeforeFragment() {
        return beforeFragment;
    }

    public void setBeforeFragment(int beforeFragment) {
        this.beforeFragment = beforeFragment;
    }
}
