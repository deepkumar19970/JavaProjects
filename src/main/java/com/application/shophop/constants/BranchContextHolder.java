package com.application.shophop.constants;

public class BranchContextHolder {

    private static ThreadLocal<Branch> threadLocal = new ThreadLocal<>();

    public static void setBranchContext(Branch branch) {
        threadLocal.set(branch);
    }

    public static Branch getBranchContext() {
        return threadLocal.get();
    }

    public static void clearBranchContext() {
        threadLocal.remove();
    }
}
